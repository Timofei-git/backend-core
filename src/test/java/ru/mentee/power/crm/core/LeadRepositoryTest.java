package ru.mentee.power.crm.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;
import ru.mentee.power.crm.infrastructure.InMemoryLeadRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

class LeadRepositoryTest {
  Address address = new Address("Mogilev", "Pysina", "220102");
  Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
  Lead lead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

  @Test
  @DisplayName("Should automatically deduplicate leads by id")
  void shouldDeduplicateLeadsById() {
    LeadRepository leadRepository = new LeadRepository();
    leadRepository.add(lead);
    leadRepository.add(lead);
    assertThat(leadRepository.size()).isEqualTo(1);
  }

  @Test
  @DisplayName("Should allow different leads with different ids")
  void shouldAllowDifferentLeads() {
    LeadRepository leadRepository = new LeadRepository();
    Lead newLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");
    leadRepository.add(lead);
    leadRepository.add(newLead);
    assertThat(leadRepository.size()).isEqualTo(2);
  }

  @Test
  @DisplayName("Should find existing lead through contains")
  void shouldFindExistingLead() {
    LeadRepository leadRepository = new LeadRepository();
    leadRepository.add(lead);
    assertThat(leadRepository.contains(lead)).isTrue();
  }

  @Test
  @DisplayName("Should return unmodifiable set from findAll")
  void shouldReturnUnmodifiableSet() {
    LeadRepository leadRepository = new LeadRepository();
    Set<Lead> result = leadRepository.findAll();
    assertThatThrownBy(() -> result.add(new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW")))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  @DisplayName("Should perform contains() faster than ArrayList")
  void shouldPerformFasterThanArrayList() {
    LeadRepository leadRepository = new LeadRepository();
    UUID id = UUID.randomUUID();
    Lead lead1= new Lead(id, contact, "TechCorp", "NEW");
    for (int i = 0; i < 10000;  i++) {
      leadRepository.add(lead1);
    }
    List<Lead> storage = new ArrayList<>();
    for (int i = 0; i < 10000;  i++) {
      storage.add(lead1);
    }
    // Подсказка: используйте System.nanoTime() для замера времени
    long start = System.nanoTime();
    for (int i = 0; i < leadRepository.size(); i++) {
      leadRepository.contains(lead);
    }
    long duration = System.nanoTime() - start;
    long start1 = System.nanoTime();
    for (int i = 0; i < storage.size(); i++) {
      storage.contains(lead);
    }
    long duration1 = System.nanoTime() - start1;
    System.out.println(duration1);
    System.out.println(duration);

    assertThat(duration).isLessThan(duration1);
    assertThat(leadRepository.size()).isEqualTo(1);
    assertThat(storage.size()).isEqualTo(10000);
  }
}