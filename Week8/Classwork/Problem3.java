import java.util.*;

interface Capability {
    String getName();
    boolean apply(Device device, Object value);
}

class PowerCapability implements Capability {
    @Override
    public String getName() { return "Power"; }

    @Override
    public boolean apply(Device device, Object value) {
        if (value instanceof Boolean) {
            boolean state = (Boolean) value;
            System.out.println(device.getName() + ": " + (state ? "ON" : "OFF") + ".");
            return true;
        }
        return false;
    }
}

class BrightnessCapability implements Capability {
    @Override
    public String getName() { return "Brightness"; }

    @Override
    public boolean apply(Device device, Object value) {
        if (value instanceof Integer) {
            int val = (Integer) value;
            if (val >= 0 && val <= 100) {
                System.out.println(device.getName() + ": brightness set to " + val + "%.");
                return true;
            } else {
                System.out.println("Rejected: " + device.getName() + " brightness must be between 0% and 100%.");
            }
        }
        return false;
    }
}

class TemperatureCapability implements Capability {
    @Override
    public String getName() { return "Temperature"; }

    @Override
    public boolean apply(Device device, Object value) {
        if (value instanceof Integer) {
            int val = (Integer) value;
            if (val >= 16 && val <= 30) {
                System.out.println(device.getName() + ": temperature set to " + val + "°C.");
                return true;
            } else {
                System.out.println("Rejected: " + device.getName() + " temperature must be between 16°C and 30°C.");
            }
        }
        return false;
    }
}

class Device {
    private String name;
    private Map<String, Capability> capabilities = new LinkedHashMap<>();

    public Device(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);
    }

    public boolean hasCapability(String capName) {
        return capabilities.containsKey(capName);
    }

    public boolean applyCapability(String capName, Object value) {
        Capability cap = capabilities.get(capName);
        if (cap != null) {
            return cap.apply(this, value);
        }
        return false;
    }
}

class SceneStep {
    private String capabilityName;
    private Object value;

    public SceneStep(String capabilityName, Object value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }

    public String getCapabilityName() { return capabilityName; }
    public Object getValue() { return value; }
}

class Scene {
    private String name;
    private List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public void addStep(String capabilityName, Object value) {
        steps.add(new SceneStep(capabilityName, value));
    }

    public int execute(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");
        int actionCount = 0;
        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (device.hasCapability(step.getCapabilityName())) {
                    if (device.applyCapability(step.getCapabilityName(), step.getValue())) {
                        actionCount++;
                    }
                }
            }
        }
        System.out.println("Scene '" + name + "' completed: " + actionCount + " actions applied.");
        return actionCount;
    }
}

public class Question3Main {
    public static void main(String[] args) {
        Device ac = new Device("Lab AC");
        ac.addCapability(new PowerCapability());
        ac.addCapability(new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability(new PowerCapability());
        lights.addCapability(new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability(new PowerCapability());

        List<Device> labDevices = Arrays.asList(ac, lights, projector);

        Scene lectureMode = new Scene("Lecture Mode");
        lectureMode.addStep("Power", true);
        lectureMode.addStep("Brightness", 40);
        lectureMode.addStep("Temperature", 24);

        lectureMode.execute(labDevices);

        ac.applyCapability("Temperature", 12);

        projector.addCapability(new BrightnessCapability());
        System.out.println("Projector: Brightness capability added.");
        projector.applyCapability("Brightness", 70);
    }
}
