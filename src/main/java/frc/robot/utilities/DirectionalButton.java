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
 * 
 * @author FRC-3539
 * 
 *  @since 11/12/17
 */
public class DirectionalButton extends Button {

    private int neededAngle;
	private Direction direction;
	private GenericHID joystick;

	public enum Direction
	{

		UP, UPRIGHT, RIGHT, DOWNRIGHT, DOWN, DOWNLEFT, LEFT, UPLEFT
	}

	public DirectionalButton(GenericHID joystick, Direction direction)
	{
		neededAngle = 1;
		this.direction = direction;
		this.joystick = joystick;

		switch (direction)
		{
		case UP:
			neededAngle = 0;
			break;
		case UPRIGHT:
			neededAngle = 45;
			break;
		case RIGHT:
			neededAngle = 90;
			break;
		case DOWNRIGHT:
			neededAngle = 135;
			break;
		case DOWN:
			neededAngle = 180;
			break;
		case DOWNLEFT:
			neededAngle = 225;
			break;
		case LEFT:
			neededAngle = 270;
			break;
		case UPLEFT:
			neededAngle = 315;
			break;

		}
	}

	public int getPOV()
	{
		return joystick.getPOV();
	}

	public boolean get()
	{
		if (joystick.getPOV() == neededAngle)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public Direction getDirection()
	{
		return direction;
	}  
}
