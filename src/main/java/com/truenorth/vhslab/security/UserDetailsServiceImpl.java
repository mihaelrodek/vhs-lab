package com.truenorth.vhslab.security;

import com.truenorth.vhslab.entities.User;
import com.truenorth.vhslab.models.Role;
import com.truenorth.vhslab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return build(user);
    }

    public UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();


        authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
        

        return new UserDetailsImpl(user.getUser_id(), user.getUsername(), user.getPassword(), authorities);
    }

}

