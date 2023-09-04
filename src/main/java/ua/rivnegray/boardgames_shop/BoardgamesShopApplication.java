package ua.rivnegray.boardgames_shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.rivnegray.boardgames_shop.config.custom_configuration_properties.ImageProperties;
import ua.rivnegray.boardgames_shop.config.custom_configuration_properties.PaginationProperties;
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
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@ComponentScan({"ua.rivnegray.boardgames_shop", "generated"})
@EnableConfigurationProperties({ImageProperties.class, PaginationProperties.class})
public class BoardgamesShopApplication {
    private final static Logger LOGGER = LoggerFactory.getLogger(BoardgamesShopApplication.class);
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

			LOGGER.info("Active profile: " + Arrays.toString(environment.getActiveProfiles()));

			UserRole roleSuperAdmin = new UserRole("ROLE_SUPER_ADMIN");
			Set<UserPermission> superAdminPermissions = EnumSet.allOf(UserPermission.class);
			roleSuperAdmin.setPermissions(superAdminPermissions);
			roleRepository.save(roleSuperAdmin);

			UserRole roleCustomer = new UserRole("ROLE_CUSTOMER");
			Set<UserPermission> userPermissions = EnumSet.of(
					UserPermission.USER_READ_ME,
					UserPermission.USER_UPDATE_ME,
					UserPermission.SHOPPING_CART_MANAGE_ME,
					UserPermission.SHOPPING_CART_READ,
					UserPermission.ORDER_READ_ME,
					UserPermission.ORDER_CANCEL_ME
			);
			roleCustomer.setPermissions(userPermissions);
			roleRepository.save(roleCustomer);

			UserProfile adminProfile = new UserProfile("glebivashyn@gmail.com", "1", "Gleb", "Ivashyn",
					Set.of(roleSuperAdmin));
			UserCredentials adminCredentials = new UserCredentials("admin", encoder.encode("admin"));
			adminCredentials.setUserProfile(adminProfile);
			adminProfile.setUserCredentials(adminCredentials);

			adminProfile.getAddresses().add(new Address("Yermaka", "1", "33000","Rivne", "Ukraine", adminProfile));

			userProfileRepository.save(adminProfile);

