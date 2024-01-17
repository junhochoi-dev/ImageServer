package com.project.imageserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.imageserver.domain.Image;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
}
