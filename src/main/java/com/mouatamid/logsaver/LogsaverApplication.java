package com.mouatamid.logsaver;

import com.mouatamid.logsaver.services.IAppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class LogsaverApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogsaverApplication.class, args);
    }

    @Bean
    CommandLineRunner run(IAppUserService service){
        return args -> {
            service.saveUser("mouatamid@gmail.com","123456789");
            service.createLog("mouatamid@gmail.com",new Date(),new Date(4562),"New Log");
        };
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
