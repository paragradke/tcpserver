package com.tcp.server.handlers;

import com.tcp.server.models.WatchList;
import com.tcp.server.schedulers.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class CommandHandler {

  public CommandHandler(final DataInputStream dataInputStream,
                        final DataOutputStream dataOutputStream,
                        final Scheduler scheduler,
                        final WatchList watchList) {
    this.dataInputStream = dataInputStream;
    this.dataOutputStream = dataOutputStream;
    this.scheduler = scheduler;
    this.watchList = watchList;
  }

  public abstract String handleCommand() throws IOException;


  protected final DataInputStream dataInputStream;
  protected final DataOutputStream dataOutputStream;
  protected final Scheduler scheduler;
  protected final WatchList watchList;
  protected String SUCCESS_RESPONSE = "";
}
