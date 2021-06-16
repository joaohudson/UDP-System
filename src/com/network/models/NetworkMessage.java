package com.network.models;

import sun.nio.ch.Net;

import java.io.*;

public class NetworkMessage implements Serializable {

    public static NetworkMessage from(byte[] data) throws IOException, ClassNotFoundException {
        NetworkMessage value = null;

        ByteArrayInputStream stream = new ByteArrayInputStream(data);
        ObjectInputStream objectStream = new ObjectInputStream(stream);
        value = (NetworkMessage)objectStream.readObject();

        objectStream.close();
        stream.close();

        return value;
    }

    public byte[] toBytes() throws IOException{

        byte[] value = null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        value = byteArrayOutputStream.toByteArray();

        objectOutputStream.close();
        byteArrayOutputStream.close();

        return value;
    }

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
