package ru.mentee.power.crm.domain;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LeadEqualsHashCodeTest {

  @Test
  void shouldBeReflexiveWhenEqualsCalledOnSameObject() {
    // Given
    UUID num = UUID.randomUUID();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead lead = new Lead(num, contact,  "TechCorp", "NEW");

    // Then: Объект равен сам себе (isEqualTo использует equals() внутри)
    assertThat(lead).isEqualTo(lead);
  }

  @Test
  void shouldBeSymmetricWhenEqualsCalledOnTwoObjects() {
    // Given
    UUID num = UUID.randomUUID();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead firstLead = new Lead(num, contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(num, contact, "TechCorp", "NEW");

    // Then: Симметричность — порядок сравнения не важен
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(firstLead);
  }

  @Test
  void shouldBeTransitiveWhenEqualsChainOfThreeObjects() {
    // Given
    UUID num = UUID.randomUUID();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead firstLead = new Lead(num, contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(num, contact, "TechCorp", "NEW");
    Lead thirdLead = new Lead(num, contact, "TechCorp", "NEW");

    // Then: Транзитивность — если A=B и B=C, то A=C
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(secondLead).isEqualTo(thirdLead);
    assertThat(firstLead).isEqualTo(thirdLead);
  }

  @Test
  void shouldBeConsistentWhenEqualsCalledMultipleTimes() {
    // Given
    UUID num = UUID.randomUUID();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead firstLead = new Lead(num, contact, "TechCorp", "NEW");
    Lead secondLead = firstLead = new Lead(num, contact, "TechCorp", "NEW");

    // Then: Результат одинаковый при многократных вызовах
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead).isEqualTo(secondLead);
  }

  @Test
  void shouldReturnFalseWhenEqualsComparedWithNull() {
    // Given
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead lead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

    // Then: Объект не равен null (isNotEqualTo проверяет equals(null) = false)
    assertThat(lead).isNotEqualTo(null);
  }

  @Test
  void shouldHaveSameHashCodeWhenObjectsAreEqual() {
    // Given
    UUID num = UUID.randomUUID();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead firstLead = new Lead(num, contact, "TechCorp", "NEW");
    Lead secondLead = new Lead(num, contact, "TechCorp", "NEW");

    // Then: Если объекты равны, то hashCode должен быть одинаковым
    assertThat(firstLead).isEqualTo(secondLead);
    assertThat(firstLead.hashCode()).isEqualTo(secondLead.hashCode());
  }

  @Test
  void shouldWorkInHashMapWhenLeadUsedAsKey() {
    // Given
    UUID num = UUID.randomUUID();
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead keyLead = new Lead(num, contact, "TechCorp", "NEW");
    Lead lookupLead = new Lead(num, contact, "TechCorp", "NEW");

    Map<Lead, String> map = new HashMap<>();
    map.put(keyLead, "CONTACTED");

    // When: Получаем значение по другому объекту с тем же id
    String status = map.get(lookupLead);

    // Then: HashMap нашел значение благодаря equals/hashCode
    assertThat(status).isEqualTo("CONTACTED");
  }

  @Test
  void shouldNotBeEqualWhenIdsAreDifferent() {
    // Given
    Address address = new Address("Mogilev", "Pysina", "220102");
    Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);
    Lead firstLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");
    Lead differentLead = new Lead(UUID.randomUUID(), contact, "TechCorp", "NEW");

    // Then: Разные id = разные объекты (isNotEqualTo использует equals() внутри)
    assertThat(firstLead).isNotEqualTo(differentLead);
  }
}