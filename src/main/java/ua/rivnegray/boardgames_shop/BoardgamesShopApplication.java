package ua.rivnegray.boardgames_shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.rivnegray.boardgames_shop.model.user.User;
import ua.rivnegray.boardgames_shop.model.user.UserBuilder;
import ua.rivnegray.boardgames_shop.model.user.UserPermission;
import ua.rivnegray.boardgames_shop.model.user.UserRole;
import ua.rivnegray.boardgames_shop.repository.UserRepository;

@SpringBootApplication
@ComponentScan({"ua.rivnegray.boardgames_shop", "generated"})
public class BoardgamesShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardgamesShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {

//			UserRole userRole = new UserRole("ROLE_USER");
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
