package dev.example.kinect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymDTO {
    private Long id;
    private String address;
    private String city;
    @NonNull
    private String name;
}
