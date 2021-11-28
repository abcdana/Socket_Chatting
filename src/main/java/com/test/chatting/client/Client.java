package com.test.chatting.client;

import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Thread sender;
    private static Thread receiver;

    static Socket getConnection() {
        Socket socket = null;
        String serverIp = Config.CONNECT_SERVER;

        try {
            socket = new Socket(serverIp, Config.CONNECT_PORT);
            System.out.println("채팅 서버 연결 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return socket;
    }

    static void setStart(Socket socket) {
        System.out.print("닉네임 : ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        sender = setSender(socket, name);
        receiver = setReceiver(socket, name);
        sender.start();
        receiver.start();
    }

    static Thread setSender(Socket socket, String name) {
        return new Thread(new ClientSender(socket, name));
    }

    static Thread setReceiver(Socket socket, String name) {
        return new Thread(new ClientReceiver(socket, name));
    }


}
