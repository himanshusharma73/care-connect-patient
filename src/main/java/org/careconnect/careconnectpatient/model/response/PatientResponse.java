package org.careconnect.careconnectpatient.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.careconnect.careconnectcommon.common.Address;
import org.careconnect.careconnectcommon.common.Name;

import java.time.LocalDate;

@JsonIgnoreProperties
@Data
public class PatientResponse {

    private LocalDate registrationDate;

    private long patientId;

    private Name name;

    private LocalDate birthdate;

    private String gender;

    private String email;

    private long mobileNo;


    private long adharNo;

    private Address address;

    private String bloodGroup;

    private String maritalStatus;

    private String occupation;

    private long emergencyContactNumber;

    private Boolean isSmoker;

    private Boolean isAlcoholic;

    private String preferredLanguage;

    private boolean hasInsurance;

    private boolean isSelfPay;

    private Boolean isMedicaidEligible;

}
