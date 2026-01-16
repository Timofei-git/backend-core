package ru.mentee.power.crm.storage;

import ru.mentee.power.crm.domain.Address;
import ru.mentee.power.crm.domain.Contact;
import ru.mentee.power.crm.domain.Lead;

import java.util.UUID;

public class LeadStorage {
  private Lead[] leads = new Lead[100];
  Address address = new Address("Mogilev", "Pysina", "220102");
  Contact contact = new Contact("timasgridin@mail.ru", "+375299700571", address);

  public boolean add(Lead lead) {
    for (int i = 0; i < leads.length; i++) {
      if (leads[i] != null && leads[i].contact().email().equals(lead.contact().email())) {
        return false;
      }
    }

    for (int i = 0; i < leads.length; i++) {
      if (leads[i] == null) {
        leads[i] = lead;
        return true;
      }
    }
    throw new IllegalStateException("Storage is full, cannot add more leads");
  }

  public Lead[] findAll() {
    int count = size();

    Lead[] result = new Lead[count];
    int resultIndex = 0;
    for (int i = 0; i < leads.length; i++) {
      if (leads[i] != null) {
        result[resultIndex++] = leads[i];
      }
    }
    return result;
  }

  public Lead findById(UUID id) {
    Lead[] foundLeads = findAll();
    for (int i = 0; i < foundLeads.length; i++) {
      if (id == foundLeads[i].id()) {
        return foundLeads[i];
      }
    }
    return null;
  }

  public int size() {
    int count = 0;
    for (int i = 0; i < leads.length; i++) {
      if (leads[i] != null) {
        count++;
      }
    }
    return count;
  }
}