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

    @Override
    public SEOData analizarSEO(String url) {

        Optional<SEOData> existingUrl = seoDataRepository.findByUrl(url);

        if (existingUrl.isPresent()) {
            return existingUrl.get();
        } else{
            // Lógica de descarga, análisis y guardado en la base de datos
            SEOData seoData = new SEOData();
            // análisis SEO y guarda la información en el objeto seoData
            seoData.setCreatedAt(Instant.now());
            return seoDataRepository.save(seoData);
        }
    }
    
    public SEOData guardarURL(String url) {
        SEOData seoData = new SEOData();
        seoData.setUrl(url);
        return seoDataRepository.save(seoData);
    }

    public SEOData guardarTitulo(String titulo) {
        SEOData seoData = new SEOData();
        seoData.setTitle(titulo);
        return seoDataRepository.save(seoData);
    }

    public SEOData obtenerURLPorId(int id) {
        return seoDataRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<SEOData> obtenerAnalisis(int limit) {
        return seoDataRepository.findAll(PageRequest.of(0, limit)).getContent();
    }
    
    public List<SEOData> findAll(){
		return seoDataRepository.findAll();
	}

	
	
}
