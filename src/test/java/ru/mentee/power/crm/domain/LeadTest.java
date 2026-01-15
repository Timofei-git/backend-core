package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.storage.LeadStorage;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LeadTest {
  @Test
  void shouldReturnIdWhenGetIdCalled() {
      // Given
    UUID num = UUID.randomUUID();
    var lead = new Lead(num, "test@example.com", "+71234567890", "TestCorp", "NEW");

        // When
    UUID id = lead.id();

        // Then
    assertThat(id).isEqualTo(num);
  }

  @Test
  void shouldReturnPhoneWhenGetPhoneCalled() {
    var lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");

      // When
    String phone = lead.phone();

      // Then
    assertThat(phone).isEqualTo("+71234567890");
  }

  @Test
  void shouldReturnCompanyWhenGetCompanyCalled() {
    var lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");

      // When
    String company = lead.company();

      // Then
    assertThat(company).isEqualTo("TestCorp");
  }

  @Test
  void shouldReturnEmailWhenGetEmailCalled() {
    var lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");

        // When
    String email = lead.email();

        // Then
    assertThat(email).isEqualTo("test@example.com");
  }

  @Test
  void shouldReturnStatusWhenGetStatusCalled() {
    var lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");

        // When
    String status = lead.status();

        // Then
    assertThat(status).isEqualTo("NEW");
  }

  @Test
  void shouldReturnToStringWhenToStringCalled() {
    UUID num = UUID.randomUUID();
    var lead = new Lead(num, "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String result = lead.toString();

    // Then
    assertThat(result).isEqualTo("Lead[id=" + num + ", email=test@example.com, phone=+71234567890, company=TestCorp, status=NEW]" );
  }

  @Test
  void shouldCreateLeadWhenValidData() {
    UUID id = UUID.randomUUID();
    var lead = new Lead(id, "test@example.com", "+71234567890", "TestCorp", "NEW");

    assertThat(lead.id()).isEqualTo(id);
  }

  @Test
  void shouldGenerateUniqueIdsWhenMultipleLeads() {
    var lead = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");
    var lead1 = new Lead(UUID.randomUUID(), "test@example.com", "+71234567890", "TestCorp", "NEW");

    assertThat(lead.id()).isNotEqualTo(lead1.id());
  }

  @Test
  void shouldPreventStringConfusionWhenUsingUUID() {
    UUID id = UUID.randomUUID();
    LeadStorage storage = new LeadStorage();
    var lead = new Lead(id, "test@example.com", "+71234567890", "TestCorp", "NEW");
    storage.add(lead);

    Lead foundLead = storage.findById(id);
    assertThat(foundLead).isEqualTo(lead);

  }
}