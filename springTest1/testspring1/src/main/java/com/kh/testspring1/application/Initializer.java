package com.kh.testspring1.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.kh.testspring1.model.Role;
import com.kh.testspring1.model.UserModel;
import com.kh.testspring1.repository.RoleRepository;
import com.kh.testspring1.repository.UserRepository;

@Component
public class Initializer implements CommandLineRunner {

@Autowired
UserRepository userRepository;
@Autowired
RoleRepository roleRepository;
@Autowired
 BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String admin_role_name = Consts.roles.ADMIN.name();
        String guest_role_name = Consts.roles.GUEST.name();

        String admin_user_username = Consts.defaultUsers.admin.name();
        String guest_user_username = Consts.defaultUsers.guest.name();

        Role admin_role= roleRepository.findByName(admin_role_name)
        .orElseGet(() -> roleRepository.save(new Role(admin_role_name)));

        Role guest_role= roleRepository.findByName(guest_role_name)
        .orElseGet(() -> roleRepository.save(new Role(guest_role_name)));

        if(userRepository.findByUsername(admin_user_username).isEmpty()){
            userRepository.save(new UserModel(admin_user_username, bCryptPasswordEncoder.encode( admin_user_username), admin_role));
        }
          if(userRepository.findByUsername(guest_user_username).isEmpty()){
            userRepository.save(new UserModel(guest_user_username, bCryptPasswordEncoder.encode(guest_user_username), guest_role));
        }
    }

}
