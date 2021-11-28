package com.test.chatting.previousCode;


public class Client {
    public static void main(String[] args) throws InterruptedException {
        EasyClient easyClient= new EasyClient("localhost",8090);
        easyClient.start();
    }
}