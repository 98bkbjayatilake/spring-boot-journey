package org.bimsara.Spring6_DI_App.Qualifier.AppRunner;

import org.bimsara.Spring6_DI_App.Qualifier.Services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "org.bimsara.Spring6_DI_App.Qualifier")
public class QualifierDemoFinal implements CommandLineRunner {

    //Using Qualifier with Field injection
    @Autowired
    @Qualifier("sriLankaGreetingService")
    private GreetingService greetingService;

    public static void main(String[] args) {
        SpringApplication.run(QualifierDemoFinal.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(greetingService.greet());
    }
}
