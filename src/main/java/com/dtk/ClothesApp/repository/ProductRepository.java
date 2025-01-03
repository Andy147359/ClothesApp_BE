package com.dtk.ClothesApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.dtk.ClothesApp.domain.entity.Product;

@Repository
public interface ProductRepository extends
                JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

        Optional<Product> findByName(String name);

}
