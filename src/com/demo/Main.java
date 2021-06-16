package com.demo;

import com.network.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{

        Scanner scan = new Scanner(System.in);
        String option = scan.nextLine();
        int port = scan.nextInt();

        switch (option){
            case "Server":
                runServer(port);
                break;

            case "Client":
                runClient(port);

            default:
                System.err.println("Opção inválida!");
        }
    }

    private static void runServer(int port) throws Exception{
        Server server = new Server(port);
        server.run();
    }

    private static void runClient(int port) throws Exception{
        Client client = new Client(port, "localhost");
        client.run();
    }
}
