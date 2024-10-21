package org.careconnect.careconnectpatient.service;

import org.careconnect.careconnectpatient.model.request.IllnessRequest;
import org.careconnect.careconnectpatient.model.response.IllnessResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IllnessService {
    IllnessResponse saveIllness(long patientId, IllnessRequest illnessRequest);
    List<IllnessResponse> getIllnessHistory(long patientId);
}
