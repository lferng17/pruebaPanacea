package com.pruebapanacea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebapanacea.model.SEOData;
import com.pruebapanacea.model.URLRequest;
import com.pruebapanacea.service.SEODataService;
import com.pruebapanacea.service.SEODataServiceImpl;

@RestController
@RequestMapping("/api/analisis/")
public class SEODataController {

	@Autowired
    private SEODataServiceImpl seoDataService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")  
    public ResponseEntity<SEOData> analizarSitio(@RequestBody URLRequest request) {
        //SEOData resultado = seoDataService.analizarSEO(request.getUrl());
    	String url = request.getUrl();
        SEOData resultado = seoDataService.guardarURL(url);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")  
    public ResponseEntity<List<SEOData>> obtenerAnalisis(@RequestParam(defaultValue = "15") int limit) {
        List<SEOData> analisis = seoDataService.obtenerAnalisis(limit);
        return new ResponseEntity<>(analisis, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SEOData> obtenerURLPorId(@PathVariable int id) {

        SEOData seoData = seoDataService.obtenerURLPorId(id);
        
        if (seoData != null) {
            return new ResponseEntity<>(seoData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
	
}
