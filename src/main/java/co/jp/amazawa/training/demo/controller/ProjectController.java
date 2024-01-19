package co.jp.amazawa.training.demo.controller;

import co.jp.amazawa.training.demo.dto.project.ProjectListResponse;
import co.jp.amazawa.training.demo.dto.project.ProjectCreateRequest;
import co.jp.amazawa.training.demo.dto.project.ProjectCreateResponse;
import co.jp.amazawa.training.demo.dto.project.ProjectOneResponse;
import co.jp.amazawa.training.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public ProjectListResponse list(Pageable pageable) {
        return service.list(pageable);
    }

    @GetMapping("/{id}")
    public ProjectOneResponse one(@PathVariable String id) {
        return service.one(id);
    }

    @PostMapping
    public ProjectCreateResponse create(@RequestBody @Validated ProjectCreateRequest request) {
        return service.create(request);
    }

}
