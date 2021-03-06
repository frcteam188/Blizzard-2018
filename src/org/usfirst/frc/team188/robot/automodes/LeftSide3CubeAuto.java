package org.usfirst.frc.team188.robot.automodes;

import org.usfirst.frc.team188.robot.commands.DelayedCommand;
import org.usfirst.frc.team188.robot.commands.ResetEnc;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroArc;
import org.usfirst.frc.team188.robot.controlcommands.LowGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.MoveElevator;
import org.usfirst.frc.team188.robot.controlcommands.MoveIntake;
import org.usfirst.frc.team188.robot.controlcommands.MultiGearGyroDrive;
import org.usfirst.frc.team188.robot.controlcommands.PivotIntake;
import org.usfirst.frc.team188.robot.controlcommands.ShiftGears;
import org.usfirst.frc.team188.robot.controlcommands.Turn;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftSide3CubeAuto extends CommandGroup {

    public LeftSide3CubeAuto() {
    	
    	addParallel(new MoveIntake(0, 0));
    	addSequential(new ShiftGears('h'));  //shift to high
    	addParallel(new DelayedCommand(1.8,new MoveElevator(2)));
    	addParallel(new PivotIntake('d'));  //intake down
    	
//	    addSequential(new HighGearGyroDrive(236500, 0)); //258000, high gear
	    addSequential(new MultiGearGyroDrive(275000,0,275000));
	    
	    addSequential(new ShiftGears('l'));  //shift to low
//	    addParallel(new DelayedCommand(0.3,new Command[]{new MoveElevator(3),new WaitCommand(0.2)}));
	    addSequential(new Turn(38, 3));  //turn towards scale
	    addParallel(new MoveElevator(3));  //elevator to scale position
	    addSequential(new WaitCommand(0.2));
	    addSequential(new MoveIntake(0.4, 0.6)); //SCORE FIRST CUBE
	    addParallel(new MoveElevator(0));  //elevator down
	    addParallel(new PivotIntake('u'));
	    addSequential(new WaitCommand(0.5));
	    addParallel(new PivotIntake('d'));
	    addSequential(new Turn(148, 9));  //turn towards first switch cube
	    addSequential(new ResetEnc());
	    addParallel(new MoveIntake(-0.55, 4));  //run intake to pick up cube
	    
//	    addSequential(new LowGearGyroDrive(70000, 148),2.25); //61000
	    addSequential(new LowGearGyroDrive(56000, 148),2.25);
	    
	    addSequential(new MoveIntake(-0.55,0.2));
	    addParallel(new MoveIntake(-0.55,2));
	    addParallel(new MoveElevator(1));  //elevator up partway
	    addSequential(new Turn(10, 5));  //turn towards scale
	    addSequential(new ResetEnc());
	    addParallel(new MoveElevator(3));  //elevator to scale position
	    addParallel(new DelayedCommand(0.8, new MoveIntake(0.8, 0, 0.6)));
	    addSequential(new LowGearGyroArc(46000, 10));  //drive towards scale
//	    addSequential(new MoveIntake(0.3, 0.6)); //SCORE SECOND CUBE
//	    addSequential(new MoveIntake(0.6, 0, 0.6));
	    addParallel(new DelayedCommand(0.3, new Command[] {new PivotIntake('u'), new MoveElevator(0)}));
	    addSequential(new LowGearGyroArc(15500, 10));  //drive back, 0
//	    addSequential(new WaitCommand(0.2));
//	    addParallel(new PivotIntake('u'));
//	    addParallel(new MoveElevator(0));  //elevator down
//	    addSequential(new WaitCommand(0.5));
	    addParallel(new PivotIntake('d'));
	    addSequential(new Turn(126, 1));  //turn towards second switch cube
	    
	    addSequential(new ResetEnc());
	    addParallel(new MoveIntake(-1,-1, 3));
	    addSequential(new LowGearGyroDrive(27000, 126),1.7);  //move to second switch cube
//	    addSequential(new LowGearGyroDrive(35000, 125, 25000));
//	    addSequential(new LowGearGyroDrive(35000, 135));
	    addSequential(new MoveIntake(-1,-1, 0.4));
	    addParallel(new MoveElevator(1));
	    addSequential(new Turn(-7));
	    addParallel(new MoveElevator(3));
	    addSequential(new ResetEnc());
	    addParallel(new DelayedCommand(0.5, new MoveIntake(0.4, 0.6)));
	    addSequential(new LowGearGyroDrive(65000, -7));
//	    addSequential(new MoveIntake(0.4, 0.6));	    
    }
}
