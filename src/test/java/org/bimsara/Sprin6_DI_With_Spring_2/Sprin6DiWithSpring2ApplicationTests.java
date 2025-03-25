package org.bimsara.Sprin6_DI_With_Spring_2;

import org.bimsara.Sprin6_DI_With_Spring_2.Services.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;
/*
* Now let's  write tests for the 'DocumentService' using the '@ActiveProfiles' annotation to specify which profile should be active during the test
* */
@SpringBootTest
@ActiveProfiles("dev")
class Sprin6DiWithSpring2ApplicationTests {

	@Autowired
	public  DocumentService documentService ;

	@Test
    public void testCreateDocument(){
		String content="This is a sample document content.We use this document for studying the behaviour of Java Profiles.@ActiveProfiles use for written test cases";
		documentService.createDocument(content);
		assertTrue(true);
	}


	@Test
	void contextLoads() {
	}

}
