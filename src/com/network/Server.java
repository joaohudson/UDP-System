package com.network;

import java.io.IOException;
import java.net.*;

public class Server {

    private DatagramSocket socket;

    public Server(int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket(port);
    }

    public void run() throws IOException {
        while(true){
            //receive message
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            socket.receive(receivePacket);
            String data = new String(receivePacket.getData());
            System.out.println(data);

            //send messsage
            InetAddress clientIp = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String response = "Reposta para: " + data;
            byte[] binaryResponse = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(binaryResponse, binaryResponse.length, clientIp, port);
            socket.send(sendPacket);
            System.out.println(response);
        }
    }
}
