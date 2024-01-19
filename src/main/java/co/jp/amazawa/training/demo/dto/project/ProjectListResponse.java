package co.jp.amazawa.training.demo.dto.project;

import java.util.List;

public record ProjectListResponse(List<Project> projects, int totalPages, long totalElements) {
}
