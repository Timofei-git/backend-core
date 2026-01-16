package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

  @Test
  void shouldReuseContactWhenCreatingCustomer() {
    Address address = new Address("Mogilev", "Pushkina", "220220");
    Address billingAddress = new Address("Minsk", "Lenina", "220221");
    Contact contact = new Contact("tima@mail.ru", "+375299700571", address);
    Customer customer = new Customer(UUID.randomUUID(), contact, billingAddress, "GOLD");
    assertThat(customer.contact().address() != customer.billingAddress()).isTrue();


    assertThat(customer.contact().address().city()).isEqualTo("Mogilev");
    assertThat(customer.billingAddress().city()).isEqualTo("Minsk");
  }

  @Test
  void shouldDemonstrateContactReuseAcrossLeadAndCustomer() {
    Address address = new Address("Mogilev", "Pushkina", "220220");
    Contact contact = new Contact("tima@mail.ru", "+375299700571", address);

    Lead lead = new Lead(UUID.randomUUID(), contact, "FinTech", "NEW");
    Customer customer = new Customer(UUID.randomUUID(), contact, address, "GOLD");
    assertThat(customer.contact().address().city()).isEqualTo(lead.contact().address().city());
  }
}