package com.nilsphotography.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nilsphotography.image.entity.CategoryEntity;

@Repository
public interface ImageCategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}