public interface SmartDevice {
    void turnOn();
    void turnOff();
    boolean isSwitchedOn();
}

public interface Connectable {
    void connectWifi(String ssid);
    boolean isWifiConnected();
}

class SmartTV implements SmartDevice, Connectable {
    private boolean isOn = false;
    private boolean wifiConnected = false;
    private String currentSsid = "";

    @Override
    public void turnOn() {
        this.isOn = true;
    }

    @Override
    public void turnOff() {
        this.isOn = false;
    }

    @Override
    public boolean isSwitchedOn() {
        return isOn;
    }

    @Override
    public void connectWifi(String ssid) {
        this.currentSsid = ssid;
        this.wifiConnected = true;
    }

    @Override
    public boolean isWifiConnected() {
        return wifiConnected;
    }

    public String getCurrentSsid() {
        return currentSsid;
    }

    public static void bootSequence(SmartTV tv, String ssid) {
        if (tv != null) {
            tv.turnOn();
            tv.connectWifi(ssid);
        }
    }
}
