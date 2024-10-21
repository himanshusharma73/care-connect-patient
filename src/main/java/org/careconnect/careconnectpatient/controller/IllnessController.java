package org.careconnect.careconnectpatient.controller;

import jakarta.validation.Valid;
import org.careconnect.careconnectpatient.model.request.IllnessRequest;
import org.careconnect.careconnectcommon.response.ApiResponse;
import org.careconnect.careconnectpatient.service.IllnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class IllnessController {

    @Autowired
    IllnessService illnessService;

    @PostMapping("/illness/patients/{patientId}")
    public ResponseEntity<ApiResponse> saveIllness(@PathVariable long patientId,@Valid @RequestBody IllnessRequest illnessRequest){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(illnessService.saveIllness(patientId, illnessRequest));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/illness/patients/{patientId}")
    public ResponseEntity<ApiResponse> illnessHistory(@PathVariable long patientId){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(illnessService.getIllnessHistory(patientId));
        return ResponseEntity.ok(apiResponse);
    }

}
