package com.petstore.qa.enums;

public enum PetType {
  DOG(1, "Dogs"),
  CAT(2, "Cats");

  private final int id;
  private final String name;

  PetType(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getID() {
    return id;
  }

  public String getName() {
    return name;
  }
}
