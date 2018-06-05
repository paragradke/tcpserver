package com.tcp.server.schedulers;

import com.tcp.server.models.Job;
import com.tcp.server.models.Tube;
import com.tcp.server.models.TubeList;
import com.tcp.server.models.WatchList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SimpleJobScheduler implements Scheduler {

  public Job schedule(WatchList watchList) {
    System.out.println("SimpleJobScheduler : schedule");
    Tube tube = getRandomTube(watchList);
    System.out.println("Random Tube : "+ tube.getName());
    tube.print();
    Job job = tube.getNextJob();
    if (job == null) {
      try {
        Thread.sleep(100);
        return schedule(watchList);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Random Job : "+ job.getBody());
    return job;
  }

  private Tube getRandomTube(final WatchList watchList) {
    while (true) {
      List<Tube> list = new ArrayList(watchList.getTubes());
      //System.out.println(list.get(0).getName());
      //dump(watchList);
      if (list.size() == 1 && !list.isEmpty()) {
        return list.get(0);
      } else if (list.size() == 0) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      } else {
        while (!list.isEmpty()) {
          if (list.size() == 1 && !list.isEmpty()) {
            return list.get(0);
          }
          Random r = new Random();
          Tube tube = list.get(r.nextInt(list.size() - 1));
          if (tube.isEmpty()) {
            list.remove(tube);
            continue;
          }
          return tube;
        }
      }
    }
  }

  private void dump(final WatchList watchList) {
    TubeList tubeList = watchList.getTubeList();
    tubeList.print();
    watchList.print();
  }


}
