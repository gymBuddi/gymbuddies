package dev.example.kinect.service;

import dev.example.kinect.exception.ProfileNotFoundException;

public interface EmailService {
    void sendEmail(Long profileId, String subject, String message) throws ProfileNotFoundException;
}
