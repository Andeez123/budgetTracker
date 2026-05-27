package com.andrew.budgetTracker.Service;

import com.andrew.budgetTracker.Exceptions.UserNotFoundException;
import com.andrew.budgetTracker.model.User;
import com.andrew.budgetTracker.model.UserPrincipal;
import com.andrew.budgetTracker.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmailAddress(username).orElseThrow(() -> new UserNotFoundException());
        return new UserPrincipal(user);
    }
}
