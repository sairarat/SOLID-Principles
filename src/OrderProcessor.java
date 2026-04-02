public class OrderProcessor {
    private final OrderCalculator calculator;
    private final OrderPlacer placer;
    private final NotificationService notification;

    // We "Inject" dependencies here
    public OrderProcessor(OrderCalculator calculator, OrderPlacer placer, NotificationService notification) {
        this.calculator = calculator;
        this.placer = placer;
        this.notification = notification;
    }

    public void process(String name, String address, double price, int qty, String email) {
        double total = calculator.calculateTotal(price, qty);
        System.out.println("Total: $" + total);
        placer.placeOrder(name, address);
        notification.sendNotification(email);
    }
}