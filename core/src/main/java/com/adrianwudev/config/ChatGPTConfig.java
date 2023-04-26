package com.adrianwudev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.adriandevwu")
@PropertySources({
  @PropertySource("classpath:config/chatGPT.properties"),
  @PropertySource("classpath:config/chatGPT-credentials.properties")
})
public class ChatGPTConfig {
    @Value("${chatGPT.credentials.api.key}")
    private String API_KEY;

    @Bean
    public String API_KEY() {
        return API_KEY;
    }

    @Value("${chatGPT.api.model}")
    private String GPT_MODEL;

    @Bean
    public String getModel() {
        return GPT_MODEL;
    }

    @Value("${chatGPT.api.temperature}")
    private int TEMPERATURE;

    @Bean
    public int getTemperature() {
        return TEMPERATURE;
    }
}
