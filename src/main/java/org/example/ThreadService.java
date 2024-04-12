package org.example;

import java.net.InetAddress;
import java.net.SocketException;

public class ThreadService {
    public void startThreads(InetAddress address, int port) throws SocketException {
        DroneService service = new DroneService(address, port);
        Thread listenerThread = new Thread() {
            public void run() {
                service.listenerService();
            }
        };
        Thread broadcasterThread = new Thread() {
            public void run() {
                service.startMissionCurrentBroadcast();  // Start broadcasting MissionCurrent messages
            }
        };
        Thread flightThread = new Thread() {
            public void run() {
                int flightTime = 10;  // Duration in seconds

                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        service.flightService();  // Execute the flight service method

                        // Sleep for the specified amount of time in milliseconds
                        Thread.sleep(flightTime * 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();  // Re-interrupt the thread to handle the interruption properly
                        System.out.println("Thread was interrupted, stopping...");
                        break;  // Exit the loop on interruption
                    }
                }
            }
        };
        flightThread.start();
        listenerThread.start();
        broadcasterThread.start();
    }
}
