The program for managing of households in the service center. 

Requirements:

JDK 8, 
Maven 3.5.0+,  
PostgreSQL 9.4+

Running the program. 

Postgres must be in localhost:5432
username: postgres  
password: postgres  
database: servicedb   

Then run the follow commands from program folder:
mvn clean package
cd /target
java -jar householdservice.jar

API:

{URL} http://localhost:8080/devices

GET {URL} - receive a list of all devices in service

GET {URL}/id - receive a device by Id

POST {URL} + [Json body] - save new device. Group of devices must be already saved. 

PUT {URL} + [Json body] - update an existing device. Group of devices must be already saved.

DELETE {URL}/id - delete device by Id

GET {URL}/devicegroup - receive existing groups of devices

POST {URL}/devicegroup/add/{here write new group} - create new device group 

[Json body] - {
                  "deviceGroupName": "Fridge",
                  "properties": [
                      "Black",
                      "50'",
                      "Sony DFS"                     
                  ],
                  "isBroken": true,
                  "description": "Not working"
               }   

Program receives queries in JSON and need a autorisation : 
login: admin
password: admin


Important! First, you need to create groups of goods in database, after this you can add Devices with this group.
If the group type has not yet been saved, the program displays a message that you need first to save the group 
name. Each product consists of a group name, characteristics (there can be any number of them), an matcher if 
device broken (false) or already repeared (true), notes.

