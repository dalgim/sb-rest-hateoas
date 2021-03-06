package com.dalgim.example.sb.rest.hateoas.persistance.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Mateusz Dalgiewicz on 01.05.2017.
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    protected Long id;
    @Column(name = "UUID", nullable = false)
    protected final String uuid;
    @CreatedDate
    @Column(name = "CREATED_DATE_TIME", nullable = false)
    protected LocalDateTime createdDateTime;
    @LastModifiedDate
    @Column(name = "LAST_UPDATE_DATE_TIME", nullable = false)
    protected LocalDateTime updatedDateTime;

    AbstractEntity() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return com.google.common.base.Objects.equal(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(uuid);
    }
}
