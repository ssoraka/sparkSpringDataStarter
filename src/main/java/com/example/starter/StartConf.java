package com.example.starter;

import com.example.unsafe_sparkdata.FirstLevelCacheService;
import com.example.unsafe_sparkdata.LazyCollectionAspectHandler;
import com.example.unsafe_sparkdata.LazySparkList;
import com.example.unsafe_sparkdata.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartConf {

    @Bean
    @Source("prototype")
    public LazySparkList lazySparkList() {
        return new LazySparkList();
    }

    @Bean
    public FirstLevelCacheService firstLevelCacheService() {
        return new FirstLevelCacheService();
    }

    @Bean
    public LazyCollectionAspectHandler lazyCollectionAspectHandler() {
        return new LazyCollectionAspectHandler();
    }
}
