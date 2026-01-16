package ru.mentee.power.crm.domain;

public record Contact(String email, String phone, Address address) {
  public Contact {
    if (email == null || email.isBlank()) {
      throw new IllegalArgumentException("Email cannot be null or empty");
    }

    if (phone == null || phone.isBlank()) {
      throw new IllegalArgumentException("Phone code cannot be null or empty");
    }

    if (address == null) {
      throw new IllegalArgumentException("Address code cannot be null or empty");
    }
  }
}