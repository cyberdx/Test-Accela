package com;

import com.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsole implements CommandLineRunner {

    private AppService appService;

    @Autowired
    public void set(AppService appService) {
        this.appService = appService;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootConsole.class);
        app.setBannerMode(Banner.Mode.LOG);
        app.run(args);
    }

    @Override
    public void run(String... strings) throws Exception {
        appService.init();
    }
}
