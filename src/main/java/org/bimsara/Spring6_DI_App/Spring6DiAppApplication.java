package org.bimsara.Spring6_DI_App;

import org.bimsara.Spring6_DI_App.controllers.MyController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Spring6DiAppApplication {

	public static void main(String[] args) {
		/*default behaviour of Spring Boot is that a configure spring to do component scan for any annotated components in the package that it's in and below */
		ApplicationContext ctx =SpringApplication.run(Spring6DiAppApplication.class, args);
		MyController controller=ctx.getBean(MyController.class);
		System.out.println("In Main Method");
		System.out.println(controller.sayhello());

	}

}
