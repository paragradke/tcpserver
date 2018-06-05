package com.tcp.server;

import com.tcp.server.handlers.CommandHandler;
import com.tcp.server.handlers.CommandHandlerFactory;
import com.tcp.server.models.Tube;
import com.tcp.server.models.TubeList;
import com.tcp.server.models.WatchList;
import com.tcp.server.schedulers.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static java.util.UUID.randomUUID;

public class TCPClientConnection extends Thread {

  public TCPClientConnection(final DataInputStream dataInputStream,
                             final DataOutputStream dataOutputStream,
                             final Socket socket,
                             final TubeList tubeList,
                             final Scheduler scheduler) {
    this.dataInputStream = dataInputStream;
    this.dataOutputStream = dataOutputStream;
    this.socket = socket;
    this.id = randomUUID().toString();
    watchList = new WatchList(tubeList);
    commandHandlerFactory = new CommandHandlerFactory(dataInputStream, dataOutputStream, scheduler, watchList);
  }

  @Override
  public void run() {
    String command;
    String response;

    while (true) {
      try {
        dataOutputStream.writeUTF("Commands? [use | put | watch | reserve | delete ]..\n" + "Type 'Exit' to terminate connection.");
        command = dataInputStream.readUTF();

        if (command.equals("Exit")) {
          dataOutputStream.writeUTF("Bye");
          this.socket.close();
          System.out.println("Closing connection for socket "+ socket);
        }
        CommandHandler commandHandler = commandHandlerFactory.getCommandHandler(command);
        response = commandHandler.handleCommand();
        dataOutputStream.writeUTF(response);
      } catch (IOException e) {
        try {
          this.dataOutputStream.close();
          this.dataInputStream.close();
        } catch(IOException ex){
          ex.printStackTrace();
        }
      } catch (UnsupportedOperationException e) {
        continue;
      }
    }
  }


  final DataInputStream dataInputStream;
  final DataOutputStream dataOutputStream;
  final Socket socket;
  final String id;
  final CommandHandlerFactory commandHandlerFactory;
  final WatchList watchList;
}
