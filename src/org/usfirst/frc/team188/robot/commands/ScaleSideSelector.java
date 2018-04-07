package org.usfirst.frc.team188.robot.commands;

import org.usfirst.frc.team188.robot.automodes.ErrorAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSideLeftScaleAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSideRightScaleAuto;
import org.usfirst.frc.team188.robot.automodes.LeftSideTwoScaleDriveAuto;
import org.usfirst.frc.team188.robot.automodes.RightSideRightScaleAuto;

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
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'L') {
    		if (DriverStation.getInstance().getGameSpecificMessage().charAt(2) == 'L')
//    			scaleAuto = new LeftSideTwoScaleDriveAuto();
    			scaleAuto = new LeftSideLeftScaleAuto(); //change this to new auto
    		else scaleAuto = new LeftSideLeftScaleAuto();
    	} else if(DriverStation.getInstance().getGameSpecificMessage().charAt(1) == 'R') 
    		scaleAuto = new LeftSideRightScaleAuto();
    	else scaleAuto = new ErrorAuto("ScaleSideSelector could not select a side.");
    	scaleAuto = new LeftSideLeftScaleAuto(); //remove this later
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
