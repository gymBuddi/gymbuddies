package dev.example.kinect.service.serviceImp;

import dev.example.kinect.dto.NotificationDTO;
import dev.example.kinect.exception.NotificationNotFoundException;
import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.model.Notification;
import dev.example.kinect.model.Profile;
import dev.example.kinect.repository.NotificationRepository;
import dev.example.kinect.repository.ProfileRepository;
import dev.example.kinect.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final ProfileRepository profileRepository;
    public NotificationServiceImp(NotificationRepository notificationRepository, ProfileRepository profileRepository){
        this.notificationRepository = notificationRepository;
        this.profileRepository = profileRepository;
    }
    @Override
    public void createNotification(NotificationDTO notificationDTO) throws ProfileNotFoundException {
        Profile notifiedProfile = profileRepository.findById(notificationDTO.getProfile())
                .orElseThrow(() -> new ProfileNotFoundException("profile not found"));
        Notification notification = new Notification();
        notification.setProfile(notifiedProfile);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setRead(false);
        notification.setMessage(notificationDTO.getMessage());
        notifiedProfile.getNotifications().add(notification);
        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDTO> getUnreadNotifications(Long profile_id) {
        List<NotificationDTO> notifications = new ArrayList<>();
        notificationRepository.findByProfileIdAndReadFalse(profile_id).stream().map(
                notification -> {
                    NotificationDTO notificationDTO = new NotificationDTO();
                    notificationDTO.setId(notification.getId());
                    notificationDTO.setMessage(notification.getMessage());
                    notificationDTO.setProfile(profile_id);
                    notifications.add(notificationDTO);
                    return notificationDTO;
                }
        );
        return notifications;
    }

    @Override
    public Void markAsRead(Long notification_id) throws NotificationNotFoundException {
        Notification notification = notificationRepository.findById(notification_id)
                .orElseThrow(() -> new NotificationNotFoundException("notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
        return null;
    }
}
