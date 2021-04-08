package eu.mrndesign.matned.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Value("${client.port}")
    private Integer clientPort;

    @Value("${product.port}")
    private Integer productPort;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public Integer clientPort() { return this.clientPort; }

    @Bean
    public Integer productPort() { return this.productPort; }

}
