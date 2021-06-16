package com.network;

import com.network.models.NetworkMessage;

import java.io.IOException;
import java.net.*;

public class Server {

    private DatagramSocket socket;

    public Server(int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket(port);
    }

    public void run() throws IOException, ClassNotFoundException {
        while(true){
            //receive message
            DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
            socket.receive(receivePacket);
            NetworkMessage data = NetworkMessage.from(receivePacket.getData());
            System.out.println(data.getText());

            //send messsage
            InetAddress clientIp = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String response = "Reposta para: " + data.getText();
            NetworkMessage message = new NetworkMessage();
            message.setText(response);
            byte[] binaryResponse = message.toBytes();
            DatagramPacket sendPacket = new DatagramPacket(binaryResponse, binaryResponse.length, clientIp, port);
            socket.send(sendPacket);
            System.out.println(message.getText());
        }
    }
}
