package com.test.chatting.previousCode;

import java.io.IOException;
import java.net.Socket;

class EasyClient{

    String host;
    int port;

    public EasyClient(String host, int port) {
        this.host = host;
        this.port = port;

    }

    public void start() throws InterruptedException {
        while(true){
            try {
                Socket sock = new Socket(host, port);
                System.out.println("서버 접속 성공");


                Thread t1=new SendThread(sock);
                Thread t2=new ReceiveThread(sock);

                t1.start();
                t2.start();


                t1.join(); //이 쓰레드가 끝나야 다음 쓰레드를 start() 함
                t2.join();
            } catch (IOException e) {
                e.printStackTrace();
            }




        }


    }


}
