package com.tcp.server.handlers.commands;

import com.tcp.server.handlers.CommandHandler;
import com.tcp.server.models.Job;
import com.tcp.server.models.WatchList;
import com.tcp.server.schedulers.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ReserveCommandHandler extends CommandHandler {

  public ReserveCommandHandler(final DataInputStream dataInputStream,
                           final DataOutputStream dataOutputStream,
                           final Scheduler scheduler, final WatchList watchList) {
    super(dataInputStream, dataOutputStream, scheduler, watchList);
    SUCCESS_RESPONSE = "RESERVED ";
  }

  public String handleCommand() {
    System.out.println("ReserveCommandHandler : handleCommand");
    Job job = scheduler.schedule(watchList);
    return SUCCESS_RESPONSE + job.getId() + " "+ job.getSize() + RESPONSE_SENTINAL + job.getBody()+RESPONSE_SENTINAL;
  }

}
