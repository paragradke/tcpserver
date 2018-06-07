package com.tcp.server;

import com.tcp.server.models.TubeList;
import com.tcp.server.schedulers.Scheduler;
import com.tcp.server.schedulers.SimpleJobScheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

  public TCPServer(String ipAddress) throws Exception {
    this.server = new ServerSocket(PORT, 1, InetAddress.getByName(ipAddress));
    tubeList = new TubeList();
    scheduler = new SimpleJobScheduler();
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
        Thread connection = new TCPClientConnection(dis, dos, socket, tubeList, scheduler);
        connection.start();
      } catch (Exception e){
        socket.close();
        e.printStackTrace();
      }
    }
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

  private TubeList tubeList;
  private Scheduler scheduler;
  private ServerSocket server;
  private static final Integer PORT = 15001;
}
