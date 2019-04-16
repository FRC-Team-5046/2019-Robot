/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.IntakePositionManual;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

//	public static String hatchMode;
	
//------------------------------------Intake Position Motor -----------------------------------	

	public void setupEncoder() {
		
		/* Config the sensor used for Primary PID and sensor direction */
        RobotMap.intakePositionMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 
                                            RobotMap.intakePositionMotorPIDLoopIdx,
				                            RobotMap.intakePositionMotorTimeoutMs);

		/* Ensure sensor is positive when output is positive */
		RobotMap.intakePositionMotor.setSensorPhase(RobotMap.intakePositionMotorSensorPhase);

		/**
		 * Set based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. 
		 */ 
		RobotMap.intakePositionMotor.setInverted(RobotMap.intakePositionMotorInvert);

		/* Config the peak and nominal outputs, 12V means full */
		RobotMap.intakePositionMotor.configNominalOutputForward(0, RobotMap.intakePositionMotorTimeoutMs);
		RobotMap.intakePositionMotor.configNominalOutputReverse(0, RobotMap.intakePositionMotorTimeoutMs);
		RobotMap.intakePositionMotor.configPeakOutputForward(1, RobotMap.intakePositionMotorTimeoutMs);
		RobotMap.intakePositionMotor.configPeakOutputReverse(-1, RobotMap.intakePositionMotorTimeoutMs);

		/**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		RobotMap.intakePositionMotor.configAllowableClosedloopError(0, RobotMap.intakePositionMotorPIDLoopIdx, RobotMap.intakePositionMotorTimeoutMs);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		RobotMap.intakePositionMotor.config_kF(RobotMap.intakePositionMotorPIDLoopIdx, RobotMap.intakePositionPID.kF , RobotMap.intakePositionMotorTimeoutMs);
		RobotMap.intakePositionMotor.config_kP(RobotMap.intakePositionMotorPIDLoopIdx, RobotMap.intakePositionPID.kP, RobotMap.intakePositionMotorTimeoutMs);
		RobotMap.intakePositionMotor.config_kI(RobotMap.intakePositionMotorPIDLoopIdx, RobotMap.intakePositionPID.kI, RobotMap.intakePositionMotorTimeoutMs);
		RobotMap.intakePositionMotor.config_kD(RobotMap.intakePositionMotorPIDLoopIdx, RobotMap.intakePositionPID.kD, RobotMap.intakePositionMotorTimeoutMs);

		/**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePosition = RobotMap.intakePositionMotor.getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		if (RobotMap.intakePositionMotorSensorPhase) { absolutePosition *= -1; }
		if (RobotMap.intakePositionMotorInvert) { absolutePosition *= -1; }
		
		/* Set the quadrature (relative) sensor to match absolute */
		RobotMap.intakePositionMotor.setSelectedSensorPosition(absolutePosition, RobotMap.intakePositionMotorPIDLoopIdx, RobotMap.intakePositionMotorTimeoutMs);

		System.out.println("intake setup complete");
	}

  public void setIntakePosition(double position)
  {
	RobotMap.intakePositionMotor.set(ControlMode.Position, position);
	System.out.println("Desired Position:" + position);
	System.out.println("Actual Position:" +  RobotMap.intakePositionMotor.getSensorCollection().getPulseWidthPosition()	);
  }

  public void setIntakePower(double power)
  {
	RobotMap.intakePositionMotor.set(ControlMode.PercentOutput, power);
	System.out.println("Output Power: " + RobotMap.intakePositionMotor.getMotorOutputPercent());
	
	}
	

//-------------------------------------------Cargo Intake -----------------------------------------------


public void setCargoIntakeInverted()
{
	RobotMap.cargoIntakeMotor.setInverted(RobotMap.cargoIntakeMotorInvert);
}

public void setCargoIntakePower(double power)
{
	RobotMap.cargoIntakeMotor.set(ControlMode.PercentOutput, power);
}






//-------------------------------------------Hatch System -----------------------------------------------


public void setHatchForward()
{
	// hatchMode = "HATCH";
	// SmartDashboard.putString("Hatch Slider" , hatchMode);
	RobotMap.hatchSliderSolenoid.set(DoubleSolenoid.Value.kForward);
}

public void setHatchBack()
{
	// hatchMode = "CARGO";
	// SmartDashboard.putString("Hatch Slider" , hatchMode);
	RobotMap.hatchSliderSolenoid.set(DoubleSolenoid.Value.kReverse);
}

public void setHatchOpen()
{
	SmartDashboard.putString("Hatch Grabber" , "CLOSED");
	RobotMap.hatchGrabberSolenoid.set(DoubleSolenoid.Value.kForward);
}

public void setHatchClosed()
{
	SmartDashboard.putString("Hatch Grabber" , "CLOSED");
	RobotMap.hatchGrabberSolenoid.set(DoubleSolenoid.Value.kReverse);
}

public boolean readHatchEngaged(boolean value)
{
	SmartDashboard.putBoolean("Hatch Engaged", RobotMap.intakePositionMotor.getSensorCollection().isFwdLimitSwitchClosed());
	return RobotMap.intakePositionMotor.getSensorCollection().isFwdLimitSwitchClosed();
}

public boolean readHatchSystemActive(boolean value)
{
	SmartDashboard.putBoolean("Hatch System Active", RobotMap.intakePositionMotor.getSensorCollection().isRevLimitSwitchClosed());
	return RobotMap.intakePositionMotor.getSensorCollection().isRevLimitSwitchClosed();
}

public void updateDashboard()
{
	
}

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
	setDefaultCommand(new IntakePositionManual());
  }
}
