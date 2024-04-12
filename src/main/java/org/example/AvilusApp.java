package org.example;

import org.example.dataservice.DataService;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class AvilusApp {
    public static void main(String[] args) {
        try {
            DataService dataService = new DataService();
            dataService.initData();
            InetAddress address=InetAddress.getByName("localhost");
            int port = 14551;  // Port to listen on na d send messages
           ThreadService threadService=new ThreadService();
           threadService.startThreads(address,port);

        } catch (SocketException e) {
            System.err.println("Socket exception: " + e.getMessage());
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
