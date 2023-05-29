package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    LocalDateTime dateCreated;

    LocalDateTime dateUpdated;

    // todo understand column definition
    // should be used as a "soft-delete". When using service only get those with true
    @Column(columnDefinition = "boolean default false")
    boolean isRemoved;
}
