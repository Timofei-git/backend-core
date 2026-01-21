package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ContactTest {

  @Test
  void shouldCreateContactWhenValidData() {
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);

    assertThat(contact.address()).isEqualTo(address);
    assertThat(contact.address().city()).isEqualTo("Mogilev");
  }

  @Test
  void shouldDelegateToAddressWhenAccessingCity() {
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);

    assertThat(contact.address().street()).isEqualTo("Pysina");
    assertThat(contact.address().city()).isEqualTo("Mogilev");
  }

  @Test
  void shouldThrowExceptionWhenAddressIsNull() {
    assertThatThrownBy(() -> new Contact("timasgridin@mail.ru", "+375299700571", null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Address");
  }
}