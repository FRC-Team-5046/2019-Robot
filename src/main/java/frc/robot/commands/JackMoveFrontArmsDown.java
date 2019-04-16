package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JackMoveFrontArmsDown extends Command {

    private double holdPct;
    private boolean atLimit;
    public JackMoveFrontArmsDown(double holdPct){
        this.holdPct = holdPct;
    }
    @Override
    protected void initialize() {
        Robot.jacks.moveFrontDown(0);

    }

    @Override
    protected void execute() {
        atLimit = Robot.jacks.getFrontEncoder()>0;
        if(atLimit){
            Robot.jacks.setFrontPower(holdPct);
        }
        else{
            Robot.jacks.moveFrontDown(0);
        }
    }
    @Override
    protected boolean isFinished() {
        return false;
    }

}