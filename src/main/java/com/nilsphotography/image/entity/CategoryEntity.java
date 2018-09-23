package com.nilsphotography.image.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="image_category")
@Data
public class CategoryEntity
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="ID")
  private Integer id;
  @Column(name="CATEGORY")
  private String category;
  
  
}
