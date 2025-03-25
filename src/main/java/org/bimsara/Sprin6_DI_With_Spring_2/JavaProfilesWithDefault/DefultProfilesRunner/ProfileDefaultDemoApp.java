package org.bimsara.Sprin6_DI_With_Spring_2.JavaProfilesWithDefault.DefultProfilesRunner;

import org.bimsara.Sprin6_DI_With_Spring_2.JavaProfilesWithDefault.Services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = "org.bimsara.Sprin6_DI_With_Spring_2.JavaProfilesWithDefault.Services")
public class ProfileDefaultDemoApp implements CommandLineRunner {

    private SalaryService salaryService;

    //don't specify any profile
    @Autowired
    public ProfileDefaultDemoApp(SalaryService salaryService){
        this.salaryService=salaryService;
    }

    public static void main(String[] args){
        SpringApplication.run(ProfileDefaultDemoApp.class,args);
    }

    public void run(String... args) {
        System.out.println("Enter the basic salary of "+salaryService.getClass().getSimpleName()+":");

        Scanner out=new Scanner(System.in);

        float basicSal=out.nextFloat();
        salaryService.setBasicSalary(basicSal);
        System.out.println("Enter the monthly profit of the company");
        float totalMonthlyProfit= out.nextFloat();
        salaryService.setTotalMonthlyProfit(totalMonthlyProfit);

        System.out.println("your monthly salary is: "+salaryService.calculateSalary());
    }

}
