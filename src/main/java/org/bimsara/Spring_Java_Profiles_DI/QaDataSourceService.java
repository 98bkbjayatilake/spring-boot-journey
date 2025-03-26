package org.bimsara.Spring_Java_Profiles_DI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("qa")
public class QaDataSourceService implements  DataSourceService{

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private  String password;

    public String getDataSource(){
        return "development DB URL: "+ dataSourceUrl +"\n The username of DB is: "+username+ "\n The password of DB is: "+password;
    }

}
