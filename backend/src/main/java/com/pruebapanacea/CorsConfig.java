package com.pruebapanacea;

//CorsConfig.java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

 @Bean
 public CorsFilter corsFilter() {
     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     CorsConfiguration config = new CorsConfiguration();

     // Permitir solicitudes desde el origen específico de Angular
     config.addAllowedOrigin("http://localhost:4200"); 

     // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
     config.addAllowedMethod("*");

     // Permitir todas las cabeceras
     config.addAllowedHeader("*");

     source.registerCorsConfiguration("/**", config);
     return new CorsFilter(source);
 }
}

