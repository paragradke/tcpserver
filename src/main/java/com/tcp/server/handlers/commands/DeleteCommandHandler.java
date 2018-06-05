package com.tcp.server.handlers.commands;

import com.tcp.server.handlers.CommandHandler;
import com.tcp.server.models.Tube;
import com.tcp.server.models.WatchList;
import com.tcp.server.schedulers.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DeleteCommandHandler extends CommandHandler {

  public DeleteCommandHandler(final DataInputStream dataInputStream,
                              final DataOutputStream dataOutputStream,
                              final Scheduler scheduler,
                              final WatchList watchList) {
    super(dataInputStream, dataOutputStream, scheduler, watchList);
    SUCCESS_RESPONSE = "DELETED ";
    ERROR_RESPONSE = "NOT FOUND ";
  }

  public String handleCommand() throws IOException {
    final String jobId = dataInputStream.readUTF();
    final Tube currentTube = watchList.getProducerDefaultTube();
    boolean result = currentTube.removeJob(jobId);
    return result? SUCCESS_RESPONSE : ERROR_RESPONSE;
  }

}
