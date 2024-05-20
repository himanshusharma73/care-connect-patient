package org.careconnect.careconnectpatient.repositry;

import org.careconnect.careconnectcommon.entity.PatientIllnessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IllnessRepo extends JpaRepository<PatientIllnessEntity,Long> {
    List<PatientIllnessEntity> findByPatientId(long patientId);
}
