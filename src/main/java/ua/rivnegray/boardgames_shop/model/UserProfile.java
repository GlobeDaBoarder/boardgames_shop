package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @Column(name = "user_credentials_id", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column( unique = true, nullable = false)
    private String email;

    private String phone;

    @Column( nullable = false)
    private String firstName;

    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Set<Address> addresses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_credentials_id")
    @MapsId
    private UserCredentials userCredentials;

    @OneToOne
    private ShoppingCart shoppingCart;

    @OneToMany
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Set<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public UserProfile(String email, String phone, String firstName, String lastName) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
