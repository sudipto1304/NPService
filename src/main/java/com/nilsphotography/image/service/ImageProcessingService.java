package com.nilsphotography.image.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nilsphotography.image.entity.CategoryEntity;
import com.nilsphotography.image.entity.ImageInfoEntity;
import com.nilsphotography.image.repository.ImageCategoryRepository;
import com.nilsphotography.image.repository.ImageDataRepository;
import com.nilsphotography.image.response.Category;
import com.nilsphotography.image.response.ImageContent;
import com.nilsphotography.image.response.ImageUploadResponse;



@Service
public class ImageProcessingService
{
    @Value("${image.store.path.root}")
    private String path;
    @Value("${service.self.host}")
    private String host;
    @Value("${server.port}")
    private String port;
    @Autowired
    private ImageDataRepository imageDataRepository;
    @Autowired
    private ImageCategoryRepository imageCategoryRepository;
    
    public ImageUploadResponse uploadImage(final String categoryId, final MultipartFile file) throws IOException {
        new File(this.path + categoryId).mkdir();
        final ModelMapper modelMapper = new ModelMapper();
        ImageUploadResponse response = null;
        if (!file.isEmpty()) {
            final double imageSize = file.getSize() / 1000000.0;
            final String fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
            final InputStream is = file.getInputStream();
            Files.copy(is, Paths.get(this.path + categoryId + "/" + fileName, new String[0]), StandardCopyOption.REPLACE_EXISTING);
            final BufferedImage image = ImageIO.read(file.getInputStream());
            ImageInfoEntity imageEntity = new ImageInfoEntity();
            imageEntity.setImageId(fileName.replace(".jpg", ""));
            imageEntity.setCategory(categoryId);
            imageEntity.setActive('Y');
            imageEntity.setHeight(image.getHeight());
            imageEntity.setWidth(image.getWidth());
            imageEntity.setImageName(fileName);
            imageEntity.setImagePath(this.path + categoryId + "/" + fileName);
            imageEntity.setImageSize(String.format("%.2f", imageSize) + " MB");
            imageEntity.setUrl("http://" + this.host + ":" + this.port + "/GlobalImage/data/image/category/" + categoryId + "/" + fileName);
            this.imageDataRepository.save(imageEntity);
            response = modelMapper.map(imageEntity, ImageUploadResponse.class);
        }
        return response;
    }
    
    public List<String> getCategories() {
        final List<CategoryEntity> entity = (List<CategoryEntity>)this.imageCategoryRepository.findAll();
        final List<String> response = new ArrayList<String>();
        entity.forEach(e -> response.add(e.getCategory()));
        return response;
    }
    
    public byte[] getImage(final String category, final String imageName) throws IOException {
        final String imageId = imageName.replace(".jpg", "");
        final ImageInfoEntity entity = this.imageDataRepository.findByImageIdAndCategory(imageId, category);
        final InputStream in = new FileInputStream(new File(entity.getImagePath()));
        final byte[] media = IOUtils.toByteArray(in);
        return media;
    }
    
    public List<Category> getImageContents(){
    	List<ImageInfoEntity> images = imageDataRepository.findAll();
    	Map<String, Category> response = new HashMap<String, Category>();
    	images.forEach(e->{
    		ImageContent image = new ImageContent();
    		image.setHeight(e.getHeight());
    		image.setWidth(e.getWidth());
    		image.setSize(e.getImageSize());
    		image.setUrl(e.getUrl());
    		if(response.containsKey(e.getCategory())) {
    			response.get(e.getCategory()).getImages().add(image);
    		}else {
    			List<ImageContent> contents  = new ArrayList<ImageContent>();
    			contents.add(image);
    			Category cat = new Category();
    			cat.setCategory(e.getCategory());
    			cat.setImages(contents);
    			response.put(e.getCategory(), cat);
    		}
    	});
    	
    	return response.entrySet().stream().map(e->e.getValue()).collect(Collectors.toList());
    }
}
