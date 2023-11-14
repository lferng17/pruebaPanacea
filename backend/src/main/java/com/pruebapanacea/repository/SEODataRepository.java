package com.pruebapanacea.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pruebapanacea.model.SEOData;

public interface SEODataRepository extends JpaRepository<SEOData, Integer> {
    Optional<SEOData> findByUrl(String url);
}
