package com.nilsphotography.image.response;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ImageUploadResponse
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String imageName;
  private String url;
  private String category;
  private String imageSize;
  
}