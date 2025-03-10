package org.bimsara.Spring6_DI_App;

import org.bimsara.Spring6_DI_App.controllers.MyController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class Spring6DiAppApplicationTests {
    /*this is going to tell the Spring context to inject a context for us*/
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	MyController myController;

    @Test
	void testAutowireOfControll(){
		System.out.println(myController.sayhello());
	}

	@Test
	void testGetControllerFromCtx(){
		MyController myControllertest=applicationContext.getBean(MyController.class);

		System.out.println(myControllertest.sayhello());
	}
	@Test
	void contextLoads() {
	}

}
