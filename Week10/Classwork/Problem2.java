import java.util.*;

public class Problem2 {
    static class Customer {
        private int id;
        private String name;

        public Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return id == customer.id && Objects.equals(name, customer.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    static class CustomerRegistry {
        private Set<Customer> customers = new HashSet<>();

        public boolean addCustomer(Customer c) {
            return customers.add(c);
        }

        public boolean containsCustomer(Customer c) {
            return customers.contains(c);
        }

        public int getUniqueCount() {
            return customers.size();
        }
    }

    public static void main(String[] args) {
        CustomerRegistry registry = new CustomerRegistry();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println(registry.addCustomer(c1));
        boolean addedC2 = registry.addCustomer(c2);
        System.out.println(addedC2 + " (duplicate rejected)");
        System.out.println(registry.addCustomer(c3));

        System.out.println("unique count " + registry.getUniqueCount());
        System.out.println("contains: " + registry.containsCustomer(new Customer(101, "Asha")));
    }
}
