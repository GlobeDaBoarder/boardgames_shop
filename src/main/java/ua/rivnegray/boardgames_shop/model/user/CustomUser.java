package ua.rivnegray.boardgames_shop.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import org.springframework.boot.json.GsonJsonParser;

import java.util.Map;


// some other type of @Entity
@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    // Json of parameters
    private String parameters;

    @Transient // maybe??
    private Map<String, String> parametersMap;

    public String getParameter(String key) {
        return this.parametersMap.get(key);
    }

    public void setParameter(String key, String parameter) {
        this.parametersMap.put(key, parameter);

        //check for alternatives to GsonJsonParser
        this.parameters = new GsonJsonParser().parseMap(this.parametersMap.toString()).toString();
    }

    public void removeParameter(String key) {
        // remove parameter
        // map from string
        //this.parameters
    }
}
