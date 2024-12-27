/*
 Singleton pattern
Command pattern
Proxy Pattern
Bridge Pattern
Templete Method
Composite Pattern
Iterator pattern
 */

// Singleton pattern

import java.util.ArrayList;
import java.util.List;

class SmartHomeHub {
    private static SmartHomeHub INSTANCE;
    private final List<String> devices;

    private SmartHomeHub() {
        devices = new ArrayList<>();
    }

    public static SmartHomeHub getInstance() {
        // Thread-safe Singleton
        if (INSTANCE == null) {
            return INSTANCE = new SmartHomeHub();
        } else {
            return INSTANCE;
        }
    }

    public void addDevice(String device) {
        devices.add(device);
        System.out.println(device + " added to the Smart Home Hub.");
    }
}

// Command pattern
interface Command {
    void execute();
}

class Device {
    private final String name; // Made final for immutability

    public Device(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + " is ON");
    }

    public void off() {
        System.out.println(name + " is OFF");
    }
}

class TurnOnCommand implements Command {
    private final Device device; // Made final for immutability

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.on();
    }
}

class TurnOffCommand implements Command {
    private final Device device; // Made final for immutability

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.off();
    }

}

// Proxy Pattern: Access Control for Devices
interface DeviceInterface {
    void request(String userRole);
}

class RealDevice implements DeviceInterface {

    @Override
    public void request(String userRole) {
        System.out.println("Device: Executing operation");
    }
}

class ProxyDevice implements DeviceInterface {
    private RealDevice realDevice;

    public ProxyDevice() {
        realDevice = new RealDevice();
    }

    @Override
    public void request(String userRole) {
        if (userRole.equals("admin")) {
            realDevice.request(userRole);
        } else {
            System.out.println("Access Denied. Only admin can access this device.");
        }
    }
}

// Bridge Pattern: Connecting Devices to different Displays
interface Control {
    void operate();

}

class RemoteControl implements Control {

    @Override
    public void operate() {
        System.out.println("Remote Control: Operating device");
    }

}

class VoiceControl implements Control {
    @Override
    public void operate() {
        System.out.println("Voice Control: Operating device");
    }
}

class DeviceControl {
    private final Control control;

    public DeviceControl(Control control) {
        this.control = control;
    }

    public void useControl() {
        control.operate();
    }
}

// Templete method control : Morning routine
abstract class Routine {

    protected void wakup() {
        System.out.println("Wake UP");
    }

    protected abstract void turnOnLights();

    protected void turnOnCoffeeMachine() {
        System.out.println("Switch ON Coffee Machine");
    }

    public void execute() {
        wakup();
        turnOnLights();
        turnOnCoffeeMachine();
    }
}

class MorningRoutine extends Routine {

    @Override
    protected void turnOnLights() {
        System.out.println("Turn ON Living Room Lights");
    }

}

// Composite Pattern : Device Group
interface Component {
    public void operation();
}

class DeviceLeaf implements Component {
    private final String name; // Made final for immutability

    public DeviceLeaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println(name + " Operation");
    }
}

class DeviceGroup implements Component {
    private final List<Component> components = new ArrayList<Component>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public void operation() {
        System.out.println("Device group operation");
        for (Component component : components) {
            component.operation();
        }
    }
}

// Iterator pattern : Device Iterator pattern
class DeviceIterator {
    private final List<Device> devices; // Made final for immutability
    private int index;

    public DeviceIterator(List<Device> devices) {
        this.devices = devices;
        this.index = 0;
    }

    public boolean hasNext() {
        return index < devices.size();
    }

    public Device next() {
        return hasNext() ? devices.get(index++) : null;
    }

}

public class SmartHomeSystem {
    public static void main(String[] args) {
        SmartHomeHub hub = SmartHomeHub.getInstance();
        hub.addDevice("Smart Light");
        SmartHomeHub newHub = SmartHomeHub.getInstance();
        System.out.println(newHub == hub);

        // Command Pattern
        Device device = new Device("Smart Light");
        Command turnOnLight = new TurnOnCommand(device);
        Command turnOffLight = new TurnOffCommand(device);
        turnOnLight.execute();
        turnOffLight.execute();

        // Proxy Pattern
        DeviceInterface proxyDevice = new ProxyDevice();
        proxyDevice.request("admin");
        proxyDevice.request("user");

        // Bridge Pattern
        Control remoteControl = new RemoteControl();
        Control voiceControl = new VoiceControl();
        DeviceControl livingRoomDisplay = new DeviceControl(remoteControl);
        livingRoomDisplay.useControl();
        DeviceControl kitchenDisplay = new DeviceControl(voiceControl);
        kitchenDisplay.useControl();

        // Templete method pattern
        MorningRoutine morningRoutine = new MorningRoutine();
        morningRoutine.execute();

        // Composite Pattern
        DeviceGroup deviceGroup = new DeviceGroup();
        deviceGroup.addComponent(new DeviceLeaf("Living Room Lights"));
        deviceGroup.addComponent(new DeviceLeaf("Kitchen Lights"));
        deviceGroup.operation();

        // Iterator Pattern
        List<Device> devices = new ArrayList<>();
        devices.add(new Device("Smart Light"));
        devices.add(new Device("Smart Thermostat"));
        devices.add(new Device("Smart Door Lock"));
        DeviceIterator deviceIterator = new DeviceIterator(devices);
        while (deviceIterator.hasNext()) {
            System.out.println(deviceIterator.next());
        }

    }
}
