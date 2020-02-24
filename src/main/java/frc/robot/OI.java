/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



import frc.robot.commands.*;



import frc.robot.utilities.LogitechF310;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  
  public LogitechF310 one = new LogitechF310(RobotMap.DRIVER);
  public LogitechF310 two = new LogitechF310(RobotMap.OPERATOR);
  public LogitechF310 three = new LogitechF310(RobotMap.ALT);
  
  public OI() 
  {

    //one.buttonR1.toggleWhenPressed(new DriveShiftToggle());
    //one.buttonL1.toggleWhenPressed(new IntakeHatchToggle());
    //one.buttonL1.toggleWhenPressed(new JacksFrontUp());
    //one.buttonSTART.whenPressed(new JacksDown());
    //three.buttonSTART.toggleWhenPressed(new JacksDown());
    //one.buttonBACK.toggleWhenPressed(new JacksUpDownManual());


    //two.buttonA.toggleWhenPressed(new IntakePositionToggle());
    // two.buttonA.whenPressed(new ArmPositionCargo1());
    // two.buttonB.whenPressed(new ArmPositionCargo2());
    // two.buttonY.whenPressed(new ArmPositionCargo3());
    // two.buttonPadDown.whenPressed(new ArmPositionHatch1());
    // two.buttonPadLeft.whenPressed(new ArmPositionHatch2());
    // two.buttonPadUp.whenPressed(new ArmPositionHatch3());

    
    //two.buttonSTART.toggleWhenPressed(new IntakeHatchAssemblyToggle());
    //two.buttonBACK.toggleWhenPressed(new IntakeHatchAssemblyToggle());
    


    one.buttonL1.whileHeld(new IntakeCargoRollersSuckIn());  //actually out
    one.buttonR1.whileHeld(new IntakeCargoRollersSpitOut());  //actually in

    //two.buttonA.toggleWhenPressed(new IntakePositionDown());
    //two.buttonY.toggleWhenPressed(new IntakePositionUp());

  
    //three.buttonR1.whileHeld(new DriveBackwards());

    // three.buttonB.whileHeld(new JackMoveRearArmDown(0.0));
    // three.buttonX.whileHeld(new JackMoveFrontArmsDown(0.0));

    // three.buttonA.whileHeld(new JackMoveFrontArmUp());
    // three.buttonY.whileHeld(new JackMoveRearArmUp());

    
   }
}
