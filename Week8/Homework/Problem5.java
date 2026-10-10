import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment Successful via Credit Card: $" + String.format("%.2f", amount));
        return true;
    }
}

class DigitalWalletPayment implements PaymentMethod {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Payment Successful via Digital Wallet: $" + String.format("%.2f", amount));
        return true;
    }
}

class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }
}

class FoodOrder {
    private static int idCounter = 101;
    private int orderId;
    private List<OrderItem> items = new ArrayList<>();
    private boolean isPlaced = false;

    public FoodOrder() {
        this.orderId = idCounter++;
    }

    public void addItem(MenuItem item, int quantity) {
        if (isPlaced) {
            System.out.println("Cannot add items: Order already placed.");
            return;
        }
        items.add(new OrderItem(item, quantity));
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public boolean placeOrder(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Order placement failed: Order must contain at least one item.");
            return false;
        }

        double total = calculateTotal();
        System.out.println("Placing Order #" + orderId + " - Total: $" + String.format("%.2f", total));
        
        if (paymentMethod.processPayment(total)) {
            isPlaced = true;
            System.out.println("Order Placed: Order #" + orderId + " has been successfully created.");
            return true;
        } else {
            System.out.println("Order placement failed: Payment failed.");
            return false;
        }
    }
}

public class Question5Main {
    public static void main(String[] args) {
        MenuItem burger = new MenuItem("Burger", 8.50);
        MenuItem pizza = new MenuItem("Pizza", 12.00);

        FoodOrder order1 = new FoodOrder();
        order1.addItem(burger, 2);
        order1.addItem(pizza, 1);

        PaymentMethod card = new CreditCardPayment();
        order1.placeOrder(card);

        FoodOrder emptyOrder = new FoodOrder();
        emptyOrder.placeOrder(card);
    }
}
