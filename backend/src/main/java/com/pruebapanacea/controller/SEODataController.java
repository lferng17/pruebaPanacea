package com.pruebapanacea.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.pruebapanacea.service.SEODataService;
import com.pruebapanacea.service.SEODataServiceImpl;

@RestController
@RequestMapping("/api/analisis/")
public class SEODataController {

	@Autowired
    private SEODataServiceImpl seoDataService;
    
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")  
    public ResponseEntity<List<SEOData>> obtenerAnalisis(@RequestParam(defaultValue = "50") int limit) {
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
    
    //------------------------- Web Scrapping -------------------------
    
    @PostMapping
    public ResponseEntity<SEOData> analizarURL(@RequestBody SEOData seoDataRequest) {
        String url = seoDataRequest.getUrl();
        SEOData resultado = seoDataService.guardarURL(url);

        // Llama al método obtenerInformacionSEO y asigna el resultado a seoDataResponse
        SEOData seoDataResponse = obtenerInformacionSEO(url);

        // Asigna los valores obtenidos a la instancia resultado
        resultado.setTitle(seoDataResponse.getTitle());
        //Guardar titulo llamando al metodo guardarTitulo() de SEODataServiceImpl
        seoDataService.guardarTitulo(seoDataResponse.getTitle());
        
        resultado.setDescription(seoDataResponse.getDescription());
        resultado.setKeywords(seoDataResponse.getKeywords());
        resultado.setUsesHTML5(seoDataResponse.isUsesHTML5());
        resultado.setImagesCount(seoDataResponse.getImagesCount());

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
    
    private SEOData obtenerInformacionSEO(String url) {
        SEOData seoDataResponse = new SEOData();
        seoDataResponse.setUrl(url);

        try {
            // Realizar scraping con JSoup
            Document document = Jsoup.connect(url).get();

            // Extraer el título de la web del tag <title>
            String title = document.title();
            seoDataResponse.setTitle(title);

            // Extraer la descripción de la web del tag <meta name="description">
            String description = obtenerMetaContent(document, "description");
            seoDataResponse.setDescription(description);

            // Extraer la lista de palabras clave del tag <meta name="keywords">
            String keywords = obtenerMetaContent(document, "keywords");
            seoDataResponse.setKeywords(Arrays.asList(keywords.split("\\s*,\\s*")));

            // Contar cuántos elementos de tipo título hay (h1, h2, ...)
            //contarTitulos(document, seoDataResponse);

            // Detectar si hace uso de etiquetas de HTML5 como <header> y <footer>
            verificarHTML5(document, seoDataResponse);

            // Contar cuántas imágenes contiene la web
            int imagesCount = contarImagenes(document);
            seoDataResponse.setImagesCount(imagesCount);

        } catch (IOException e) {
            System.out.println("Error en la petición HTTP");
        }

        return seoDataResponse;
    }
    
    private String obtenerMetaContent(Document document, String metaType) {
        Element metaTag = document.select("meta[name=" + metaType + "]").first();
        return (metaTag != null) ? metaTag.attr("content") : "";
    }

    private void contarTitulos(Document document, SEOData seoDataResponse) {
        
    }

    private void verificarHTML5(Document document, SEOData seoDataResponse) {
        //Es html5 si contiene la etiqueta <!DOCTYPE html> al inicio del documento
        boolean isHTML5 = document.toString().contains("<!DOCTYPE html>");
        seoDataResponse.setUsesHTML5(isHTML5);
    }

    private int contarImagenes(Document document) {
        Elements images = document.select("img");
        return images.size();
    }
    
	
}
