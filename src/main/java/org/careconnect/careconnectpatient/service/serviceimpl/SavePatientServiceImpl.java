package org.careconnect.careconnectpatient.service.serviceimpl;

import org.careconnect.careconnectcommon.entity.PatientEntity;
import org.careconnect.careconnectcommon.exception.ResourceNotFoundException;
import org.careconnect.careconnectcommon.versoning.Address;
import org.careconnect.careconnectcommon.versoning.Name;
import org.careconnect.careconnectpatient.model.request.PatientRequest;
import org.careconnect.careconnectpatient.model.response.PatientResponse;
import org.careconnect.careconnectpatient.repositry.PatientRepo;
import org.careconnect.careconnectpatient.service.SavePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SavePatientServiceImpl implements SavePatientService {

    @Autowired
    PatientRepo patientRepo;

    @Override
    public PatientResponse savePatient(PatientRequest patientRequest) {
        if(patientRepo.existsByEmail(patientRequest.getEmail())){
            throw new ResourceNotFoundException("Patient","EmailId", patientRequest.getEmail());
        }else if(patientRepo.existsByAdharNo(patientRequest.getAdharNo())) {
            throw new ResourceNotFoundException("Patient","Adhar Number",String.valueOf(patientRequest.getAdharNo()));
        }else {
            PatientEntity patientEntity = toPatientEntity(patientRequest);
            PatientEntity savedPatient=patientRepo.save(patientEntity);
            return toPatientResponse(savedPatient);
        }
    }

    @Override
    public List<PatientResponse> getAllPatient() {
        List<PatientEntity> patientEntities = patientRepo.findAll();
        List<PatientResponse> patientResponses = new ArrayList<>();
        for (PatientEntity patientEntity : patientEntities) {
            patientResponses.add(toPatientResponse(patientEntity));
        }
        return patientResponses;
    }

    @Override
    public PatientResponse getPatientById(Long patientId) {
        Optional<PatientEntity> optionalPatient = patientRepo.findById(patientId);
        if (optionalPatient.isPresent()){
            PatientEntity patientEntity=optionalPatient.get();
            return toPatientResponse(patientEntity);
        }
        else {
            throw new ResourceNotFoundException("Patient","Id",String.valueOf(patientId));
        }
    }

    public PatientResponse toPatientResponse(PatientEntity patientEntity) {
        PatientResponse patientResponse = new PatientResponse();

        patientResponse.setRegistrationDate(patientEntity.getRegistrationDate());
        patientResponse.setPatientId(patientEntity.getPatientId());
        patientResponse.setName(getName(patientEntity));
        patientResponse.setBirthdate(patientEntity.getBirthdate());
        patientResponse.setGender(patientEntity.getGender());
        patientResponse.setEmail(patientEntity.getEmail());
        patientResponse.setMobileNo(patientEntity.getMobileNo());
        patientResponse.setAdharNo(patientEntity.getAdharNo());
        patientResponse.setAddress(getAddress(patientEntity));
        patientResponse.setBloodGroup(patientEntity.getBloodGroup());
        patientResponse.setMaritalStatus(patientEntity.getMaritalStatus());
        patientResponse.setOccupation(patientEntity.getOccupation());
        patientResponse.setEmergencyContactNumber(patientEntity.getEmergencyContactNumber());
        patientResponse.setIsSmoker(patientEntity.getIsSmoker());
        patientResponse.setIsAlcoholic(patientEntity.getIsAlcoholic());
        patientResponse.setPreferredLanguage(patientEntity.getPreferredLanguage());
        patientResponse.setHasInsurance(patientEntity.isHasInsurance());
        patientResponse.setSelfPay(patientEntity.isSelfPay());
        patientResponse.setIsMedicaidEligible(patientEntity.getIsMedicaidEligible());
        return patientResponse;
    }

    public PatientEntity toPatientEntity(PatientRequest patientRequest) {
        PatientEntity patientEntity = new PatientEntity();

        patientEntity.setPatientId(patientRequest.getPatientId());
        patientEntity.setName(getName(patientRequest));
        patientEntity.setBirthdate(patientRequest.getBirthdate());
        patientEntity.setGender(patientRequest.getGender());
        patientEntity.setEmail(patientRequest.getEmail());
        patientEntity.setMobileNo(patientRequest.getMobileNo());
        patientEntity.setAdharNo(patientRequest.getAdharNo());
        patientEntity.setAddress(getAddress(patientRequest));
        patientEntity.setBloodGroup(patientRequest.getBloodGroup());
        patientEntity.setMaritalStatus(patientRequest.getMaritalStatus());
        patientEntity.setOccupation(patientRequest.getOccupation());
        patientEntity.setEmergencyContactNumber(patientRequest.getEmergencyContactNumber());
        patientEntity.setIsSmoker(patientRequest.getIsSmoker());
        patientEntity.setIsAlcoholic(patientRequest.getIsAlcoholic());
        patientEntity.setPreferredLanguage(patientRequest.getPreferredLanguage());
        patientEntity.setHasInsurance(patientRequest.isHasInsurance());
        patientEntity.setSelfPay(patientRequest.isSelfPay());
        patientEntity.setIsMedicaidEligible(patientRequest.getIsMedicaidEligible());
        return patientEntity;
    }

    private static Name getName(PatientRequest patientRequest) {
        return Name.builder().firstName(patientRequest.getName().getFirstName())
                .lastName(patientRequest.getName().getLastName()).middleName(patientRequest.getName()
                        .getMiddleName()).build();
    }

    private static org.careconnect.careconnectcommon.common.Address getAddress(PatientEntity patientEntity) {
        return org.careconnect.careconnectcommon.common.Address.builder().street(patientEntity.getAddress().getStreet())
                .city(patientEntity.getAddress().getCity()).state(patientEntity.getAddress().getState()).
                postalCode(patientEntity.getAddress().getPostalCode()).country(patientEntity.getAddress().getCountry()).build();
    }

    private static org.careconnect.careconnectcommon.common.Name getName(PatientEntity patientEntity) {
        return org.careconnect.careconnectcommon.common.Name.builder().firstName(patientEntity.getName().getFirstName())
                .lastName(patientEntity.getName().getLastName()).middleName(patientEntity.getName()
                        .getMiddleName()).build();
    }

    private static Address getAddress(PatientRequest patientRequest) {
        return Address.builder().street(patientRequest.getAddress().getStreet())
                .city(patientRequest.getAddress().getCity()).state(patientRequest.getAddress().getState()).
                postalCode(patientRequest.getAddress().getPostalCode()).country(patientRequest.getAddress().getCountry()).build();
    }
}
