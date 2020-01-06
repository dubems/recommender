package com.nriagudubem.recommenderspring.entity.repository;

import com.nriagudubem.recommenderspring.entity.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, UUID> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
