package com.petstore.qa.enums;

import java.util.Arrays;

public enum PetStatus {
  AVAILABLE("available"),
  PENDING("pending"),
  SOLD("sold");

  private final String petStatus;

  PetStatus(String petStatus) {
    this.petStatus = petStatus;
  }

  public static PetStatus fromName(String name) {
    return Arrays.stream(PetStatus.values())
        .filter(v -> v.petStatus.equals(name))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown pet status: " + name));
  }

  public String getValue() {
    return petStatus;
  }
}
