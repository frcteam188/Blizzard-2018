package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.automodes.ErrorAuto;
import org.usfirst.frc.team188.automodes.LeftSideLeftScaleAuto;
import org.usfirst.frc.team188.automodes.RightSideRightScaleAuto;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleSideSelector extends Command {
	
	CommandGroup scaleAuto;
	char side;

    public ScaleSideSelector(char side) {
    	this.side = side;
    }

    protected void initialize() {
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L')
    		scaleAuto = new LeftSideLeftScaleAuto();
    	else if(DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') 
    		scaleAuto = new RightSideRightScaleAuto();
    	else scaleAuto = new ErrorAuto("ScaleSideSelector could not select a side.");
    	
    	scaleAuto.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	if(scaleAuto != null && !scaleAuto.isCanceled()) 
    		scaleAuto.cancel();
    }

    protected void interrupted() {
    	end();
    }
}