package com.test.chatting.previousCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class ReceiveThread extends Thread {
    public  Socket sock;
    public  ReceiveThread(Socket sock){
        this.sock = sock;
    }


    public void run(){
        try{

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(sock.getInputStream()));

            while(true){

                String msg = br.readLine();
                if(msg==null){
                    System.out.println("접속이 종료되었어요.");
                    break;
                }
                System.out.println(msg);
            }
            br.close();
            sock.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
