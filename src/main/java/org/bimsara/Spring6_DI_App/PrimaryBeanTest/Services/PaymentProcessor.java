package org.bimsara.Spring6_DI_App.PrimaryBeanTest.Services;

import org.springframework.stereotype.Service;

@Service
public class PaymentProcessor {

    private  PaymentService paymentService;
    /*ObjectConfig class create beans (something like objects) which are  instancse of CreditCardPayment Class
    and PayPalPayment Class and register in spring  context */
    //obtain the primary bean of these two and assign that instance or object to the paymentService variable or prpperty
    public PaymentProcessor(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    public void makePayment(){
        paymentService.processPayment();
    }
}
