package ua.rivnegray.boardgames_shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.model.User;
import ua.rivnegray.boardgames_shop.model.UserPermission;
import ua.rivnegray.boardgames_shop.model.UserRole;
import ua.rivnegray.boardgames_shop.repository.UserRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

import java.util.Set;

@SpringBootApplication
@ComponentScan({"ua.rivnegray.boardgames_shop", "generated"})
public class BoardgamesShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardgamesShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, UserRoleRepository roleRepository,
											   PasswordEncoder encoder) {
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


			User admin = new User(
					"admin",
					encoder.encode("admin"),
					"email",
					"phone",
					"url",
					Set.of(roleAdmin)
			);

			userRepository.save(admin);

			User user = new User(
					"user",
					encoder.encode("user"),
					"email",
					"phone",
					"url",
					Set.of(roleUser)
			);

			userRepository.save(user);


//			HashMap

//			roleRepository.save(userRole);
//
//			User user1 = new UserBuilder()
//					.username("admin")
//					.password(encoder.encode("admin"))
//					.email("email")
//					.phone("phone")
//					.imageUrl("url")
//					.permission(UserRole.instanceOf("ROLE_ADMIN"), UserPermission.USER_READ)
//					.permission(UserRole.instanceOf("ROLE_ADMIN"), UserPermission.USER_WRITE)
//					.permission(UserRole.instanceOf("ROLE_ADMIN"), UserPermission.ADMIN_READ)
//					.permission(UserRole.instanceOf("ROLE_ADMIN"), UserPermission.ADMIN_WRITE)
//					.build();
//
//			userRepository.save(user1);
//
//
//			User user2
//					= new UserBuilder()
//					.username("user")
//					.password(encoder.encode("user"))
//					.email("email")
//					.phone("phone")
//					.imageUrl("url")
//					.permission(userRole, UserPermission.USER_READ)
//					.permission(userRole, UserPermission.USER_WRITE)
//					.build();
//
//			userRepository.save(user2);




		};
	}
}
