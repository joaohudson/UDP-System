package com.network;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {

    private DatagramSocket socket;
    private int serverPort;
    private InetAddress ipServer;

    public Client(int serverPort, String ipServer) throws SocketException, UnknownHostException {
        this.serverPort = serverPort;
        this.socket = new DatagramSocket();
        this.ipServer = InetAddress.getByName(ipServer);
    }

    public void run() throws IOException {
        while(true){
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            //send message
            byte[] sendData = input.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipServer, serverPort);
            socket.send(sendPacket);

            //receive response
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData());
            System.out.println(response);
        }
    }
}
