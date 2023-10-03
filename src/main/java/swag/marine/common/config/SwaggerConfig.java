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
                .group("auth")
                .pathsToMatch("/marine/auth/**")
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