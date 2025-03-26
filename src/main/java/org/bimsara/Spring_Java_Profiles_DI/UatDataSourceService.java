package org.bimsara.Spring_Java_Profiles_DI;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("uat")
public class UatDataSourceService implements  DataSourceService{

    @Value("${firebase.database.url}")
    private String dataSourceUrl;

    @Value("${firebase.config.path}")
    private String configPath;
    public String getDataSource(){
        return "development DB URL: "+ dataSourceUrl +"\n The config path of DB: "+configPath;
    }


}
