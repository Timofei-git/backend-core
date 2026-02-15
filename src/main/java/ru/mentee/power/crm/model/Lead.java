package ru.mentee.power.crm.model;

import ru.mentee.power.crm.domain.LeadStatus;

import java.util.UUID;

public record Lead(
    UUID id,
    String email,
    String company,
    LeadStatus status
) {
}