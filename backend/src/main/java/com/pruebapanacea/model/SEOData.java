package com.pruebapanacea.model;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "seodata")
public class SEOData {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(unique = true)
    private String url;
    private String title;
    private String description;
    
    @ElementCollection
    private List<String> keywords;
    @ElementCollection
    private List<String> titles;

    private boolean usesHTML5;
    private int imagesCount;
    private Instant createdAt;

	//Constructores
	public SEOData() {
	}

	public SEOData(String url) {
        this.url = url;
    }
	
	public SEOData(String url, String title, String description, List<String> keywords, List<String> titles, boolean usesHTML5, int imagesCount, Instant createdAt) {
		this.url = url;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.titles = titles;
		this.usesHTML5 = usesHTML5;
		this.imagesCount = imagesCount;
		this.createdAt = createdAt;
	}
	
	
	//Getters y Setters
    
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getKeywords() {
		return keywords;
	}
	
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}
	
	public boolean isUsesHTML5() {
		return usesHTML5;
	}
	
	public void setUsesHTML5(boolean usesHTML5) {
		this.usesHTML5 = usesHTML5;
	}
	
	public int getImagesCount() {
		return imagesCount;
	}
	
	public void setImagesCount(int imagesCount) {
		this.imagesCount = imagesCount;
	}
	
	public Instant getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
    
    

	
}
