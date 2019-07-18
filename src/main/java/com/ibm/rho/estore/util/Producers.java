package com.ibm.rho.estore.util;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.Arrays;
import java.util.logging.Logger;


public class Producers {

    Logger log = Logger.getLogger(Produces.class.getName());

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }



    @Produces @ApplicationScoped
    public MongoClient createMongoClient() {
        log.info("Creating MongoClient");
        String dbName = System.getenv("DB_NAME");
        if(dbName==null || dbName.isEmpty()) {
            log.info("Could not get environment variable DB_NAME using the default value of 'CatalogDB'");
            dbName = "productsDB";
        }

        String dbServer = System.getenv("DB_SERVER");
        if(dbServer==null || dbServer.isEmpty()) {
            log.info("Could not get environment variable DB_SERVER using the default value of 'localhost'");
            dbServer = "catalogdbservice";
        }


        String dbUsername = System.getenv("DB_USERNAME");
        if(dbUsername==null || dbUsername.isEmpty()) {
            log.info("Could not get environment variable DB_USERNAME using the default value of 'cataloguser'");
            dbUsername = "cataloguser";
        }
        
        String dbPassword = System.getenv("DB_PASSWORD");
        if(dbPassword==null || dbPassword.isEmpty()) {
            log.info("Could not get environment variable DB_PASSWORD using the default value of 'catalogpwd'");
            dbPassword = "catalogpwd";
        }
        if(dbUsername!=null && !dbUsername.isEmpty() && dbPassword!=null && !dbPassword.isEmpty()) {
            log.info(String.format("Connecting to MongoDB %s@%s using %s user credentials",dbName,dbServer,dbUsername));
            return new MongoClient(new ServerAddress("mongbdb.inmbzicp6144.in.dst.ibm.com",27017), Arrays.asList(MongoCredential.createCredential(dbUsername, dbName, dbPassword.toCharArray())));
        } else {
            log.info(String.format("Connecting to MongoDB %s@%s without authentication",dbName,dbServer));
            return new MongoClient(dbServer);
        }

    }
}
