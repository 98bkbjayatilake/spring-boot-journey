package org.bimsara.Spring6_DI_App.Qualifier.AppRunner;

import org.bimsara.Spring6_DI_App.Qualifier.Services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.bimsara.Spring6_DI_App.Qualifier")
/*Now, if we want to use one of the services(EnglishGreetingService and SpanishGreetingService) */
public class QualifierDemoApplication implements CommandLineRunner {
   //final-which means it must be initialized in the constructor and cannot be changed afterward.
    private final GreetingService greetingService;

 /*
 *The @Autowired annotation is placed on the constructor of the GreetingController.
 * This means that when Spring creates an instance of 'GreetingController', it will look for a suitable
 * 'GreetingService' bean to inject into the constructor(Qualifier with the constructor injection)
 */
    @Autowired
    public QualifierDemoApplication(@Qualifier("englishGreetingService") GreetingService greetingService){
        this.greetingService=greetingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(QualifierDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // This will be executed after the application context is loaded
        System.out.println(greetingService.greet());
    }
}
