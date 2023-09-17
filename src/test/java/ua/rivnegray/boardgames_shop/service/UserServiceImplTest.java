package ua.rivnegray.boardgames_shop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.repository.AddressRepository;
import ua.rivnegray.boardgames_shop.repository.UserProfileRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private  UserProfileRepository userProfileRepository;
    @Mock
    private  UserRoleRepository userRoleRepository;
    @Mock
    private  AddressRepository userAddressRepository;
    @Mock
    private  UserMapper userMapper;
    @Mock
    private  PasswordEncoder passwordEncoder;

    private  UserServiceImpl userServiceImplUnderTest;


    @BeforeEach
    void setUp() {
        this.userServiceImplUnderTest = new UserServiceImpl(userProfileRepository, userRoleRepository, userMapper, passwordEncoder, userAddressRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllUsersPublicInfo() {

    }

    @Test
    void testGetUserPublicInfoById() {
    }

    @Test
    void testCreateSpecifiedUser() {
    }

    @Test
    void testCreateCustomerUser() {
    }

    @Test
    void testUpdateFullUserProfile() {
    }

    @Test
    void testUpdateUsername() {
    }

    @Test
    void testUpdatePassword() {
    }

    @Test
    void testUpdateEmail() {
    }

    @Test
    void testUpdatePhone() {
    }

    @Test
    void testUpdateAddress() {
    }

    @Test
    void testAddAddress() {
    }

    @Test
    void testRemoveAddress() {
    }

    @Test
    void testDeleteUser() {
    }
}
