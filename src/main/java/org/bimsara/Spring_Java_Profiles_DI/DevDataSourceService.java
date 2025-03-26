package org.bimsara.Spring_Java_Profiles_DI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevDataSourceService implements DataSourceService{

    /*
    *The @Value annotation in Spring boot is used for injecting values into fields, methods,
    or constructors form properties files, environment variables, or inline expressions
    * If spring.profiles.active=dev, it will load application-dev.properties.
    * Spring Boot will automatically pick the correct application-{profile}.properties file based on the active profile.
     */
    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private  String password;



    public String getDataSource(){
        String passwordDisplay = (password == null || password.isEmpty()) ? "Not Set" : password;
        return "development DB URL: "+ dataSourceUrl +"\n The username of DB is: "+username+"\n The password of DB is: "+passwordDisplay;
    }

}
