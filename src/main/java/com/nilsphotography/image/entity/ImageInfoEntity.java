package com.nilsphotography.image.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "image_data")
@Data
public class ImageInfoEntity {

	@Id
	@Column(name = "IMAGE_ID")
	private String imageId;
	@Column(name = "IMAGE_NAME")
	private String imageName;
	@Column(name = "CATEGORY")
	private String category;
	@Column(name = "IMAGE_PATH")
	private String imagePath;
	@Column(name = "ACTIVE")
	private char active;
	@Column(name = "HEIGHT")
	private int height;
	@Column(name = "WIDTH")
	private int width;
	@Column(name = "LINK")
	private String link;
	@Column(name = "SIZE")
	private String imageSize;
	@Column(name = "CLIENT_URL")
	private String url;

}
