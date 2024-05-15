package org.careconnect.careconnectpatient.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.careconnect.careconnectcommon.entity.PatientEntity;
import org.careconnect.careconnectcommon.entity.PatientIllnessEntity;
import org.careconnect.careconnectcommon.exception.ResourceNotFoundException;
import org.careconnect.careconnectpatient.repositry.IllnessRepo;
import org.careconnect.careconnectpatient.repositry.PatientRepo;
import org.careconnect.careconnectcommon.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class IllnessController {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    IllnessRepo illnessRepo;

    @PostMapping("/illness/patients/{patient_Id}")
    public ResponseEntity<ApiResponse> postIllness(@PathVariable long patient_Id, @Valid @RequestBody PatientIllnessEntity patientIllnessEntity){
        Optional<PatientEntity> optionalPatient = patientRepo.findById(patient_Id);
        if(optionalPatient.isPresent()){
            patientIllnessEntity.setPatient(optionalPatient.get());
            PatientIllnessEntity savedIllness = illnessRepo.save(patientIllnessEntity);
            ApiResponse apiResponse=new ApiResponse();
            apiResponse.setData(savedIllness);
            return ResponseEntity.ok(apiResponse);
        }else{
            throw new ResourceNotFoundException("Patient","Id",String.valueOf(patient_Id));
        }
    }

    @GetMapping("/illnesses/patients/{patient_Id}")
    public ResponseEntity<ApiResponse> illnessHistory(@PathVariable long patient_Id){
        Optional<PatientEntity> optionalPatient = patientRepo.findById(patient_Id);
        if(optionalPatient.isPresent()) {
            List<PatientIllnessEntity> illness = illnessRepo.findByPatientPatientId(patient_Id);
            if (illness.isEmpty()){
                throw new ResourceNotFoundException("Illness","Id",String.valueOf(patient_Id));
            }
            else {
                log.info("Patient found {}",illness);
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setData(illness);
                return ResponseEntity.ok(apiResponse);
            }
        }else{
            throw new ResourceNotFoundException("Patient", "Id",String.valueOf(patient_Id));
        }
    }


















}
