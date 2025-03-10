package org.bimsara.Spring6_DI_App.Qualifier.AppRunner;

import org.bimsara.Spring6_DI_App.Qualifier.Services.GreetingService;
import org.bimsara.Spring6_DI_App.Qualifier.Services.GreetingServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
@SpringBootApplication(scanBasePackages = "org.bimsara.Spring6_DI_App.Qualifier")
public class QualifierDemoApplication2 implements CommandLineRunner {
   //Qualifier with the constructor injection

    private final GreetingServiceFactory greetingServiceFactory;
    @Autowired
    public QualifierDemoApplication2(GreetingServiceFactory greetingServiceFactory){
        this.greetingServiceFactory=greetingServiceFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(QualifierDemoApplication2.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // This will be executed after the application context is loaded
        Scanner reader=new Scanner(System.in);
        System.out.println("What is the language you prefer:English(en) or Spanish(es)\n type es or en");
        String userLanguage=reader.nextLine();
        GreetingService greetingServiceChoose=greetingServiceFactory.getGreetingService(userLanguage);
        System.out.println(greetingServiceChoose.greet());
    }
}
