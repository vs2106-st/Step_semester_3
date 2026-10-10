interface Switchable {
    String turnOn();
    String turnOff();
}

abstract class Appliance {
    private String brand;

    public Appliance(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public abstract String getEnergyRating();
}

class SmartBulb extends Appliance implements Switchable {
    public SmartBulb(String brand) {
        super(brand);
    }

    @Override
    public String turnOn() {
        return getBrand() + " bulb turned ON";
    }

    @Override
    public String turnOff() {
        return getBrand() + " bulb turned OFF";
    }

    @Override
    public String getEnergyRating() {
        return "A+";
    }
}

class DimmableSmartBulb extends SmartBulb {
    private int brightness;

    public DimmableSmartBulb(String brand) {
        super(brand);
        this.brightness = 100;
    }

    public String setBrightness(int level) {
        this.brightness = level;
        return getBrand() + " bulb brightness set to " + brightness + "%";
    }

    @Override
    public String turnOn() {
        return super.turnOn() + " at " + brightness + "% brightness";
    }
}

class SmartPlug implements Switchable {
    private String deviceId;

    public SmartPlug(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String turnOn() {
        return "Plug " + deviceId + " power ON";
    }

    @Override
    public String turnOff() {
        return "Plug " + deviceId + " power OFF";
    }
}

public class Problem5Test {
    public static void toggleAll(Switchable[] devices) {
        for (Switchable device : devices) {
            System.out.println(device.turnOn());
        }
    }

    public static String checkEnergyIfAppliance(Switchable s) {
        if (s instanceof Appliance) {
            Appliance app = (Appliance) s;
            return app.getEnergyRating();
        }
        return "Not an Appliance";
    }

    public static void main(String[] args) {
        SmartBulb b = new SmartBulb("Philips");
        System.out.println(b.turnOn());

        DimmableSmartBulb d = new DimmableSmartBulb("Philips");
        System.out.println(d.setBrightness(50));
        System.out.println(d.turnOn());

        SmartPlug p = new SmartPlug("SP-99");
        System.out.println(p.turnOn());

        Switchable[] devices = {b, d, p};
        toggleAll(devices);

        System.out.println(checkEnergyIfAppliance(b));
        System.out.println(checkEnergyIfAppliance(p));
    }
}
