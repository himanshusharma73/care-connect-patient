package org.careconnect.careconnectpatient.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.careconnect.careconnectcommon.common.Address;
import org.careconnect.careconnectcommon.common.Name;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties
public class PatientRequest {

    private long patientId;

    @Valid
    private Name name;

    @Past(message = "Enter correct Date")
    @NotNull(message = "Enter date")
    private LocalDate birthdate;

    @NotBlank(message = "Enter Gender")
    private String gender;

    @Column(unique = true)
    @NotBlank(message = "Enter Email")
    @Email
    private String email;

    @Column(unique = true)
    @Min(value = 1000000000l,message = "Mobile number must be at least 10 digit")
    @Max(value = 9999999999l,message = "Mobile number must not exceed 10 digit")
    private long mobileNo;


    @Column(unique = true)
    @Min(value = 100000000000l,message = "Enter correct Adhar number")
    @Max(value = 999999999999l,message = "Enter correct Adhar number")
    private long adharNo;

    @Valid
    private Address address;

    @NotBlank(message = "Enter Blood Group")
    private String bloodGroup;

    @NotBlank(message = "Enter Marital Status")
    private String maritalStatus;

    @NotBlank(message = "Enter Occupation")
    private String occupation;

    @Min(value = 1000000000l,message = "Mobile number must be at least 10 digit")
    @Max(value = 9999999999l,message = "Mobile number must not exceed 10 digit")
    private long emergencyContactNumber;

    @NotNull(message = "Enter smoking status")
    private Boolean isSmoker;

    @NotNull(message = "Enter Alcoholic status")
    private Boolean isAlcoholic;

    @NotBlank(message = "Enter Preferred Language")
    private String preferredLanguage;

    @NotNull(message = "Enter Insurance status")
    private boolean hasInsurance;

    @NotNull(message = "Please indicate if you are self-pay")
    private boolean isSelfPay;

    @NotNull(message = "Please indicate Medicaid eligibility")
    private Boolean isMedicaidEligible;

}
