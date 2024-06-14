package com.rca.mySecurity.services;

import com.rca.mySecurity.entity.UserData;
import com.rca.mySecurity.repository.UserDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataService implements UserDetailsService {
    @Autowired
    private UserDataRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> userData = repository.findByEmail(username);
        //Convert userData to UserDetails
        return userData.map(UserDataDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
    public UserData loadCurrentUser(String username) throws UsernameNotFoundException {
        Optional<UserData> userDetail = repository.findByEmail(username);
        return userDetail
                .orElseThrow(() -> new UsernameNotFoundException("User not found " +
                        username));
    }
    public String addUser(UserData userData) {
        userData.setPassword(encoder.encode(userData.getPassword()));
        repository.save(userData);
        return "User Added Successfully";
    }
    public List<UserData> listAll() {
        return repository.findAll();
    }
}

