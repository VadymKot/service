package com.somecompany.householdservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HouseholdServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseholdServiceApplication.class, args);
    }
//
//    @Configuration
//    class LoadDatabase {
//
//        @Autowired
//        HouseHoldService deviceService;
//
//        @Bean
//        CommandLineRunner initDatabase() {
//            return args -> {
//
//                deviceService.addDeviceGroupName("Fridge");
//                deviceService.addDeviceGroupName("TV");
//                deviceService.addDeviceGroupName("Oven");
//
//                deviceService.addDevice(new Device("Fridge",
//                        new ArrayList<>(Arrays.asList("White","High","CMX-56")),
//                        true, "not working a compressor"));
//                deviceService.addDevice(new Device("TV",
//                        new ArrayList<>(Arrays.asList("Black","50'","Sony DFS")),
//                        true, "White rounds on the screen"));
//                deviceService.addDevice(new Device("Oven",
//                        new ArrayList<>(Arrays.asList("Silver","Nano","Temperature autocontrol","Siemens X-68")),
//                        true, "Not heating"));
//            };
//        }
//    }
}
