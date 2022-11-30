package com.example.dogadjaji213;

import com.example.dogadjaji213.dto.CategoryReqDto;
import com.example.dogadjaji213.dto.EventReqDto;
import com.example.dogadjaji213.dto.LocationReqDto;
import com.example.dogadjaji213.dto.RegisterReqDto;
import com.example.dogadjaji213.model.Role;
import com.example.dogadjaji213.service.category.CategoryService;
import com.example.dogadjaji213.service.event.EventService;
import com.example.dogadjaji213.service.location.LocationService;
import com.example.dogadjaji213.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Dogadjaji213Application {

    public static void main(String[] args) {
        SpringApplication.run(Dogadjaji213Application.class, args);
    }
    @Bean
    CommandLineRunner run(UserService userService, EventService eventService, LocationService locationService, CategoryService categoryService){
        return args ->{
            userService.saveRole(new Role(null,"USER"));
            userService.saveRole(new Role(null,"ADMIN"));
            userService.saveUser(new RegisterReqDto("mahir","prcanovic","mahirprcanovic@gmail.com","1234"));
            userService.saveUser(new RegisterReqDto( "asim","bajric","asimb@gmail.com","1234"));
            userService.addRoleToUser("mahirprcanovic@gmail.com","ADMIN");
            userService.addRoleToUser("asimb@gmail.com","USER");
            var location = locationService.createNewLocation(new LocationReqDto("BiH-Zenica","stadion","https://www.zenicablog.com/wp-content/uploads/2020/11/stadion-bilino-polje-aerial.jpg"));
            var category = categoryService.createNewCategory(new CategoryReqDto("football match","https://i.ytimg.com/vi/R-cDKTgp7-k/maxresdefault.jpg"));
            eventService.createNewEvent(new EventReqDto("football match", LocalDateTime.now(),"lorem ipsum","https://d1e00ek4ebabms.cloudfront.net/production/61a52cef-9a5c-4740-9181-f4b2c68782be.jpg",location.getId(),category.getId()));
        };
    }
}
