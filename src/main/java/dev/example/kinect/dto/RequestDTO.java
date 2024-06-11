package dev.example.kinect.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDTO {
    private Long id;
    private boolean isMatched;
    private Long offer;
    private LocalDateTime creationDate;
}
