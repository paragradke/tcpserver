package com.tcp.server.schedulers;

import com.tcp.server.models.Job;
import com.tcp.server.models.WatchList;

import java.util.List;

public interface Scheduler {

  public Job schedule(final WatchList watchList);

}
