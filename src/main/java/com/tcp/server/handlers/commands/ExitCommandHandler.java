package com.tcp.server.handlers.commands;

import com.tcp.server.handlers.CommandHandler;
import com.tcp.server.models.WatchList;
import com.tcp.server.schedulers.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ExitCommandHandler extends CommandHandler {

  public ExitCommandHandler(final DataInputStream dataInputStream,
                              final DataOutputStream dataOutputStream,
                              final Scheduler scheduler,
                            final WatchList watchList) {
    super(dataInputStream, dataOutputStream, scheduler, watchList);
  }

  public String handleCommand() {
    return null;
  }
}
