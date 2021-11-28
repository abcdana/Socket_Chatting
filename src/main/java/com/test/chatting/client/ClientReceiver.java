package com.test.chatting.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver implements Runnable{

    private Socket socket;
    private DataInputStream in;
    private String name;

    // 생성자
    ClientReceiver(Socket socket, String name) {
        this.socket = socket;
        this.name = name;

        try {
            // 서버로 부터 데이터를 받을 수 있도록 DataInputStream 생성
            this.in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        while (in != null) {
            try {
                // 서버로 부터 전송되는 데이터를 출력
                System.out.println(in.readUTF());
            } catch (IOException e) {
                System.out.println("서버와의 연결이 끊어졌습니다.");
                break;
            }
        }
    }
}
