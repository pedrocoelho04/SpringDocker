package com.lancer.compcon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuração para resolver o problema de CORS (Cross-Origin Resource Sharing).
 * Este problema ocorre quando o frontend (neste caso, o Swagger UI) tenta fazer uma requisição
 * para o backend (sua API Spring Boot) a partir de uma origem (domínio, porta ou protocolo) diferente.
 * Por segurança, os navegadores bloqueiam essas requisições, a menos que o servidor explicitamente as permita.
 * Esta configuração permite que sua API aceite requisições de qualquer origem.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Aplica a configuração a todos os endpoints sob /api/
                .allowedOrigins("*")   // Permite requisições de qualquer origem. Para produção, seria mais restritivo.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos HTTP especificados.
                .allowedHeaders("*");  // Permite todos os cabeçalhos na requisição.
    }
}
