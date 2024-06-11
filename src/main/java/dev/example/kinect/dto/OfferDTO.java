package dev.example.kinect.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class OfferDTO {
    private Long id;
    private List<RequestDTO> request;
    private Long planning;
    private LocalDateTime publicationDate;
    private LocalDateTime planningTime;
    private Boolean isActive;
}
