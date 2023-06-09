package ua.rivnegray.boardgames_shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateAnyUserDto;
import ua.rivnegray.boardgames_shop.DTO.request.create.CreateCustomerUserDto;
import ua.rivnegray.boardgames_shop.DTO.response.UserPublicDto;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.Product;
import ua.rivnegray.boardgames_shop.model.ShoppingCart;
import ua.rivnegray.boardgames_shop.model.UserCredentials;
import ua.rivnegray.boardgames_shop.model.UserPermission;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.model.UserRole;
import ua.rivnegray.boardgames_shop.repository.UserCredentialsRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;
import ua.rivnegray.boardgames_shop.service.UserService;
import ua.rivnegray.boardgames_shop.service.UserServiceImpl;

import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
@ComponentScan({"ua.rivnegray.boardgames_shop", "generated"})
public class BoardgamesShopApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BoardgamesShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner( UserProfileRepository userProfileRepository, Environment environment,
											   UserRoleRepository roleRepository, PasswordEncoder encoder) {
		return args -> {

			System.out.println("Active profile: " + Arrays.toString(environment.getActiveProfiles()));

			UserRole roleAdmin = new UserRole("ROLE_ADMIN");
			Set<UserPermission> adminPermissions = Set.of(UserPermission.USER_READ, UserPermission.USER_WRITE,
					UserPermission.ADMIN_READ, UserPermission.ADMIN_WRITE);
			roleAdmin.setPermissions(adminPermissions);
			roleRepository.save(roleAdmin);

			UserRole roleUser = new UserRole("ROLE_CUSTOMER");
			Set<UserPermission> userPermissions = Set.of(UserPermission.USER_READ, UserPermission.USER_WRITE);
			roleUser.setPermissions(userPermissions);
			roleRepository.save(roleUser);

//			UserCredentials userCredentials = new UserCredentials("admin", encoder.encode("admin"), Set.of(roleAdmin));
//
//			UserProfile userProfile = new UserProfile("@", "1", "Gleb", "Ivashyn");
//			userProfile.setUserCredentials(userCredentials);
//
//			userProfileRepository.save(userProfile);

			UserProfile userProfile = new UserProfile("@", "1", "Gleb", "Ivashyn",
					Set.of(roleAdmin, roleUser));
			UserCredentials userCredentials = new UserCredentials("admin", encoder.encode("admin"));
			userCredentials.setUserProfile(userProfile);
			userProfile.setUserCredentials(userCredentials);

//			userCredentialsRepository.save(userCredentials);
			userProfileRepository.save(userProfile);

//
//			Product game1 = new BoardGame(
//
//			)
//
//
//			ShoppingCart shoppingCart = new ShoppingCart();
//			shoppingCart.setUserProfile(userProfile);
//

		};
	}
}
