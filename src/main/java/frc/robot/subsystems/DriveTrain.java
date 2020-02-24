/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;
import frc.robot.utilities.Drive;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  
  private Drive drive; //setup DriveTrain

	
	//setup variables needed for error checking
	private int maxLoopNumber = 0;  
	private int onTargetCounter = 0;
	private int allowedErrorRange = 0;

	
	public DriveTrain() 
	{
		
		//Setup NavX using USB instead of the MXP port
//		try {
//            ahrs = new AHRS(SerialPort.Port.kUSB);
//        } catch (RuntimeException ex ) {
//            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
//        }	
		
     setFollower();  //runs code to setup the follower talons
    
    //this may be redundant put this back if its still needed
    //RobotMap.leftDriveSlave1.follow(RobotMap.leftDriveMaster);
   	//RobotMap.leftDriveSlave2.follow(RobotMap.leftDriveMaster);
   	//RobotMap.rightDriveSlave1.follow(RobotMap.rightDriveMaster);
   	//RobotMap.rightDriveSlave2.follow(RobotMap.rightDriveMaster);
	
   	
   	//sets power levels on the talons(currently just using defaults)
	// RobotMap.leftDriveMaster.configNominalOutputForward(0, 10);
	// RobotMap.leftDriveMaster.configNominalOutputReverse(0, 10);
	// RobotMap.leftDriveMaster.configPeakOutputForward(1, 10);
	// RobotMap.leftDriveMaster.configPeakOutputReverse(-1, 10);
	// RobotMap.rightDriveMaster.configNominalOutputForward(0, 10);
	// RobotMap.rightDriveMaster.configNominalOutputReverse(0, 10);
	// RobotMap.rightDriveMaster.configPeakOutputForward(1, 10);
	// RobotMap.rightDriveMaster.configPeakOutputReverse(-1, 10);

	//setup the master drivetrain talons to use Greyhill encoders
	// RobotMap.leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
   	// RobotMap.rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
	
   	// clearStickyFaults(true); //clears stickyfaults that may be left around on drive motors
	 setBrakeMode(false); //sets the drivetrain in brake mode(makes PID tuning easier since you won't coast and overshoot your target)
	 setInverted(); //inverts the drive train motors to the proper setting
	// setSensorPhase(false,false); //sets the phase of the encoders so that when a positive number is applied the encoders are reading a positive value

   
	drive=new Drive(RobotMap.rightDriveMaster,RobotMap.leftDriveMaster);  //builds the actual drive with the master talons

	}
	
	
	//This clears the sticky faults on the six drive train talons
	// public void clearStickyFaults(boolean shouldClearFaults) 
	// {
	// 	if (shouldClearFaults)
	// 	{
	// 		RobotMap.leftDriveMaster.clearStickyFaults(0);
	// 	   	RobotMap.leftDriveSlave1.clearStickyFaults(0);
	// 	   	RobotMap.leftDriveSlave2.clearStickyFaults(0);
		   	
	// 		RobotMap.rightDriveMaster.clearStickyFaults(0);		 
	// 		RobotMap.rightDriveSlave1.clearStickyFaults(0);
	// 	   	RobotMap.rightDriveSlave2.clearStickyFaults(0);
		   	

	// 	   	}
	// }
	
	//Enables Braking mode
	public void setBrakeMode(boolean shouldBrakeMode)
	{
		if (shouldBrakeMode)
		{
			RobotMap.leftDriveMaster.setIdleMode(IdleMode.kBrake);
		   	RobotMap.leftDriveSlave1.setIdleMode(IdleMode.kBrake);
		   	RobotMap.leftDriveSlave2.setIdleMode(IdleMode.kBrake);

		   	RobotMap.rightDriveMaster.setIdleMode(IdleMode.kBrake);
		   	RobotMap.rightDriveSlave1.setIdleMode(IdleMode.kBrake);
			RobotMap.rightDriveSlave2.setIdleMode(IdleMode.kBrake);
			   
			   
				}
		else
		{
			RobotMap.leftDriveMaster.setIdleMode(IdleMode.kCoast);
		   	RobotMap.leftDriveSlave1.setIdleMode(IdleMode.kCoast);
		   	RobotMap.leftDriveSlave2.setIdleMode(IdleMode.kCoast);
			
		   	RobotMap.rightDriveMaster.setIdleMode(IdleMode.kCoast);
		   	RobotMap.rightDriveSlave1.setIdleMode(IdleMode.kCoast);
		   	RobotMap.rightDriveSlave2.setIdleMode(IdleMode.kCoast);
		}
	}
	
	// Enables Braking mode
	// public void setBrakeMode(boolean shouldBrakeMode)
	// {
	// 	if (shouldBrakeMode)
	// 	{
	// 		RobotMap.leftDriveMaster.setIdleMode(IdleMode.kBrake);
	// 	   	RobotMap.leftDriveSlave1.setIdleMode(IdleMode.kBrake);
	// 	   	RobotMap.leftDriveSlave2.setIdleMode(IdleMode.kBrake);

	// 	   	RobotMap.rightDriveMaster.setIdleMode(IdleMode.kBrake);
	// 	   	RobotMap.rightDriveSlave1.setIdleMode(IdleMode.kBrake);
	// 		   RobotMap.rightDriveSlave2.setIdleMode(IdleMode.kBrake);
			   
			   
	// 			}
	// 	else
	// 	{
	// 		RobotMap.leftDriveMaster.setIdleMode(IdleMode.kCoast);
	// 	   	RobotMap.leftDriveSlave1.setIdleMode(IdleMode.kCoast);
	// 	   	RobotMap.leftDriveSlave2.setIdleMode(IdleMode.kCoast);
			
	// 	   	RobotMap.rightDriveMaster.setIdleMode(IdleMode.kCoast);
	// 	   	RobotMap.rightDriveSlave1.setIdleMode(IdleMode.kCoast);
	// 	   	RobotMap.rightDriveSlave2.setIdleMode(IdleMode.kCoast);
	// 	}
	// }

	//Setup which motors are followers and what they are following for the drive train
	private void setFollower()
	{
		RobotMap.leftDriveSlave1.follow(RobotMap.leftDriveMaster);
	   	RobotMap.leftDriveSlave2.follow(RobotMap.leftDriveMaster);
	   	
	   	RobotMap.rightDriveSlave1.follow(RobotMap.rightDriveMaster);
	   	RobotMap.rightDriveSlave2.follow(RobotMap.rightDriveMaster);
	}
	
	//Setup the inversion of the motors
	private void setInverted() {
		
		RobotMap.leftDriveMaster.setInverted(RobotMap.leftDriveInverted);
	 	RobotMap.leftDriveSlave1.setInverted(RobotMap.leftDriveInverted);
	   	RobotMap.leftDriveSlave2.setInverted(RobotMap.leftDriveInverted);
	   	
	   	RobotMap.rightDriveMaster.setInverted(RobotMap.rightDriveInverted);
	   	RobotMap.rightDriveSlave1.setInverted(RobotMap.rightDriveInverted);
	   	RobotMap.rightDriveSlave2.setInverted(RobotMap.rightDriveInverted);
	  
	}
	
	// public void setRamping() {
	// 	RobotMap.leftDriveMaster.configOpenloopRamp(0, 10);
	// 	RobotMap.leftDriveSlave1.configOpenloopRamp(0, 10);
	// 	RobotMap.leftDriveSlave2.configOpenloopRamp(0, 10);
		
	// 	RobotMap.rightDriveMaster.configOpenloopRamp(0, 10);
	// 	RobotMap.rightDriveSlave1.configOpenloopRamp(0, 10);
	// 	RobotMap.rightDriveSlave2.configOpenloopRamp(0, 10);
	// }

	//Setup the sensor phase of the encoders
	// public void setSensorPhase(boolean leftphase, boolean rightphase)
	// {
	// 	RobotMap.leftDriveMaster.setSensorPhase(leftphase);
	//    	RobotMap.rightDriveMaster.setSensorPhase(rightphase);
	// }

	//Zero's the encoders
	// public void zeroEncoders() {
	// 	RobotMap.leftDriveMaster.setSelectedSensorPosition(0, 0, 0);
	// 	RobotMap.rightDriveMaster.setSelectedSensorPosition(0, 0, 0);
		
	  //	RobotMap.leftDriveMaster.getSensorCollection().setQuadraturePosition(0, 0);
		//RobotMap.rightDriveMaster.getSensorCollection().setQuadraturePosition(0, 0);
		   //newhaven comenot
		
	//}
	
	//Sets the PID values on the talons
	// public void setPID(double P, double I, double D, double F) {

	// 	RobotMap.leftDriveMaster.config_kF(0, F, 10);
	// 	RobotMap.leftDriveMaster.config_kP(0, P, 10);
	// 	RobotMap.leftDriveMaster.config_kI(0, I, 10);
	// 	RobotMap.leftDriveMaster.config_kD(0, D, 10);

	// 	RobotMap.rightDriveMaster.config_kF(0, F, 10);
	// 	RobotMap.rightDriveMaster.config_kP(0, P, 10);
	// 	RobotMap.rightDriveMaster.config_kI(0, I, 10);
	// 	RobotMap.rightDriveMaster.config_kD(0, D, 10);

	// }
	
	//Sets the distance that you are trying to reach in inches
	// public void setSetpointDrive(double setpointinches)
	// {
	// 	System.out.println("Target "+inchToEncoder(setpointinches));
	// 	RobotMap.leftDriveMaster.set(ControlMode.Position,
	// 			inchToEncoder(setpointinches));
	// 	RobotMap.rightDriveMaster.set(ControlMode.Position, 
	// 			inchToEncoder(setpointinches));
	// }

	//Sets the angle that you are trying to reach
	// public void setSetpointTurn(double setpointdegrees)
	// {
	// 	zeroEncoders();
	// 	zeroGyro();

	// 	System.out.println("setpoint: " + degreesToEncoder(setpointdegrees));

	// 	RobotMap.leftDriveMaster.set(ControlMode.Position,
	// 			degreesToEncoder(-setpointdegrees));
	// 	RobotMap.rightDriveMaster.set(ControlMode.Position, 
	// 			degreesToEncoder(setpointdegrees));
	// }
	
	//This makes sure that your on target to hit your value
	// public void setupOnTarget(int ticks, int maxLoopNumber)
	// {
	// 	// zero the on target counter
	// 	onTargetCounter = 0;

	// 	RobotMap.leftDriveMaster.configAllowableClosedloopError(0, ticks, 10);
	// 	RobotMap.rightDriveMaster.configAllowableClosedloopError(0, ticks, 10);

	// 	// set tolerance in ticks
	// 	allowedErrorRange = ticks;
		
	// 	this.maxLoopNumber=maxLoopNumber;

	// }
	
	//converts inches to encoder pulses (needs to be tuned to the pulses of your encoder
	// public double inchToEncoder(double inches)
	// {
	// 	System.out.println("inchtoencoder: "+(inches / RobotMap.wheelCir) * 5000);
	// 	return (inches / RobotMap.wheelCir) * 5000;
	// }
	
	
	//converts degrees to encoder pulses
	// public double degreesToEncoder(double degrees)
	// {
	// 	return inchToEncoder((RobotMap.robotCir / 360) * degrees);
	// }
	
	
	//Makes sure you are within range of the values you set
	// public boolean onTarget()
	// {
	// 	if (Math.abs(RobotMap.leftDriveMaster.getClosedLoopError(0))
	// 			<= allowedErrorRange && 
	// 			Math.abs(RobotMap.rightDriveMaster.getClosedLoopError(0))
	// 			<= allowedErrorRange)
	// 	{
	// 		onTargetCounter++;
	// 	}
	// 	else
	// 	{
	// 		onTargetCounter = 0;
	// 	}

	// 	if (maxLoopNumber <= onTargetCounter)
	// 	{
	// 		return true;
	// 	}

	// 	return false;
	// }

	//Sends encoder data to the dashboard when requested
	// public void updateEncoders()
	// {
	// 	SmartDashboard.putNumber("Right Encoder", RobotMap.rightDriveMaster.getSelectedSensorPosition(0));
	// 	SmartDashboard.putNumber("Left Encoder", RobotMap.leftDriveMaster.getSelectedSensorPosition(0));
	// 	SmartDashboard.putNumber("Right Enc VEL", RobotMap.rightDriveMaster.getSelectedSensorVelocity(0));
	// 	SmartDashboard.putNumber("Left Enc VEL", RobotMap.leftDriveMaster.getSelectedSensorVelocity(0));
	// }
	
	//Actual method to drive the robot using a power(throttle) and wheel(rotation) as inputs
	public void driveArcade(double throttle, double wheel)
	{
		//  if (Robot.jacks.diableDrivetrain = true) {
		// // 	drive.driveArcade(throttle, wheel);
		// 	System.out.println("Drive Disabled");
		//  }
		// System.out.println(Robot.jacks.diableDrivetrain);
		drive.driveArcade(throttle  , wheel);
		
		Robot.jacks.setDrivePower(Robot.oi.one.getLeftStickY(), Robot.oi.one.getLeftStickY()* -1);
	}
	
	//set drive train shifters to low gear
	public void shiftLow() {
		RobotMap.solenoidDriveShifters.set(DoubleSolenoid.Value.kReverse);
		System.out.println("Shift Low");	
		SmartDashboard.putString("Drive Train Gear" , "LOW");

	}
	
	// set drive train shifters to high gear
	public void shiftHigh() {
		RobotMap.solenoidDriveShifters.set(DoubleSolenoid.Value.kForward);
		System.out.println("Shift High");
		SmartDashboard.putString("Drive Train Gear" , "HIGH");
	
	}
	
	//gets the heading of the NavX gyro
// 	public double getHeading() {
// //		return ahrs.getAngle();
// 		return 0;
// 	}
	
	//zeros the NavX gyro
// 	public void zeroGyro() {
// //		ahrs.reset();
		
// 	}
	
	//puts the Gyro heading on the dashboard
	// public void updateHeading() {
	// 	SmartDashboard.putNumber("Gyro Heading", this.getHeading());
	// }
	
	//sets the default command to drive the robot by joystick
    public void initDefaultCommand() {
   	
		setDefaultCommand(new DriveCommand());

    }
}
