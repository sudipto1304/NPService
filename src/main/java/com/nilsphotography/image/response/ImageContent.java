package com.nilsphotography.image.response;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ImageContent implements Serializable{
	
	private String url;
	private int height;
	private int width;
	private String size;
	

}
