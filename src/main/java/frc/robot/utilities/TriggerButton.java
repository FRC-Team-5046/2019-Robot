/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Add your docs here.
 * @author FRC-3539
 *
 * @since 02/02/17
 * 
 * 
 */
public class TriggerButton extends Button {


    private int triggerAxis;
	private GenericHID joystick;
	public boolean isTriggered;
	private double lowRange = .1;
	private double topRange = .75;

	public TriggerButton(GenericHID joystick, int axis)
	{
		triggerAxis = axis;
		this.joystick = joystick;
	}

	public double getValue()
	{
		return joystick.getRawAxis(triggerAxis);
	}

	public void setRange(double topRange, double lowRange)
	{
		this.lowRange = lowRange;
		this.topRange = topRange;
	}

	@Override
	public boolean get()
	{
		if (Math.abs(joystick.getRawAxis(triggerAxis)) > topRange)
		{
			isTriggered = true;
		}
		else if (Math.abs(joystick.getRawAxis(triggerAxis)) < lowRange)
		{
			isTriggered = false;
		}
		return isTriggered;
	}

}
