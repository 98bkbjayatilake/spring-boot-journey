package org.bimsara.Spring6_DI_App.PrimaryBeanTest.Services;

public class CreditCardPayment implements PaymentService{
    @Override
    public  void processPayment(){
        System.out.println("Processing Payment with CreditCard!");
    }
}
