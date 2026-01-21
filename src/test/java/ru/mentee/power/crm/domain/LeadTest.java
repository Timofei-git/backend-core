package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LeadTest {

  @Test
  void shouldCreateLeadWhenValidData() {
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead lead = new Lead(UUID.randomUUID(), contact, "Microsoft", "NEW");

    assertThat(lead.contact()).isEqualTo(contact);
  }

  @Test
  void shouldAccessEmailThroughDelegationWhenLeadCreated() {
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead lead = new Lead(UUID.randomUUID(), contact, "Microsoft", "NEW");

    String email = lead.contact().email();
    assertThat(email).isEqualTo("timasgridin@mail.ru");
    String city = lead.contact().address().city();
    assertThat(city).isEqualTo("Mogilev");
  }

  @Test
  void shouldBeEqualWhenSameIdButDifferentContact() {
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Contact contact1 = new Contact("timasgridin1@mail.ru", "+375299700570", address);
    UUID num = UUID.randomUUID();
    Lead lead = new Lead(num, contact, "Microsoft", "NEW");
    Lead lead1 = new Lead(num, contact1, "Microsoft", "NEW");

    assertThat(lead.equals(lead1)).isFalse();
  }

  @Test
  void shouldThrowExceptionWhenContactIsNull() {

    assertThatThrownBy(() -> new Lead(UUID.randomUUID(), null, "Microsoft", "NEW"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Contact");
  }

  @Test
  void shouldThrowExceptionWhenInvalidStatus() {
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);

    assertThatThrownBy(() -> new Lead(UUID.randomUUID(), contact, "Microsoft", "Invalid"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Invalid status");


  }

  @Test
  void shouldDemonstrateThreeLevelCompositionWhenAccessingCity() {
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead lead = new Lead(UUID.randomUUID(), contact, "Microsoft", "NEW");

    String city = lead.contact().address().city();
    assertThat(city).isEqualTo("Mogilev");
  }
}