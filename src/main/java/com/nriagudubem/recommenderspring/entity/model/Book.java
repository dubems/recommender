package com.nriagudubem.recommenderspring.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private Long ASIN;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String genre;

    @OneToMany(mappedBy = "book")
    private List<Feedback> feedback;

    @Column
    private Instant createdAt;

    @Column
    private Instant updatedAt;

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

}
