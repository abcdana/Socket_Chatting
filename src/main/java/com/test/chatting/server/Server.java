package com.test.chatting.server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private Map<String, DataOutputStream> clients;

    //private Map<String, Socket> clientMap;

    Server() throws Exception {
        clients = new ConcurrentHashMap<>();
    }

    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(Config.CONNECTION_PORT);
            System.out.println("서버 시작");

            while (true) {
                socket = serverSocket.accept();
                System.out.println("[" + socket.getInetAddress()
                        + ":" + socket.getPort() + "]" + "에서 접속하였습니다.");

                ServerReceiver thread = new ServerReceiver(socket, this.clients);
                thread.start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //socketUtil.close(serverSocket);
        }
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
