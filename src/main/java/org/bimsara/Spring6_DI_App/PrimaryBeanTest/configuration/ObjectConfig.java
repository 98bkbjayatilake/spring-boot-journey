package org.bimsara.Spring6_DI_App.PrimaryBeanTest.configuration;

import org.bimsara.Spring6_DI_App.PrimaryBeanTest.Services.CreditCardPayment;
import org.bimsara.Spring6_DI_App.PrimaryBeanTest.Services.PayPalPayment;
import org.bimsara.Spring6_DI_App.PrimaryBeanTest.Services.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectConfig {

    //return an object of needed class(CeditCardPayment or PayPalPayment) and register as a bean
    @Bean
    @Primary
    public PaymentService getCreditCardPayment(){
        return new CreditCardPayment();
    }

    @Bean
    public  PaymentService getPayPalPayment(){
        return new PayPalPayment();
    }
}
