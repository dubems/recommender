package com.nriagudubem.recommenderspring.entity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private UUID id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Feedback> feedbacks;

    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private boolean accountExpired = false;

    @Builder.Default
    private boolean credentialsExpired = false;

    @Builder.Default
    private boolean accountLocked = false;

    @Builder.Default
    private boolean blocked = false;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column
    private Instant createdAt;

    @Column
    private Instant updatedAt;


    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

}
