package co.jp.amazawa.training.demo.service;

import co.jp.amazawa.training.demo.dao.project.ProjectEntity;
import co.jp.amazawa.training.demo.dto.project.Project;
import co.jp.amazawa.training.demo.dto.project.ProjectCreateRequest;
import co.jp.amazawa.training.demo.dto.project.ProjectCreateResponse;
import co.jp.amazawa.training.demo.dto.project.ProjectDetail;
import co.jp.amazawa.training.demo.dto.project.ProjectListResponse;
import co.jp.amazawa.training.demo.dto.project.ProjectOneResponse;
import co.jp.amazawa.training.demo.repository.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectRepository repository;

    public ProjectListResponse list(Pageable pageable) {
        Page<ProjectEntity> projectEntityPage = repository.findAll(pageable);
        List<ProjectEntity> projectEntities = projectEntityPage.getContent();
        List<Project> projects = projectEntities.stream()
                .map(projectEntity -> new Project(projectEntity.getId(), projectEntity.getName()))
                .toList();
        return new ProjectListResponse(projects, projectEntityPage.getTotalPages(), projectEntityPage.getTotalElements());
    }

    public ProjectOneResponse one(String id) {
        ProjectEntity projectEntity = repository.findById(id).orElseThrow();
        ProjectDetail projectDetail = objectMapper.convertValue(projectEntity, ProjectDetail.class);
        return new ProjectOneResponse(projectDetail);
    }

    public ProjectCreateResponse create(ProjectCreateRequest request) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(request.name());
        projectEntity = repository.save(projectEntity);
        return new ProjectCreateResponse(projectEntity.getId(), projectEntity.getName());
    }

}
