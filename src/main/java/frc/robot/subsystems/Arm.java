package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import java.util.function.DoubleSupplier;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DefaultArmCommand;

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

    public void initDefaultCommand(CommandXboxController armController)
    {
        this.setDefaultCommand(
            new DefaultArmCommand(
                this, 
                () -> armController.getLeftY(),
                () -> armController.getRightY()));
    }

    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }
}
