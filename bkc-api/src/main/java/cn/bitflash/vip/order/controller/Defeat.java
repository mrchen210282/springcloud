package cn.bitflash.vip.order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Defeat {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw.println("啦啦啦啦啦啦");
            pw.flush();
            String line = bufferedReader.readLine();
            System.out.println("接受到数据为："+line);
            pw.close();
            bufferedReader.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
