package com.pruebapanacea.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebapanacea.model.SEOData;
import com.pruebapanacea.service.SEODataServiceImpl;

@RestController
@RequestMapping ("/api/")
public class SEODataRest {

	@Autowired
	private SEODataServiceImpl seoDataService;
	
	@GetMapping
	private ResponseEntity<List<SEOData>> getAllURL(){
		return ResponseEntity.ok(seoDataService.findAll());
	}
	
	
}
