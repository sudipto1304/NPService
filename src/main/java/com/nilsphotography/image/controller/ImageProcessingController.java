package com.nilsphotography.image.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nilsphotography.image.response.Category;
import com.nilsphotography.image.response.ImageContent;
import com.nilsphotography.image.response.ImageUploadResponse;
import com.nilsphotography.image.service.ImageProcessingService;

@RestController
@RequestMapping({"/data/image"})
public class ImageProcessingController
{
  @Autowired
  private ImageProcessingService service;
  
  @RequestMapping(value={"/category/{categoryId}/{imageName}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"image/jpeg"})
  public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String categoryId, @PathVariable String imageName)
    throws IOException
  {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Access-Control-Allow-Origin", "*");
    headers.setCacheControl(CacheControl.noCache().getHeaderValue());
    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(this.service.getImage(categoryId, imageName), headers, HttpStatus.OK);
    return responseEntity;
  }
  
  @RequestMapping(value={"/upload/category/{categoryId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json"})
  public ResponseEntity<ImageUploadResponse> uploadImage(@PathVariable String categoryId, @RequestParam("file") MultipartFile file)
    throws IOException
  {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Access-Control-Allow-Origin", "*");
    return new ResponseEntity<>(this.service.uploadImage(categoryId, file), headers, HttpStatus.CREATED);
  }
  
  @RequestMapping(value={"/getCategory"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<List<String>> getCategories()
    throws IOException
  {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Access-Control-Allow-Origin", "*");
    return new ResponseEntity<>(this.service.getCategories(), headers, HttpStatus.OK);
  }
  
  @RequestMapping(value={"/getContent"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
  public ResponseEntity<List<Category>> getContent()
    throws IOException
  {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Access-Control-Allow-Origin", "*");
    return new ResponseEntity<>(this.service.getImageContents(), headers, HttpStatus.OK);
  }
}
