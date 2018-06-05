package com.tcp.server.models;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TubeList {

  public TubeList() {
    defaultTube = new Tube(DEFAULT_TUBE_NAME);
    tubes = new ConcurrentHashMap();
    tubes.putIfAbsent(DEFAULT_TUBE_NAME, defaultTube);
  }

  public Tube getDefaultTube() {
    return defaultTube;
  }

  public Tube getTube(final String tubeName) {
    return tubes.get(tubeName);
  }

  public Tube addTube(final Tube tube) {
    return tubes.putIfAbsent(tube.getName(), tube);
  }

  public void print() {
    System.out.println("TubeList... ");
    for (Map.Entry<String, Tube> entry : tubes.entrySet()) {
      String key = entry.getKey().toString();
      Tube value = entry.getValue();
      System.out.println("key: " + key + " value: " + value.getId());
    }
    System.out.println("TubeList... ");
  }

  private final ConcurrentHashMap<String, Tube> tubes;
  private final Tube defaultTube;
  private final static int DEFAULT_TUBE_ID = 0;
  private final static String DEFAULT_TUBE_NAME = "default";
}
