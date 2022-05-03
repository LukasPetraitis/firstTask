//package com.visma.warehouseApp;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//import static com.fasterxml.jackson.databind.PropertyNamingStrategy.UPPER_CAMEL_CASE;

//@Configuration
//public class JsonConfig {
//
//    @Bean
//    @Primary
//    @SuppressWarnings("deprecation")
//    public ObjectMapper customJson(){
//        return new Jackson2ObjectMapperBuilder()
//                .propertyNamingStrategy(UPPER_CAMEL_CASE)
//                .build();
//    }
//}
