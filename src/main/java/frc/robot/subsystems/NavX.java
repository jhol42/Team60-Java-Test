package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;


public class NavX extends SubsystemBase {
    AHRS ahrs;
    private double pitchAngleDegrees = 0;
    private double rollAngleDegrees = 0;
    private double headingAngleDegrees = 0;
    private double angularVelocity = 0;
    private double angularAcceleration = 0;
    
    private Timer periodicDeltaTimer = new Timer();

    public NavX(){
        periodicDeltaTimer.start();
        try {
            /***********************************************************************
             * navX-MXP: - Communication via RoboRIO MXP (SPI, I2C) and USB. - See
             * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
             * 
             * navX-Micro: - Communication via I2C (RoboRIO MXP or Onboard) and USB. - See
             * http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
             * 
             * VMX-pi: - Communication via USB. - See
             * https://vmx-pi.kauailabs.com/installation/roborio-installation/
             * 
             * Multiple navX-model devices on a single robot are supported.
             ************************************************************************/
                ahrs = new AHRS(SPI.Port.kMXP);
        } catch (RuntimeException ex) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
    }

    @Override
    public void periodic() 
    {
        try
        {
            periodicDeltaTimer.stop();
            double timeDelta = periodicDeltaTimer.get(); // in seconds.

            // Make sure timeDelta is valid to prevent division by zero.
            if(timeDelta > 0)
            {
                // double newPitchAngleDegrees = ahrs.getPitch();
                // double newRollAngleDegrees = ahrs.getRoll();
                double newHeadingAngleDegrees = ahrs.getAngle();

                // angular velocity in degrees per second.
                // keep old value for now to compute the acceleration.
                double newAngularVelocity = (newHeadingAngleDegrees - headingAngleDegrees) / timeDelta;

                // angular acceleration degrees per second^2.
                angularAcceleration = (newAngularVelocity - angularVelocity) / timeDelta;

                // now set the current velocity.
                angularVelocity = newAngularVelocity;

                // post to smart dashboard periodically
                SmartDashboard.putNumber("Pitch", pitchAngleDegrees);
                SmartDashboard.putNumber("Roll", rollAngleDegrees);
                SmartDashboard.putNumber("Heading", headingAngleDegrees);
                SmartDashboard.putNumber("Angular Velocity", angularVelocity);
                SmartDashboard.putNumber("Angular Acceleration", angularAcceleration);
                SmartDashboard.putNumber("NavX Time Delta", timeDelta);
            }
        }
        catch(Exception ex)
        {

        }
        finally
        {
            periodicDeltaTimer.reset();
            periodicDeltaTimer.start();
        }
    }
}
