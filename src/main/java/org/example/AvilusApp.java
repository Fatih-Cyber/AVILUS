package org.example;

import org.example.dataservice.DataService;

import java.net.SocketException;

public class AvilusApp {
    public static void main(String[] args) {
         try {
             DataService dataService=new DataService();
             dataService.initData();
            int port = 14551;  // Port to listen on na d send messages
            DroneService service = new DroneService(port);
            Thread listenerThread= new Thread(){
            public void run(){
                 service.listenerService();
            }
            };
            Thread broadcasterThread= new Thread(){
            public void run(){
                service.startMissionCurrentBroadcast();  // Start broadcasting MissionCurrent messages
            }
            };
            listenerThread.start();
            broadcasterThread.start();

        } catch (SocketException e) {
            System.err.println("Socket exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
