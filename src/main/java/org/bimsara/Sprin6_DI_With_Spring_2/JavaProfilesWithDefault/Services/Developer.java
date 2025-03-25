package org.bimsara.Sprin6_DI_With_Spring_2.JavaProfilesWithDefault.Services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("Dev")
public class Developer implements SalaryService {
    private float basicSal;
    private float TotalMonthlyProfit;
   @Override
    public float calculateSalary(){
     return (float) (basicSal+(0.1*TotalMonthlyProfit));
    }
    @Override
    public void setBasicSalary(float basicSal){
      this.basicSal=basicSal;
    }
    @Override
    public  void setTotalMonthlyProfit(float totalMonthlyProfit){
    this.TotalMonthlyProfit=totalMonthlyProfit;
    }
}
