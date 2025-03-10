package org.bimsara.Spring6_DI_App.Services;

import org.springframework.stereotype.Service;
//@Service-Hey this is a Spring Component
@Service
public class GreetingServiceImplWithDISpring implements  GreetingService{

    @Override
    public String sayGreeting(){
      return "Hello Everyone From Base Service which contain DI with Spring!" ;
    }
}
