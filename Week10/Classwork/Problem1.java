import java.util.*;

public class Problem1 {
    interface Dimmable {
        String dim(int level);
    }

    interface Schedulable {
        String schedule(String time);
    }

    interface EnergyMonitored {
        String getEnergy();
    }

    static abstract class Device {
        protected String id;

        public Device(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public String turnOn() {
            return id + " is ON";
        }
    }

    static class Light extends Device implements Dimmable, Schedulable {
        public Light(String id) {
            super(id);
        }

        @Override
        public String dim(int level) {
            return id + " dimmed to " + level;
        }

        @Override
        public String schedule(String time) {
            return id + " scheduled " + time;
        }
    }

    static class Fan extends Device implements Schedulable {
        public Fan(String id) {
            super(id);
        }

        @Override
        public String schedule(String time) {
            return id + " scheduled " + time;
        }
    }

    static class Plug extends Device implements EnergyMonitored {
        private int energyKwh;

        public Plug(String id, int energyKwh) {
            super(id);
            this.energyKwh = energyKwh;
        }

        @Override
        public String getEnergy() {
            return id + " energy " + energyKwh + " kWh";
        }
    }

    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();
        devices.put("L1", new Light("L1"));
        devices.put("F1", new Fan("F1"));
        devices.put("P1", new Plug("P1", 12));

        System.out.println(devices.get("L1").turnOn());

        Device l1 = devices.get("L1");
        if (l1 instanceof Dimmable) {
            System.out.println(((Dimmable) l1).dim(40));
        } else {
            System.out.println(l1.getId() + " rejected: DIM unsupported");
        }

        Device f1 = devices.get("F1");
        if (f1 instanceof Dimmable) {
            System.out.println(((Dimmable) f1).dim(30));
        } else {
            System.out.println(f1.getId() + " rejected: DIM unsupported");
        }

        if (f1 instanceof Schedulable) {
            System.out.println(((Schedulable) f1).schedule("22:00"));
        } else {
            System.out.println(f1.getId() + " rejected: SCHEDULE unsupported");
        }

        Device p1 = devices.get("P1");
        if (p1 instanceof EnergyMonitored) {
            System.out.println(((EnergyMonitored) p1).getEnergy());
        } else {
            System.out.println(p1.getId() + " rejected: ENERGY unsupported");
        }

        if (l1 instanceof EnergyMonitored) {
            System.out.println(((EnergyMonitored) l1).getEnergy());
        } else {
            System.out.println(l1.getId() + " rejected: ENERGY unsupported");
        }
    }
}
