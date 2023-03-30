package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import java.util.function.DoubleSupplier;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

    private static int BlueMotorCanId = 3;
    private static int RedMotorCanId = 4;
    private static int LimitSwitchDIOPort = 9;

    private CANSparkMax BlueMotor;
    private CANSparkMax RedMotor;
    private Encoder BlueEncoder;
    private Encoder RedEncoder;
    private DigitalInput LimitSwitch;


    public Arm()
    {
        super();
        BlueMotor = new CANSparkMax(BlueMotorCanId, MotorType.kBrushless);
        RedMotor = new CANSparkMax(RedMotorCanId, MotorType.kBrushless);
        LimitSwitch = new DigitalInput(LimitSwitchDIOPort);
    }
}
