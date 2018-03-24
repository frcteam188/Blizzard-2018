package org.usfirst.frc.team188.robot.subsystems;

import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	double intakePower = 1.0, outtakePower = 1.0;
	
	public static DoubleSolenoid pivotPiston = RobotMap.intakePivotPiston;
	
	//Motors
	public TalonSRX intakeLeft = new TalonSRX(RobotMap.intakeLeft);
	public TalonSRX intakeRight = new TalonSRX(RobotMap.intakeRight);
	
	//Digital Sensors
	public DigitalInput lightSensor = new DigitalInput(RobotMap.intakeLightSensor);
	
	public void moveIntake(){
		if(Math.abs(Robot.m_oi.stick2.getRawAxis(1)) < 0.05){
			intakeLeft.set(ControlMode.PercentOutput, -0.2);
			intakeRight.set(ControlMode.PercentOutput, 0.2);
		} else{
			intakeLeft.set(ControlMode.PercentOutput, -Robot.m_oi.stick2.getRawAxis(1)*0.7);
			intakeRight.set(ControlMode.PercentOutput, Robot.m_oi.stick2.getRawAxis(1)*0.7);
		}
	}
	
	public void drive(double power){
		intakeLeft.set(ControlMode.PercentOutput, power);
		intakeRight.set(ControlMode.PercentOutput, -power);
	}
	
	public void pivot(char dir){
		if (dir == 'u')
			pivotPiston.set(Value.kForward);
		else if(dir == 'd')
			pivotPiston.set(Value.kReverse);
	}
	
	public void pivotIntake(){
		if (Robot.m_oi.pivotIn.get())
			pivotPiston.set(Value.kForward);
		else if(Robot.m_oi.pivotOut.get())
			pivotPiston.set(Value.kReverse);
	}
	
	public void stop(){
		intakeLeft.set(ControlMode.PercentOutput, 0);
		intakeRight.set(ControlMode.PercentOutput, 0);
		pivotPiston.set(Value.kOff);
	}
	
    public void initDefaultCommand() {
    }
}
