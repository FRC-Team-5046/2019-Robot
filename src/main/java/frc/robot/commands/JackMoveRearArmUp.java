package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JackMoveRearArmUp extends Command {

    public JackMoveRearArmUp(){
    }


    @Override
    protected void initialize() {
        Robot.jacks.moveRearDown(-0);    
    }
    @Override
    protected void execute() {
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}