package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@Setter
@Getter
@ToString
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    protected Long id;

    @CreationTimestamp
    @Column(updatable = false)
    @Setter(AccessLevel.NONE)
    protected LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(updatable = false)
    @Setter(AccessLevel.NONE)
    protected LocalDateTime dateUpdated;

    // should be used as a "soft-delete". When using service only get those with true
    @Column(columnDefinition = "boolean default false", nullable = false)
    protected Boolean isRemoved = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
