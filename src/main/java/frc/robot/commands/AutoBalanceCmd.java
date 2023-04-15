package frc.robot.commands;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Arm;

// Command used to auto balance the robot if it is
// aligned with the dock.

public class AutoBalanceCmd extends PIDCommand 
{
    private DriveTrain driveTrain;
    private Arm arm;
    private NavX navx;

    private boolean finished = true;

    public AutoBalanceCmd(
            PIDController controller,
            DriveTrain driveTrain,
            Arm arm, 
            NavX navx) 
    {
        super(
            controller, 
            ()->navx.getPitch(), //measurement. Lamba function to get pitch from navx.
            ()->0, // Always goto zero pitch.  Lamba function always returns zero.
            (val)->driveTrain.setSpeed(val), // Set speed from controller  The PIDController calls this (every 20ms?)
            driveTrain, arm, navx);  // Subsystems used by this derived PIDCommand

        this.driveTrain = driveTrain;
        this.arm = arm;
        this.navx = navx;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        // TODO: Set arm to starting position
        driveTrain.zeroEncoders();
        finished = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() 
    {
        // TODO: move forward while navx pitch is static
        // TODO: if navx pitch increases dock has been driven on to.
        // TODO: drive forward until pitch acceleration is negative.
        // TODO: PID controller?
        finished = true;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) 
    {
        driveTrain.stop();
        finished = true;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() 
    {
        return finished;
    }

}
