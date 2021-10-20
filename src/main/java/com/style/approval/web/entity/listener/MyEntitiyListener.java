package com.style.approval.web.entity.listener;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@EnableJpaAuditing
public class MyEntitiyListener {
    @PrePersist
    public void prePersist(Object o) {
        if(o instanceof Auditable){
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o){
        if(o instanceof Auditable){
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}
