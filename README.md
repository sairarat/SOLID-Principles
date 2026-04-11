# Lab Assignment 5 — SOLID Principles

The original code violates the **Single Responsibility Principle (SRP)** and the **Interface Segregation Principle (ISP)**. The `OrderAction` class bundles four unrelated responsibilities — calculating totals, placing orders, generating invoices, and sending emails — into a single class, giving it multiple reasons to change. Meanwhile, the `Order` interface forces every implementing class to provide all four methods, even when only a subset is needed.

To fix these violations, the monolithic `Order` interface was split into four focused interfaces — `Calculatable`, `Placeable`, `Invoiceable`, and `Notifiable` — so that clients only depend on what they need (ISP). Each responsibility was then extracted into its own class — `OrderCalculator`, `OrderPlacer`, `InvoiceGenerator`, and `EmailNotifier` — so that each class has only one reason to change (SRP).

## Before vs. After Comparison

### Before (Violates SRP & ISP)
```java
// One "God Interface" — forces all methods on every implementor
public interface Order {
    void calculateTotal(double price, int quantity);
    void placeOrder(String customerName, String address);
    void generateInvoice(String fileName);
    void sendEmailNotification(String email);
}

// One "God Class" — handles four distinct domains of logic
public class OrderAction implements Order {
    @Override
    public void calculateTotal(double price, int quantity) { /* logic */ }
    @Override
    public void placeOrder(String name, String addr) { /* logic */ }
    @Override
    public void generateInvoice(String file) { /* logic */ }
    @Override
    public void sendEmailNotification(String email) { /* logic */ }
}
```

### After (Follows SRP & ISP)
```java
// Segregated interfaces (ISP)
public interface OrderCalculator {
    double calculateTotal(double price, int quantity);
}

public interface OrderPlacer {
    void placeOrder(String name, String address);
}

public interface NotificationService {
    void sendNotification(String destination);
}


// Single Responsibility implementations (SRP)
public class TotalCalculator implements OrderCalculator {
    public double calculateTotal(double p, int q) { return p * q; }
}

public class OrderRepository implements OrderPlacer {
    public void placeOrder(String n, String a) { /* Save to DB */ }
}

public class EmailNotification implements NotificationService {
    public void sendNotification(String email) { /* SMTP Logic */ }
}

// Orchestrator depending on Abstractions (DIP)
public class OrderProcessor {
    private final OrderCalculator calculator;
    private final OrderPlacer placer;
    private final NotificationService notification;

    public OrderProcessor(OrderCalculator c, OrderPlacer p, NotificationService n) {
        this.calculator = c;
        this.placer = p;
        this.notification = n;
    }
}

```

![img](https://github.com/sairarat/SOLID-Principles/blob/master/Solid%20UML_5.png)
