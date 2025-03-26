package org.bimsara.Spring_Java_Profiles_DI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.bimsara.Spring_Java_Profiles_DI")
public class SpringJavaProfilesDiApplication implements CommandLineRunner {

	private DataSourceService dataSourceService;

	@Autowired
	public  SpringJavaProfilesDiApplication(DataSourceService dataSourceService){
		this.dataSourceService=dataSourceService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJavaProfilesDiApplication.class, args);
	}

	public void run(String... args){
		System.out.println(dataSourceService.getDataSource());
	}
}
