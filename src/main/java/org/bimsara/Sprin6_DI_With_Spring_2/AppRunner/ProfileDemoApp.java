package org.bimsara.Sprin6_DI_With_Spring_2.AppRunner;

import org.bimsara.Sprin6_DI_With_Spring_2.Services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class ProfileDemoApp implements CommandLineRunner {

    private final DocumentService documentService;
    @Autowired
    public ProfileDemoApp(DocumentService documentService){
        this.documentService=documentService;
    }

    public static void main(String[] args){
        SpringApplication.run(ProfileDemoApp.class,args);
    }

    @Override
    public void run(String... args) {
     String content="This is a sample document content.\n We use this document for studying the behaviour of Java Profiles.";
     documentService.createDocument(content);
     System.out.println("Document Created Successfully");
    }
}
