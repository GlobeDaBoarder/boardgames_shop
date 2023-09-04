package ua.rivnegray.boardgames_shop.config.custom_configuration_properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "custom.images")
@Getter
@Setter
public class ImageProperties {
    private String storagePath = "/home/globe/rivnegray/images/";
    private String endpointBaseUrl = "/boardgames/images/";
    private List<String> supportedImageFileExtensions = List.of(".jpg", ".jpeg", ".png");
    private String serverBaseUrl = "http://localhost:8080";
}
