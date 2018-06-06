package com.tcp.server.handlers.commands;

import com.tcp.server.handlers.CommandHandler;
import com.tcp.server.models.Job;
import com.tcp.server.models.Tube;
import com.tcp.server.models.WatchList;
import com.tcp.server.schedulers.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PutCommandHandler extends CommandHandler {

  public PutCommandHandler(final DataInputStream dataInputStream,
                            final DataOutputStream dataOutputStream,
                            final Scheduler scheduler,
                           final WatchList watchList) {
    super(dataInputStream, dataOutputStream, scheduler, watchList);
    SUCCESS_RESPONSE = "INSERTED ";
  }

  public String handleCommand() throws IOException {
    final String jobBody = dataInputStream.readUTF();
    final Job job = new Job(jobBody);
    final Tube currentTube = watchList.getProducerDefaultTube();
    currentTube.putJob(job);
    return SUCCESS_RESPONSE + job.getId() + RESPONSE_SENTINAL;
  }

}
