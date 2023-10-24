package ua.rivnegray.boardgames_shop.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class User extends BaseEntity{
    @Column(unique = true, nullable = false, length = 64)
    private String email;

    private String password;

    @Column(length = 15)
    private String phone;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String nickname;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRegistrationStatus registrationStatus = UserRegistrationStatus.NOT_REGISTERED;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @Setter(AccessLevel.NONE)
    @Singular
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    @Singular
    private Set<Address> addresses = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private ShoppingCart shoppingCart = new ShoppingCart();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    @Singular
    private Set<Order> orders = new HashSet<>();
}
