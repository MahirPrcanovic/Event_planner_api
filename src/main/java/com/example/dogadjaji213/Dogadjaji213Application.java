package com.example.dogadjaji213;

import com.example.dogadjaji213.model.AppUser;
import com.example.dogadjaji213.model.Role;
import com.example.dogadjaji213.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class Dogadjaji213Application {

    public static void main(String[] args) {
        SpringApplication.run(Dogadjaji213Application.class, args);
    }
    @Bean
    CommandLineRunner run(UserService userService){
        return args ->{
            userService.saveRole(new Role(null,"USER"));
            userService.saveRole(new Role(null,"ADMIN"));

            userService.saveUser(new AppUser(null,"mahir","prcanovic","mahirprcanovic@gmail.com","1234",null));
            userService.saveUser(new AppUser(null, "asim","bajric","asimb@gmail.com","1234",null));

            userService.addRoleToUser("mahirprcanovic@gmail.com","ADMIN");
            userService.addRoleToUser("asimb@gmail.com","USER");
        };
    }
}
