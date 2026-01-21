package ru.mentee.power.crm.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.model.Lead;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class LeadRepositoryTest {
  private LeadRepository repository;

  @BeforeEach
  void setUp() {
    repository = new LeadRepository();
  }

  @Test
  void shouldSaveAndFindLeadByIdWhenLeadSaved() {
    Lead lead = new Lead("1", "timas@mail.ru", "+375299700571", "BigTech", "NEW");
    repository.save(lead);

    assertThat(repository.findById("1")).isEqualTo(lead);
  }

  @Test
  void shouldReturnNullWhenLeadNotFound() {
    assertThat(repository.findById("unknown-id")).isNull();
  }

  @Test
  void shouldReturnAllLeadsWhenMultipleLeadsSaved() {
    Lead lead = new Lead("1", "timas@mail.ru", "+375299700571", "BigTech", "NEW");
    repository.save(lead);
    Lead lead1 = new Lead("2", "timas@mail.ru", "+375299700571", "BigTech", "NEW");
    repository.save(lead1);
    Lead lead2 = new Lead("3", "timas@mail.ru", "+375299700571", "BigTech", "NEW");
    repository.save(lead2);

    assertThat(repository.size()).isEqualTo(3);
  }

  @Test
  void shouldDeleteLeadWhenLeadExists() {
    Lead lead = new Lead("1", "timas@mail.ru", "+375299700571", "BigTech", "NEW");
    repository.save(lead);
    repository.delete(lead.id());
    assertThat(repository.findById("1")).isNull();
    assertThat(repository.size()).isEqualTo(0);
  }

  @Test
  void shouldOverwriteLeadWhenSaveWithSameId() {
    Lead lead = new Lead("lead-1", "timas1@mail.ru", "+375299700571", "BigTech", "NEW");
    repository.save(lead);
    Lead lead1 = new Lead("lead-1", "timas2@mail.ru", "+375299700571", "BigTech", "NEW");
    repository.save(lead1);
    assertThat(repository.findById("lead-1")).isEqualTo(lead1);
    assertThat(repository.size()).isEqualTo(1);
  }

  @Test
  void shouldFindFasterWithMapThanWithListFilter() {
    // Given: Создать 1000 лидов
    List<Lead> leadList = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      String id = "lead-";
      Lead lead = new Lead(id + i, "email" + i + "@test.com", "+7" + i, "Company" + i, "NEW");
      repository.save(lead);
      leadList.add(lead);
    }

    String targetId = "lead-500";  // Средний элемент

    // When: Поиск через Map
    long mapStart = System.nanoTime();
    Lead foundInMap = repository.findById(targetId);
    long mapDuration = System.nanoTime() - mapStart;

    // When: Поиск через List.stream().filter()
    long listStart = System.nanoTime();
    Lead foundInList = leadList.stream()
        .filter(lead -> lead.id().equals(targetId))
        .findFirst()
        .orElse(null);
    long listDuration = System.nanoTime() - listStart;

    // Then: Map должен быть минимум в 10 раз быстрее
    assertThat(foundInMap).isEqualTo(foundInList);
    assertThat(listDuration).isGreaterThan(mapDuration * 10);

    System.out.println("Map поиск: " + mapDuration + " ns");
    System.out.println("List поиск: " + listDuration + " ns");
    System.out.println("Ускорение: " + (listDuration / mapDuration) + "x");
  }
}