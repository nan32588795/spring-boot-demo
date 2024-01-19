package co.jp.amazawa.training.demo.dao.project;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProjectEntityListener {

    @PrePersist
    public void prePersist(ProjectEntity projectEntity) {
        // 新規登録時に呼ばれるメソッド
        String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        projectEntity.setId(id);
        projectEntity.setCreatedAt(now);
        projectEntity.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(ProjectEntity projectEntity) {
        // 更新時に呼ばれるメソッド
        LocalDateTime now = LocalDateTime.now();
        projectEntity.setUpdatedAt(now);
    }
}
