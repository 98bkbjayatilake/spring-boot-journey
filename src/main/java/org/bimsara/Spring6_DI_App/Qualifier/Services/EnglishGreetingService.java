package org.bimsara.Spring6_DI_App.Qualifier.Services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("englishGreetingService")
public class EnglishGreetingService implements  GreetingService{
    public String greet(){
        return "Hello";
    }
}
