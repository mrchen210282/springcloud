package cn.bitflash.vip.order.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Success {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = new ServerSocket().accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = bufferedReader.readLine();
            System.out.println("服务端接收到的数据是：" + line);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("发送数据：搜狗输入法");
            pw.flush();
            pw.close();
            bufferedReader.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}