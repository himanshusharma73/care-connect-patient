package org.careconnect.careconnectpatient.service;

import org.careconnect.careconnectpatient.model.request.PatientRequest;
import org.careconnect.careconnectpatient.model.response.PatientResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SavePatientService {
    PatientResponse savePatient(PatientRequest patientRequest);

    List<PatientResponse> getAllPatient();

    PatientResponse getPatientById(Long patientId);
}
