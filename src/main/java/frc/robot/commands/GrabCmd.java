package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX;
import frc.robot.subsystems.Arm;


// Simple command to close and release the grabber.
public class GrabCmd extends CommandBase 
{
    private Arm arm;

    public GrabCmd(Arm arm)
    {
        this.arm = arm;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
    }    

    @Override
    public void execute() 
    {
        arm.grab();
    }    

    @Override
    public void end(boolean interrupted) 
    {
        arm.grabRelease();
    }
}