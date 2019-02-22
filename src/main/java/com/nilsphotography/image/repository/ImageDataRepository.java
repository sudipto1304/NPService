package com.nilsphotography.image.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nilsphotography.image.entity.ImageInfoEntity;

@Repository
public interface ImageDataRepository
  extends JpaRepository<ImageInfoEntity, String>
{
  public ImageInfoEntity findByImageIdAndCategory(String paramString1, String paramString2);
  public List<ImageInfoEntity> findAll();
}
