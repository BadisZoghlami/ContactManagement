package com.contact.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Address name is required")
    private String address;
    private boolean isFreelance;
    private String tvaNumber;
    @ManyToMany
    @JoinTable(
            name = "contact_company",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Company> companies = new HashSet<>();

    @AssertTrue(message = "Freelance contacts must have a TVA number")
    public boolean isVatNumberRequired() {
        return !isFreelance || (tvaNumber != null && !tvaNumber.trim().isEmpty());
    }
}
