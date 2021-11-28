package com.test.chatting.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 클라이언트 메세지 전송용
 */
public class ClientSender implements Runnable{
    private Socket socket;
    private DataOutputStream out;
    private String name;
    private Scanner scanner;

    public ClientSender(Socket socket, String name) {
        this.socket = socket;
        this.name = name;

        try {
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        scanner = new Scanner(System.in);
        try {
            // 시작하자 마자, 자신의 대화명을 서버로 전송
            if (out != null) {
                out.writeUTF(name);
            }

            while (out != null) {
                String message = "[" + name + "]" + scanner.nextLine();
                // 키보드로 입력받은 데이터를 서버로 전송
                out.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
