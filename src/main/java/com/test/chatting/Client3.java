package com.test.chatting;


public class Client3 {
    public static void main(String[] args) throws InterruptedException {
        EasyClient easyClient= new EasyClient("localhost",8090);
        easyClient.start();
    }
}
