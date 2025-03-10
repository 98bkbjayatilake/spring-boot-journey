package org.bimsara.Spring6_DI_App.Qualifier.Services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("spanishGreetingService")
public class SpanishGreetingService implements  GreetingService{
    public String greet(){
        return "jHola!";
    }
}
