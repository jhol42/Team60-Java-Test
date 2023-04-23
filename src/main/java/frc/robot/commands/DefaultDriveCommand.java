package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DefaultDriveCommand extends CommandBase {
  private final DriveTrain drive;
  private final DoubleSupplier leftVal;
  private final DoubleSupplier rightVal;

  // This value was evaluated via testing by
  // moving left and right sticks to max and letting go.
  // This might be controller dependent.
  private final Double xboxDeadZone = 0.15;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DefaultDriveCommand(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
    drive = subsystem;
    leftVal = forward;
    rightVal = rotation;
    addRequirements(drive);
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("leftVal", leftVal.getAsDouble());
    SmartDashboard.putNumber("rightVal", rightVal.getAsDouble());

    // negate the xbox controller values.
    // seems more natural for up to be positive.
    drive.drive.tankDrive(

        adjustForDeadZone(-leftVal.getAsDouble()), 
        adjustForDeadZone(-rightVal.getAsDouble()));
  }

  double adjustForDeadZone(double origValue)
  {
    // if the magnitude (absolute value here) is less than the
    // deadzone return 0.
    return Math.abs(origValue) < xboxDeadZone ? 0 : origValue; 
  }
}