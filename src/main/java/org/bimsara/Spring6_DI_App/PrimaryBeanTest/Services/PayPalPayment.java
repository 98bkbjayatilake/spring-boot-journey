package org.bimsara.Spring6_DI_App.PrimaryBeanTest.Services;

public class PayPalPayment implements PaymentService{
    @Override
    public void processPayment() {
        System.out.println("Processing payment with PayPal");
    }
}
