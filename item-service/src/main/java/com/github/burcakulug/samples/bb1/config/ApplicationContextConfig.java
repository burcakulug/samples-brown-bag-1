package com.github.burcakulug.samples.bb1.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
