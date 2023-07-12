package ua.rivnegray.boardgames_shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.rivnegray.boardgames_shop.model.Address;
import ua.rivnegray.boardgames_shop.model.BoardGame;
import ua.rivnegray.boardgames_shop.model.BoardGameGenre;
import ua.rivnegray.boardgames_shop.model.BoardGameLanguage;
import ua.rivnegray.boardgames_shop.model.BoardGameMechanic;
import ua.rivnegray.boardgames_shop.model.ProductCategory;
import ua.rivnegray.boardgames_shop.model.ProductInShoppingCart;
import ua.rivnegray.boardgames_shop.model.ShoppingCart;
import ua.rivnegray.boardgames_shop.model.UserCredentials;
import ua.rivnegray.boardgames_shop.model.UserPermission;
import ua.rivnegray.boardgames_shop.model.UserProfile;
import ua.rivnegray.boardgames_shop.model.UserRole;
import ua.rivnegray.boardgames_shop.repository.BoardGameGenreRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameMechanicRepository;
import ua.rivnegray.boardgames_shop.repository.BoardGameRepository;
import ua.rivnegray.boardgames_shop.repository.ShoppingCartRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

import java.math.BigDecimal;
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
											   UserRoleRepository roleRepository, PasswordEncoder encoder,
												BoardGameMechanicRepository boardGameMechanicRepository,
												BoardGameGenreRepository boardGameGenreRepository,
												BoardGameRepository boardGameRepository,
												ShoppingCartRepository shoppingCartRepository) {
		return args -> {

			// SAMPLE DATA

			//---------USERS----------------

			System.out.println("Active profile: " + Arrays.toString(environment.getActiveProfiles()));

			UserRole roleAdmin = new UserRole("ROLE_ADMIN");
			Set<UserPermission> adminPermissions = Set.of(UserPermission.USER_READ, UserPermission.USER_WRITE,
					UserPermission.ADMIN_READ, UserPermission.ADMIN_WRITE);
			roleAdmin.setPermissions(adminPermissions);
			roleRepository.save(roleAdmin);

			UserRole roleCustomer = new UserRole("ROLE_CUSTOMER");
			Set<UserPermission> userPermissions = Set.of(UserPermission.USER_READ, UserPermission.USER_WRITE);
			roleCustomer.setPermissions(userPermissions);
			roleRepository.save(roleCustomer);


			UserProfile adminProfile = new UserProfile("@", "1", "Gleb", "Ivashyn",
					Set.of(roleAdmin));
			UserCredentials adminCredentials = new UserCredentials("admin", encoder.encode("admin"));
			adminCredentials.setUserProfile(adminProfile);
			adminProfile.setUserCredentials(adminCredentials);

			adminProfile.getAddresses().add(new Address("Yermaka", "1", "33000","Rivne", "Ukraine"));

			userProfileRepository.save(adminProfile);

			UserProfile customerProfile = new UserProfile("@2", "2", "Gleb", "Ivashyn",
					Set.of(roleCustomer));
			UserCredentials customerCredentials = new UserCredentials("customer", encoder.encode("customer"));
			customerCredentials.setUserProfile(customerProfile);
			customerProfile.setUserCredentials(customerCredentials);

			userProfileRepository.save(customerProfile);

			//------------------BOARDGAMES---------------------------------

			boardGameMechanicRepository.save(new BoardGameMechanic("Deck Building",
					" Players start with a small deck of cards and can acquire more cards to improve their deck over the course of the game. Examples include \"Dominion\" and \"Star Realms\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Worker Placement",
					"Players place tokens (\"workers\") on specific locations to perform actions or gather resources, such as in \"Agricola\" and \"Lords of Waterdeep\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Tile Placement",
					"Players place tiles to create a game board during play, such as in \"Carcassonne\" and \"Alhambra\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Dice Rolling",
					"Players roll dice to determine the outcome of the game, such as in \"Yahtzee\" and \"King of Tokyo\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Cooperative Play",
					"Players work together to achieve a common goal, such as in \"Pandemic\" and \"Forbidden Island\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Role Playing",
					"Players assume the roles of fictional characters and collaborate to tell a story, such as in \"Dungeons & Dragons\" and \"Fiasco\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Auction/Bidding",
					"Players compete to acquire items or resources by placing bids, such as in \"Ra\" and \"Power Grid\"."));


			boardGameGenreRepository.save(new BoardGameGenre("Abstract Strategy",
					"Games that rely on strategic thinking and reasoning rather than on luck or chance. Examples include \"Chess\" and \"Go\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Adventure",
					"Games that involve storytelling and exploration, such as \"Tales of Arabian Nights\" and \"Eldritch Horror\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Family",
					"Games that are suitable for players of all ages, such as \"Ticket to Ride\" and \"Catan\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Fantasy",
					"Games that are set in fantastical worlds and often involve elements like magic, mythical creatures, and epic quests. Examples include \"Mage Knight\" and \"Dungeons & Dragons\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Horror",
					"Games that aim to scare players or provide a tense atmosphere, like \"Arkham Horror\" and \"Mysterium\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Science Fiction",
					"Games that take place in futuristic or alternate settings, featuring technology, space exploration, or alien civilizations, such as \"Terraforming Mars\" and \"Twilight Imperium\"."));
			boardGameGenreRepository.save(new BoardGameGenre("War",
					"Games that simulate warfare or battles, such as \"Risk\" and \"Axis & Allies\"."));

			BoardGame boardGame1 = BoardGame.builder()
					.productName("Catan")
					.productDescription("Catan, previously known as The Settlers of Catan or simply Settlers, is a multiplayer board game designed by Klaus Teuber, and first published in 1995 in Germany by Franckh-Kosmos Verlag (Kosmos) as Die Siedler von Catan.")
					.productPrice(new BigDecimal(100))
					.manufacturer("Kosmos")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("CATAN")
					.productQuantityInStock(10)
					.productImageURL("https://cf.geekdo-images.com/itemrep/img/0X0Y1Z2X3X4X5X6X7X8X9X10/fit-in/246x300/pic2419375.jpg")
					.gameDuration("60-120 min")
					.gameSet("Game box, game board, 2 dice,  game rules and almanac.")
					.minPlayers(3)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(1L).get(), boardGameMechanicRepository.findById(2L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/13/catan")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(10)
					.build();

			boardGameRepository.save(boardGame1);

			BoardGame boardGame2 = BoardGame.builder()
					.productName("Ticket to Ride")
					.productDescription("Ticket to Ride is a railway-themed German-style board game designed by Alan R. Moon. It was illustrated by Julien Delval and Cyrille Daujean and published in 2004 by Days of Wonder. The game is also known as Zug um Zug (German), Les Aventuriers du Rail (French), Aventureros al Tren (Spanish), Wsiąść do pociągu (Polish), and Menolippu (Finnish).")
					.productPrice(new BigDecimal(100))
					.manufacturer("Days of Wonder")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("TTR")
					.productQuantityInStock(10)
					.productImageURL("https://cf.geekdo-images.com/itemrep/img/0X0Y1Z2X3X4X5X6X7X8X9X10/fit-in/246x300/pic38668.jpg")
					.gameDuration("30-60 min")
					.gameSet("Game board, 240 colored train cars, 110 train car cards, 30 destination tickets, 1 summary card, 5 wooden scoring markers, and game rules and almanac.")
					.minPlayers(2)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(3L).get(), boardGameMechanicRepository.findById(4L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/9209/ticket-ride")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			boardGameRepository.save(boardGame2);

			BoardGame boardGame3 = BoardGame.builder()
					.productName("Dixit")
					.productDescription("Dixit is a card game created by Jean-Louis Roubira, and published by Libellud. Using a deck of cards illustrated with dreamlike images, players select cards that match a title suggested by the \"storyteller\", and attempt to guess which card the \"storyteller\" selected.")
					.productPrice(new BigDecimal(100))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("DIXIT")
					.productQuantityInStock(10)
					.productImageURL("https://cf.geekdo-images.com/itemrep/img/0X0Y1Z2X3X4X5X6X7X8X9X10/fit-in/246x300/pic3483909.jpg")
					.gameDuration("30 min")
					.gameSet("84 cards, 36 voting tokens in 6 different colors numbered from 1 to 6, 6 wooden rabbit tokens, 1 game board, and game rules and almanac.")
					.minPlayers(3)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(5L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/39856/dixit")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			boardGameRepository.save(boardGame3);


			//-------------------shoppingCart-------------------

			ShoppingCart cart1 = adminProfile.getShoppingCart();

			cart1.getProductsInShoppingCart().add(new ProductInShoppingCart(boardGame1, cart1, 1));

			shoppingCartRepository.save(cart1);


		};
	}
}
