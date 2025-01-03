package ar.edu.unq.desapp.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${cryptoexchange.openapi.dev-url}")
    private String devUrl;

    @Value("${cryptoexchange.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("noreply@gmail.com");
        contact.setName("DesApp-GrupoC");
        contact.setUrl("https://github.com/LeaTex/UNQ-DESAPP-2024c2-cryptoexchange-api");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Crypto Exchange API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints for the Crypto Exchange system.")
                .termsOfService("https://github.com/martinBoglione/UNQ-2024S2-DesApp-GrupoC")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
