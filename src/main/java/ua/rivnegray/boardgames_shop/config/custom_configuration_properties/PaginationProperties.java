package ua.rivnegray.boardgames_shop.config.custom_configuration_properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom.pagination")
@Getter
@Setter
public class PaginationProperties {
    private Integer pageSize = 20;
}
