package org.bimsara.Spring6_DI_App.Services;

import org.springframework.stereotype.Service;


public class GreetingServiceImpl implements GreetingService{
    @Override
    public  String  sayGreeting(){
        return "Hello Everyone From Base Service!";
    }
}
