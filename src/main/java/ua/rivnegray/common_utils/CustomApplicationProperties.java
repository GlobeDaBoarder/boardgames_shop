package ua.rivnegray.common_utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "custom")
@Getter
@Setter
public class CustomApplicationProperties {
    private String imagesDirectory = "/home/globe/rivnegray/images/";
    private Integer pageSize = 20;
    private String baseImagesUrl = "/boardgames/images/";
    private List<String> supportedImageExtensions = List.of(".jpg", ".jpeg", ".png");
}
