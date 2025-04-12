package org.careconnect.careconnectpatient.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonIgnoreProperties
public class IllnessRequest {

    private long illnessId;

    @NotNull(message = "Illness list must not be null")
    @NotEmpty(message = "Illness list must not be empty")
    private List<String> illness;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotNull(message = "Date must not be null")
    private LocalDate illnessDate;
}
