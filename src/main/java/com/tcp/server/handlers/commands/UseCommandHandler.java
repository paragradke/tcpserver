package com.tcp.server.handlers.commands;

import com.tcp.server.handlers.CommandHandler;
import com.tcp.server.models.Tube;
import com.tcp.server.models.WatchList;
import com.tcp.server.schedulers.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UseCommandHandler extends CommandHandler {

  public UseCommandHandler(final DataInputStream dataInputStream,
                               final DataOutputStream dataOutputStream,
                               final Scheduler scheduler,
                           final WatchList watchList) {
    super(dataInputStream, dataOutputStream, scheduler, watchList);
    SUCCESS_RESPONSE = "USING ";
  }

  public String handleCommand() throws IOException {
    final String tubeName = dataInputStream.readUTF();
    Tube tube = watchList.getFromGlobalTubeList(tubeName);
    if (tube == null) {
      tube = new Tube(tubeName);
      watchList.getTubeList().addTube(tube);
    }
    watchList.setProducerDefaultTube(tube);
    return SUCCESS_RESPONSE + tube.getName() + RESPONSE_SENTINAL;
  }

}
