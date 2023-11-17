package com.pruebapanacea.service;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pruebapanacea.model.SEOData;
import com.pruebapanacea.repository.SEODataRepository;

@Service
public class SEODataServiceImpl implements SEODataService{
	
	@Autowired 
    private SEODataRepository seoDataRepository;


    public SEOData guardarAtributos(SEOData seoData) {
        return seoDataRepository.save(seoData);
    }

    public SEOData obtenerURLPorId(int id) {
        return seoDataRepository.findById(id).orElse(null);
    }

    public SEOData existeURL(String url) {
		return seoDataRepository.findByUrl(url).orElse(null);
	}
    
    @Override
    public List<SEOData> obtenerAnalisis(int limit) {
        return seoDataRepository.findAll(PageRequest.of(0, limit)).getContent();

    }

    public void borrarURLPorId(int id) {
        seoDataRepository.deleteById(id);	
	}
    
    public List<SEOData> findAll(){
		return seoDataRepository.findAll();
	}

}
