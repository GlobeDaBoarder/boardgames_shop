package ua.rivnegray.boardgames_shop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.rivnegray.boardgames_shop.mapper.UserMapper;
import ua.rivnegray.boardgames_shop.repository.AddressRepository;
import ua.rivnegray.boardgames_shop.repository.UserRepository;
import ua.rivnegray.boardgames_shop.repository.UserRoleRepository;
import ua.rivnegray.boardgames_shop.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private  UserRoleRepository userRoleRepository;
    @Mock
    private  AddressRepository userAddressRepository;
    @Mock
    private  UserMapper userMapper;
    @Mock
    private  PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImplUnderTest;


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
