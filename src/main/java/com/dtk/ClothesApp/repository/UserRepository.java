package com.dtk.ClothesApp.repository;

import com.dtk.ClothesApp.domain.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
                JpaRepository<User, String>, JpaSpecificationExecutor<User> {
        boolean existsByEmail(String email);

        Optional<User> findByEmail(String email);

        boolean existsByEmailAndIsDeletedFalse(String email);
}
