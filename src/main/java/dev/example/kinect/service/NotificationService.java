package dev.example.kinect.service;

import dev.example.kinect.dto.NotificationDTO;
import dev.example.kinect.exception.NotificationNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;

import java.util.List;

public interface NotificationService {
    void createNotification(NotificationDTO notificationDTO) throws ProfileNotFoundException;
    List<NotificationDTO> getUnreadNotifications(Long profile_id);
    Void markAsRead(Long notification_id) throws NotificationNotFoundException;
    void notifyUser(Long profile_id, String subject, String message) throws ProfileNotFoundException;
}
