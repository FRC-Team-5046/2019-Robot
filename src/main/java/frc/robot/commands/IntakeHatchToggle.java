/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeHatchToggle extends Command {
  public IntakeHatchToggle() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
}

// Called just before this Command runs the first time
protected void initialize() {
  Robot.intake.setHatchClosed();
    //System.out.println("Lift Up init");
}

// Called repeatedly when this Command is scheduled to run
protected void execute() {
  }

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished() {
    return false;
}

// Called once after isFinished returns true
protected void end() {
  //System.out.println("Lift Up end");
  
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted() {
  //System.out.println("Lift Interupted");
  Robot.intake.setHatchOpen();
    
}
}
