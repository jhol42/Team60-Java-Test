package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase  {
  public static int LeftFront = 0;
  public static int LeftBack = 1;
  public static int RightFront = 2;
  public static int RightBack = 3;
  private WPI_TalonSRX LFMotor = new WPI_TalonSRX(LeftFront);
  private WPI_TalonSRX LBMotor  = new WPI_TalonSRX(LeftBack);
  private WPI_TalonSRX RFMotor = new WPI_TalonSRX(RightFront);
  private WPI_TalonSRX RBMotor  = new WPI_TalonSRX(RightBack);

  private MotorControllerGroup RightGroup = new MotorControllerGroup(RFMotor, RBMotor);
  private MotorControllerGroup LeftGroup = new MotorControllerGroup(LFMotor, LBMotor);


  private final DifferentialDrive m_drive = new DifferentialDrive(LeftGroup, RightGroup);

  // private final Encoder m_leftEncoder = new Encoder(1, 2);
  // private final Encoder m_rightEncoder = new Encoder(3, 4);
  // private final AnalogInput m_rangefinder = new AnalogInput(6);
  // private final AnalogGyro m_gyro = new AnalogGyro(1);

  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    super();
    LBMotor.setInverted(true);
    RBMotor.setInverted(true);

    addChild("drive", m_drive);
  }

 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }    

  public Command tankDriveCommand(DoubleSupplier fwd, DoubleSupplier rot) {
    return Commands.run(() -> m_drive.tankDrive(fwd.getAsDouble(), rot.getAsDouble()), this);
  }

}
