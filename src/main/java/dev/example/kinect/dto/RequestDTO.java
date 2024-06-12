package dev.example.kinect.dto;

import dev.example.kinect.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDTO {
    private Long id;
    private boolean isMatched;
    private Long offer;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    @Enumerated(EnumType.STRING)
    private Status status;
}
