package com.pruebapanacea.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/analisis/")
public class SEODataController {

	@Autowired
    private SEODataServiceImpl seoDataService;
    
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> borrarURLPorId(@PathVariable int id) {

        seoDataService.borrarURLPorId(id);
        
        return ResponseEntity.ok(!(seoDataService.obtenerURLPorId(id)!= null));
        
    }
    
    //------------------------- Web Scrapping -------------------------
    
    @PostMapping
    public ResponseEntity<SEOData> analizarURL(@RequestBody SEOData seoDataRequest) {
    	
        String url = seoDataRequest.getUrl();
        //Comprobar si la url ya existe en la base de datos
        SEOData urlExistente = seoDataService.existeURL(url);

        if (urlExistente == null) {
            // Llama al método obtenerInformacionSEO y asigna el resultado a seoDataResponse
            SEOData seoDataResponse = obtenerInformacionSEO(url);

            // Guardar todos los atributos llamando al metodo guardarAtributos() de SEODataServiceImpl
            seoDataService.guardarAtributos(seoDataResponse);

            return new ResponseEntity<>(seoDataResponse, HttpStatus.CREATED);        
        } else {
            return new ResponseEntity<>(urlExistente, HttpStatus.OK);
        }
    }
    
    private SEOData obtenerInformacionSEO(String url) {
        SEOData seoDataResponse = new SEOData();
        seoDataResponse.setUrl(url);
        seoDataResponse.setCreatedAt(java.time.Instant.now());
        
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
            contarTitulos(document, seoDataResponse);

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
        //Contar cuántos elementos de tipo título, es decir, <h1>, <h2>... hay
        //Añadirlos a List<String> titles, separados por comas (h1,nºh1,h2,nºh2,...)

        // Contadores para cada tipo de título
        Map<String, Long> titleCounts = document.select("h1, h2, h3, h4, h5, h6")
                .stream()
                .collect(Collectors.groupingBy(Element::tagName, Collectors.counting()));

        // Crear la lista de títulos (tagName, count)
        List<String> titlesList = new ArrayList<>();
        titleCounts.forEach((tagName, count) -> titlesList.add(tagName + "," + count));

        // Asignar la lista de títulos al objeto SEOData
        seoDataResponse.setTitles(titlesList);
        

    }

    private void verificarHTML5(Document document, SEOData seoDataResponse) {
        //Es html5 si contiene las etiquetas <header> y <footer>
        Elements header = document.select("header");
        Elements footer = document.select("footer");
        seoDataResponse.setUsesHTML5(header.size() > 0 && footer.size() > 0);
    }

    private int contarImagenes(Document document) {
        // Contar cuántas imágenes contiene la web (tag <img>)
        Elements images = document.select("img");
        return images.size();
    }
    
	
}
