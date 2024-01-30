package com.example.employeeapi.config;

import com.example.employeeapi.dto.EmployeeDTO;
import com.example.employeeapi.dto.EmployeeDTOCodec;
import com.example.employeeapi.dto.ObjectIdCodec;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MongoDBConfig {


    @Value("${mongodb.db}")
    private String databaseName;
    @Value("${mongodb.collection.uri}")
    private String mongoConnectionUri;

    @Bean
    public CodecRegistry codecRegistry() {
        CodecRegistry defaultCodecRegistry = MongoClientSettings.getDefaultCodecRegistry();

        // Create a list of codecs including the custom EmployeeDTOCodec and ObjectIdCodec
        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .register("com.example.employeeapi.dto")
                .register("org.bson.types")
                .build();

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(defaultCodecRegistry,
                CodecRegistries.fromCodecs(new ObjectIdCodec()),
                CodecRegistries.fromProviders(pojoCodecProvider));

        return pojoCodecRegistry;
    }





//    @Bean
//    public MongoClient mongoClient(CodecRegistry codecRegistry) {
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .codecRegistry(codecRegistry)
//                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
//                .build();
//        return MongoClients.create(settings);
//    }
    @Bean
    public MongoClient mongoClient(CodecRegistry codecRegistry) {
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .applyConnectionString(new ConnectionString(mongoConnectionUri))
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoDatabase mongoDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase(databaseName);
    }
    @Bean
    public EmployeeDTOCodec employeeDTOCodec(CodecRegistry codecRegistry) {
        return new EmployeeDTOCodec(codecRegistry);
    }
}
