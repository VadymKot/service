package com.somecompany.householdservice.Controller;


import com.somecompany.householdservice.Model.Device;
import com.somecompany.householdservice.Service.HouseHoldService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class HouseHoldControllerTest {

//    @Autowired
//    HouseHoldService deviceService;

//    @Autowired
//    public HouseHoldControllerTest(HouseHoldService deviceService) {
//        this.deviceService=deviceService;
//
//        initializeData();
//    }

//    @Before
//    public void initializeData() {
//
//        deviceService.addDeviceGroupName("Fridge");
//        deviceService.addDeviceGroupName("TV");
//        deviceService.addDeviceGroupName("Oven");
//
//        System.out.println("init1");
//
//        try {
//            deviceService.addDevice(new Device("Fridge",
//                    new ArrayList<>(Arrays.asList("White","High","CMX-56")),
//                    true, "not working a compressor"));
//
//            deviceService.addDevice(new Device("TV",
//                    new ArrayList<>(Arrays.asList("Black","50'","Sony DFS")),
//                    true, "White rounds on the screen"));
//            deviceService.addDevice(new Device("Oven",
//                    new ArrayList<>(Arrays.asList("Silver","Nano","Temperature autocontrol","Siemens X-68")),
//                    true, "Not heating"));
//            System.out.println(deviceService.findAllDevices());
//        }
//        catch (DeviceGroupNameException e) {
//            System.out.println("Exception");
//        }
//    }

    @Test
    void canMakeNewDeviceWithExistingGroup() {
        Device device = new Device("Fridge",
                new ArrayList<>(Arrays.asList("White","Compact","S23434567")),
                true, "Not turning on");
        given().
                auth().basic("admin","admin").
                contentType("application/json").
                body(device).
        when().
                post("http://localhost:8080/devices").
        then().
                statusCode(200).
                body(containsString("S23434567"));
    }

    @Test
    void canMakeNewDeviceWithNotExistingGroup() {
        Device device = new Device("MicroWave",
                new ArrayList<>(Arrays.asList("Black","Compact","M556788")),
                true, "Not turning on");
        given().
                auth().basic("admin","admin").
                contentType("application/json").
                body(device).
        when().
                post("http://localhost:8080/devices").
        then().
                statusCode(200).
                body(containsString("There is no "+device.getDeviceGroupName()));
    }

    @Test
    void canReceiveDeviceById() {
        given().
                auth().basic("admin","admin").
        when().
                get("http://localhost:8080/devices/id/2").
        then().
                statusCode(200).
                body(containsString("Sony DFS"));
    }

    @Test
    void canReceiveAllDevices() {
        given().
                auth().basic("admin","admin").
        when().
                get("http://localhost:8080/devices").
        then().
                statusCode(200).
                body(containsString("CMX-56")).
                body(containsString("Sony DFS"));
    }

    @Test
    void canUpdateDeviceById() {
        Device device = new Device("Fridge",
                new ArrayList<>(Arrays.asList("White","High","DRF-67878")),
                true, "not working a compressor");
        given().
                auth().basic("admin","admin").
                contentType("application/json").
                body(device).
        when().
                put("http://localhost:8080/devices/id/1").
        then().
                statusCode(200).
                body(containsString("DRF-67878"));
    }

    @Test
    void canDeleteDeviceById() {
        given().
                auth().basic("admin","admin").
        when().
                delete("http://localhost:8080/devices/id/1").
        then().
                statusCode(200).
                body(containsString("was successfully deleted"));
    }

    @Test
    void canMakeNewDeviceGroup() {
        given().
                auth().basic("admin","admin").
        when().
                post("http://localhost:8080/devices/devicegroup/add/Some New Group").
        then().
                statusCode(200).
                body(containsString("Some New Group"));
    }

    @Test
    void canReceiveAllDeviceGroups() {
        given().
                auth().basic("admin","admin").
        when().
                get("http://localhost:8080/devices/devicegroup").
        then().
                statusCode(200).
                body(containsString("TV")).
                body(containsString("Oven"));

    }
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
                deviceService.addDeviceGroupName("Oven");

                deviceService.addDevice(new Device("Fridge",
                        new ArrayList<>(Arrays.asList("White","High","CMX-56")),
                        true, "not working a compressor"));
                deviceService.addDevice(new Device("TV",
                        new ArrayList<>(Arrays.asList("Black","50'","Sony DFS")),
                        true, "White rounds on the screen"));
                deviceService.addDevice(new Device("Oven",
                        new ArrayList<>(Arrays.asList("Silver","Nano","Temperature autocontrol","Siemens X-68")),
                        true, "Not heating"));
            };
        }
    }

