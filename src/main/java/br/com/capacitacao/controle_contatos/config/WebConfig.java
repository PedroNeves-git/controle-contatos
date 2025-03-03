package br.com.capacitacao.controle_contatos.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Mapeia todas as rotas que começam com "/api"
                .allowedOrigins("http://localhost:4200") // Permite o acesso da URL do frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Métodos permitidos
    }
}
