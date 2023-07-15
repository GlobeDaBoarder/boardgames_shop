package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = )
public class UserProfile extends BaseEntity{


    @Column( unique = true, nullable = false)
    private String email;

    private String phone;

    @Column( nullable = false)
    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @Setter(AccessLevel.NONE)
    private Set<UserRole> roles = new HashSet<>();

    // todo figure out how to properly deal with LazyInitializationException;
    //  possibly will need to use @Transactional to specify loading
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Set<Address> addresses = new HashSet<>();

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private UserCredentials userCredentials;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ShoppingCart shoppingCart = new ShoppingCart();

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    private Set<Order> orders = new HashSet<>();

    public UserProfile(String email, String phone, String firstName, String lastName,
                       Set<UserRole> userRoles) {
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = userRoles;
    }
}
