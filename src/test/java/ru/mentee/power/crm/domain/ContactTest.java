package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ContactTest {

  @Test
  void shouldCreateContactWhenValidData() {
    Contact contact = new Contact("John", "Doe", "john@example.com");
    String firstName = contact.firstName();

    assertThat(firstName).isEqualTo("John");
    assertThat(contact.lastName()).isEqualTo("Doe");
    assertThat(contact.email()).isEqualTo("john@example.com");
  }

  @Test
  void shouldBeEqualWhenSameData() {
    Contact contact = new Contact("John", "Doe", "john@example.com");
    Contact contact1 = new Contact("John", "Doe", "john@example.com");

    assertThat(contact).isEqualTo(contact1);
    assertThat(contact.hashCode()).hasSameHashCodeAs(contact1.hashCode());
  }

  @Test
  void shouldNotBeEqualWhenDifferentData() {
    Contact contact = new Contact("John", "Doe", "john@example.com");
    Contact contact1 = new Contact("John1", "Doe1", "john1@example.com");

    assertThat(contact).isNotEqualTo(contact1);
  }
}