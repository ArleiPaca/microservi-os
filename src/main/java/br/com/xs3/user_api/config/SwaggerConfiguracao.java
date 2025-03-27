package br.com.xs3.user_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

/**
 * Classe responsável por toda a configuração referente ao swagger
 */
@Configuration
public class SwaggerConfiguracao {

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI().info(criarInfo());
    }

    private Info criarInfo() {
        return new Info()
                .title("Cadastro Usuário")
                .description("API responsável pelo CRUD de Usuários.")
                .version("1");
    }

}