			UserProfile customerProfile = new UserProfile("customeruser@gmail.com", "2", "Gleb", "Ivashyn",
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
			boardGameMechanicRepository.save(new BoardGameMechanic("Card Drafting",
					"Players select cards from a limited subset, such as in \"7 Wonders\" and \"Sushi Go!\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Hand Management",
					"Players have a hand of cards that they manage over the course of the game, such as in \"Poker\" and \"The Mind\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Grid Movement",
					"Players move pieces along a grid of spaces, such as in \"Chess\" and \"Catan\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Variable Player Powers",
					"Players have unique abilities or traits that give them advantages or disadvantages, such as in \"Cosmic Encounter\" and \"Root\"."));
			boardGameMechanicRepository.save(new BoardGameMechanic("Team-Based Game",
					"Players work together in teams to achieve a common goal, such as in \"Codenames\" and \"Captain Sonar\"."));

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
			boardGameGenreRepository.save(new BoardGameGenre("Fighting",
					"Games that simulate hand-to-hand combat between players, such as \"Magic: The Gathering\" and \"Yomi\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Economic",
					"Games that simulate economic systems, such as \"Power Grid\" and \"Brass\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Ancient",
					"Games that are set in ancient times, such as \"Tigris & Euphrates\" and \"Hannibal: Rome vs. Carthage\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Medieval",
					"Games that are set in medieval times, such as \"Carcassonne\" and \"Agricola\"."));

			BoardGame boardGame1 = BoardGame.builder()
					.productName("Catan")
					.productDescription("Catan, previously known as The Settlers of Catan or simply Settlers, is a multiplayer board game designed by Klaus Teuber, and first published in 1995 in Germany by Franckh-Kosmos Verlag (Kosmos) as Die Siedler von Catan.")
					.productPrice(new BigDecimal(100))
					.manufacturer("Kosmos")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("CATAN")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://www.catan.com/sites/default/files/2021-08/3DBox_CATAN_w_b.jpg"))
					.minGameDuration(60)
					.maxGameDuration(120)
					.gameSet("Game box, game board, 2 dice,  game rules and almanac.")
					.minPlayers(3)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(1L).get(), boardGameMechanicRepository.findById(2L).get(), boardGameMechanicRepository.findByMechanicName("Grid Movement").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/13/catan")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(10)
					.build();

			BoardGame boardGame2 = BoardGame.builder()
					.productName("Ticket to Ride")
					.productDescription("Ticket to Ride is a railway-themed German-style board game designed by Alan R. Moon. It was illustrated by Julien Delval and Cyrille Daujean and published in 2004 by Days of Wonder. The game is also known as Zug um Zug (German), Les Aventuriers du Rail (French), Aventureros al Tren (Spanish), Wsiąść do pociągu (Polish), and Menolippu (Finnish).")
					.productPrice(new BigDecimal("10.99"))
					.manufacturer("Days of Wonder")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("TTR")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91YNJM4oyhL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("Game board, 240 colored train cars, 110 train car cards, 30 destination tickets, 1 summary card, 5 wooden scoring markers, and game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(3L).get(), boardGameMechanicRepository.findById(4L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/9209/ticket-ride")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			BoardGame boardGame3 = BoardGame.builder()
					.productName("Dixit")
					.productDescription("Dixit is a card game created by Jean-Louis Roubira, and published by Libellud. Using a deck of cards illustrated with dreamlike images, players select cards that match a title suggested by the \"storyteller\", and attempt to guess which card the \"storyteller\" selected.")
					.productPrice(new BigDecimal("45.50"))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("DIXIT")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81KNlhEz06L.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("84 cards, 36 voting tokens in 6 different colors numbered from 1 to 6, 6 wooden rabbit tokens, 1 game board, and game rules and almanac.")
					.minPlayers(3)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(5L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/39856/dixit")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			BoardGame boardGame4 = BoardGame.builder()
					.productName("Mysterium")
					.productDescription("In the 1920s, Mr. MacDowell, a gifted astrologist, immediately detected a supernatural being upon entering his new house in Scotland. He gathered eminent mediums of his time for an extraordinary seance, and they have seven hours to contact the ghost and investigate any clues that it can provide to unlock an old mystery.")
					.productPrice(new BigDecimal("56.70"))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("MYSTERIUM")
					.productQuantityInStock(4)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/61FDE9dJlyL.__AC_SX300_SY300_QL70_FMwebp_.jpg"))
					.minGameDuration(42)
					.maxGameDuration(42)
					.gameSet("1 game board, 6 intuitive cards, 6 sleeves, 6 clairvoyance level markers, 36 clairvoyance tokens, 4 progress boards, 18 character psychic cards, 18 location psychic cards, 18 object psychic cards, 1 sand timer, 18 character ghost cards, 18 location ghost cards, 18 object ghost cards, 6 ghost tokens, 84 vision cards, 6 culprit tokens, 3 crow markers, 1 clairvoyance track, 1 clairvoyance token, and game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(7)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Horror").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Cooperative Play").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/181304/mysterium")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(10)
					.build();


			BoardGame boardGame5 = BoardGame.builder()
					.productName("King of Tokyo")
					.productDescription("In King of Tokyo, you play mutant monsters, gigantic robots, and strange aliens—all of whom are destroying Tokyo and whacking each other in order to become the one and only King of Tokyo.")
					.productPrice(new BigDecimal("35.30"))
					.manufacturer("Iello")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("KOT")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81fVDF-Gb8L.jpg"))
					.minGameDuration(50)
					.maxGameDuration(50)
					.gameSet("1 game board, 6 monster boards, 6 monster tokens with plastic stands, 8 six-sided dice, 50 energy cubes, 66 cards, and game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Fighting").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Dice Rolling").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/70323/king-tokyo")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(8)
					.build();

