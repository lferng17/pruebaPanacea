package com.pruebapanacea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebapanacea.model.SEOData;
import com.pruebapanacea.model.URLRequest;
import com.pruebapanacea.service.SEODataService;

@RestController
@RequestMapping("/api/analisis/")
public class SEODataController {

	@Autowired
    private SEODataService seoDataService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")  
    public ResponseEntity<SEOData> analizarSitio(@RequestBody URLRequest request) {
        SEOData resultado = seoDataService.analizarSEO(request.getUrl());
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")  
    public ResponseEntity<List<SEOData>> obtenerAnalisis(@RequestParam(defaultValue = "15") int limit) {
        List<SEOData> analisis = seoDataService.obtenerAnalisis(limit);
        return new ResponseEntity<>(analisis, HttpStatus.OK);
    }
	
}
