package com.tcp.server.handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPClientHandler extends Thread {

  final DataInputStream dataInputStream;
  final DataOutputStream dataOutputStream;
  final Socket socket;

  final DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
  final DateFormat fortime = new SimpleDateFormat("hh:mm:ss");

  public TCPClientHandler(final DataInputStream dataInputStream,
                          final DataOutputStream dataOutputStream,
                          final Socket socket) {
    this.dataInputStream = dataInputStream;
    this.dataOutputStream = dataOutputStream;
    this.socket = socket;
  }

  @Override
  public void run() {
    String command;
    String response;
    final String data;

    while (true) {
      try {
        dataOutputStream.writeUTF("What do you want?[Date | Time]..\n"+"Type 'Exit' to terminate connection.");
        command = dataInputStream.readUTF();
        switch (command) {
          case "Date" :
            response = fordate.format(new Date());
            dataOutputStream.writeUTF(response);
            break;

          case "Time" :
            response = fortime.format(new Date());
            dataOutputStream.writeUTF(response);
            break;

          case "Exit" :
            response = "Closing the connection from server";
            dataOutputStream.writeUTF(response);
            this.socket.close();
            break;
        }
      } catch (IOException e) {
        try {
          this.dataOutputStream.close();
          this.dataInputStream.close();
        } catch(IOException ex){
          ex.printStackTrace();
        }
      }
    }
  }
}
