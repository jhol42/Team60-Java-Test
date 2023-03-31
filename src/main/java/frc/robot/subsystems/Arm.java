package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import java.util.function.DoubleSupplier;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

    private static int BlueMotorCanId = 4;
    private static int RedMotorCanId = 5;
    private static int LimitSwitchDIOPort = 9;

    private CANSparkMax blueMotor;
    private CANSparkMax redMotor;
    private Encoder blueEncoder;
    private Encoder redEncoder;
    private DigitalInput limitSwitch;


    public Arm()
    {
        super();
        blueMotor = new CANSparkMax(BlueMotorCanId, MotorType.kBrushless);
        redMotor = new CANSparkMax(RedMotorCanId, MotorType.kBrushless);

        blueMotor.restoreFactoryDefaults();
        redMotor.restoreFactoryDefaults();

        limitSwitch = new DigitalInput(LimitSwitchDIOPort);
    }

    public void setBlueSpeed(double joystickVal)
    {
        // TODO: Scale value [-1, 1]?
        double scale = 1.0;
        double val = joystickVal * scale;
        blueMotor.set(val);
    }

    public void setRedSpeed(double joystickVal)
    {
        // TODO: Scale value [-1, 1]?
        double scale = 1.0;
        double val = joystickVal * scale;
        if(limitSwitch.get())
            val = 0;
        redMotor.set(val);
    }

    // Default command to handle joystick input for arm movement.
    public Command moveArm(DoubleSupplier blue, DoubleSupplier red)
    {
        return Commands.run(
            () -> {
                this.setBlueSpeed(blue.getAsDouble());
                this.setRedSpeed(red.getAsDouble());
            }, 
            this);
    }
}
