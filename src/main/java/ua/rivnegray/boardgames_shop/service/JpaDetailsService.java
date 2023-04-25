package ua.rivnegray.boardgames_shop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.model.user.SecurityUser;
import ua.rivnegray.boardgames_shop.repository.UserRepository;

@Service
public class JpaDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public JpaDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
