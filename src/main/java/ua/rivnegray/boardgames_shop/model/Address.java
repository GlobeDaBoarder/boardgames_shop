package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"street", "house_number", "postal_code", "city", "country", "user_profile_id"})})
public class Address extends BaseEntity{

    @Column(nullable = false)
    private String street;

    @Column( nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    public String toStringForExcel(){
        return street + " " + houseNumber + ", " + postalCode + ", " + city + ", " + country;
    }
}
