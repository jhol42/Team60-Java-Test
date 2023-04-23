package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

import java.util.function.DoubleSupplier;
public class DefaultArmCommand extends CommandBase{
    private final Arm arm;
    private final DoubleSupplier horizontalControllerValue;
    private final DoubleSupplier verticalControllerValue;
 
    private final Double xboxDeadZone = 0.15;

    public DefaultArmCommand(Arm subsystem, DoubleSupplier leftControllerVal, DoubleSupplier rightControllerVal) {
        arm = subsystem;
        horizontalControllerValue = leftControllerVal;
        verticalControllerValue = rightControllerVal;
        addRequirements(arm);
      }
    
      @Override
      public void execute() {
        SmartDashboard.putNumber("redVal", horizontalControllerValue.getAsDouble());
        SmartDashboard.putNumber("blueVal", verticalControllerValue.getAsDouble());

        // negate the xbox controller values.
        // seems more natural for up to be positive.
        double adjustedHorizontal = adjustForDeadZone(-horizontalControllerValue.getAsDouble());
        double adjustedVertical = adjustForDeadZone(-verticalControllerValue.getAsDouble());

        // TODO: computer motion of two motors to move the grabber 
        // in a human understadable way (this is also to be dertermined.)

      }
    
      double adjustForDeadZone(double origValue)
      {
        // if the magnitude (absolute value here) is less than the
        // deadzone return 0.
        return Math.abs(origValue) < xboxDeadZone ? 0 : origValue; 
      }
    

}
