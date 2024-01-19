package co.jp.amazawa.training.demo.dto.project;

import java.time.LocalDateTime;

public record ProjectDetail(
        String id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
