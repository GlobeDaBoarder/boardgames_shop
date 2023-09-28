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

			boardGameMechanicRepository.save(new BoardGameMechanic("Конструювання колоди",
					"Гравці починають з невеликої колоди карток і можуть додавати в неї нові, щоб покращити свої можливості протягом гри. Приклади: \"Dominion\" та \"Star Realms\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Розміщення робітників",
					"Гравці розміщують жетони (\"робітники\") на певних локаціях, щоб виконувати дії або збирати ресурси, як це є в \"Agricola\" та \"Lords of Waterdeep\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Розміщення плиток",
					"Гравці розміщують плитки, щоб створити ігрове поле під час гри, наприклад, в \"Carcassonne\" та \"Alhambra\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Кидання кубиків",
					"Гравці кидають кубики для визначення результату гри, як в \"Yahtzee\" та \"King of Tokyo\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Кооперативна гра",
					"Гравці працюють разом, щоб досягти загальної мети, наприклад, в \"Pandemic\" та \"Forbidden Island\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Рольова гра",
					"Гравці приймають на себе ролі вигаданих персонажів і спільно створюють історію, як в \"Dungeons & Dragons\" та \"Fiasco\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Аукціон/Ліцитація",
					"Гравці конкурують за можливість придбати предмети або ресурси, роблячи ставки, як в \"Ra\" та \"Power Grid\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Вибір карт",
					"Гравці вибирають карти з обмеженого набору, наприклад, в \"7 Wonders\" та \"Sushi Go!\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Управління рукою",
					"Гравці мають в руках карти, якими управляють протягом гри, наприклад, в \"Poker\" та \"The Mind\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Рух по сітці",
					"Гравці пересувають фішки по сітці просторів, наприклад, в \"Chess\" та \"Catan\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Змінні спроможності гравців",
					"У гравців є унікальні здібності або характеристики, які дають їм переваги або недоліки, наприклад, в \"Cosmic Encounter\" та \"Root\"."));

			boardGameMechanicRepository.save(new BoardGameMechanic("Командна гра",
					"Гравці працюють разом в командах для досягнення загальної мети, наприклад, в \"Codenames\" та \"Captain Sonar\"."));

			boardGameGenreRepository.save(new BoardGameGenre("Абстрактна стратегія",
					"Ігри, які залежать від стратегічного мислення та розсудливості, а не від везіння або випадку. Приклади включають \"Шахи\" та \"Го\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Пригоди",
					"Ігри, які включають розповідання та дослідження, такі як \"Казки арабських ночей\" та \"Елдрічний жах\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Сімейні",
					"Ігри, які підходять для гравців всіх віків, такі як \"Квиток на поїзд\" та \"Катан\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Фентезі",
					"Ігри, які розміщені у фантастичних світах і часто включають елементи, такі як магія, мітологічні істоти та епічні квести. Приклади включають \"Маг-воїн\" та \"Підземелля і дракони\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Жахи",
					"Ігри, які мають на меті налякати гравців або забезпечити напружену атмосферу, як \"Аркемський жах\" та \"Містеріум\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Наукова фантастика",
					"Ігри, які відбуваються у майбутніх або альтернативних налаштуваннях, з технологіями, космічними дослідженнями або інопланетними цивілізаціями, такі як \"Тераформування Марса\" та \"Сутінкова імперія\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Війна",
					"Ігри, які імітують війну або битви, такі як \"Ризик\" та \"Осі та союзники\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Бійка",
					"Ігри, які імітують рукопашний бій між гравцями, такі як \"Магія: Збірка\" та \"Йомі\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Економіка",
					"Ігри, які імітують економічні системи, такі як \"Енергетична сітка\" та \"Латунь\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Стародавні часи",
					"Ігри, які відбуваються у стародавні часи, такі як \"Тигрис та Євфрат\" та \"Ганнібал: Рим проти Карфагена\"."));
			boardGameGenreRepository.save(new BoardGameGenre("Середньовіччя",
					"Ігри, які відбуваються у середньовічні часи, такі як \"Каркасон\" та \"Агрікола\"."));

			BoardGame boardGame1 = BoardGame.builder()
					.productName("Катан")
					.productDescription("Катан, раніше відомий як Поселенці Катану або просто Поселенці, - це багатокористувацька настільна гра, створена Клаусом Тойбером, і вперше опублікована в 1995 році в Німеччині фірмою Franckh-Kosmos Verlag (Kosmos) під назвою Die Siedler von Catan.")
					.productPrice(new BigDecimal(100))
					.manufacturer("Kosmos")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КАТАН")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://www.catan.com/sites/default/files/2021-08/3DBox_CATAN_w_b.jpg"))
					.minGameDuration(60)
					.maxGameDuration(120)
					.gameSet("Коробка для гри, ігрова дошка, 2 кубики, правила гри та альманах.")
					.minPlayers(3)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(1L).get(), boardGameMechanicRepository.findById(2L).get(), boardGameMechanicRepository.findByMechanicName("Розміщення плиток").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/13/catan")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(2)
					.build();

			BoardGame boardGame2 = BoardGame.builder()
					.productName("Квиток на поїзд")
					.productDescription("Квиток на поїзд - це залізнична настільна гра в німецькому стилі, розроблена Аланом Р. Муном. Ілюстровано Жульєном Дельвалем та Сіріль Дожаном, опублікована в 2004 році Days of Wonder.")
					.productPrice(new BigDecimal("10.99"))
					.manufacturer("Days of Wonder")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КНП")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91YNJM4oyhL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("Ігрова дошка, 240 кольорових вагончиків, 110 карток вагончиків, 30 квитків на маршрут, 1 підсумкова карта, 5 дерев'яних позначок для підрахунку очок, та правила гри і альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(3L).get(), boardGameMechanicRepository.findById(4L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/9209/ticket-ride")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			BoardGame boardGame3 = BoardGame.builder()
					.productName("Діксіт")
					.productDescription("Діксіт - це карточна гра, створена Жан-Луї Рубіра, та опублікована Libellud. Використовуючи колоду карток з мрійливими зображеннями, гравці вибирають картки, які відповідають заголовку, запропонованому 'розповідачем', і намагаються вгадати, яку карту 'розповідач' вибрав.")
					.productPrice(new BigDecimal("45.50"))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ДІКСІТ")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81KNlhEz06L.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("84 карти, 36 голосувальних фішок у 6 різних кольорах з номерами від 1 до 6, 6 дерев'яних фішок-зайців, 1 ігрова дошка, та правила гри і альманах.")
					.minPlayers(3)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(5L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/39856/dixit")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			BoardGame boardGame4 = BoardGame.builder()
					.productName("Містеріум")
					.productDescription("У 1920-х роках пан МакДауелл, обдарований астролог, відразу відчув надприродне існування, увійшовши в свій новий будинок у Шотландії. Він зібрав видатних медіумів свого часу для неймовірного спіритичного сеансу, і вони мають сім годин, щоб зв'язатися з привидом і дослідити будь-які ключові підказки, які він може надати для розкриття старої таємниці.")
					.productPrice(new BigDecimal("56.70"))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("МІСТЕРІУМ")
					.productQuantityInStock(4)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/61FDE9dJlyL.__AC_SX300_SY300_QL70_FMwebp_.jpg"))
					.minGameDuration(42)
					.maxGameDuration(42)
					.gameSet("1 ігрова дошка, 6 інтуітивних карт, 6 конвертів, 6 маркерів рівня ясновидіння, 36 фішок ясновидіння, 4 дошки прогресу, 18 картах персонажів-психіків, 18 картах локацій-психіків, 18 картах об'єктів-психіків, 1 пісковий таймер, 18 картах персонажів-привидів, 18 картах локацій-привидів, 18 картах об'єктів-привидів, 6 фішок-привидів, 84 карти видінь, 6 фішок винних, 3 маркери ворон, 1 доріжка ясновидіння, 1 фішка ясновидіння, і правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(7)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Жахи").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/181304/mysterium")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(2)
					.build();

			BoardGame boardGame5 = BoardGame.builder()
					.productName("Король Токіо")
					.productDescription("У Королі Токіо ви граєте за мутантних монстрів, гігантських роботів і дивних інопланетян, які всі руйнують Токіо і б'ють один одного, щоб стати єдиним і неповторним Королем Токіо.")
					.productPrice(new BigDecimal("35.30"))
					.manufacturer("Ієлло")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КОТ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81fVDF-Gb8L.jpg"))
					.minGameDuration(50)
					.maxGameDuration(50)
					.gameSet("1 ігрова дошка, 6 дошок монстрів, 6 фішок монстрів з пластиковими підставками, 8 кубиків з шістьма гранями, 50 енергетичних кубиків, 66 карт, і правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Бійка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/70323/king-tokyo")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(8)
					.build();

			BoardGame boardGame6 = BoardGame.builder()
					.productName("7 чудес: Дуель")
					.productDescription("У багатьох аспектах 7 чудес: Дуель нагадує свою материнську гру 7 чудес, де протягом трьох епох гравці отримують карти, які забезпечують ресурси або сприяють їхньому військовому чи науковому розвитку, щоб розвинути цивілізацію та здійснити чудеса. Те, що робить 7 чудес: Дуель унікальною, це те, що гра призначена лише для двох гравців, які не вибирають карти одночасно з рук, але з виставки карток, розташованих обличчям вниз і вгору на початку раунду. Гравець може взяти карту лише тоді, коли вона не перекрита жодною іншою, тому тут важливий час, а також бонусні ходи, які дозволяють вам взяти другу карту відразу.")
					.productPrice(new BigDecimal("35.10"))
					.manufacturer("Repos Production")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("7ЧУДЕСДУЕЛЬ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://cdn.svc.asmodee.net/production-rprod/storage/games/7-wonders-duel/57du-3dmockup-1597826159KUURt.png"))
					.minGameDuration(40)
					.maxGameDuration(50)
					.gameSet("1 ігрова дошка, 23 карти І епохи, 23 карти ІІ епохи, 20 карт ІІІ епохи, 7 карт Гільдій, 12 карт Чудес, 4 військові фішки, 10 фішок Прогресу, 1 фішка Конфлікту, 31 монета, 1 записник результатів, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(2)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Вибір карт").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/173346/7-wonders-duel")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(3)
					.build();

			BoardGame boardGame7 = BoardGame.builder()
					.productName("Домініон")
					.productDescription("У Домініоні кожен гравець починає з ідентичної, дуже маленької колоди карт. У центрі столу розміщений вибір інших карт, які гравці можуть «купувати», якщо вони можуть їх собі дозволити. Шляхом вибору карт для покупки та того, як вони грають свої руки, коли вони їх витягують, гравці створюють свою колоду на льоту, прагнучи до найефективнішого шляху до дорогоцінних очок перемоги до кінця гри.")
					.productPrice(new BigDecimal("120"))
					.manufacturer("Rio Grande Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ДОМІНІОН")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_0591f27c-12d2-46bc-ba08-5d2a50136de7?wid=488&hei=488&fmt=pjpeg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("500 карт, 1 картка сміття, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/36218/dominion")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(3)
					.build();


			BoardGame boardGame8 = BoardGame.builder()
					.productName("Тераформування Марса")
					.productDescription("У 2400-х роках людство починає терраформувати планету Марс. Великі корпорації, які спонсує Всесвітній уряд на Землі, ініціюють великі проекти, щоб підняти температуру, рівень кисню та океанське покриття, поки середовище не стане придатним для життя. У Тераформуванні Марса ви граєте за одну з цих корпорацій і спільно працюєте над терраформуванням, але конкуруєте за отримання очок перемоги, які видаються не тільки за ваш внесок у терраформування, але й за розвиток інфраструктури людства по всьому Сонячному променю, а також за роблення інших похвальних речей.")
					.productPrice(new BigDecimal("45"))
					.manufacturer("FryxGames")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ТЕРАФОРМУВАННЯМАРСА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/713hLG3OkaL._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(120)
					.maxGameDuration(180)
					.gameSet("1 ігрова дошка, 200 ігрових маркерів, 5 карток корпорацій, 208 карток проектів, 8 карток-посібників, 60 карток дій, 11 нейтральних карток, 1 жетон першого гравця, 1 жетон покоління, 1 жетон терраформування, 1 жетон кисню, 1 жетон температури, 1 жетон океану, 1 правила гри та альманах.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Управління рукою").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/167791/terraforming-mars")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(12)
					.build();

			BoardGame boardGame9 = BoardGame.builder()
					.productName("Сайт")
					.productDescription("Сайт - це інженерна, асиметрична, конкурентоспроможна настільна гра, розташована в альтернативній історії 1920-х років. Це час фермерства та війни, розбитих сердець та ржавих шестерень, інновацій та доблесті. У Сайті кожен гравець представляє персонажа з однієї з п'яти фракцій Східної Європи, які намагаються заробити своє багатство та заявити свою частку в землі навколо таємничої фабрики. Гравці завойовують територію, наймають нових найманців, збирають ресурси, отримують селян, будують споруди та активують потужні механізми.")
					.productPrice(new BigDecimal("105"))
					.manufacturer("Stonemaier Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("САЙТ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3tJzVAoYnx9FYCs3bUE3nv7VLnyr5CNot8A&usqp=CAU"))
					.minGameDuration(90)
					.maxGameDuration(115)
					.gameSet("1 ігрова дошка, 1 лист досягнень, 5 жетонів дій, 5 жетонів популярності, 5 жетонів енергії, 5 жетонів зірок, 30 спеціальних дерев'яних ресурсних жетонів, 20 спеціальних дерев'яних жетонів будівель, 20 фішок, 20 спеціальних дерев'яних кубиків технологій, 12 спеціальних дерев'яних фішок зустрічей, 6 бонусних плиток будівель, 6 карток фабрики, 2 кільця енергії, 5 карток швидкого посилання, 31 картка Автома, 28 карток зустрічей, 23 картки цілей, 42 картки бою, 12 карток фабрики, 25 карток досягнень, 5 матів фракцій, 5 спеціальних дерев'яних жетонів дій, 5 спеціальних дерев'яних матів гравців, 5 спеціальних дерев'яних жетонів гравців, 80 спеціальних дерев'яних монет, 11 карток дій, 2 спеціальні кубики, 1 правила гри та альманах.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/169786/scythe")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(14)
					.build();

			BoardGame boardGame10 = BoardGame.builder()
					.productName("Місто Кінгів")
					.productDescription("Місто Кінгів - це настільна стратегічна гра, в якій ви граєте за одного з п'яти родів, які змагаються за владу в Імперії. Використовуючи таланти своїх підданих, ви збираєте ресурси, відкриваєте нові регіони та робите все можливе, щоб здобути підтримку лордів у вашій боротьбі за престол. Місто Кінгів - це гра про стратегію, планування та впевнений крок у майбутнє.")
					.productPrice(new BigDecimal("80"))
					.manufacturer("Лібеллуд")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("МІСТОКІНГІВ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/71Xq1ZNYJ8L._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(60)
					.maxGameDuration(120)
					.gameSet("1 ігрова дошка, 5 дошок родів, 5 дошок лордів, 25 карток лордів, 25 карток регіонів, 5 карток родів, 90 карток талантів, 25 карток підтримки, 25 карток будівель, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/155426/city-kings")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();


			BoardGame boardGame11 = BoardGame.builder()
					.productName("Терра Містика")
					.productDescription("Терра Містика - гра з дуже малою кількістю вдачі, яка винагороджує стратегічне планування. Кожен гравець керує однією з 14 груп. З дотепністю та майстерністю гравець повинен намагатися володіти якомога більшою територією та розвивати навички цієї групи. Також є чотири релігійні культи, в яких ви можете просуватися. Для цього кожна група має спеціальні навички та здібності.")
					.productPrice(new BigDecimal("115"))
					.manufacturer("Feuerland Spiele")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ТЕРРАМІСТИКА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81dqL2OtjQL._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(60)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 28 плиток ландшафту, 56 жетонів структур, 70 жетонів робочих, 65 монет, 1 жетон першого гравця, 17 жетонів дій, 10 карток міст, 8 бонусних карток, 4 дошки культів, 1 бонусна плитка культу, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/120677/terra-mystica")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(14)
					.build();

			BoardGame boardGame12 = BoardGame.builder()
					.productName("Сутінкове Імперіум (Четверте видання)")
					.productDescription("Сутінкове Імперіум (Четверте видання) - це гра про галактичне завоювання, у якій від трьох до шести гравців виступають у ролі однієї з сімнадцяти фракцій, які прагнуть до галактичного панування через військову могутність, політичні маневри та економічні переговори.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Fantasy Flight Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("СУТІНКОВЕІМПЕРІУМ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_e903067a-c9d5-46bd-91e3-791e6c0af016?wid=488&hei=488&fmt=pjpeg"))
					.minGameDuration(240)
					.maxGameDuration(480)
					.gameSet("1 ігрова дошка, 17 аркушів фракцій, 6 командних аркушів, 51 системна плитка, 562 карти, 510 пластикових одиниць, 80 пластикових винищувачів, 50 пластикових наземних військ, 40 пластикових споруд, 8 пластикових космічних доків, 60 пластикових одиниць ПДС, 1 правила гри та річний звіт.")
					.minPlayers(3)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/233078/twilight-imperium-fourth-edition")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();

			BoardGame boardGame13 = BoardGame.builder()
					.productName("Замки Бургундії")
					.productDescription("Гра відбувається в регіоні Бургундія високого середньовіччя Франції. Кожен гравець взяв на себе роль аристократа, початково керуючи малим князівством. Граючи, вони прагнуть будувати поселення та потужні замки, займатися торгівлею по річці, експлуатувати срібні шахти та використовувати знання мандрівників.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Ravensburger Spieleverlag GmbH")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ЗАМКИБУРГУНДІЇ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81ALqSnd1QL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(90)
					.gameSet("1 ігрова дошка, 6 дошок гравців, 9 кубиків, 1 набір з 16 дерев'яних фігур, 6 карток-резюме, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/84876/castles-burgundy")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();


			BoardGame boardGame14 = BoardGame.builder()
					.productName("Пуерто Ріко")
					.productDescription("У Пуерто Ріко гравці приймають на себе ролі колоніальних губернаторів на острові Пуерто Ріко. Мета гри - набрати найбільше очок перемоги, відвантажуючи товари в Європу або будуючи будівлі.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Ravensburger Spieleverlag GmbH")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ПУЕРТОРІКО")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_0a8d7cfb-9b80-47d3-b549-9890b918731d"))
					.minGameDuration(90)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 5 дошок гравців, 9 кубиків, 1 набір з 16 дерев'яних фігур, 6 карток-резюме, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/3076/puerto-rico")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(15)
					.build();

			BoardGame boardGame15 = BoardGame.builder()
					.productName("Електромережа")
					.productDescription("Мета Електромережі - постачати електроенергією найбільше міст, коли мережа когось досягає попередньо визначеного розміру. У цьому новому виданні гравці позначають існуючі маршрути між містами для з'єднання, а потім ставлять проти один одного на аукціоні, щоб купити електростанції, які вони використовують для живлення своїх міст.")
					.productPrice(new BigDecimal(95))
					.manufacturer("Rio Grande Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ЕЛЕКТРОМЕРЕЖА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81cwzwEL2+L.jpg"))
					.minGameDuration(120)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 132 дерев'яних будинку, 84 дерев'яних жетони ресурсів, 1 ринкова дошка, 43 карти електростанцій, 1 карта 3 етапу, 1 карта 2 етапу, 1 карта 1 етапу, 1 оглядова карта, 1 карта початкового гравця, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Аукціон/Ліцитація").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/2651/power-grid")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(15)
					.build();

			BoardGame boardGame16 = BoardGame.builder()
					.productName("Agricola")
					.productDescription("У Agricola ви є фермером у дерев'яній хаті зі своєю дружиною та майже нічим більше. На ході у вас є можливість взяти лише дві дії, одну для себе та одну для дружини, з усіх можливостей, які ви знайдете на фермі: збирати глину, дерево або камінь; будувати паркани; і так далі. Можливо, ви подумаєте про те, щоб мати дітей, щоб отримати більше виконаної роботи, але спершу вам потрібно розширити свій будинок. А чим ви будете годувати всіх цих маленьких непосид?")
					.productPrice(new BigDecimal(95))
					.manufacturer("Lookout Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("AGRICOLA")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81qWMPD9+LL.jpg"))
					.minGameDuration(120)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 360 карток, 9 ігрових дошок, 1 блокнот для підрахунку, 1 тканинний мішечок, 1 правила гри та річний звіт.")
					.minPlayers(1)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/31260/agricola")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(17)
					.build();


			BoardGame boardGame17 = BoardGame.builder()
					.productName("7 Чудес")
					.productDescription("7 Чудес триває три епохи. У кожній епосі гравці отримують сім карток з певної колоди, обирають одну з цих карток, а потім передають решту сусідньому гравцю. Гравці одночасно відкривають свої картки, платять ресурси, якщо потрібно, або збирають ресурси або взаємодіють з іншими гравцями різними способами. (У гравців є індивідуальні дошки з особливими можливостями, на яких організувати свої картки, і дошки є двосторонніми). Кожен гравець потім обирає іншу картку з колоди, яку йому передали, і процес повторюється, поки у гравців є шість карток у грі з цієї епохи. Після трьох епох гра закінчується.")
					.productPrice(new BigDecimal(95))
					.manufacturer("Repos Production")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("7WONDERS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/710S0SyIpPL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("1 ігрова дошка, 7 дошок чудес, 7 карток чудес, 49 карток епохи І, 49 карток епохи II, 50 карток епохи III, 46 жетонів конфлікту, 24 монети номіналом 3, 46 монет номіналом 1, 1 брошура для підрахунку очок, 2 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(7)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/68448/7-wonders")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(4)
					.build();

			BoardGame boardGame18 = BoardGame.builder()
					.productName("Шифроназви")
					.productDescription("Два суперницьких розвідувача знають секретні ідентичності 25 агентів. Їхні товариші знають агентів лише по їхніх ШИФРОНАЗВАМ. У Шифроназвах дві команди змагаються, щоб першими зробити контакт із всіма своїми агентами. Розвідувачі дають однослівні підказки, які можуть вказувати на кілька слів на дошці. Їхні товариші намагаються вгадати слова правильного кольору, уникаючи тих, які належать суперницькій команді. І всі хочуть уникнути асасина.")
					.productPrice(new BigDecimal(75))
					.manufacturer("Czech Games Edition")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("CODENAMES")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/41feKk3dWdL.jpg"))
					.minGameDuration(15)
					.maxGameDuration(30)
					.gameSet("1 ігрова дошка, 200 карток, 40 ключових карток, 1 підставка для карт, 1 таймер, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(8)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Командна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/178900/codenames")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();

			BoardGame boardGame19 = BoardGame.builder()
					.productName("Особняки Божевілля: Друге Видання")
					.productDescription("Особняки Божевілля: Друге Видання - це настільна гра про дослідження надприродного, в якій 1-5 гравців беруть на себе ролі дослідників, які досліджують темні особняки, в яких відбуваються страшні події, щоб знайти відповіді на незвичайні речі, які відбуваються навколо них. Ці дослідники мають вижити в цих особняках, щоб розповісти історії про те, що відбувається, а потім зробити крок назад, щоб відповісти на питання, які ще залишилися без відповіді.")
					.productPrice(new BigDecimal(85))
					.manufacturer("Fantasy Flight Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("MANSIONSOFMADNESS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91ZGULlSMwS.jpg"))
					.minGameDuration(120)
					.maxGameDuration(180)
					.gameSet("1 ігрова дошка, 24 картки кімнат, 8 карток ініціативи, 24 картки сюжету, 16 карток об'єктів, 16 карт")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Жахи").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/205059/mansions-madness-second-edition")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(17)
					.build();

			BoardGame boardGame20 = BoardGame.builder()
					.productName("Крила")
					.productDescription("...")
					.productPrice(new BigDecimal(85))
					.manufacturer("Stonemaier Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("WINGSPAN")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://stonemaiergames.com/wp-content/uploads/2019/02/3d-wingspan.png"))
					.minGameDuration(40)
					.maxGameDuration(70)
					.gameSet("...")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/266192/wingspan")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(4)
					.build();

			BoardGame boardGame21 = BoardGame.builder()
					.productName("Шарлатани з Кведлінбурга")
					.productDescription("...")
					.productPrice(new BigDecimal(85))
					.manufacturer("North Star Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("QUACKSOFQUEDLINBURG")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91cR+taIA3S._AC_SL1500_.jpg"))
					.minGameDuration(45)
					.maxGameDuration(60)
					.gameSet("...")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/244521/quacks-quedlinburg")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(5)
					.build();

			BoardGame boardGame22 = BoardGame.builder()
					.productName("Катан 2")
					.productDescription("Катан, раніше відомий як Поселенці Катану або просто Поселенці, - це багатокористувацька настільна гра, створена Клаусом Тойбером, і вперше опублікована в 1995 році в Німеччині фірмою Franckh-Kosmos Verlag (Kosmos) під назвою Die Siedler von Catan.")
					.productPrice(new BigDecimal(100))
					.manufacturer("Kosmos")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КАТАН")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://www.catan.com/sites/default/files/2021-08/3DBox_CATAN_w_b.jpg"))
					.minGameDuration(60)
					.maxGameDuration(120)
					.gameSet("Коробка для гри, ігрова дошка, 2 кубики, правила гри та альманах.")
					.minPlayers(3)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(1L).get(), boardGameMechanicRepository.findById(2L).get(), boardGameMechanicRepository.findByMechanicName("Розміщення плиток").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/13/catan")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(5)
					.build();

			BoardGame boardGame23 = BoardGame.builder()
					.productName("Квиток на поїзд 2")
					.productDescription("Квиток на поїзд - це залізнична настільна гра в німецькому стилі, розроблена Аланом Р. Муном. Ілюстровано Жульєном Дельвалем та Сіріль Дожаном, опублікована в 2004 році Days of Wonder.")
					.productPrice(new BigDecimal("10.99"))
					.manufacturer("Days of Wonder")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КНП")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91YNJM4oyhL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("Ігрова дошка, 240 кольорових вагончиків, 110 карток вагончиків, 30 квитків на маршрут, 1 підсумкова карта, 5 дерев'яних позначок для підрахунку очок, та правила гри і альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(3L).get(), boardGameMechanicRepository.findById(4L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/9209/ticket-ride")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			BoardGame boardGame24 = BoardGame.builder()
					.productName("Діксіт 2")
					.productDescription("Діксіт - це карточна гра, створена Жан-Луї Рубіра, та опублікована Libellud. Використовуючи колоду карток з мрійливими зображеннями, гравці вибирають картки, які відповідають заголовку, запропонованому 'розповідачем', і намагаються вгадати, яку карту 'розповідач' вибрав.")
					.productPrice(new BigDecimal("45.50"))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ДІКСІТ")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81KNlhEz06L.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("84 карти, 36 голосувальних фішок у 6 різних кольорах з номерами від 1 до 6, 6 дерев'яних фішок-зайців, 1 ігрова дошка, та правила гри і альманах.")
					.minPlayers(3)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(5L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/39856/dixit")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			BoardGame boardGame25 = BoardGame.builder()
					.productName("Містеріум 2")
					.productDescription("У 1920-х роках пан МакДауелл, обдарований астролог, відразу відчув надприродне існування, увійшовши в свій новий будинок у Шотландії. Він зібрав видатних медіумів свого часу для неймовірного спіритичного сеансу, і вони мають сім годин, щоб зв'язатися з привидом і дослідити будь-які ключові підказки, які він може надати для розкриття старої таємниці.")
					.productPrice(new BigDecimal("56.70"))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("МІСТЕРІУМ")
					.productQuantityInStock(4)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/61FDE9dJlyL.__AC_SX300_SY300_QL70_FMwebp_.jpg"))
					.minGameDuration(42)
					.maxGameDuration(42)
					.gameSet("1 ігрова дошка, 6 інтуітивних карт, 6 конвертів, 6 маркерів рівня ясновидіння, 36 фішок ясновидіння, 4 дошки прогресу, 18 картах персонажів-психіків, 18 картах локацій-психіків, 18 картах об'єктів-психіків, 1 пісковий таймер, 18 картах персонажів-привидів, 18 картах локацій-привидів, 18 картах об'єктів-привидів, 6 фішок-привидів, 84 карти видінь, 6 фішок винних, 3 маркери ворон, 1 доріжка ясновидіння, 1 фішка ясновидіння, і правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(7)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Жахи").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/181304/mysterium")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(6)
					.build();

			BoardGame boardGame26 = BoardGame.builder()
					.productName("Король Токіо 2")
					.productDescription("У Королі Токіо ви граєте за мутантних монстрів, гігантських роботів і дивних інопланетян, які всі руйнують Токіо і б'ють один одного, щоб стати єдиним і неповторним Королем Токіо.")
					.productPrice(new BigDecimal("35.30"))
					.manufacturer("Ієлло")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КОТ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81fVDF-Gb8L.jpg"))
					.minGameDuration(50)
					.maxGameDuration(50)
					.gameSet("1 ігрова дошка, 6 дошок монстрів, 6 фішок монстрів з пластиковими підставками, 8 кубиків з шістьма гранями, 50 енергетичних кубиків, 66 карт, і правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Бійка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/70323/king-tokyo")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(8)
					.build();

			BoardGame boardGame27 = BoardGame.builder()
					.productName("7 чудес: Дуель 2")
					.productDescription("У багатьох аспектах 7 чудес: Дуель нагадує свою материнську гру 7 чудес, де протягом трьох епох гравці отримують карти, які забезпечують ресурси або сприяють їхньому військовому чи науковому розвитку, щоб розвинути цивілізацію та здійснити чудеса. Те, що робить 7 чудес: Дуель унікальною, це те, що гра призначена лише для двох гравців, які не вибирають карти одночасно з рук, але з виставки карток, розташованих обличчям вниз і вгору на початку раунду. Гравець може взяти карту лише тоді, коли вона не перекрита жодною іншою, тому тут важливий час, а також бонусні ходи, які дозволяють вам взяти другу карту відразу.")
					.productPrice(new BigDecimal("35.10"))
					.manufacturer("Repos Production")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("7ЧУДЕСДУЕЛЬ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://cdn.svc.asmodee.net/production-rprod/storage/games/7-wonders-duel/57du-3dmockup-1597826159KUURt.png"))
					.minGameDuration(40)
					.maxGameDuration(50)
					.gameSet("1 ігрова дошка, 23 карти І епохи, 23 карти ІІ епохи, 20 карт ІІІ епохи, 7 карт Гільдій, 12 карт Чудес, 4 військові фішки, 10 фішок Прогресу, 1 фішка Конфлікту, 31 монета, 1 записник результатів, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(2)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Вибір карт").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/173346/7-wonders-duel")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(6)
					.build();

			BoardGame boardGame28 = BoardGame.builder()
					.productName("Домініон 2")
					.productDescription("У Домініоні кожен гравець починає з ідентичної, дуже маленької колоди карт. У центрі столу розміщений вибір інших карт, які гравці можуть «купувати», якщо вони можуть їх собі дозволити. Шляхом вибору карт для покупки та того, як вони грають свої руки, коли вони їх витягують, гравці створюють свою колоду на льоту, прагнучи до найефективнішого шляху до дорогоцінних очок перемоги до кінця гри.")
					.productPrice(new BigDecimal("120"))
					.manufacturer("Rio Grande Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ДОМІНІОН")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_0591f27c-12d2-46bc-ba08-5d2a50136de7?wid=488&hei=488&fmt=pjpeg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("500 карт, 1 картка сміття, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/36218/dominion")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(6)
					.build();


			BoardGame boardGame29 = BoardGame.builder()
					.productName("Тераформування Марса 2")
					.productDescription("У 2400-х роках людство починає терраформувати планету Марс. Великі корпорації, які спонсує Всесвітній уряд на Землі, ініціюють великі проекти, щоб підняти температуру, рівень кисню та океанське покриття, поки середовище не стане придатним для життя. У Тераформуванні Марса ви граєте за одну з цих корпорацій і спільно працюєте над терраформуванням, але конкуруєте за отримання очок перемоги, які видаються не тільки за ваш внесок у терраформування, але й за розвиток інфраструктури людства по всьому Сонячному променю, а також за роблення інших похвальних речей.")
					.productPrice(new BigDecimal("45"))
					.manufacturer("FryxGames")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ТЕРАФОРМУВАННЯМАРСА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/713hLG3OkaL._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(120)
					.maxGameDuration(180)
					.gameSet("1 ігрова дошка, 200 ігрових маркерів, 5 карток корпорацій, 208 карток проектів, 8 карток-посібників, 60 карток дій, 11 нейтральних карток, 1 жетон першого гравця, 1 жетон покоління, 1 жетон терраформування, 1 жетон кисню, 1 жетон температури, 1 жетон океану, 1 правила гри та альманах.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Управління рукою").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/167791/terraforming-mars")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(18)
					.build();

			BoardGame boardGame30 = BoardGame.builder()
					.productName("Сайт 2")
					.productDescription("Сайт - це інженерна, асиметрична, конкурентоспроможна настільна гра, розташована в альтернативній історії 1920-х років. Це час фермерства та війни, розбитих сердець та ржавих шестерень, інновацій та доблесті. У Сайті кожен гравець представляє персонажа з однієї з п'яти фракцій Східної Європи, які намагаються заробити своє багатство та заявити свою частку в землі навколо таємничої фабрики. Гравці завойовують територію, наймають нових найманців, збирають ресурси, отримують селян, будують споруди та активують потужні механізми.")
					.productPrice(new BigDecimal("105"))
					.manufacturer("Stonemaier Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("САЙТ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3tJzVAoYnx9FYCs3bUE3nv7VLnyr5CNot8A&usqp=CAU"))
					.minGameDuration(90)
					.maxGameDuration(115)
					.gameSet("1 ігрова дошка, 1 лист досягнень, 5 жетонів дій, 5 жетонів популярності, 5 жетонів енергії, 5 жетонів зірок, 30 спеціальних дерев'яних ресурсних жетонів, 20 спеціальних дерев'яних жетонів будівель, 20 фішок, 20 спеціальних дерев'яних кубиків технологій, 12 спеціальних дерев'яних фішок зустрічей, 6 бонусних плиток будівель, 6 карток фабрики, 2 кільця енергії, 5 карток швидкого посилання, 31 картка Автома, 28 карток зустрічей, 23 картки цілей, 42 картки бою, 12 карток фабрики, 25 карток досягнень, 5 матів фракцій, 5 спеціальних дерев'яних жетонів дій, 5 спеціальних дерев'яних матів гравців, 5 спеціальних дерев'яних жетонів гравців, 80 спеціальних дерев'яних монет, 11 карток дій, 2 спеціальні кубики, 1 правила гри та альманах.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/169786/scythe")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(14)
					.build();

			BoardGame boardGame31 = BoardGame.builder()
					.productName("Місто Кінгів 2")
					.productDescription("Місто Кінгів - це настільна стратегічна гра, в якій ви граєте за одного з п'яти родів, які змагаються за владу в Імперії. Використовуючи таланти своїх підданих, ви збираєте ресурси, відкриваєте нові регіони та робите все можливе, щоб здобути підтримку лордів у вашій боротьбі за престол. Місто Кінгів - це гра про стратегію, планування та впевнений крок у майбутнє.")
					.productPrice(new BigDecimal("80"))
					.manufacturer("Лібеллуд")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("МІСТОКІНГІВ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/71Xq1ZNYJ8L._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(60)
					.maxGameDuration(120)
					.gameSet("1 ігрова дошка, 5 дошок родів, 5 дошок лордів, 25 карток лордів, 25 карток регіонів, 5 карток родів, 90 карток талантів, 25 карток підтримки, 25 карток будівель, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/155426/city-kings")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();


			BoardGame boardGame32 = BoardGame.builder()
					.productName("Терра Містика 2")
					.productDescription("Терра Містика - гра з дуже малою кількістю вдачі, яка винагороджує стратегічне планування. Кожен гравець керує однією з 14 груп. З дотепністю та майстерністю гравець повинен намагатися володіти якомога більшою територією та розвивати навички цієї групи. Також є чотири релігійні культи, в яких ви можете просуватися. Для цього кожна група має спеціальні навички та здібності.")
					.productPrice(new BigDecimal("115"))
					.manufacturer("Feuerland Spiele")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ТЕРРАМІСТИКА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81dqL2OtjQL._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(60)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 28 плиток ландшафту, 56 жетонів структур, 70 жетонів робочих, 65 монет, 1 жетон першого гравця, 17 жетонів дій, 10 карток міст, 8 бонусних карток, 4 дошки культів, 1 бонусна плитка культу, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/120677/terra-mystica")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(18)
					.build();

			BoardGame boardGame33 = BoardGame.builder()
					.productName("Сутінкове Імперіум (Четверте видання) 2")
					.productDescription("Сутінкове Імперіум (Четверте видання) - це гра про галактичне завоювання, у якій від трьох до шести гравців виступають у ролі однієї з сімнадцяти фракцій, які прагнуть до галактичного панування через військову могутність, політичні маневри та економічні переговори.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Fantasy Flight Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("СУТІНКОВЕІМПЕРІУМ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_e903067a-c9d5-46bd-91e3-791e6c0af016?wid=488&hei=488&fmt=pjpeg"))
					.minGameDuration(240)
					.maxGameDuration(480)
					.gameSet("1 ігрова дошка, 17 аркушів фракцій, 6 командних аркушів, 51 системна плитка, 562 карти, 510 пластикових одиниць, 80 пластикових винищувачів, 50 пластикових наземних військ, 40 пластикових споруд, 8 пластикових космічних доків, 60 пластикових одиниць ПДС, 1 правила гри та річний звіт.")
					.minPlayers(3)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/233078/twilight-imperium-fourth-edition")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();

			BoardGame boardGame34 = BoardGame.builder()
					.productName("Замки Бургундії 2")
					.productDescription("Гра відбувається в регіоні Бургундія високого середньовіччя Франції. Кожен гравець взяв на себе роль аристократа, початково керуючи малим князівством. Граючи, вони прагнуть будувати поселення та потужні замки, займатися торгівлею по річці, експлуатувати срібні шахти та використовувати знання мандрівників.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Ravensburger Spieleverlag GmbH")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ЗАМКИБУРГУНДІЇ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81ALqSnd1QL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(90)
					.gameSet("1 ігрова дошка, 6 дошок гравців, 9 кубиків, 1 набір з 16 дерев'яних фігур, 6 карток-резюме, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/84876/castles-burgundy")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(20)
					.build();


			BoardGame boardGame35 = BoardGame.builder()
					.productName("Пуерто Ріко 2")
					.productDescription("У Пуерто Ріко гравці приймають на себе ролі колоніальних губернаторів на острові Пуерто Ріко. Мета гри - набрати найбільше очок перемоги, відвантажуючи товари в Європу або будуючи будівлі.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Ravensburger Spieleverlag GmbH")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ПУЕРТОРІКО")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_0a8d7cfb-9b80-47d3-b549-9890b918731d"))
					.minGameDuration(90)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 5 дошок гравців, 9 кубиків, 1 набір з 16 дерев'яних фігур, 6 карток-резюме, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/3076/puerto-rico")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(20)
					.build();

			BoardGame boardGame36 = BoardGame.builder()
					.productName("Електромережа 2")
					.productDescription("Мета Електромережі - постачати електроенергією найбільше міст, коли мережа когось досягає попередньо визначеного розміру. У цьому новому виданні гравці позначають існуючі маршрути між містами для з'єднання, а потім ставлять проти один одного на аукціоні, щоб купити електростанції, які вони використовують для живлення своїх міст.")
					.productPrice(new BigDecimal(95))
					.manufacturer("Rio Grande Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ЕЛЕКТРОМЕРЕЖА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81cwzwEL2+L.jpg"))
					.minGameDuration(120)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 132 дерев'яних будинку, 84 дерев'яних жетони ресурсів, 1 ринкова дошка, 43 карти електростанцій, 1 карта 3 етапу, 1 карта 2 етапу, 1 карта 1 етапу, 1 оглядова карта, 1 карта початкового гравця, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Аукціон/Ліцитація").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/2651/power-grid")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(21)
					.build();

			BoardGame boardGame37 = BoardGame.builder()
					.productName("Agricola 2")
					.productDescription("У Agricola ви є фермером у дерев'яній хаті зі своєю дружиною та майже нічим більше. На ході у вас є можливість взяти лише дві дії, одну для себе та одну для дружини, з усіх можливостей, які ви знайдете на фермі: збирати глину, дерево або камінь; будувати паркани; і так далі. Можливо, ви подумаєте про те, щоб мати дітей, щоб отримати більше виконаної роботи, але спершу вам потрібно розширити свій будинок. А чим ви будете годувати всіх цих маленьких непосид?")
					.productPrice(new BigDecimal(95))
					.manufacturer("Lookout Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("AGRICOLA")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81qWMPD9+LL.jpg"))
					.minGameDuration(120)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 360 карток, 9 ігрових дошок, 1 блокнот для підрахунку, 1 тканинний мішечок, 1 правила гри та річний звіт.")
					.minPlayers(1)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/31260/agricola")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(21)
					.build();


			BoardGame boardGame38 = BoardGame.builder()
					.productName("7 Чудес 2")
					.productDescription("7 Чудес триває три епохи. У кожній епосі гравці отримують сім карток з певної колоди, обирають одну з цих карток, а потім передають решту сусідньому гравцю. Гравці одночасно відкривають свої картки, платять ресурси, якщо потрібно, або збирають ресурси або взаємодіють з іншими гравцями різними способами. (У гравців є індивідуальні дошки з особливими можливостями, на яких організувати свої картки, і дошки є двосторонніми). Кожен гравець потім обирає іншу картку з колоди, яку йому передали, і процес повторюється, поки у гравців є шість карток у грі з цієї епохи. Після трьох епох гра закінчується.")
					.productPrice(new BigDecimal(95))
					.manufacturer("Repos Production")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("7WONDERS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/710S0SyIpPL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("1 ігрова дошка, 7 дошок чудес, 7 карток чудес, 49 карток епохи І, 49 карток епохи II, 50 карток епохи III, 46 жетонів конфлікту, 24 монети номіналом 3, 46 монет номіналом 1, 1 брошура для підрахунку очок, 2 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(7)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/68448/7-wonders")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(7)
					.build();

			BoardGame boardGame39 = BoardGame.builder()
					.productName("Шифроназви 2")
					.productDescription("Два суперницьких розвідувача знають секретні ідентичності 25 агентів. Їхні товариші знають агентів лише по їхніх ШИФРОНАЗВАМ. У Шифроназвах дві команди змагаються, щоб першими зробити контакт із всіма своїми агентами. Розвідувачі дають однослівні підказки, які можуть вказувати на кілька слів на дошці. Їхні товариші намагаються вгадати слова правильного кольору, уникаючи тих, які належать суперницькій команді. І всі хочуть уникнути асасина.")
					.productPrice(new BigDecimal(75))
					.manufacturer("Czech Games Edition")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("CODENAMES")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/41feKk3dWdL.jpg"))
					.minGameDuration(15)
					.maxGameDuration(30)
					.gameSet("1 ігрова дошка, 200 карток, 40 ключових карток, 1 підставка для карт, 1 таймер, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(8)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Командна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/178900/codenames")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();

			BoardGame boardGame40 = BoardGame.builder()
					.productName("Особняки Божевілля: Друге Видання 2")
					.productDescription("Особняки Божевілля: Друге Видання - це настільна гра про дослідження надприродного, в якій 1-5 гравців беруть на себе ролі дослідників, які досліджують темні особняки, в яких відбуваються страшні події, щоб знайти відповіді на незвичайні речі, які відбуваються навколо них. Ці дослідники мають вижити в цих особняках, щоб розповісти історії про те, що відбувається, а потім зробити крок назад, щоб відповісти на питання, які ще залишилися без відповіді.")
					.productPrice(new BigDecimal(85))
					.manufacturer("Fantasy Flight Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("MANSIONSOFMADNESS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91ZGULlSMwS.jpg"))
					.minGameDuration(120)
					.maxGameDuration(180)
					.gameSet("1 ігрова дошка, 24 картки кімнат, 8 карток ініціативи, 24 картки сюжету, 16 карток об'єктів, 16 карт")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Жахи").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/205059/mansions-madness-second-edition")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(21)
					.build();

			BoardGame boardGame41 = BoardGame.builder()
					.productName("Крила 2")
					.productDescription("...")
					.productPrice(new BigDecimal(85))
					.manufacturer("Stonemaier Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("WINGSPAN")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://stonemaiergames.com/wp-content/uploads/2019/02/3d-wingspan.png"))
					.minGameDuration(40)
					.maxGameDuration(70)
					.gameSet("...")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/266192/wingspan")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(8)
					.build();

			BoardGame boardGame42 = BoardGame.builder()
					.productName("Шарлатани з Кведлінбурга 2")
					.productDescription("...")
					.productPrice(new BigDecimal(85))
					.manufacturer("North Star Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("QUACKSOFQUEDLINBURG")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91cR+taIA3S._AC_SL1500_.jpg"))
					.minGameDuration(45)
					.maxGameDuration(60)
					.gameSet("...")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/244521/quacks-quedlinburg")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();


			BoardGame boardGame43 = BoardGame.builder()
					.productName("Катан 3")
					.productDescription("Катан, раніше відомий як Поселенці Катану або просто Поселенці, - це багатокористувацька настільна гра, створена Клаусом Тойбером, і вперше опублікована в 1995 році в Німеччині фірмою Franckh-Kosmos Verlag (Kosmos) під назвою Die Siedler von Catan.")
					.productPrice(new BigDecimal(100))
					.manufacturer("Kosmos")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КАТАН")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://www.catan.com/sites/default/files/2021-08/3DBox_CATAN_w_b.jpg"))
					.minGameDuration(60)
					.maxGameDuration(120)
					.gameSet("Коробка для гри, ігрова дошка, 2 кубики, правила гри та альманах.")
					.minPlayers(3)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(1L).get(), boardGameMechanicRepository.findById(2L).get(), boardGameMechanicRepository.findByMechanicName("Розміщення плиток").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/13/catan")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(9)
					.build();

			BoardGame boardGame44 = BoardGame.builder()
					.productName("Квиток на поїзд 3")
					.productDescription("Квиток на поїзд - це залізнична настільна гра в німецькому стилі, розроблена Аланом Р. Муном. Ілюстровано Жульєном Дельвалем та Сіріль Дожаном, опублікована в 2004 році Days of Wonder.")
					.productPrice(new BigDecimal("10.99"))
					.manufacturer("Days of Wonder")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КНП")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91YNJM4oyhL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("Ігрова дошка, 240 кольорових вагончиків, 110 карток вагончиків, 30 квитків на маршрут, 1 підсумкова карта, 5 дерев'яних позначок для підрахунку очок, та правила гри і альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(3L).get(), boardGameMechanicRepository.findById(4L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/9209/ticket-ride")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			BoardGame boardGame45 = BoardGame.builder()
					.productName("Діксіт 3")
					.productDescription("Діксіт - це карточна гра, створена Жан-Луї Рубіра, та опублікована Libellud. Використовуючи колоду карток з мрійливими зображеннями, гравці вибирають картки, які відповідають заголовку, запропонованому 'розповідачем', і намагаються вгадати, яку карту 'розповідач' вибрав.")
					.productPrice(new BigDecimal("45.50"))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ДІКСІТ")
					.productQuantityInStock(10)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81KNlhEz06L.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("84 карти, 36 голосувальних фішок у 6 різних кольорах з номерами від 1 до 6, 6 дерев'яних фішок-зайців, 1 ігрова дошка, та правила гри і альманах.")
					.minPlayers(3)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findById(3L).get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findById(5L).get()))
					.BGGLink("https://boardgamegeek.com/boardgame/39856/dixit")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(8)
					.build();

			BoardGame boardGame46 = BoardGame.builder()
					.productName("Містеріум 3")
					.productDescription("У 1920-х роках пан МакДауелл, обдарований астролог, відразу відчув надприродне існування, увійшовши в свій новий будинок у Шотландії. Він зібрав видатних медіумів свого часу для неймовірного спіритичного сеансу, і вони мають сім годин, щоб зв'язатися з привидом і дослідити будь-які ключові підказки, які він може надати для розкриття старої таємниці.")
					.productPrice(new BigDecimal("56.70"))
					.manufacturer("Libellud")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("МІСТЕРІУМ")
					.productQuantityInStock(4)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/61FDE9dJlyL.__AC_SX300_SY300_QL70_FMwebp_.jpg"))
					.minGameDuration(42)
					.maxGameDuration(42)
					.gameSet("1 ігрова дошка, 6 інтуітивних карт, 6 конвертів, 6 маркерів рівня ясновидіння, 36 фішок ясновидіння, 4 дошки прогресу, 18 картах персонажів-психіків, 18 картах локацій-психіків, 18 картах об'єктів-психіків, 1 пісковий таймер, 18 картах персонажів-привидів, 18 картах локацій-привидів, 18 картах об'єктів-привидів, 6 фішок-привидів, 84 карти видінь, 6 фішок винних, 3 маркери ворон, 1 доріжка ясновидіння, 1 фішка ясновидіння, і правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(7)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Жахи").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/181304/mysterium")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(9)
					.build();

			BoardGame boardGame47 = BoardGame.builder()
					.productName("Король Токіо 3")
					.productDescription("У Королі Токіо ви граєте за мутантних монстрів, гігантських роботів і дивних інопланетян, які всі руйнують Токіо і б'ють один одного, щоб стати єдиним і неповторним Королем Токіо.")
					.productPrice(new BigDecimal("35.30"))
					.manufacturer("Ієлло")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("КОТ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81fVDF-Gb8L.jpg"))
					.minGameDuration(50)
					.maxGameDuration(50)
					.gameSet("1 ігрова дошка, 6 дошок монстрів, 6 фішок монстрів з пластиковими підставками, 8 кубиків з шістьма гранями, 50 енергетичних кубиків, 66 карт, і правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Бійка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/70323/king-tokyo")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(8)
					.build();

			BoardGame boardGame48 = BoardGame.builder()
					.productName("7 чудес: Дуель 3")
					.productDescription("У багатьох аспектах 7 чудес: Дуель нагадує свою материнську гру 7 чудес, де протягом трьох епох гравці отримують карти, які забезпечують ресурси або сприяють їхньому військовому чи науковому розвитку, щоб розвинути цивілізацію та здійснити чудеса. Те, що робить 7 чудес: Дуель унікальною, це те, що гра призначена лише для двох гравців, які не вибирають карти одночасно з рук, але з виставки карток, розташованих обличчям вниз і вгору на початку раунду. Гравець може взяти карту лише тоді, коли вона не перекрита жодною іншою, тому тут важливий час, а також бонусні ходи, які дозволяють вам взяти другу карту відразу.")
					.productPrice(new BigDecimal("35.10"))
					.manufacturer("Repos Production")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("7ЧУДЕСДУЕЛЬ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://cdn.svc.asmodee.net/production-rprod/storage/games/7-wonders-duel/57du-3dmockup-1597826159KUURt.png"))
					.minGameDuration(40)
					.maxGameDuration(50)
					.gameSet("1 ігрова дошка, 23 карти І епохи, 23 карти ІІ епохи, 20 карт ІІІ епохи, 7 карт Гільдій, 12 карт Чудес, 4 військові фішки, 10 фішок Прогресу, 1 фішка Конфлікту, 31 монета, 1 записник результатів, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(2)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Вибір карт").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/173346/7-wonders-duel")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(11)
					.build();

			BoardGame boardGame49 = BoardGame.builder()
					.productName("Домініон 3")
					.productDescription("У Домініоні кожен гравець починає з ідентичної, дуже маленької колоди карт. У центрі столу розміщений вибір інших карт, які гравці можуть «купувати», якщо вони можуть їх собі дозволити. Шляхом вибору карт для покупки та того, як вони грають свої руки, коли вони їх витягують, гравці створюють свою колоду на льоту, прагнучи до найефективнішого шляху до дорогоцінних очок перемоги до кінця гри.")
					.productPrice(new BigDecimal("120"))
					.manufacturer("Rio Grande Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ДОМІНІОН")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_0591f27c-12d2-46bc-ba08-5d2a50136de7?wid=488&hei=488&fmt=pjpeg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("500 карт, 1 картка сміття, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/36218/dominion")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(11)
					.build();


			BoardGame boardGame50 = BoardGame.builder()
					.productName("Тераформування Марса 3")
					.productDescription("У 2400-х роках людство починає терраформувати планету Марс. Великі корпорації, які спонсує Всесвітній уряд на Землі, ініціюють великі проекти, щоб підняти температуру, рівень кисню та океанське покриття, поки середовище не стане придатним для життя. У Тераформуванні Марса ви граєте за одну з цих корпорацій і спільно працюєте над терраформуванням, але конкуруєте за отримання очок перемоги, які видаються не тільки за ваш внесок у терраформування, але й за розвиток інфраструктури людства по всьому Сонячному променю, а також за роблення інших похвальних речей.")
					.productPrice(new BigDecimal("45"))
					.manufacturer("FryxGames")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ТЕРАФОРМУВАННЯМАРСА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/713hLG3OkaL._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(120)
					.maxGameDuration(180)
					.gameSet("1 ігрова дошка, 200 ігрових маркерів, 5 карток корпорацій, 208 карток проектів, 8 карток-посібників, 60 карток дій, 11 нейтральних карток, 1 жетон першого гравця, 1 жетон покоління, 1 жетон терраформування, 1 жетон кисню, 1 жетон температури, 1 жетон океану, 1 правила гри та альманах.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Управління рукою").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/167791/terraforming-mars")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(12)
					.build();

			BoardGame boardGame51 = BoardGame.builder()
					.productName("Сайт 3")
					.productDescription("Сайт - це інженерна, асиметрична, конкурентоспроможна настільна гра, розташована в альтернативній історії 1920-х років. Це час фермерства та війни, розбитих сердець та ржавих шестерень, інновацій та доблесті. У Сайті кожен гравець представляє персонажа з однієї з п'яти фракцій Східної Європи, які намагаються заробити своє багатство та заявити свою частку в землі навколо таємничої фабрики. Гравці завойовують територію, наймають нових найманців, збирають ресурси, отримують селян, будують споруди та активують потужні механізми.")
					.productPrice(new BigDecimal("105"))
					.manufacturer("Stonemaier Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("САЙТ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3tJzVAoYnx9FYCs3bUE3nv7VLnyr5CNot8A&usqp=CAU"))
					.minGameDuration(90)
					.maxGameDuration(115)
					.gameSet("1 ігрова дошка, 1 лист досягнень, 5 жетонів дій, 5 жетонів популярності, 5 жетонів енергії, 5 жетонів зірок, 30 спеціальних дерев'яних ресурсних жетонів, 20 спеціальних дерев'яних жетонів будівель, 20 фішок, 20 спеціальних дерев'яних кубиків технологій, 12 спеціальних дерев'яних фішок зустрічей, 6 бонусних плиток будівель, 6 карток фабрики, 2 кільця енергії, 5 карток швидкого посилання, 31 картка Автома, 28 карток зустрічей, 23 картки цілей, 42 картки бою, 12 карток фабрики, 25 карток досягнень, 5 матів фракцій, 5 спеціальних дерев'яних жетонів дій, 5 спеціальних дерев'яних матів гравців, 5 спеціальних дерев'яних жетонів гравців, 80 спеціальних дерев'яних монет, 11 карток дій, 2 спеціальні кубики, 1 правила гри та альманах.")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/169786/scythe")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(14)
					.build();

			BoardGame boardGame52 = BoardGame.builder()
					.productName("Місто Кінгів 3")
					.productDescription("Місто Кінгів - це настільна стратегічна гра, в якій ви граєте за одного з п'яти родів, які змагаються за владу в Імперії. Використовуючи таланти своїх підданих, ви збираєте ресурси, відкриваєте нові регіони та робите все можливе, щоб здобути підтримку лордів у вашій боротьбі за престол. Місто Кінгів - це гра про стратегію, планування та впевнений крок у майбутнє.")
					.productPrice(new BigDecimal("80"))
					.manufacturer("Лібеллуд")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("МІСТОКІНГІВ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/71Xq1ZNYJ8L._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(60)
					.maxGameDuration(120)
					.gameSet("1 ігрова дошка, 5 дошок родів, 5 дошок лордів, 25 карток лордів, 25 карток регіонів, 5 карток родів, 90 карток талантів, 25 карток підтримки, 25 карток будівель, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/155426/city-kings")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();


			BoardGame boardGame53 = BoardGame.builder()
					.productName("Терра Містика 3")
					.productDescription("Терра Містика - гра з дуже малою кількістю вдачі, яка винагороджує стратегічне планування. Кожен гравець керує однією з 14 груп. З дотепністю та майстерністю гравець повинен намагатися володіти якомога більшою територією та розвивати навички цієї групи. Також є чотири релігійні культи, в яких ви можете просуватися. Для цього кожна група має спеціальні навички та здібності.")
					.productPrice(new BigDecimal("115"))
					.manufacturer("Feuerland Spiele")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ТЕРРАМІСТИКА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81dqL2OtjQL._AC_UF894,1000_QL80_.jpg"))
					.minGameDuration(60)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 28 плиток ландшафту, 56 жетонів структур, 70 жетонів робочих, 65 монет, 1 жетон першого гравця, 17 жетонів дій, 10 карток міст, 8 бонусних карток, 4 дошки культів, 1 бонусна плитка культу, 1 правила гри та альманах.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Фентезі").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/120677/terra-mystica")
					.gameLanguage(BoardGameLanguage.RUSSIAN)
					.minAge(12)
					.build();

			BoardGame boardGame54 = BoardGame.builder()
					.productName("Сутінкове Імперіум (Четверте видання) 3")
					.productDescription("Сутінкове Імперіум (Четверте видання) - це гра про галактичне завоювання, у якій від трьох до шести гравців виступають у ролі однієї з сімнадцяти фракцій, які прагнуть до галактичного панування через військову могутність, політичні маневри та економічні переговори.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Fantasy Flight Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("СУТІНКОВЕІМПЕРІУМ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_e903067a-c9d5-46bd-91e3-791e6c0af016?wid=488&hei=488&fmt=pjpeg"))
					.minGameDuration(240)
					.maxGameDuration(480)
					.gameSet("1 ігрова дошка, 17 аркушів фракцій, 6 командних аркушів, 51 системна плитка, 562 карти, 510 пластикових одиниць, 80 пластикових винищувачів, 50 пластикових наземних військ, 40 пластикових споруд, 8 пластикових космічних доків, 60 пластикових одиниць ПДС, 1 правила гри та річний звіт.")
					.minPlayers(3)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Наукова фантастика").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Конструювання колоди").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/233078/twilight-imperium-fourth-edition")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();

			BoardGame boardGame55 = BoardGame.builder()
					.productName("Замки Бургундії 3")
					.productDescription("Гра відбувається в регіоні Бургундія високого середньовіччя Франції. Кожен гравець взяв на себе роль аристократа, початково керуючи малим князівством. Граючи, вони прагнуть будувати поселення та потужні замки, займатися торгівлею по річці, експлуатувати срібні шахти та використовувати знання мандрівників.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Ravensburger Spieleverlag GmbH")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ЗАМКИБУРГУНДІЇ")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81ALqSnd1QL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(90)
					.gameSet("1 ігрова дошка, 6 дошок гравців, 9 кубиків, 1 набір з 16 дерев'яних фігур, 6 карток-резюме, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/84876/castles-burgundy")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();


			BoardGame boardGame56 = BoardGame.builder()
					.productName("Пуерто Ріко 3")
					.productDescription("У Пуерто Ріко гравці приймають на себе ролі колоніальних губернаторів на острові Пуерто Ріко. Мета гри - набрати найбільше очок перемоги, відвантажуючи товари в Європу або будуючи будівлі.")
					.productPrice(new BigDecimal(120))
					.manufacturer("Ravensburger Spieleverlag GmbH")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ПУЕРТОРІКО")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://target.scene7.com/is/image/Target/GUEST_0a8d7cfb-9b80-47d3-b549-9890b918731d"))
					.minGameDuration(90)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 5 дошок гравців, 9 кубиків, 1 набір з 16 дерев'яних фігур, 6 карток-резюме, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Абстрактна стратегія").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кидання кубиків").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/3076/puerto-rico")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();

			BoardGame boardGame57 = BoardGame.builder()
					.productName("Електромережа 3")
					.productDescription("Мета Електромережі - постачати електроенергією найбільше міст, коли мережа когось досягає попередньо визначеного розміру. У цьому новому виданні гравці позначають існуючі маршрути між містами для з'єднання, а потім ставлять проти один одного на аукціоні, щоб купити електростанції, які вони використовують для живлення своїх міст.")
					.productPrice(new BigDecimal(95))
					.manufacturer("Rio Grande Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("ЕЛЕКТРОМЕРЕЖА")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81cwzwEL2+L.jpg"))
					.minGameDuration(120)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 132 дерев'яних будинку, 84 дерев'яних жетони ресурсів, 1 ринкова дошка, 43 карти електростанцій, 1 карта 3 етапу, 1 карта 2 етапу, 1 карта 1 етапу, 1 оглядова карта, 1 карта початкового гравця, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(6)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Аукціон/Ліцитація").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/2651/power-grid")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();

			BoardGame boardGame58 = BoardGame.builder()
					.productName("Agricola 3")
					.productDescription("У Agricola ви є фермером у дерев'яній хаті зі своєю дружиною та майже нічим більше. На ході у вас є можливість взяти лише дві дії, одну для себе та одну для дружини, з усіх можливостей, які ви знайдете на фермі: збирати глину, дерево або камінь; будувати паркани; і так далі. Можливо, ви подумаєте про те, щоб мати дітей, щоб отримати більше виконаної роботи, але спершу вам потрібно розширити свій будинок. А чим ви будете годувати всіх цих маленьких непосид?")
					.productPrice(new BigDecimal(95))
					.manufacturer("Lookout Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("AGRICOLA")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/81qWMPD9+LL.jpg"))
					.minGameDuration(120)
					.maxGameDuration(150)
					.gameSet("1 ігрова дошка, 360 карток, 9 ігрових дошок, 1 блокнот для підрахунку, 1 тканинний мішечок, 1 правила гри та річний звіт.")
					.minPlayers(1)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/31260/agricola")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(12)
					.build();


			BoardGame boardGame59 = BoardGame.builder()
					.productName("7 Чудес 3")
					.productDescription("7 Чудес триває три епохи. У кожній епосі гравці отримують сім карток з певної колоди, обирають одну з цих карток, а потім передають решту сусідньому гравцю. Гравці одночасно відкривають свої картки, платять ресурси, якщо потрібно, або збирають ресурси або взаємодіють з іншими гравцями різними способами. (У гравців є індивідуальні дошки з особливими можливостями, на яких організувати свої картки, і дошки є двосторонніми). Кожен гравець потім обирає іншу картку з колоди, яку йому передали, і процес повторюється, поки у гравців є шість карток у грі з цієї епохи. Після трьох епох гра закінчується.")
					.productPrice(new BigDecimal(95))
					.manufacturer("Repos Production")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("7WONDERS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/710S0SyIpPL.jpg"))
					.minGameDuration(30)
					.maxGameDuration(60)
					.gameSet("1 ігрова дошка, 7 дошок чудес, 7 карток чудес, 49 карток епохи І, 49 карток епохи II, 50 карток епохи III, 46 жетонів конфлікту, 24 монети номіналом 3, 46 монет номіналом 1, 1 брошура для підрахунку очок, 2 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(7)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Економіка").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Розміщення робітників").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/68448/7-wonders")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(10)
					.build();

			BoardGame boardGame60 = BoardGame.builder()
					.productName("Шифроназви 3")
					.productDescription("Два суперницьких розвідувача знають секретні ідентичності 25 агентів. Їхні товариші знають агентів лише по їхніх ШИФРОНАЗВАМ. У Шифроназвах дві команди змагаються, щоб першими зробити контакт із всіма своїми агентами. Розвідувачі дають однослівні підказки, які можуть вказувати на кілька слів на дошці. Їхні товариші намагаються вгадати слова правильного кольору, уникаючи тих, які належать суперницькій команді. І всі хочуть уникнути асасина.")
					.productPrice(new BigDecimal(75))
					.manufacturer("Czech Games Edition")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("CODENAMES")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/41feKk3dWdL.jpg"))
					.minGameDuration(15)
					.maxGameDuration(30)
					.gameSet("1 ігрова дошка, 200 карток, 40 ключових карток, 1 підставка для карт, 1 таймер, 1 правила гри та річний звіт.")
					.minPlayers(2)
					.maxPlayers(8)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Командна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/178900/codenames")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(14)
					.build();

			BoardGame boardGame61 = BoardGame.builder()
					.productName("Особняки Божевілля: Друге Видання 3")
					.productDescription("Особняки Божевілля: Друге Видання - це настільна гра про дослідження надприродного, в якій 1-5 гравців беруть на себе ролі дослідників, які досліджують темні особняки, в яких відбуваються страшні події, щоб знайти відповіді на незвичайні речі, які відбуваються навколо них. Ці дослідники мають вижити в цих особняках, щоб розповісти історії про те, що відбувається, а потім зробити крок назад, щоб відповісти на питання, які ще залишилися без відповіді.")
					.productPrice(new BigDecimal(85))
					.manufacturer("Fantasy Flight Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("MANSIONSOFMADNESS")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91ZGULlSMwS.jpg"))
					.minGameDuration(120)
					.maxGameDuration(180)
					.gameSet("1 ігрова дошка, 24 картки кімнат, 8 карток ініціативи, 24 картки сюжету, 16 карток об'єктів, 16 карт")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Жахи").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/205059/mansions-madness-second-edition")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(13)
					.build();

			BoardGame boardGame62 = BoardGame.builder()
					.productName("Крила 3")
					.productDescription("...")
					.productPrice(new BigDecimal(85))
					.manufacturer("Stonemaier Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("WINGSPAN")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://stonemaiergames.com/wp-content/uploads/2019/02/3d-wingspan.png"))
					.minGameDuration(40)
					.maxGameDuration(70)
					.gameSet("...")
					.minPlayers(1)
					.maxPlayers(5)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/266192/wingspan")
					.gameLanguage(BoardGameLanguage.UKRAINIAN)
					.minAge(10)
					.build();

			BoardGame boardGame63 = BoardGame.builder()
					.productName("Шарлатани з Кведлінбурга 3")
					.productDescription("...")
					.productPrice(new BigDecimal(85))
					.manufacturer("North Star Games")
					.productCategory(ProductCategory.BOARD_GAMES)
					.productCode("QUACKSOFQUEDLINBURG")
					.productQuantityInStock(2)
					.productImageURLs(Set.of("https://m.media-amazon.com/images/I/91cR+taIA3S._AC_SL1500_.jpg"))
					.minGameDuration(45)
					.maxGameDuration(60)
					.gameSet("...")
					.minPlayers(2)
					.maxPlayers(4)
					.gameGenres(Set.of(boardGameGenreRepository.findByGenreName("Пригоди").get()))
					.gameMechanics(Set.of(boardGameMechanicRepository.findByMechanicName("Кооперативна гра").get()))
					.BGGLink("https://boardgamegeek.com/boardgame/244521/quacks-quedlinburg")
					.gameLanguage(BoardGameLanguage.ENGLISH)
					.minAge(10)
					.build();

			boardGameRepository.saveAll(List.of(boardGame1, boardGame2, boardGame3, boardGame4, boardGame5, boardGame6, boardGame7, boardGame8, boardGame9, boardGame10, boardGame11, boardGame12, boardGame13, boardGame14, boardGame15, boardGame16, boardGame17, boardGame18, boardGame19, boardGame20, boardGame21, boardGame22, boardGame23, boardGame24, boardGame25, boardGame26, boardGame27, boardGame28, boardGame29, boardGame30, boardGame31, boardGame32, boardGame33, boardGame34, boardGame35, boardGame36, boardGame37, boardGame38, boardGame39, boardGame40, boardGame41, boardGame42, boardGame43, boardGame44, boardGame45, boardGame46, boardGame47, boardGame48, boardGame49, boardGame50, boardGame51, boardGame52, boardGame53, boardGame54, boardGame55, boardGame56, boardGame57, boardGame58, boardGame59, boardGame60, boardGame61, boardGame62, boardGame63));

			//-------------------shoppingCart-------------------
			ShoppingCart cart1 = adminProfile.getShoppingCart();
			cart1.getProductsInShoppingCart().add(new ProductInShoppingCart(boardGame1, cart1, 1));

			shoppingCartRepository.save(cart1);

			LOGGER.debug("test");


		};
	}
}
