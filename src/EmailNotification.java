// Logic for Email notifications
public class EmailNotification implements NotificationService {
    @Override
    public void sendNotification(String email) {
        System.out.println("Email notification sent to: " + email);
    }
}