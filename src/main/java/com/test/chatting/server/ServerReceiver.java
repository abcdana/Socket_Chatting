package com.test.chatting.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

public class ServerReceiver extends Thread{

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    Map<String, DataOutputStream> clients;

    public ServerReceiver(Socket socket, Map<String, DataOutputStream> clients) throws Exception{
        this.socket = socket;
        this.clients = clients;

        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new IOException("소켓의 정보가 맞지 않습니다.");
        }
    }

    @Override
    public void run() {
        String name = "";
        try {
            name = in.readUTF();
            sendToAll("#" + name + "님이 들어오셨습니다.");

            this.clients.put(name, out);
            System.out.println("log4j: 현재 서버접속자 수는 " + clients.size() + "입니다.");

            while (in != null) {
                String msg = in.readUTF();
                if (msg.charAt(0) == '/') {
//                    this.sendWhisper();
                }
                this.sendToAll(msg);
            }

        } catch (IOException e) {
        } finally {

            try {
                sendToAll("#" + name + "님이 나가셨습니다.");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // 대화방에서 객체 삭제
            this.clients.remove(name);
            System.out.println("[" + socket.getInetAddress() //
                    + ":" + socket.getPort() + "]" + "에서 접속을 종료하였습니다.");
            System.out.println("현재 서버접속자 수는 " + clients.size() + "입니다.");
        }

    }

    void sendToAll(String msg) throws IOException {

        Iterator<String> it = clients.keySet().iterator();

        while (it.hasNext()) {
            try {
                String name = it.next();
                DataOutputStream out = clients.get(name);
                out.writeUTF(msg);
            } catch (IOException e) {
                System.out.println("Client와 접속이 끊어졌습니다.");
//                throw new IOException("Client와 접속이 끊어졌습니다.");
            }
        }
    }

    void sendWhisper(String msg, String sendName) {
        String[] split = msg.split(" ");
        String username = split[0].substring(1);

        DataOutputStream out = clients.get(username);;

        if (out != null) {
            try {
                String text = username + "님의 귓속말 > " + split[1];
                out.writeUTF(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                out = clients.get(sendName);
                out.writeUTF("해당하는 사용자 없습니다");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
