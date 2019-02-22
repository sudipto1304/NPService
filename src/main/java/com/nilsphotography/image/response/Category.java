package com.nilsphotography.image.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Category implements Serializable{
	private String category;
	private List<ImageContent> images;
}
