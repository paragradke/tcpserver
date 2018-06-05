package com.tcp.server.models;

import static java.util.UUID.randomUUID;

public class Job {

  public Job(final String body) {
    this.id = randomUUID().toString();
    this.body = body;
    this.size = body.length();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Tube)) {
      return false;
    }
    Job j = (Job) o;
    return j.id.equals(this.id);
  }


  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + id.hashCode();
    return result;
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
