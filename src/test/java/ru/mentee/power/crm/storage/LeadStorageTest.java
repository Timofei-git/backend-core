package ru.mentee.power.crm.storage;

import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LeadStorageTest {

  @Test
  void shouldAddLeadWhenLeadIsUnique() {
        // Given
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    LeadStorage storage = new LeadStorage();
    Lead uniqueLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

        // When
    boolean added = storage.add(uniqueLead);

        // Then
    assertThat(added).isTrue();
    assertThat(storage.size()).isEqualTo(1);
    assertThat(storage.findAll()).containsExactly(uniqueLead);
  }

  @Test
  void shouldRejectDuplicateWhenEmailAlreadyExists() {
        // Given
    LeadStorage storage = new LeadStorage();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead existingLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");
    Lead duplicateLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");
    storage.add(existingLead);

        // When
    boolean added = storage.add(duplicateLead);

        // Then
    assertThat(added).isFalse();
    assertThat(storage.size()).isEqualTo(1);
    assertThat(storage.findAll()).containsExactly(existingLead);
  }

 // @Test
//  void shouldThrowExceptionWhenStorageIsFull() {
//        // Given: Заполни хранилище 100 лидами
//    LeadStorage storage = new LeadStorage();
//    for (int index = 0; index < 100; index++) {
//      storage.add(new Lead(String.valueOf(index),
//      "lead" + index + "@mail.ru", "+7000", "Company", "NEW"));
//    }
//
//        // When + Then: 101-й лид должен выбросить исключение
//    Lead hundredFirstLead = new Lead("101", "lead101@mail.ru", "+7001", "Company", "NEW");
//
//    assertThatThrownBy(() -> storage.add(hundredFirstLead))
//            .isInstanceOf(IllegalStateException.class)
//            .hasMessageContaining("Storage is full");
//  }

  @Test
  void shouldReturnOnlyAddedLeadsWhenFindAllCalled() {
        // Given
    LeadStorage storage = new LeadStorage();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Contact contact1 = new Contact("timasgridin1@mail.ru", "+3752929700571", address);
    Lead firstLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(UUID.randomUUID(), contact1,  "StartupLab", "NEW");
    storage.add(firstLead);
    storage.add(secondLead);

        // When
    Lead[] result = storage.findAll();

        // Then
    assertThat(result).hasSize(2);
    assertThat(result).containsExactly(firstLead, secondLead);
  }
}