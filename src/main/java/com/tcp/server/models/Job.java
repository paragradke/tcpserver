package com.tcp.server.models;

import static java.util.UUID.randomUUID;

public class Job {

  public Job(final String body) {
    this.id = randomUUID().toString();;
    this.body = body;
    this.size = body.length();
  }

  public String getId() {
    return id;
  }

  public String getBody() {
    return body;
  }

  public int getSize() {
    return size;
  }

  private String id;
  private String body;
  private int size;

}
