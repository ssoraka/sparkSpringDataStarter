package com.example.unsafe_sparkdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spark")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SparkPropsHolder {
    private String appName;
    private String packagesToScan;
}
