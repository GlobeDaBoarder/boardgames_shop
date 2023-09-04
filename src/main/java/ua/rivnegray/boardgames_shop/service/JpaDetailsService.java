package ua.rivnegray.boardgames_shop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.rivnegray.boardgames_shop.model.SecurityUser;
import ua.rivnegray.boardgames_shop.repository.UserCredentialsRepository;

@Service
public class JpaDetailsService implements UserDetailsService {
    private final UserCredentialsRepository userCredentialsRepository;

    @Autowired
    JpaDetailsService(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCredentialsRepository
                .findByUsername(username)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
