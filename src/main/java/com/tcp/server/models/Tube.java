package com.tcp.server.models;

import java.util.concurrent.ConcurrentLinkedQueue;
import static java.util.UUID.randomUUID;

public class Tube {

  public Tube(final String name) {
    this.id = randomUUID().toString();
    this.name = name;
    jobs = new ConcurrentLinkedQueue();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Tube)) {
      return false;
    }
    Tube t = (Tube) o;
    return t.name.equals(this.name);
  }


  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + name.hashCode();
    return result;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public Job getNextJob() {
    return jobs.poll();
  }

  public void putJob(final Job job) {
    jobs.add(job);
  }

  public boolean isEmpty() {
    return jobs.isEmpty();
  }

  public boolean removeJob(final String id) {
    Job toBeRemoved = null;
    for (Job job: jobs) {
      if (job.getId().equals(id)) {
        toBeRemoved = job;
        break;
      }
    }
    return toBeRemoved == null ? false : jobs.remove(toBeRemoved);
  }

  public void print() {
    for (Job job: jobs) {
      System.out.println("id: " + job.getId() + " value: " + job.getBody());
    }
  }

  private ConcurrentLinkedQueue<Job> jobs;
  private String name;
  private String id;
}
