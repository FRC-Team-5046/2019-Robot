package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JackMoveRearArmDown extends Command {

    private double holdPct;
    private boolean isAtLimit;
    public JackMoveRearArmDown(double holdPct){
        this.holdPct = holdPct;
    }
    @Override
    protected void initialize() {
        Robot.jacks.moveRearDown(0);
    }

    @Override
    protected void execute() {
        isAtLimit = Robot.jacks.getRearEncoder()>0;
        if(isAtLimit){
            Robot.jacks.setRearPower(0);
        }
        else{
            Robot.jacks.moveRearDown(0);
        }
    }
    @Override
    protected boolean isFinished() {
        return false;
    }

}