package com.tcp.server.handlers;

import com.tcp.server.handlers.commands.*;
import com.tcp.server.models.WatchList;
import com.tcp.server.schedulers.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class CommandHandlerFactory {

  public CommandHandlerFactory(final DataInputStream dataInputStream,
                               final DataOutputStream dataOutputStream,
                               final Scheduler scheduler,
                               final WatchList watchList) {
    this.dataInputStream = dataInputStream;
    this.dataOutputStream = dataOutputStream;
    this.scheduler = scheduler;
    this.watchList = watchList;
  }

  public CommandHandler getCommandHandler(final String command) {
    switch (command) {
      case "use" :
          return new UseCommandHandler(this.dataInputStream, this.dataOutputStream, scheduler, watchList);

      case "put" :
          return new PutCommandHandler(this.dataInputStream, this.dataOutputStream, scheduler, watchList);

      case "watch" :
          return new WatchCommandHandler(this.dataInputStream, this.dataOutputStream, scheduler, watchList);

      case "reserve" :
          return new ReserveCommandHandler(this.dataInputStream, this.dataOutputStream, scheduler, watchList);

      case "delete" :
          return new DeleteCommandHandler(this.dataInputStream, this.dataOutputStream, scheduler, watchList);

      case "Exit" :
          return new ExitCommandHandler(this.dataInputStream, this.dataOutputStream, scheduler, watchList);

      default:
        throw new UnsupportedOperationException("This command is currently not supported by the server");
    }
  }

  private final Scheduler scheduler;
  private final WatchList watchList;
  private final DataInputStream dataInputStream;
  private final DataOutputStream dataOutputStream;
}
