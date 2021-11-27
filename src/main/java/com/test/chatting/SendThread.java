package com.test.chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class SendThread extends Thread {

    public Socket sock;

    public SendThread(Socket sock) {
        this.sock = sock;
    }



    public void run() {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {

            PrintWriter pw = new PrintWriter(sock.getOutputStream());
            System.out.println("닉네임 입력 : ");

            String ss = br.readLine();

            pw.println(ss);
            pw.flush();

            while(true) {

                String msg = br.readLine();

                if(msg.equals("exit"))
                    break;

                pw.println(msg);
                pw.flush();
            }
            pw.close();
            sock.close();
        }catch (IOException e ) {
            e.printStackTrace();
        }
    }
}