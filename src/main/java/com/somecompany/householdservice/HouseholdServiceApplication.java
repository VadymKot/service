package com.somecompany.householdservice;

import com.somecompany.householdservice.Model.Device;
import com.somecompany.householdservice.Service.HouseHoldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class HouseholdServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseholdServiceApplication.class, args);
    }

    @Configuration
    class LoadDatabase {

        @Autowired
        HouseHoldService deviceService;

        @Bean
        CommandLineRunner initDatabase() {
            return args -> {

                deviceService.addDeviceGroupName("Fridge");
                deviceService.addDeviceGroupName("TV");

                deviceService.addDevice(new Device("Fridge",
                        new ArrayList<String>(Arrays.asList("White","High","CMX-56")),
                        true, "not working a compressor"));
                deviceService.addDevice(new Device("TV",
                        new ArrayList<String>(Arrays.asList("Black","50'","Sony DFS")),
                        true, "White rounds on the screen"));
            };
        }
    }
}
