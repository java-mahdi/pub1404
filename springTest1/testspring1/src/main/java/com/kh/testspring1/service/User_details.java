package com.kh.testspring1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.testspring1.application.Initializer;
import com.kh.testspring1.model.UserModel;
import com.kh.testspring1.repository.UserRepository;

@Service
public class User_details  implements UserDetailsService{ 
   
    @Autowired
    UserRepository userRepository;
   @Autowired
   Initializer initializer;
    


    public User_details() {
    }




    @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
       UserModel user_ =   userRepository.findByUsername(username)
    .orElseThrow(() -> new UsernameNotFoundException("user name not found!"));
    
    return 
         org.springframework.security.core.userdetails.User
         .withUsername(username)
         .password(user_.getPassword())
         .roles(String.join(",", user_.getRoles().stream().map(r -> r.getName()).toList() ))
         .build(); 
   
   }

}
