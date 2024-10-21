package org.careconnect.careconnectpatient.controller;

import jakarta.validation.Valid;
import org.careconnect.careconnectcommon.response.ApiResponse;
import org.careconnect.careconnectpatient.model.request.PatientRequest;
import org.careconnect.careconnectpatient.model.response.PatientResponse;
import org.careconnect.careconnectpatient.service.SavePatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    SavePatientService savePatientService;

    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @PostMapping("/patients")
    public ResponseEntity<ApiResponse> registerPatient(@RequestBody @Valid PatientRequest patientRequest) {
        logger.info("Received request to register a patient: {}", patientRequest);
        PatientResponse patientResponse = savePatientService.savePatient(patientRequest);
        logger.info("Patient saved successfully: {}", patientResponse);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(patientResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/patients")
    public ResponseEntity<ApiResponse> retrievePatients() {
        ApiResponse apiResponse = new ApiResponse();
        List<PatientResponse> patientsResponse = savePatientService.getAllPatient();
        apiResponse.setData(patientsResponse);
        logger.info("Patients retrieved successfully: {}", patientsResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<ApiResponse> retrievePatientById(@PathVariable Long patientId) {
        ApiResponse apiResponse = new ApiResponse();
        PatientResponse patient = savePatientService.getPatientById(patientId);
        apiResponse.setData(patient);
        logger.info("Patient retrieved successfully By id:{} -> : {}", patientId, patient);
        return ResponseEntity.ok(apiResponse);
    }
}
