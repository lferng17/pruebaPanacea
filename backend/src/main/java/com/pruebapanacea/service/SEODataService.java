package com.pruebapanacea.service;


import java.util.List;

import com.pruebapanacea.model.SEOData;

public interface SEODataService {
	
	

	SEOData analizarSEO(String url);
	List<SEOData> obtenerAnalisis(int limit);

	
	
}