			BoardGame boardGame6 = BoardGame.builder()
					.productName("7 Wonders Duel")
					.productDescription("In many ways 7 Wonders Duel resembles its parent game 7 Wonders as over three ages players acquire cards that provide resources or advance their military or scientific development in order to develop a civilization and complete wonders. What's different about 7 Wonders Duel is that, as the title suggests, the game is solely for two players, with the players not drafting cards simultaneously from hands of cards, but from a display of face-down and face-up cards arranged at the start of a round. A player can take a card only if it's not covered by any others, so timing comes into play as well as bonus moves that allow you to take a second card immediately. As in the original game, each card that you acquire can be built, discarded for coins, or used to construct a wonder.")
					.productPrice(new BigDecimal("35.10"))
					.manufacturer("Repos Production")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("7WONDERSDUEL")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://cdn.svc.asmodee.net/production-rprod/storage/games/7-wonders-duel/57du-3dmockup-1597826159KUURt.png"))
					.minGameDuration(40)
					.maxGameDuration(50)
					.gameSet("1 game board, 23 Age I cards, 23 Age II cards, 20 Age III cards, 7 Guild cards, 12 Wonder cards, 4 Military tokens, 10 Progress tokens, 1 Conflict pawn, 31 coins, 1 score book, 1 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(2)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Abstract Strategy").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Card Drafting").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/173346/7-wonders-duel")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(10)
					.build();

			BoardGame boardGame7 = BoardGame.builder()
					.productName("Dominion")
					.productDescription("In Dominion, each player starts with an identical, very small deck of cards. In the center of the table is a selection of other cards the players can \"buy\" as they can afford them. Through their selection of cards to buy, and how they play their hands as they draw them, the players construct their deck on the fly, striving for the most efficient path to the precious victory points by game end.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Rio Grande Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("DOMINION")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_0591f27c-12d2-46bc-ba08-5d2a50136de7?wid=488&hei=488&fmt=pjpeg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("500 cards, 1 Trash card, 1 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Fantasy").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Deck Building").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/36218/dominion")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(13)
					.build();

			BoardGame boardGame8 = BoardGame.builder()
					.productName("Terraforming Mars")
					.productDescription("In the 2400s, mankind begins to terraform the planet Mars. Giant corporations, sponsored by the World Government on Earth, initiate huge projects to raise the temperature, the oxygen level, and the ocean coverage until the environment is habitable. In Terraforming Mars, you play one of those corporations and work together in the terraforming process, but compete for getting victory points that are awarded not only for your contribution to the terraforming, but also for advancing human infrastructure throughout the solar system, and doing other commendable things.")
					.productPrice(new BigDecimal(45))
					.manufacturer("FryxGames")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("TERRAFORMINGMARS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/713hLG3OkaL._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(120)
					.maxGameDuration(180)
					.gameSet("1 game board, 200 player markers, 5 corporation cards, 208 project cards, 8 reference cards, 60 action cards, 11 neutral cards, 1 first player token, 1 generation marker, 1 terraform rating marker, 1 oxygen marker, 1 temperature marker, 1 ocean marker, 1 game rules and almanac.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Science Fiction").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Hand Management").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/167791/terraforming-mars")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(12)
					.build();

			BoardGame boardGame9 = BoardGame.builder()
					.productName("Scythe")
					.productDescription("Scythe is an engine-building, asymmetric, competitive board game set in an alternate-history 1920s period. It is a time of farming and war, broken hearts and rusted gears, innovation and valor. In Scythe, each player represents a character from one of five factions of Eastern Europa who are attempting to earn their fortune and claim their faction's stake in the land around the mysterious Factory. Players conquer territory, enlist new recruits, reap resources, gain villagers, build structures, and activate monstrous mechs.")
					.productPrice(new BigDecimal(105))
					.manufacturer("Stonemaier Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("SCYTHE")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3tJzVAoYnx9FYCs3bUE3nv7VLnyr5CNot8A&usqp=CAU"))
					.minGameDuration(90)
					.maxGameDuration(115)
					.gameSet("1 game board, 1 achievement sheet, 5 action tokens, 5 popularity tokens, 5 power tokens, 5 star tokens, 30 custom wooden resource tokens, 20 custom wooden structure tokens, 20 meeples, 20 custom wooden technology cubes, 12 custom wooden encounter tokens, 6 structure bonus tiles, 6 factory cards, 2 power dials, 5 quick-reference cards, 31 Automa cards, 28 encounter cards, 23 objective cards, 42 combat cards, 12 factory cards, 25 achievement cards, 5 faction mats, 5 custom wooden action tokens, 5 custom wooden player mats, 5 custom wooden player tokens, 80 custom wooden coins, 11 action tiles, 2 custom dice, 1 game rules and almanac.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Science Fiction").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Worker Placement").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/169786/scythe")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(14)
					.build();


			BoardGame boardGame10 = BoardGame.builder()
					.productName("Gloomhaven")
					.productDescription("Gloomhaven is a game of Euro-inspired tactical combat in a persistent world of shifting motives. Players will take on the role of a wandering adventurer with their own special set of skills and their own reasons for traveling to this dark corner of the world. Players must work together out of necessity to clear out menacing dungeons and forgotten ruins. In the process, they will enhance their abilities with experience and loot, discover new locations to explore and plunder, and expand an ever-branching story fueled by the decisions they make.")
					.productPrice(new BigDecimal(175))
					.manufacturer("Cephalofair Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("GLOOMHAVEN")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81crhhZd63L._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(60)
					.maxGameDuration(120)
					.gameSet("1 game board, 1 scenario book, 1 rule book, 1 map board, 4 sticker sheets")
					.minPlayers(1)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Adventure").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Grid Movement").get(), boardGameMechanicRepository.findByMechanicName("Hand Management").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/174430/gloomhaven")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();


			BoardGame boardGame11 = BoardGame.builder()
					.productName("Terra Mystica")
					.productDescription("Terra Mystica is a game with very little luck that rewards strategic planning. Each player governs one of the 14 groups. With subtlety and craft, the player must attempt to rule as great an area as possible and to develop that group's skills. There are also four religious cults in which you can progress. To do all that, each group has special skills and abilities.")
					.productPrice(new BigDecimal(115))
					.manufacturer("Feuerland Spiele")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("TERRAMYSTICA")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81dqL2OtjQL._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(60)
					.maxGameDuration(150)
					.gameSet("1 game board, 28 terrain tiles, 56 structure tokens, 70 worker tokens, 65 coins, 1 starting player token, 17 action tokens, 10 town tiles, 8 bonus cards, 4 cult boards, 1 cult bonus tile, 1 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Fantasy").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Variable Player Powers").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/120677/terra-mystica")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(12)
					.build();


			BoardGame boardGame12 = BoardGame.builder()
					.productName("Twilight Imperium (Fourth Edition)")
					.productDescription("Twilight Imperium (Fourth Edition) is a game of galactic conquest in which three to six players take on the role of one of seventeen factions vying for galactic domination through military might, political maneuvering, and economic bargaining.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Fantasy Flight Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("TWILIGHTIMPERIUM")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_e903067a-c9d5-46bd-91e3-791e6c0af016?wid=488&hei=488&fmt=pjpeg"))
					.minGameDuration(240)
					.maxGameDuration(480)
					.gameSet("1 game board, 17 faction sheets, 6 command sheets, 51 system tiles, 562 cards, 510 plastic units, 80 plastic fighters, 50 plastic ground forces, 40 plastic structures, 8 plastic Space Docks, 60 plastic PDS units, 1 game rules and almanac.")
					.minPlayers(3)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Science Fiction").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Variable Player Powers").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/233078/twilight-imperium-fourth-edition")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();


			BoardGame boardGame13 = BoardGame.builder()
					.productName("The Castles of Burgundy")
					.productDescription("The game is set in the Burgundy region of High Medieval France. Each player takes on the role of an aristocrat, originally controlling a small princedom. While playing they aim to build settlements and powerful castles, practice trade along the river, exploit silver mines, and use the knowledge of travellers.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Ravensburger Spieleverlag GmbH")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("CASTLESOFBURGUNDY")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81ALqSnd1QL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(90)
					.gameSet("1 game board, 6 player boards, 9 dice, 1 set of 16 wooden playing pieces, 6 summary cards, 1 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Abstract Strategy").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Dice Rolling").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/84876/castles-burgundy")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();


			BoardGame boardGame14 = BoardGame.builder()
					.productName("Puerto Rico")
					.productDescription("In Puerto Rico players assume the roles of colonial governors on the island of Puerto Rico. The aim of the game is to amass victory points by shipping goods to Europe or by constructing buildings.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Ravensburger Spieleverlag GmbH")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("PUERTORICO")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_0a8d7cfb-9b80-47d3-b549-9890b918731d"))
					.minGameDuration(90)
					.maxGameDuration(150)
					.gameSet("1 game board, 5 player boards, 9 dice, 1 set of 16 wooden playing pieces, 6 summary cards, 1 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Economic").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Variable Player Powers").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/3076/puerto-rico")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();


			BoardGame boardGame15 = BoardGame.builder()
					.productName("Power Grid")
					.productDescription("The objective of Power Grid is to supply the most cities with power when someone's network gains a predetermined size. In this new edition, players mark pre-existing routes between cities for connection, and then bid against each other to purchase the power plants that they use to power their cities.")
					.productPrice(new BigDecimal(95))
					.manufacturer("Rio Grande Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("POWERGRID")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81cwzwEL2+L.jpg"))
					.minGameDuration(120)
					.maxGameDuration(150)
					.gameSet("1 game board, 132 wooden houses, 84 wooden resource tokens, 1 market board, 43 power plant cards, 1 step 3 card, 1 step 2 card, 1 step 1 card, 1 overview card, 1 starting player card, 1 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Economic").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Auction/Bidding").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/2651/power-grid")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();



			BoardGame boardGame16 = BoardGame.builder()
					.productName("Agricola")
					.productDescription("In Agricola, you're a farmer in a wooden shack with your spouse and little else. On a turn, you get to take only two actions, one for you and one for the spouse, from all the possibilities you'll find on a farm: collecting clay, wood, or stone; building fences; and so on. You might think about having kids in order to get more work accomplished, but first you need to expand your house. And what are you going to feed all the little rugrats?")
					.productPrice(new BigDecimal(95))
					.manufacturer("Lookout Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("AGRICOLA")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81qWMPD9+LL.jpg"))
					.minGameDuration(120)
					.maxGameDuration(150)
					.gameSet("1 game board, 360 cards, 9 game boards, 1 scoring pad, 1 cloth bag, 1 game rules and almanac.")
					.minPlayers(1)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Economic").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Worker Placement").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/31260/agricola")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();



			BoardGame boardGame17 = BoardGame.builder()
					.productName("7 Wonders")
					.productDescription("7 Wonders lasts three ages. In each age, players receive seven cards from a particular deck, choose one of those cards, then pass the remainder to an adjacent player. Players reveal their cards simultaneously, paying resources if needed or collecting resources or interacting with other players in various ways. (Players have individual boards with special powers on which to organize their cards, and the boards are double-sided). Each player then chooses another card from the deck they were passed, and the process repeats until players have six cards in play from that age. After three ages, the game ends.")
					.productPrice(new BigDecimal(95))
					.manufacturer("Repos Production")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("7WONDERS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/710S0SyIpPL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("1 game board, 7 wonder boards, 7 wonder cards, 49 age I cards, 49 age II cards, 50 age III cards, 46 conflict tokens, 24 value 3 coins, 46 value 1 coins, 1 score booklet, 2 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(7)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Ancient").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Card Drafting").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/68448/7-wonders")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(10)
					.build();



			BoardGame boardGame18 = BoardGame.builder()
					.productName("Codenames")
					.productDescription("Two rival spymasters know the secret identities of 25 agents. Their teammates know the agents only by their CODENAMES. In Codenames, two teams compete to see who can make contact with all of their agents first. Spymasters give one-word clues that can point to multiple words on the board. Their teammates try to guess words of the right color while avoiding those that belong to the opposing team. And everyone wants to avoid the assassin.")
					.productPrice(new BigDecimal(75))
					.manufacturer("Czech Games Edition")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("CODENAMES")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/41feKk3dWdL.jpg"))
					.minGameDuration(15)
					.maxGameDuration(30)
					.gameSet("1 game board, 200 cards, 40 key cards, 1 card stand, 1 timer, 1 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(8)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Family").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Team-Based Game").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/178900/codenames")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();



			BoardGame boardGame19 = BoardGame.builder()
					.productName("Mansions of Madness: Second Edition")
					.productDescription("Mansions of Madness: Second Edition is a fully cooperative, app-driven board game of horror and mystery for one to five players that takes place in the same universe as Eldritch Horror and Elder Sign. Let the immersive app guide you through the veiled streets of Innsmouth and the haunted corridors of Arkham's cursed mansions as you search for answers and respite. Eight brave investigators stand ready to confront four scenarios of fear and mystery, collecting weapons, tools, and information, solving complex puzzles, and fighting monsters, insanity, and death. Open the door and step inside these hair-raising Mansions of Madness: Second Edition. It will take more than just survival to conquer the evils terrorizing this town.")
					.productPrice(new BigDecimal(85))
					.manufacturer("Fantasy Flight Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("MANSIONSOFMADNESS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91ZGULlSMwS.jpg"))
					.minGameDuration(120)
					.maxGameDuration(180)
					.gameSet("24 map tiles, 8 investigator figures, 24 monster figures, 40 common item cards, 24 unique item cards, 36 spell cards, 24 condition cards, 16 damage cards, 16 horror cards, 12 search cards, 4 barricade tokens, 4 secret passage tokens, 8 rubble tokens, 4 furniture tokens, 4 wall tokens, 30 clue/doom tokens, 1 entrance token, 4 starting space tokens, 1 rulebook, 1 learn to play guide, 1 quest guide, 1 reference guide, 1 app guide, 1 conversion kit guide, 1 game rules and almanac.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Horror").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Cooperative Play").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/205059/mansions-madness-second-edition")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(12)
					.build();

			BoardGame boardGame20 = BoardGame.builder()
					.productName("Wingspan")
					.productDescription("Wingspan is a competitive, medium-weight, card-driven, engine-building board game from Stonemaier Games. You are bird enthusiasts—researchers, bird watchers, ornithologists, and collectors—seeking to discover and attract the best birds to your network of wildlife preserves. Each bird extends a chain of powerful combinations in one of your habitats (actions). These habitats focus on several key aspects of growth:")
					.productPrice(new BigDecimal(85))
					.manufacturer("Stonemaier Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("WINGSPAN")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://stonemaiergames.com/wp-content/uploads/2019/02/3d-wingspan.png"))
					.minGameDuration(40)
					.maxGameDuration(70)
					.gameSet("170 unique bird cards (57x87mm), 26 bonus cards (57x87mm), 16 automa cards (57x87mm), 103 food tokens, 75 egg miniatures, 5 custom wooden dice, 5 player mats, 1 birdfeeder dice tower, 2-piece Game Trayz custom tray, 1 goal mat, 8 goal tiles, 1 first-player token, 40 action cubes (8 per player), 4 clear plastic resource containers, 1 scorepad (50 sheets; 1 sheet used for all players each game), 3 rulebooks (core, Automa, and Appendix).")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Adventure").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Card Drafting").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/266192/wingspan")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(10)
					.build();

			BoardGame boardGame21 = BoardGame.builder()
					.productName("The Quacks of Quedlinburg")
					.productDescription("In The Quacks of Quedlinburg, players are charlatans — or quack doctors — each making their own secret brew by adding ingredients one at a time. Take care with what you add, though, for a pinch too much of this or that will spoil the whole mixture!")
					.productPrice(new BigDecimal(85))
					.manufacturer("North Star Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("QUACKSOFQUEDLINBURG")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91cR+taIA3S._AC_SL1500_.jpg"))
					.minGameDuration(45)
					.maxGameDuration(60)
					.gameSet("1 game board, 4 player boards, 4 rat stones, 1 start player marker, 1 round marker, 1 scoring marker, 1 rat stone bag, 1 bonus tile, 1 rulebook, 1 game rules and almanac.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Medieval").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Dice Rolling").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/244521/quacks-quedlinburg")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(10)
					.build();

			boardGameRepository.saveAll(List.of(boardGame1, boardGame2, boardGame3, boardGame4, boardGame5, boardGame6, boardGame7, boardGame8, boardGame9, boardGame10, boardGame11, boardGame12, boardGame13, boardGame14, boardGame15, boardGame16, boardGame17, boardGame18, boardGame19, boardGame20, boardGame21));

			//-------------------shoppingCart-------------------
			ShoppingCart cart1 = adminProfile.getShoppingCart();
			cart1.getProductsInShoppingCart().add(new ProductInShoppingCart(boardGame1, cart1, 1));

			shoppingCartRepository.save(cart1);

			LOGGER.debug("test");
		};
	}
}
