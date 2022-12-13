package com.example.dogadjaji213;
import com.example.dogadjaji213.dto.category.CategoryReqDto;
import com.example.dogadjaji213.dto.event.EventReqDto;
import com.example.dogadjaji213.dto.location.LocationReqDto;
import com.example.dogadjaji213.dto.user.RegisterReqDto;
import com.example.dogadjaji213.model.Role;
import com.example.dogadjaji213.service.category.CategoryService;
import com.example.dogadjaji213.service.event.EventService;
import com.example.dogadjaji213.service.location.LocationService;
import com.example.dogadjaji213.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

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
            var location = locationService.createNewLocation(new LocationReqDto("England-Manchester","Manchester is a major city in the northwest of England with a rich industrial heritage. The Castlefield conservation area’s 18th-century canal system recalls the city’s days as a textile powerhouse, and visitors can trace this history at the interactive Museum of Science & Industry.","https://tourscanner.com/blog/wp-content/uploads/2021/08/things-to-do-in-Manchester-UK.jpg"));
            var locationCon = locationService.createNewLocation(new LocationReqDto("USA-Las Vegas","Las Vegas, often known simply as Vegas, is the 25th-most populous city in the United States, the most populous city in the state of Nevada, and the county seat of Clark County. The city anchors the Las Vegas Valley metropolitan area and is the largest city within the greater Mojave Desert. ","https://www.thestreet.com/.image/t_share/MTg3NjE5NzU0MTY3NTEwMjcx/las-vegas-strip.jpg"));
            var category = categoryService.createNewCategory(new CategoryReqDto("football match","https://cdn4.iconfinder.com/data/icons/football-13/64/Football-22-512.png"));
            var categoryCon = categoryService.createNewCategory(new CategoryReqDto("concert","https://www.clipartmax.com/png/middle/304-3046321_concert-comments-concert-icon-png.png"));
            eventService.createNewEvent(new EventReqDto("Man Utd vs Man City", LocalDate.now(),"A football match consists of two halves and each half is 45 minutes long. Between the two halves, there is an interval, which is not more than 15 minutes long. Stoppage time (also called injury time) is the time added on at the end of each half at the discretion of the referee.","https://d1e00ek4ebabms.cloudfront.net/production/61a52cef-9a5c-4740-9181-f4b2c68782be.jpg",location.getId(),category.getId()));
            eventService.createNewEvent(new EventReqDto("Adele Concert", LocalDate.now(),"Adele Laurie Blue Adkins MBE, professionally known by the mononym Adele, is an English singer and songwriter. After graduating in arts from the BRIT School in 2006, Adele signed a record deal with XL Recordings.","https://www.masslive.com/resizer/ARsoOtXwdJCKVQXRZyYkb-yoaTE=/1280x0/smart/cloudfront-us-east-1.images.arcpublishing.com/advancelocal/6JPIQU6SS5BQLNIIFWEWG4MRXQ.png",locationCon.getId(),categoryCon.getId()));
        };
    }
}
