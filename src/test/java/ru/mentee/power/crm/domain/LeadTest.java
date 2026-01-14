package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LeadTest {
  @Test
  void shouldReturnId_whenGetIdCalled() {
      // Given
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

        // When
    String id = lead.getId();

        // Then
    assertThat(id).isEqualTo("L1");
  }

  @Test
  void shouldReturnPhone_whenGetPhoneCalled() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

      // When
    String phone = lead.getPhone();

      // Then
    assertThat(phone).isEqualTo("+71234567890");
  }

  @Test
  void shouldReturnCompany_whenGetCompanyCalled() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

      // When
    String company = lead.getCompany();

      // Then
    assertThat(company).isEqualTo("TestCorp");
  }

  @Test
  void shouldReturnEmail_whenGetEmailCalled() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

        // When
    String email = lead.getEmail();

        // Then
    assertThat(email).isEqualTo("test@example.com");
  }

  @Test
  void shouldReturnStatus_whenGetStatusCalled() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

        // When
    String status = lead.getStatus();

        // Then
    assertThat(status).isEqualTo("NEW");
  }

  @Test
  void shouldReturntoString_whentoStringCalled() {
    Lead lead = new Lead("L1", "test@example.com", "+71234567890", "TestCorp", "NEW");

    // When
    String result = lead.toString();

    // Then
    assertThat(result).isEqualTo("Id: L1, Email: test@example.com, Phone: +71234567890, Status: NEW, Company: TestCorp" );
  }

}