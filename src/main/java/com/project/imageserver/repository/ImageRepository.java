package com.project.imageserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.imageserver.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
