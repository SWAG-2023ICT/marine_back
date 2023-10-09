package swag.marine.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userAPI(){
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/marine/users/**")
                .build();
    }
    @Bean
    public GroupedOpenApi storeAPI(){
        return GroupedOpenApi.builder()
                .group("store")
                .pathsToMatch("/marine/store/**")
                .build();
    }
    @Bean
    public GroupedOpenApi destinationAPI(){
        return GroupedOpenApi.builder()
                .group("destination")
                .pathsToMatch("/marine/destination/**")
                .build();
    }
    @Bean
    public GroupedOpenApi productAPI(){
        return GroupedOpenApi.builder()
                .group("product")
                .pathsToMatch("/marine/product/**")
                .build();
    }
    @Bean
    public OpenAPI marineAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("project marine API 명세서")
                        .description("TEST 및 API 명세서로 사용할 예정"));
    }
}