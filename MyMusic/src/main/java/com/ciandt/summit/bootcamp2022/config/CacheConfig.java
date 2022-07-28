package com.ciandt.summit.bootcamp2022.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public <K, V> IGenericCache<K, V> getCache(@Value("${app.cache-timeout}") Long cacheTimeout) {
        return new GenericCache<K, V>(cacheTimeout);
    }
}
