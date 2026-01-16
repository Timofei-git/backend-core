package ru.mentee.power.crm.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public record Lead (
    UUID id,
    Contact contact,
    String company,
    String status) {
  private static final String[] ALLOWED_STATUSES = {"NEW", "QUALIFIED", "CONVERTED"};
  public Lead {
    if (id == null) {
      throw new IllegalArgumentException("Id cannot be null or empty");
    }

    if (contact == null) {
      throw new IllegalArgumentException("Contact code cannot be null or empty");
    }

    if (status == null || status.isBlank()) {
      throw new IllegalArgumentException("Status code cannot be null or empty");
    }

    if (!Arrays.asList(ALLOWED_STATUSES).contains(status)) {
      throw new IllegalArgumentException("Invalid status");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Lead lead = (Lead) o;
    return Objects.equals(id, lead.id) && Objects.equals(status, lead.status) && Objects.equals(company, lead.company) && Objects.equals(contact, lead.contact);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, contact, company, status);
  }
}


