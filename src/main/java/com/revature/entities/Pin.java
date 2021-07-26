package com.revature.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PIN")
// @JsonRootName(value = "pin")
@XmlRootElement
public class Pin {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "descript")
  private String desc;

  @Column(name = "lat")
  private double latitude;

  @Column(name = "longitude")
  private double longitude;

  @ManyToOne(cascade= {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
  @JoinColumn(name="owner_email", referencedColumnName="email", foreignKey = @ForeignKey(name="USER_PIN_OWNER_FK"), nullable= true)
  @JsonBackReference
  private User owner;

  public Pin() {}
  
  public Pin(String name, String desc, double latitude, double longitude, User owner) {
    this.name = name;
    this.desc = desc;
    this.latitude = latitude;
    this.longitude = longitude;
    this.owner = owner;
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

  public User getOwner() {
    return owner;
  }

  public void setOwnerUsername(User owner) {
    this.owner = owner;
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
