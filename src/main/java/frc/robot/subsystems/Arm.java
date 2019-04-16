/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;


import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;
import frc.robot.commands.ArmPositionManual;

/**
 * Add your docs here.
 */
public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void setupSPARK(){

	RobotMap.armMotor.restoreFactoryDefaults();
	RobotMap.armMotor.setIdleMode(IdleMode.kBrake);
	RobotMap.armMotor.setInverted(true);
  }



	//public String armPosition;
  

	// public void setupEncoder() {
		

	// 	RobotMap.armPositionMotor.setNeutralMode(NeutralMode.Brake);
		   	
	// 	/* Config the sensor used for Primary PID and sensor direction */
    //     RobotMap.armPositionMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 
    //                                         RobotMap.armPositionMotorPIDLoopIdx,
	// 			                            RobotMap.armPositionMotorTimeoutMs);

	// 	/* Ensure sensor is positive when output is positive */
	// 	RobotMap.armPositionMotor.setSensorPhase(RobotMap.armPositionMotorSensorPhase);

	// 	/**
	// 	 * Set based on what direction you want forward/positive to be.
	// 	 * This does not affect sensor phase. 
	// 	 */ 
	// 	RobotMap.armPositionMotor.setInverted(RobotMap.armPositionMotorInvert);

	// 	/* Config the peak and nominal outputs, 12V means full */
	// 	RobotMap.armPositionMotor.configNominalOutputForward(0, RobotMap.armPositionMotorTimeoutMs);
	// 	RobotMap.armPositionMotor.configNominalOutputReverse(0, RobotMap.armPositionMotorTimeoutMs);
	// 	RobotMap.armPositionMotor.configPeakOutputForward(1, RobotMap.armPositionMotorTimeoutMs);
	// 	RobotMap.armPositionMotor.configPeakOutputReverse(-1, RobotMap.armPositionMotorTimeoutMs);

	// 	/**
	// 	 * Config the allowable closed-loop error, Closed-Loop output will be
	// 	 * neutral within this range. See Table in Section 17.2.1 for native
	// 	 * units per rotation.
	// 	 */
	// 	RobotMap.armPositionMotor.configAllowableClosedloopError(0, RobotMap.armPositionMotorPIDLoopIdx, RobotMap.armPositionMotorTimeoutMs);

	// 	/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
	// 	RobotMap.armPositionMotor.config_kF(RobotMap.armPositionMotorPIDLoopIdx, RobotMap.armPositionPID.kF , RobotMap.armPositionMotorTimeoutMs);
	// 	RobotMap.armPositionMotor.config_kP(RobotMap.armPositionMotorPIDLoopIdx, RobotMap.armPositionPID.kP, RobotMap.armPositionMotorTimeoutMs);
	// 	RobotMap.armPositionMotor.config_kI(RobotMap.armPositionMotorPIDLoopIdx, RobotMap.armPositionPID.kI, RobotMap.armPositionMotorTimeoutMs);
	// 	RobotMap.armPositionMotor.config_kD(RobotMap.armPositionMotorPIDLoopIdx, RobotMap.armPositionPID.kD, RobotMap.armPositionMotorTimeoutMs);

	// 	/**
	// 	 * Grab the 360 degree position of the MagEncoder's absolute
	// 	 * position, and intitally set the relative sensor to match.
	// 	 */
	// 	int absolutePosition = RobotMap.armPositionMotor.getSensorCollection().getAnalogIn();

	// 	// /* Mask out overflows, keep bottom 12 bits */
	// 	// absolutePosition &= 0xFFF;
	// 	// if (RobotMap.armPositionMotorSensorPhase) { absolutePosition *= -1; }
	// 	// if (RobotMap.armPositionMotorInvert) { absolutePosition *= -1; }
		
	// 	// /* Set the quadrature (relative) sensor to match absolute */
	// 	RobotMap.armPositionMotor.setSelectedSensorPosition(absolutePosition, RobotMap.armPositionMotorPIDLoopIdx, RobotMap.armPositionMotorTimeoutMs);

	// 	System.out.println("arm encoders setup");
		
	// }

	// public void setPosition(double position)
	// {
	// 	double midpoint = 300;
	// 	if (Intake.hatchMode == "HATCH")
	// 	{
	// 		if (RobotMap.armPositionMotor.getSensorCollection().getAnalogIn() < midpoint && position > midpoint )
	// 		{
				
	// 			Robot.intake.setHatchBack();				
	// 		}

	// 	}

	// 	  RobotMap.armPositionMotor.set(ControlMode.Position, position);
	// 	  System.out.println("Desired Arm Position: " + position);
	// 	  System.out.println("Actual Arm Position" + RobotMap.armPositionMotor.getSensorCollection().getAnalogIn());
  
	// }

//   public void setPosition(double position)
//   {
// 		RobotMap.armPositionMotor.set(ControlMode.Position, position);
// 		System.out.println("Desired Arm Position: " + position);
// 		System.out.println("Actual Arm Position" + RobotMap.armPositionMotor.getSensorCollection().getAnalogIn());

//   }

//   public void setPower(double power)
//   {
// 		RobotMap.armPositionMotor.set(ControlMode.PercentOutput, power);
// 		System.out.println("Arm Power: " + power);
//   }

public void setPower(double power)
   {
	   RobotMap.armMotor.set(power);
// 		RobotMap.armPositionMotor.set(ControlMode.PercentOutput, power);
 		System.out.println("Arm Power: " + power);
   }

// 	public void updateDashboard()
// 	{
// 		SmartDashboard.putNumber("Arm Motor Power", RobotMap.armPositionMotor.getMotorOutputPercent());
// 			SmartDashboard.putNumber("Arm Encoder Position", RobotMap.armPositionMotor.getSelectedSensorPosition(0));
// 		SmartDashboard.putNumber("Arm Enc VEL", RobotMap.armPositionMotor.getSelectedSensorVelocity(0));
// 	}
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
	setDefaultCommand(new ArmPositionManual());
  }
}
