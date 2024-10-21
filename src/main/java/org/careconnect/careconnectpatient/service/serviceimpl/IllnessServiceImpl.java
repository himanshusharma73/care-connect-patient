package org.careconnect.careconnectpatient.service.serviceimpl;

import org.careconnect.careconnectcommon.entity.PatientEntity;
import org.careconnect.careconnectcommon.entity.PatientIllnessEntity;
import org.careconnect.careconnectcommon.exception.ResourceNotFoundException;
import org.careconnect.careconnectpatient.model.request.IllnessRequest;
import org.careconnect.careconnectpatient.model.response.IllnessResponse;
import org.careconnect.careconnectpatient.repositry.IllnessRepo;
import org.careconnect.careconnectpatient.repositry.PatientRepo;
import org.careconnect.careconnectpatient.service.IllnessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IllnessServiceImpl implements IllnessService {

    @Autowired
    PatientRepo patientRepo;

    @Autowired
    IllnessRepo illnessRepo;

    private static final Logger logger = LoggerFactory.getLogger(IllnessServiceImpl.class);

    @Override
    public IllnessResponse saveIllness(long patientId, IllnessRequest illnessRequest) {
        logger.info("Illness request received {} : ", illnessRequest);
        Optional<PatientEntity> optionalPatient = patientRepo.findById(patientId);
        if (optionalPatient.isPresent()) {
            illnessRequest.setPatientId(optionalPatient.get().getPatientId());
            PatientIllnessEntity patientillnessEntity = toPatientillnessEntity(illnessRequest);
            PatientIllnessEntity savedIllness = illnessRepo.save(patientillnessEntity);
            IllnessResponse illnessResponse = toIllnessResponse(savedIllness);
            logger.info("Illness response {} : ", illnessResponse);
            return illnessResponse;

        } else {
            throw new ResourceNotFoundException("Patient", "Id", String.valueOf(patientId));
        }
    }

    @Override
    public List<IllnessResponse> getIllnessHistory(long patientId) {
        Optional<PatientEntity> optionalPatient = patientRepo.findById(patientId);
        if (optionalPatient.isPresent()) {
            List<PatientIllnessEntity> illness = illnessRepo.findByPatientId(patientId);
            if (illness.isEmpty()) {
                throw new ResourceNotFoundException("Illness", "Id", String.valueOf(patientId));
            } else {
                logger.info("Illness found with patientId {}, illness {}",patientId, illness);
                List<IllnessResponse> illnessResponses = new ArrayList<>();
                for (PatientIllnessEntity illnessEntity : illness) {
                    IllnessResponse illnessResponse = toIllnessResponse(illnessEntity);
                    illnessResponses.add(illnessResponse);
                }
                return illnessResponses;
            }
        } else {
            throw new ResourceNotFoundException("Patient", "Id", String.valueOf(patientId));
        }
    }


    PatientIllnessEntity toPatientillnessEntity(IllnessRequest illnessRequest) {
        PatientIllnessEntity illnessEntity = new PatientIllnessEntity();
        illnessEntity.setPatientId(illnessRequest.getPatientId());
        illnessEntity.setIllnessId(illnessRequest.getIllnessId());
        illnessEntity.setIllness(illnessRequest.getIllness());
        illnessEntity.setDescription(illnessRequest.getDescription());
        illnessEntity.setIllnessDate(illnessRequest.getIllnessDate());
        return illnessEntity;
    }

    IllnessResponse toIllnessResponse(PatientIllnessEntity patientIllnessEntity) {
        IllnessResponse illnessResponse = new IllnessResponse();
        illnessResponse.setPatientId(patientIllnessEntity.getPatientId());
        illnessResponse.setIllnessId(patientIllnessEntity.getIllnessId());
        illnessResponse.setIllness(patientIllnessEntity.getIllness());
        illnessResponse.setDescription(patientIllnessEntity.getDescription());
        illnessResponse.setIllnessDate(patientIllnessEntity.getIllnessDate());
        return illnessResponse;
    }
}
