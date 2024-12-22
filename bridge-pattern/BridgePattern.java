interface Device {
    void turnOn();

    void turnOff();
}

class TV implements Device {
    public void turnOn() {
        System.out.println("Turning on TV");
    }

    public void turnOff() {
        System.out.println("Turning off TV");
    }
}

class Radio implements Device {
    public void turnOn() {
        System.out.println("Turning on Radio");
    }

    public void turnOff() {
        System.out.println("Turning off Radio");
    }
}

abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void togglePower();

}

class AdvanceRemoteControl extends RemoteControl {
    boolean isOn = false;

    public AdvanceRemoteControl(Device device) {
        super(device);
    }

    public void togglePower() {
        if (isOn) {
            device.turnOff();
        } else {
            device.turnOn();
        }
        isOn = !isOn;
    }

}

public class BridgePattern {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remoteControl = new AdvanceRemoteControl(tv);
        remoteControl.togglePower();
        remoteControl.togglePower();

        Device radio = new Radio();
        remoteControl = new AdvanceRemoteControl(radio);
        remoteControl.togglePower();
    }
}