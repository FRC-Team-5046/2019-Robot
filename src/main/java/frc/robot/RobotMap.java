/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.utilities.Gains;



import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static final int DRIVER = 0; 
	public static final int OPERATOR = 1; //operator joysick USB1
  public static final int ALT = 2;

	public static final int pcm = 0;  //setup pneumatics
  public static final int pdp = 0; //setup 
  

  

  //-----------------------------DriveTrain--------------------------------------------------------
	
	//setup PIDF values for DriveTrain
	public static double driveP = .13;
	public static double driveI = 0.0;
	public static double driveD = 0.0;
	public static double driveF = 0.0;

	//setup PIDF values for Drivetrain when turning with Gyro
	public static double turnP = .3;
	public static double turnI = 0.0;
	public static double turnD = 0.0;
	public static double turnF = 0.0;
  
  
  public static final double wheelCir = 18.84; //Set wheel circumfrence for 6in wheels
  public static final double robotCir = 100;  //Set circumfrence of the robot
  
  public static DoubleSolenoid solenoidDriveShifters = new DoubleSolenoid(0, 7);
  
	//sets the sensor phase of the drivetrain motors
	public static boolean leftDriveSensorPhase = false;
	public static boolean rightDriveSensorPhase = false;
	
	//sets the direction of the drivetrain motors
	public static boolean leftDriveInverted = true;
	public static boolean rightDriveInverted = false;

	//defines the drivetrain master talons (these have the encoders hooked to them)
  // public static TalonSRX leftDriveMaster = new TalonSRX(1);
  // public static TalonSRX rightDriveMaster = new TalonSRX(2);
  
public static CANSparkMax leftDriveMaster = new CANSparkMax(1, MotorType.kBrushless);
public static CANSparkMax rightDriveMaster = new CANSparkMax(2, MotorType.kBrushless);
	
  // public static VictorSPX leftDriveMaster = new VictorSPX(3);
	//public static VictorSPX rightDriveMaster = new VictorSPX(1);
	
	//these are the follower talons or victors in the drivetrain depending on the robot we are using
	// public static VictorSPX leftDriveSlave1 = new VictorSPX(3);
	// public static VictorSPX leftDriveSlave2 = new VictorSPX(5);
	// public static VictorSPX rightDriveSlave1 = new VictorSPX(4);
	// public static VictorSPX rightDriveSlave2 = new VictorSPX(6);

  public static CANSparkMax leftDriveSlave1 = new CANSparkMax(3, MotorType.kBrushless);
  public static CANSparkMax leftDriveSlave2 = new CANSparkMax(5, MotorType.kBrushless);
  public static CANSparkMax rightDriveSlave1 = new CANSparkMax(4, MotorType.kBrushless);
  public static CANSparkMax rightDriveSlave2 = new CANSparkMax(6, MotorType.kBrushless);
    

  //public static TalonSRX leftDriveSlave1 = new TalonSRX(3);
	//public static TalonSRX leftDriveSlave2 = new TalonSRX(5);
	//public static TalonSRX rightDriveSlave1 = new TalonSRX(4);
	//public static TalonSRX rightDriveSlave2 = new TalonSRX(6);
	

public static CANSparkMax armMotor = new CANSparkMax(7, MotorType.kBrushless);
  //---------------------------ARM Position Motor -------------------------------------------

  // public static TalonSRX armPositionMotor = new TalonSRX(7);

  // public static final Gains armPositionPID = new Gains(45, 0.00, 0.0, 0.0, 0, 1);
  // public static boolean armPositionMotorInvert = true;
  // public static boolean armPositionMotorSensorPhase = true;
  // public static final int armPositionMotorTimeoutMs = 30;
  // public static final int armPositionMotorPIDLoopIdx = 0;
  // public static final int armPositionMotorSlotIdx = 0;

  //---------------------------Front Jack Position Motor -------------------------------------------

  public static TalonSRX frontJackPositionMotor = new TalonSRX(8);

  public static final Gains frontJackPositionPID = new Gains(6.5, 2, 0.0, 0.0, 0, 1);
  public static boolean frontJackPositionMotorInvert = false;
  public static boolean frontJackPositionMotorSensorPhase = false;
  public static final int frontJackPositionMotorTimeoutMs = 30;
  public static final int frontJackPositionMotorPIDLoopIdx = 0;
  public static final int frontJackPositionMotorSlotIdx = 0;

  //---------------------------Rear Jack Position Motor -------------------------------------------

  public static TalonSRX rearJackPositionMotor = new TalonSRX(9);

  public static final Gains rearJackPositionPID = new Gains(7.8, 0.00, 0.0, 0.0, 0, 1);
  public static boolean rearJackPositionMotorInvert = false;
  public static boolean rearJackPositionMotorSensorPhase = false;
  public static final int rearJackPositionMotorTimeoutMs = 30;
  public static final int rearJackPositionMotorPIDLoopIdx = 0;
  public static final int rearJackPositionMotorSlotIdx = 0;

  

  //----------------------------Cargo Intake Motor ----------------------------------------------------
  public static TalonSRX cargoIntakeMotor = new TalonSRX(10);
  public static boolean cargoIntakeMotorInvert = false;

  

  //----------------------------Hatch System ---------------------------------------------------------
 public static DoubleSolenoid hatchSliderSolenoid = new DoubleSolenoid(2,5);
 public static DoubleSolenoid hatchGrabberSolenoid = new DoubleSolenoid(1,6);

 public boolean hatchMode = false; 


  
  //---------------------------INTAKE Position Motor ----------------------------------------

  public static TalonSRX intakePositionMotor = new TalonSRX(11);

  public static final Gains intakePositionPID = new Gains(1, 0.0, 1.0, 0.0, 0, 1);
  public static boolean intakePositionMotorInvert = false;
  public static boolean intakePositionMotorSensorPhase = false;
  public static final int intakePositionMotorTimeoutMs = 30;
  public static final int intakePositionMotorPIDLoopIdx = 0;
  public static final int intakePositionMotorSlotIdx = 0;

  //--------------------------Rear Jack Drive Motors -----------------------------------------------
  public static TalonSRX rearJackLeftDriveMotor = new TalonSRX(12);
  public static boolean rearJackLeftDriveMotorInvert = false;
  public static TalonSRX rearJackRightDriveMotor = new TalonSRX(13);
  public static boolean rearJackRightDriveMotorInvert = false;
  



}
