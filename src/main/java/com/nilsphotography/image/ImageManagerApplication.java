package com.nilsphotography.image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ImageManagerApplication
  extends SpringBootServletInitializer
{
  public static void main(String[] args)
  {
    SpringApplication.run(ImageManagerApplication.class, args);
  }
  
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
  {
    return application.sources(new Class[] { ImageManagerApplication.class });
  }
}
