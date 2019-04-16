/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;

/**
 * Add your docs here.
 */
public class Drive {


//  TalonSRX talon1, talon2, talon3, talon4, talon5, talon6;
CANSparkMax talon1, talon2, talon3, talon4, talon5, talon6;

DriveMode driveMode;
	public static enum DriveMode
	{
		TWO, FOUR, SIX;
	}

//	public Drive(TalonSRX rightDriveMaster, TalonSRX leftDriveMaster)
	public Drive(CANSparkMax rightDriveMaster, CANSparkMax leftDriveMaster)
	
	{
		driveMode = DriveMode.TWO;

		talon1 = rightDriveMaster;
		talon2 = leftDriveMaster;
		
	}

//	public Drive(TalonSRX rightFront, TalonSRX rightBack, TalonSRX leftFront, TalonSRX leftBack)
	public Drive(CANSparkMax rightFront, CANSparkMax rightBack, CANSparkMax leftFront, CANSparkMax leftBack)
	
	{
		driveMode = DriveMode.FOUR;
		talon1 = rightFront;
		talon2 = rightBack;
		talon3 = leftFront;
		talon4 = leftBack;
	}

//	public Drive(TalonSRX rightFront, TalonSRX rightMiddle, TalonSRX rightBack, TalonSRX leftFront, TalonSRX leftMiddle, TalonSRX leftBack)
	public Drive(CANSparkMax rightFront, CANSparkMax rightMiddle, CANSparkMax rightBack, CANSparkMax leftFront, CANSparkMax leftMiddle, CANSparkMax leftBack)

	{
		driveMode = DriveMode.SIX;
		talon1 = rightFront;
		talon2 = rightMiddle;
		talon3 = rightBack;
		talon4 = leftFront;
		talon5 = leftMiddle;
		talon6 = leftBack;
	}
	

	public void driveArcade(double moveValue, double rotateValue)
	{
		double leftMotorSpeed;
		double rightMotorSpeed;

		moveValue = limitValue(moveValue);
		rotateValue = limitValue(rotateValue);

//		double maxInput = Math.copySign(Math.max(Math.abs(moveValue), Math.abs(rotateValue)), moveValue);
		
		if (moveValue >= 0.0)
		{
			if (rotateValue >= 0.0)
			{
				leftMotorSpeed = moveValue - rotateValue;
				//leftMotorSpeed = maxInput;
				rightMotorSpeed = Math.max(moveValue, rotateValue);
				//rightMotorSpeed = moveValue - rotateValue;
			}
			else
			{
				leftMotorSpeed = Math.max(moveValue, -rotateValue);
				//leftMotorSpeed = moveValue + rotateValue;
				rightMotorSpeed = moveValue + rotateValue;
				//rightMotorSpeed = maxInput;
			}
		}
		else
		{
			if (rotateValue >= 0.0)
			{
				leftMotorSpeed = -Math.max(-moveValue, rotateValue);
				//leftMotorSpeed = moveValue + rotateValue;
				rightMotorSpeed = moveValue + rotateValue;
				//rightMotorSpeed = maxInput;
			}
			else
			{
				leftMotorSpeed = moveValue - rotateValue;
				//leftMotorSpeed = maxInput;
				rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
				//rightMotorSpeed = moveValue - rotateValue;
			}
		}
		setMotorOutputs(-rightMotorSpeed, -leftMotorSpeed);
		//System.out.println("left speed " + leftMotorSpeed + "  right speed " + rightMotorSpeed);
		//setMotorOutputs(rightMotorSpeed, leftMotorSpeed);
	}

	public void setMotorOutputs(double right, double left)
	{
		if (driveMode == DriveMode.TWO)
		{
			//System.out.println(left + " " + right);
			//talon1.set(ControlMode.PercentOutput, right);
			talon1.set(right);
			//talon2.set(ControlMode.PercentOutput, left);
			talon2.set(left);

		}
		if (driveMode == DriveMode.FOUR)
		{
			// talon1.set(ControlMode.PercentOutput, right);
			// talon2.set(ControlMode.PercentOutput, right);
			// talon3.set(ControlMode.PercentOutput, left);
			// talon4.set(ControlMode.PercentOutput, left);
			talon1.set(right);
			talon2.set(right);
			talon3.set(left);
			talon4.set(left);
			// System.out.println(right+" "+left);
		}
		if (driveMode == DriveMode.SIX)
		 {
		// 	talon1.set(ControlMode.PercentOutput, right);
		// 	talon2.set(ControlMode.PercentOutput, right);
		// 	talon3.set(ControlMode.PercentOutput, right);
		// 	talon4.set(ControlMode.PercentOutput, left);
		// 	talon5.set(ControlMode.PercentOutput, left);
		// 	talon6.set(ControlMode.PercentOutput, left);
			talon1.set(right);
			talon2.set(right);
			talon3.set(right);
			talon4.set(left);
			talon5.set(left);
			talon6.set(left);

		}
	}

	public double limitValue(double value)
	{
		if (value > 1.0)
			value = 1.0;
		if (value < -1.0)
			value = -1.0;
		return value;
	} 
}
