/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.JacksUpDownManual;

/**
 * Add your docs here.
 */
public class Jacks extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public boolean diableDrivetrain = false;
  public boolean jacksDown;
  
  private int maxLoopNumber = 0;  
	private int onTargetCounter = 0;
	private int allowedErrorRange = 0;


	//Zero's the encoders
	public void zeroEncoders() {
		RobotMap.frontJackPositionMotor.setSelectedSensorPosition(0, 0, 0);
		RobotMap.frontJackPositionMotor.getSensorCollection().setPulseWidthPosition(0,0);
		RobotMap.frontJackPositionMotor.getSensorCollection().setQuadraturePosition(0,0);
		
		RobotMap.rearJackPositionMotor.setSelectedSensorPosition(0, 0, 0);
		RobotMap.rearJackPositionMotor.getSensorCollection().setPulseWidthPosition(0,0);
		RobotMap.rearJackPositionMotor.getSensorCollection().setQuadraturePosition(0,0);
	  }

	public void setupEncoder() {
		
		/* Config the sensor used for Primary PID and sensor direction */
        RobotMap.frontJackPositionMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 
                                            RobotMap.frontJackPositionMotorPIDLoopIdx,
				                            RobotMap.frontJackPositionMotorTimeoutMs);
      	RobotMap.rearJackPositionMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 
                                            RobotMap.rearJackPositionMotorPIDLoopIdx,
				                            RobotMap.rearJackPositionMotorTimeoutMs);

		/* Ensure sensor is positive when output is positive */
		RobotMap.frontJackPositionMotor.setSensorPhase(false);
		RobotMap.rearJackPositionMotor.setSensorPhase(false);

		/**
		 * Set based on what direction you want forward/positive to be.
		 * This does not affect sensor phase. 
		 */ 
		RobotMap.frontJackPositionMotor.setInverted(RobotMap.frontJackPositionMotorInvert);
		RobotMap.rearJackPositionMotor.setInverted(RobotMap.rearJackPositionMotorInvert);

		/* Config the peak and nominal outputs, 12V means full */
		RobotMap.frontJackPositionMotor.configNominalOutputForward(0, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.frontJackPositionMotor.configNominalOutputReverse(0, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.frontJackPositionMotor.configPeakOutputForward(1, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.frontJackPositionMotor.configPeakOutputReverse(-1, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.configNominalOutputForward(0, RobotMap.rearJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.configNominalOutputReverse(0, RobotMap.rearJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.configPeakOutputForward(1, RobotMap.rearJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.configPeakOutputReverse(-1, RobotMap.rearJackPositionMotorTimeoutMs);

		/**
		 * Config the allowable closed-loop error, Closed-Loop output will be
		 * neutral within this range. See Table in Section 17.2.1 for native
		 * units per rotation.
		 */
		RobotMap.frontJackPositionMotor.configAllowableClosedloopError(0, RobotMap.frontJackPositionMotorPIDLoopIdx, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.configAllowableClosedloopError(0, RobotMap.rearJackPositionMotorPIDLoopIdx, RobotMap.rearJackPositionMotorTimeoutMs);

		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		RobotMap.frontJackPositionMotor.config_kF(RobotMap.frontJackPositionMotorPIDLoopIdx, 0 , RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.frontJackPositionMotor.config_kP(RobotMap.frontJackPositionMotorPIDLoopIdx, 0, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.frontJackPositionMotor.config_kI(RobotMap.frontJackPositionMotorPIDLoopIdx, 0, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.frontJackPositionMotor.config_kD(RobotMap.frontJackPositionMotorPIDLoopIdx, 0, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.config_kF(RobotMap.rearJackPositionMotorPIDLoopIdx, 0 , RobotMap.rearJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.config_kP(RobotMap.rearJackPositionMotorPIDLoopIdx,0, RobotMap.rearJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.config_kI(RobotMap.rearJackPositionMotorPIDLoopIdx, 0, RobotMap.rearJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.config_kD(RobotMap.rearJackPositionMotorPIDLoopIdx, 0, RobotMap.rearJackPositionMotorTimeoutMs);

		/**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int frontJackAbsolutePosition = RobotMap.frontJackPositionMotor.getSensorCollection().getAnalogIn();
		int rearJackAbsolutePosition = RobotMap.rearJackPositionMotor.getSensorCollection().getAnalogIn();

		/* Mask out overflows, keep bottom 12 bits */
		// frontJackAbsolutePosition &= 0xFFF;
		// if (RobotMap.frontJackPositionMotorSensorPhase) { frontJackAbsolutePosition *= -1; }
		// if (RobotMap.frontJackPositionMotorInvert) { frontJackAbsolutePosition *= -1; }

		// rearJackAbsolutePosition &= 0xFFF;
		// if (RobotMap.rearJackPositionMotorSensorPhase) { rearJackAbsolutePosition *= -1; }
		// if (RobotMap.rearJackPositionMotorInvert) { rearJackAbsolutePosition *= -1; }

		
		/* Set the quadrature (relative) sensor to match absolute */
		RobotMap.frontJackPositionMotor.setSelectedSensorPosition(0, RobotMap.frontJackPositionMotorPIDLoopIdx, RobotMap.frontJackPositionMotorTimeoutMs);
		RobotMap.rearJackPositionMotor.setSelectedSensorPosition(0, RobotMap.rearJackPositionMotorPIDLoopIdx, RobotMap.rearJackPositionMotorTimeoutMs);

		System.out.println("jack encoders setup");
		
	}

	public void jacksDisableDrivetrain()
	{
		diableDrivetrain = true;
	}

	public void setFrontPosition(double frontposition)
	{
		RobotMap.frontJackPositionMotor.set(ControlMode.Position, frontposition);	
	}

	public void setRearPosition(double rearposition)
	{
		RobotMap.rearJackPositionMotor.set(ControlMode.Position, rearposition);	
	}

    public void setPosition(double frontposition, double rearposition)
    {
    RobotMap.frontJackPositionMotor.set(ControlMode.Position, frontposition);
	RobotMap.rearJackPositionMotor.set(ControlMode.Position, rearposition);
	System.out.println("Analog in:" + RobotMap.frontJackPositionMotor.getSensorCollection().getAnalogIn());
	}

  public void setPower(double frontpower, double rearpower)
  {
    RobotMap.frontJackPositionMotor.set(ControlMode.PercentOutput, frontpower);
	  RobotMap.rearJackPositionMotor.set(ControlMode.PercentOutput, rearpower);
  }
  public void setFrontPower(double pct){
	  RobotMap.frontJackPositionMotor.set(ControlMode.PercentOutput,pct);
  }

  public void setRearPower(double pct){
	RobotMap.rearJackPositionMotor.set(ControlMode.PercentOutput,pct);
  }



  public void setDrivePower(double leftpower, double rightpower)
  {
	  RobotMap.rearJackLeftDriveMotor.set(ControlMode.PercentOutput, leftpower);
	  RobotMap.rearJackRightDriveMotor.set(ControlMode.PercentOutput, rightpower);
  }
  public void updateDashboard()
  {
	SmartDashboard.putNumber("Front Jack Power:", RobotMap.frontJackPositionMotor.getMotorOutputPercent());
	SmartDashboard.putNumber("Front Jack Encoder Position: ", RobotMap.frontJackPositionMotor.getSelectedSensorPosition(0));
	SmartDashboard.putNumber("Front Jack Velocity: ", RobotMap.frontJackPositionMotor.getSelectedSensorVelocity(0));
	SmartDashboard.putNumber("Rear Jack Power:", RobotMap.rearJackPositionMotor.getMotorOutputPercent());
	SmartDashboard.putNumber("Rear Jack Encoder Position: ", RobotMap.rearJackPositionMotor.getSelectedSensorPosition(0));
	SmartDashboard.putNumber("Rear Jack Velocity: ", RobotMap.rearJackPositionMotor.getSelectedSensorVelocity(0));
  }
  


  public void moveRearDown(int vel) {
	  RobotMap.rearJackPositionMotor.set(ControlMode.Velocity, vel);
  }
  public void moveFrontDown(int vel) {
	RobotMap.frontJackPositionMotor.set(ControlMode.Velocity, vel);
  }

  public int getRearEncoder(){
 	return RobotMap.rearJackLeftDriveMotor.getSelectedSensorPosition();
  }
  public int getFrontEncoder(){
	return RobotMap.rearJackRightDriveMotor.getSelectedSensorPosition();
  }



  
	//This makes sure that your on target to hit your value
	public void setupOnTarget(int ticks, int maxLoopNumber)
	{
		// zero the on target counter
		onTargetCounter = 0;

		RobotMap.rearJackPositionMotor.configAllowableClosedloopError(0, ticks, 10);
		RobotMap.frontJackPositionMotor.configAllowableClosedloopError(0, ticks, 10);

		// set tolerance in ticks
		allowedErrorRange = ticks;
		
		this.maxLoopNumber=maxLoopNumber;

	}

	
	//Makes sure you are within range of the values you set
	public boolean onTarget()
	{
		if (Math.abs(RobotMap.frontJackPositionMotor.getClosedLoopError(0))
				<= allowedErrorRange && 
				Math.abs(RobotMap.rearJackPositionMotor.getClosedLoopError(0))
				<= allowedErrorRange)
		{
			onTargetCounter++;
		}
		else
		{
			onTargetCounter = 0;
		}

		if (maxLoopNumber <= onTargetCounter)
		{
			return true;
		}

		return false;
	}


  public void toggleJackMode()
  {
	 System.out.println("Setting Jack Mode: ");
	 if (jacksDown = true)
	 {
		 System.out.println("Jacks Engaged");

	 }
	 if (jacksDown = false)
	 {
		System.out.println("Normal Drive");
	 }


//---------------------------------------------chicken code ------------------------
// public void climb (double speed) 
// {
// 	//Get pigeon angle stuff
// 	double[] ypr = new double[3];
// 	pigeon.getYawPitchRoll(ypr); //files ypr with the Yaw, Pitch, and Roll values
// 	double pigeonPitch = ypr[1]; //Yaw = 0, Pitch = 1, Roll = 2
// 	//You may need yaw or roll if the pigeon isn't oriented correctly

// 	//get correction values
// 	double correction = 0; //correction speed, default to 0
// 	if (speed != 0 && Math.abs(pigeonPitch) > 2) 
// 	{
// 		// must be +/-2 degrees off and trying to move
// 		correction = 0.015 * pigeonPitch;
// 		//constant (0.015) may be adjusted as needed
// 	 f
//	speed -= Math.abs(correction);
// 		//slow down speed so the max motor speed (speed + Math.abs(correction)) is the same as the input speed
// 	}

// 	//set motors
// 	RobotMap.rearJackPositionMotor.set(speed + correction);  //might be subtract

// 	RobotMap.frontJackPositionMotor.set(speed - correction); //reverse above

// }



  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
	setDefaultCommand(new JacksUpDownManual());
  }
}
