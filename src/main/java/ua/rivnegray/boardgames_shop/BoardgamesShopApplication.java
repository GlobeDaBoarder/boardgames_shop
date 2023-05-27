package ua.rivnegray.boardgames_shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.rivnegray.boardgames_shop.model.UserCredentials;
import ua.rivnegray.boardgames_shop.model.UserPermission;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.model.UserRole;
import ua.rivnegray.boardgames_shop.repository.UserCredentialsRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

import java.util.Set;

@SpringBootApplication
//@ComponentScan({"ua.rivnegray.boardgames_shop", "generated"})
public class BoardgamesShopApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(BoardgamesShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserCredentialsRepository userCredentialsRepository, UserProfileRepository userProfileRepository,
											   UserRoleRepository roleRepository, PasswordEncoder encoder) {
		return args -> {

			UserRole roleAdmin = new UserRole("ROLE_ADMIN");
			Set<UserPermission> adminPermissions = Set.of(UserPermission.USER_READ, UserPermission.USER_WRITE,
					UserPermission.ADMIN_READ, UserPermission.ADMIN_WRITE);
			roleAdmin.setPermissions(adminPermissions);
			roleRepository.save(roleAdmin);

			UserRole roleUser = new UserRole("ROLE_USER");
			Set<UserPermission> userPermissions = Set.of(UserPermission.USER_READ, UserPermission.USER_WRITE);
			roleUser.setPermissions(userPermissions);
			roleRepository.save(roleUser);

			UserCredentials userCredentials = new UserCredentials("admin", encoder.encode("admin"), Set.of(roleAdmin));

			UserProfile userProfile = new UserProfile("@", "1", "Gleb", "Ivashyn");
			userProfile.setUserCredentials(userCredentials);

			userProfileRepository.save(userProfile);
		};
	}
}
