package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JackMoveFrontArmUp extends Command {

    public JackMoveFrontArmUp(){
    }

    @Override
    protected void initialize() {
        Robot.jacks.moveFrontDown(-0);    
    }

	@Override
	protected boolean isFinished() {
		return false;
	}
}