package com.contact.management.model;

import lombok.Data;

import java.util.List;

@Data
public class ContactToCompaniesRequest {
    private Long contactId;
    private List<Long> companyIds;
}
