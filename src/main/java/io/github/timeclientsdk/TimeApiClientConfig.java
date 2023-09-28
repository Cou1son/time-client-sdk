package io.github.timeclientsdk;

import io.github.timeclientsdk.client.TimeApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("time.client")
@Data
@ComponentScan
public class TimeApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public TimeApiClient mitApiClient(){
        return new TimeApiClient(accessKey,secretKey);
    }

}
