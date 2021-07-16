package com.revature.entities;

@Entity
@Table(name = "pin")
public class Pin {
  @Id @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "desc")
  private String desc;

  @Column(name = "lat")
  private double latitude;

  @Column(name = "long")
  private double longitude;

  @ManyToOne
  @Column(name = "ownerusername_fk")
  private String ownerUsername;
  /*
  potential add-ons:
  category
   */

  public Pin(String name, String desc, double latitude, double longitude, String ownerUsername) {
    this.name = name;
    this.desc = desc;
    this.latitude = latitude;
    this.longitude = longitude;
    this.ownerUsername = ownerUsername;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public String getOwnerUsername() {
    return ownerUsername;
  }

  public void setOwnerUsername(String ownerUsername) {
    this.ownerUsername = ownerUsername;
  }

  @Override
  public String toString() {
    return "com.revature.entities.Pin{" +
      "name='" + name + '\'' +
      ", desc='" + desc + '\'' +
      ", latitude=" + latitude +
      ", longitude=" + longitude +
      '}';
  }
}
