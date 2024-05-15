package org.careconnect.careconnectpatient.controller;

import jakarta.validation.Valid;
import org.careconnect.careconnectcommon.entity.PatientEntity;
import org.careconnect.careconnectcommon.exception.PatientExitException;
import org.careconnect.careconnectcommon.exception.ResourceNotFoundException;
import org.careconnect.careconnectpatient.repositry.PatientRepo;
import org.careconnect.careconnectcommon.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    PatientRepo patientRepo;

    @PostMapping("/patients")
    public ResponseEntity<ApiResponse> registerPatient(@Valid @RequestBody PatientEntity patientEntity){
            if(patientRepo.existsByEmail(patientEntity.getEmail())){
                throw new PatientExitException("Patient","EmailId",patientEntity.getEmail());
            }else if(patientRepo.existsByAdharNo(patientEntity.getAdharNo())) {
                throw new PatientExitException("Patient","Adhar Number",String.valueOf(patientEntity.getAdharNo()));
            }else {
                PatientEntity savedPatient=patientRepo.save(patientEntity);
                ApiResponse apiResponse=new ApiResponse();
                apiResponse.setData(savedPatient);
                return ResponseEntity.ok(apiResponse);
            }
    }

    @GetMapping("/patients")
    public List retrievePatients(){
        return patientRepo.findAll();
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<ApiResponse> retrievePatientById(@PathVariable Long patientId) {
        Optional<PatientEntity> optionalPatient = patientRepo.findById(patientId);
        if (optionalPatient.isPresent()){
            PatientEntity patientEntity=optionalPatient.get();
            ApiResponse apiResponse=new ApiResponse();
            apiResponse.setData(patientEntity);
            return ResponseEntity.ok(apiResponse);
        }
        else {
            throw new ResourceNotFoundException("Patient","Id",String.valueOf(patientId));
        }
    }
}
