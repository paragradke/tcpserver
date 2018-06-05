package com.tcp.server.models;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WatchList {

  public WatchList(final TubeList tubeList) {
    this.tubeList = tubeList;
    tubes = new HashSet<Tube>();
    tubes.add(tubeList.getDefaultTube());
    producerDefaultTube = tubeList.getDefaultTube();
  }

  public int getSize() {
    return tubes.size();
  }

  public Tube addTubeToWatchList(final Tube tube) {
    Tube newTube = tubeList.addTube(tube);
    this.tubes.add(tube);
    return tube;
  }

  public Set<Tube> getTubes() {
    return tubes;
  }


  public Tube getProducerDefaultTube() {
    return producerDefaultTube;
  }

  public Tube getFromGlobalTubeList(final String tubeName) {
    return tubeList.getTube(tubeName);
  }

  public TubeList getTubeList() {
    return tubeList;
  }

  public void print() {
    System.out.println("WatchList... ");
    Iterator<Tube> it = tubes.iterator();
    while (it.hasNext()) {
      Tube tube = it.next();
      System.out.println(tube.getName());
    }
    System.out.println("WatchList... ");
  }

  public void setProducerDefaultTube(Tube producerDefaultTube) {
    this.producerDefaultTube = producerDefaultTube;
  }

  private Tube producerDefaultTube;
  private Set<Tube> tubes;
  private TubeList tubeList;
}
