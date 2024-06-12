package dev.example.kinect.model;

import dev.example.kinect.model.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Profile sender;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    private boolean isMatched;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdatedDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean enabled;
}
