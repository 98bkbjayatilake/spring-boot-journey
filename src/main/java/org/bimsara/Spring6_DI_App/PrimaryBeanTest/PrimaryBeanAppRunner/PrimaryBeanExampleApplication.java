package org.bimsara.Spring6_DI_App.PrimaryBeanTest.PrimaryBeanAppRunner;

import org.bimsara.Spring6_DI_App.PrimaryBeanTest.Services.PayPalPayment;
import org.bimsara.Spring6_DI_App.PrimaryBeanTest.Services.PaymentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.bimsara.Spring6_DI_App.PrimaryBeanTest")
public class PrimaryBeanExampleApplication implements CommandLineRunner {

    private PaymentProcessor paymentProcessor;

    public PrimaryBeanExampleApplication(PaymentProcessor paymentProcessor){
        this.paymentProcessor=paymentProcessor;
    }
    public static void main(String[] args) {
        SpringApplication.run(PrimaryBeanExampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        paymentProcessor.makePayment();
    }


}
