package org.example;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import io.dronefleet.mavlink.MavlinkConnection;
import io.dronefleet.mavlink.MavlinkMessage;
import io.dronefleet.mavlink.common.*;
import io.dronefleet.mavlink.minimal.Heartbeat;
import static org.example.dataservice.DataService.AIRCRAFT;

public class DroneService {
    private DatagramSocket socket;
    private ScheduledExecutorService scheduler;
    int port;
    InetAddress address;

    public DroneService(InetAddress address, int port) throws SocketException {
        // Initialize the socket and bind it to the specific port
        this.socket = new DatagramSocket(port);
        this.address = address;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }
  // This is a mock service that assumes aircraft moves to next wayPoint for a constant time and reaches next wayPoint On reaching wayPoint
  // Mission_ITEM_REACHED is broadcast over UDP
    public void flightService() {
        AIRCRAFT.flyToNextWayPoint();
        if (!AIRCRAFT.isCurrentMissionCompleted()) {
            broadcastMissionItemReachedMessage(AIRCRAFT.getMissionSeq() - 1);
        }
    }
   // This Service listens to UDP Port and handles messages
    // getting the message  and handling could be seperated but due to the limitations of dronefleet library
    // I am̀ keeping as it is
    public void listenerService() {
        try {
            while (true) {
                System.out.println("Listening for MAVLink messages on UDP port number:... " +port);
                byte[] buffer = new byte[4096]; // Create a buffer to hold incoming data
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Receive the incoming packet

                // Create a ByteArrayInputStream to wrap the buffer
                ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());

                // Create a MAVLink connection using the ByteArrayInputStream
                MavlinkConnection connection = MavlinkConnection.create(bais, null);

                // Read MAVLink message
                MavlinkMessage<?> message = connection.next();

                // Check the messages
                if (message.getPayload() instanceof Heartbeat) {
                    System.out.println("Heartbeat message received.........");// this was for testing not requied for the task
                } else if (message.getPayload() instanceof MissionRequestList) {
                    System.out.println("Mission_Request_List message received.........");
                    broadcastMissionCountMessage();
                } else if (message.getPayload() instanceof MissionRequestInt) {
                    System.out.println("Mission_Request_int message received........." + "Seq number is " + ((MissionRequestInt) message.getPayload()).seq());
                    broadcastMissionItemIntMessage(((MissionRequestInt) message.getPayload()).seq());
                }
            }
        } catch (IOException e) {
            System.err.println("IO exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
// Builds a MISSION_ITEM_REACHED Message and sends it over UDP port
    public void broadcastMissionItemReachedMessage(int seq) {
        try {
            MissionItemReached missionItemReached = MissionItemReached.builder()
                    .seq(seq)
                    .build();

            // Serialize the message
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MavlinkConnection connection = MavlinkConnection.create(null, baos);
            connection.send2(0, 0, missionItemReached);

            byte[] messageBytes = baos.toByteArray();

            // Create and send the packet
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, address, port);
            socket.send(packet);
            System.out.println("Mission_item_Reached  message sent.");
        } catch (IOException e) {
            System.err.println("Failed to send Mission_Item_Int message: " + e.getMessage());
            e.printStackTrace();
        }
    }
// Gets an int sequence from REQUEST_MISSION_INT message and returns associated Mission Item from the list
    public void broadcastMissionItemIntMessage(int itemSeq) {
        try {
            MissionItemInt missionItemInt = MissionItemInt.builder().
                    seq(itemSeq)
                    .current(AIRCRAFT.getMissionItemsList().get(itemSeq).getCurrent())
                    .autocontinue(AIRCRAFT.getMissionItemsList().get(itemSeq).getAutocontinue())
                    .x(AIRCRAFT.getMissionItemsList().get(itemSeq).getX())
                    .y(AIRCRAFT.getMissionItemsList().get(itemSeq).getY())
                    .z(AIRCRAFT.getMissionItemsList().get(itemSeq).getZ())
                    .build();

            // Serialize the message
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MavlinkConnection connection = MavlinkConnection.create(null, baos);
            connection.send2(0, 0, missionItemInt);

            byte[] messageBytes = baos.toByteArray();

            // Create and send the packet
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, address, port);
            socket.send(packet);
            System.out.println("Mission_item_int  message sent.");
        } catch (IOException e) {
            System.err.println("Failed to send Mission_Item_Int message: " + e.getMessage());
            e.printStackTrace();
        }
    }
//  Returns MISSION_COUNT message upon REQUEST_MISSION_LIST message
    public void broadcastMissionCountMessage() {
        try {
            MissionCount missionCount = MissionCount.builder()
                    .count(AIRCRAFT.numberOfWayPoints())
                    .missionType(MavMissionType.MAV_MISSION_TYPE_MISSION)
                    .build();
// Serialize the message
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MavlinkConnection connection = MavlinkConnection.create(null, baos);
            connection.send2(0, 0, missionCount);

            byte[] messageBytes = baos.toByteArray();

            // Create and send the packet
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, address, port);
            socket.send(packet);
            System.out.println("Mission Count message sent.");
        } catch (IOException e) {
            System.err.println("Failed to send Mission Count message: " + e.getMessage());
            e.printStackTrace();
        }
    }
// Broadcasting MISSION_CURRENT message every second
    public void startMissionCurrentBroadcast() {
        // Schedule the broadcastMissionCurrent to run every second
        scheduler.scheduleWithFixedDelay(this::broadcastMissionCurrent, 0, 1, TimeUnit.SECONDS);
    }

    void broadcastMissionCurrent() {
        try {
            MissionCurrent missionCurrent = MissionCurrent.builder()
                    .seq(AIRCRAFT.getCurrentMission().getSeq()) // The sequence number of the current mission item
                    .total(AIRCRAFT.getCurrentMission().getTotal())
                    .missionState()
                    .missionMode(AIRCRAFT.getCurrentMission().getMission_mode())
                    .build();
            // Serialize the message
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            MavlinkConnection connection = MavlinkConnection.create(null, baos);
            connection.send2(0, 0, missionCurrent);

            byte[] messageBytes = baos.toByteArray();
            // Create and send the packet
            DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, address, port);
            socket.send(packet);
            System.out.println("Mission Current message sent.");
        } catch (IOException e) {
            System.err.println("Failed to send Mission Current message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



