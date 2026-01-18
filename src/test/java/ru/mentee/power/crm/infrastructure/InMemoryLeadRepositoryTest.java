package ru.mentee.power.crm.infrastructure;

import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryLeadRepositoryTest {
  @Test
  void shouldAddNewLead() {
    InMemoryLeadRepository repository = new InMemoryLeadRepository();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead existingLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

    repository.add(existingLead);
    assertThat(repository.findAll().contains(existingLead)).isTrue();
    assertThat(repository.findById(existingLead.id())).contains(existingLead);
  }

  @Test
  void shouldRemoveNewLead() {
    InMemoryLeadRepository repository = new InMemoryLeadRepository();
    UUID id = UUID.randomUUID();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead existingLead = new Lead(id, contact, "TechCorp", "NEW");

    repository.add(existingLead);
    repository.remove(id);
    assertThat(repository.findById(id)).isEmpty();
  }


}
