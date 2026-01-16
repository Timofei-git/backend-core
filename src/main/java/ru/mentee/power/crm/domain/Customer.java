package ru.mentee.power.crm.domain;

import java.util.Arrays;
import java.util.UUID;

public record Customer(UUID id, Contact contact,Address billingAddress, String loyaltyTier) {
  private static final String[] ALLOWED_STATUSES = {"BRONZE", "SILVER", "GOLD"};
  public Customer {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null or empty");
    }

    if (contact == null) {
      throw new IllegalArgumentException("Contact code cannot be null or empty");
    }

    if (billingAddress == null) {
      throw new IllegalArgumentException("Status code cannot be null or empty");
    }

    if (!Arrays.asList(ALLOWED_STATUSES).contains(loyaltyTier)) {
      throw new IllegalArgumentException("Invalid status");
    }
  }
}
