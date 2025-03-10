package org.bimsara.Spring6_DI_App.Qualifier.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceFactory {
    private final  GreetingService englishGreetingService;
    private  final GreetingService spanishGreetingService;
@Autowired
    public GreetingServiceFactory(
            @Qualifier("englishGreetingService")EnglishGreetingService englishGreetingService,
            @Qualifier("spanishGreetingService") SpanishGreetingService spanishGreetingService) {
    this.englishGreetingService=englishGreetingService;
    this.spanishGreetingService=spanishGreetingService;
   }

   public GreetingService getGreetingService(String language){
    /*equalsIgnonoreCase method is a part of the 'string' class in Java and is used to compare two strings for equality
    *while ignoring case differences.
   */
   if("es".equalsIgnoreCase(language)){
       return  spanishGreetingService;
   }
   return englishGreetingService;
   }
}
