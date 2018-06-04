package com.tcp.server;

import com.tcp.server.handlers.TCPClientHandler;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

  private ServerSocket server;
  public TCPServer(String ipAddress) throws Exception {
    this.server = new ServerSocket(0, 1, InetAddress.getByName(ipAddress));
  }

  private void listen() throws Exception {
    Socket socket = null;
    while (true) {
      try {
        socket = this.server.accept();
        System.out.println("A new client is connected : " + socket);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("Assigning new thread for this client");
        Thread t = new TCPClientHandler(dis, dos, socket);
        t.start();
      } catch (Exception e){
        socket.close();
        e.printStackTrace();
      }
    }
//    String clientAddress = client.getInetAddress().getHostAddress();
//    System.out.println("\r\nNew connection from " + clientAddress);
//    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//    while ( (data = in.readLine()) != null ) {
//      System.out.println("\r\nMessage from " + clientAddress + ": " + data);
//    }
  }

  public InetAddress getSocketAddress() {
    return this.server.getInetAddress();
  }

  public int getPort() {
    return this.server.getLocalPort();
  }

  public static void main(String[] args) throws Exception {
    TCPServer app = new TCPServer(args[0]);
    System.out.println("\r\nRunning Server: " +
        "Host=" + app.getSocketAddress().getHostAddress() +
        " Port=" + app.getPort());

    app.listen();
  }
}
