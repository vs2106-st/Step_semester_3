class FoodOrder {
    private String studentName;
    private String dishName;
    private boolean delivered = false;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty() || dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name or dish name.");
        }
        this.studentName = studentName;
        this.dishName = dishName;
    }

    public void markDelivered() {
        if (this.delivered) {
            System.out.println("Warning: Order for " + studentName + " was already marked delivered!");
        } else {
            this.delivered = true;
            System.out.println("Order delivered successfully to " + studentName + ".");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (String[] order : rawOrders) {
            if (order == null || order.length < 2) {
                rejected++;
                continue;
            }

            try {
                new FoodOrder(order[0], order[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] rawOrders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(rawOrders);
    }
}
