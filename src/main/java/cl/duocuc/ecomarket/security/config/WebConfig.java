package cl.duocuc.ecomarket.security.config;

import cl.duocuc.ecomarket.security.AuthPermisoJWTHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthPermisoJWTHandler permisoInterceptor;

    public WebConfig(AuthPermisoJWTHandler permisoInterceptor) {
        this.permisoInterceptor = permisoInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permisoInterceptor)
                .addPathPatterns("/api/**"); // rutas donde se aplica
    }
}
