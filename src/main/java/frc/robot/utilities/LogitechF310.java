/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;
import frc.robot.utilities.DirectionalButton.Direction;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 * 
 * 
 */
public class LogitechF310 extends Joystick {
    private static final int A = 1;
	private static final int B = 2;
	private static final int X = 3;
	private static final int Y = 4;

	private static final int L1 = 5;
	private static final int R1 = 6;

	private static final int BACK = 7;
	private static final int START = 8;

	private static final int LS = 9;
	private static final int RS = 10;

	
	private static final int RIGHT_TRIGGER = 3;
	private static final int LEFT_TRIGGER = 2;

	// AXIS
	private static final int X_AxisL = 0;
	private static final int Y_AxisL = 1;

	private static final int X_AxisR = 4;
	private static final int Y_AxisR = 5;

	public LogitechF310(int port)
	{
		super(port);
	}

	public JoystickButton buttonX = new JoystickButton(this, X);
	public JoystickButton buttonY = new JoystickButton(this, Y);
	public JoystickButton buttonA = new JoystickButton(this, A);
	public JoystickButton buttonB = new JoystickButton(this, B);

	public JoystickButton buttonL1 = new JoystickButton(this, L1);
	public JoystickButton buttonR1 = new JoystickButton(this, R1);

	public TriggerButton buttonTL = new TriggerButton(this, LEFT_TRIGGER);
	public TriggerButton buttonTR = new TriggerButton(this, RIGHT_TRIGGER);

	public JoystickButton buttonSTART = new JoystickButton(this, START);
	public JoystickButton buttonBACK = new JoystickButton(this, BACK);

	public JoystickButton buttonLS = new JoystickButton(this, LS);
	public JoystickButton buttonRS = new JoystickButton(this, RS);

	public DirectionalButton buttonPadLeft = new DirectionalButton(this, Direction.LEFT);
	public DirectionalButton buttonPadRight = new DirectionalButton(this, Direction.RIGHT);
	public DirectionalButton buttonPadUp = new DirectionalButton(this, Direction.UP);
	public DirectionalButton buttonPadDown = new DirectionalButton(this, Direction.DOWN);

	public double getLeftStickX()
	{
		return getRawAxis(X_AxisL);
	}

	public double getLeftStickY()
	{
		return getRawAxis(Y_AxisL);
	}

	public double getRightStickX()
	{
		return getRawAxis(X_AxisR);
	}

	public double getRightStickY()
	{
		return getRawAxis(Y_AxisR);
	}
}
