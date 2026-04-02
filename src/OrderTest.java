public class OrderTest {
    public static void main(String[] args) {
        // Setup the components
        OrderCalculator calculator = new TotalCalculator();
        OrderPlacer repository = new OrderRepository();
        NotificationService emailSvc = new EmailNotification();

        // Inject them into the processor
        OrderProcessor processor = new OrderProcessor(calculator, repository, emailSvc);

        // Execute
        processor.process("Sakiki Abad", "123 Culiat St.", 10.0, 2, "sakikiabad@example.com");
    }
}