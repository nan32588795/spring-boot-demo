package co.jp.amazawa.training.demo.repository;

import co.jp.amazawa.training.demo.dao.project.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<ProjectEntity, String>  {
    @Override
    List<ProjectEntity> findAll();

    Page<ProjectEntity> findAll(Pageable pageable);

//    ProjectEntity findAllById(String id);

    List<ProjectEntity> findAllByOrderByCreatedAtAsc();

    Page<ProjectEntity> findAllByOrderByCreatedAtAsc(Pageable pageable);
}
