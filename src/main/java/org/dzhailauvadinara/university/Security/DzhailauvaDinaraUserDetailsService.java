package org.dzhailauvadinara.university.Security;

import lombok.RequiredArgsConstructor;
import org.dzhailauvadinara.university.entity.DzhailauvaDinaraUser;
import org.dzhailauvadinara.university.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class DzhailauvaDinaraUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DzhailauvaDinaraUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}