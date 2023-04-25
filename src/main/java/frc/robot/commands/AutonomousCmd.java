package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Arm;

// Command called at the start of autonomous mode.
public class AutonomousCmd extends CommandBase {
    private DriveTrain driveTrain;
    private Arm arm;

    private boolean finished = true;

    public AutonomousCmd(
            DriveTrain driveTrain,
            Arm arm) {
        this.driveTrain = driveTrain;
        this.arm = arm;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // TODO: Set arm to starting position
        driveTrain.zeroEncoders();
        finished = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() 
    {
        // TODO: Move forward.
        // TODO: activate command to drop object.
        // TODO: activate backup command.
        finished = true;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // TODO: set all motion to zero?
        finished = true;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return finished;
    }

}
