package org.usfirst.frc.team188.robot.controlcommands;

import org.usfirst.frc.team188.robot.Constants;
import org.usfirst.frc.team188.robot.Robot;
import org.usfirst.frc.team188.robot.subsystems.BaseEncPID;
import org.usfirst.frc.team188.robot.subsystems.BaseGyroPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowGearGyroArc extends Command {
	
	
	double power;
	int endpoint;
	
	BaseEncPID baseEncPID;
	BaseGyroPID baseGyroPID;
	
	double pEnc = Constants.baseArcPID[0];
	double iEnc = Constants.baseArcPID[1];
	double dEnc = Constants.baseArcPID[2];
	
	double pGyro = Constants.gyroArcPID[0];
	double iGyro = Constants.gyroArcPID[1];
	double dGyro = Constants.gyroArcPID[2];

	
    public LowGearGyroArc(int encSetpoint, double gyroSetpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.base);
    	this.power = 0.5;
    	baseEncPID = new BaseEncPID(pEnc,iEnc,dEnc,encSetpoint,this.power,true);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,gyroSetpoint,this.power,true);
    	this.endpoint = 0;
    }
    
    public LowGearGyroArc(int encSetpoint, double gyroSetpoint, double power) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.base);
    	this.power = power;
    	baseEncPID = new BaseEncPID(pEnc,iEnc,dEnc,encSetpoint,this.power,true);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,gyroSetpoint,0.5,true);
    	this.endpoint = 0;
    }
    
    public LowGearGyroArc(int encSetpoint, double gyroSetpoint, double power, int endpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.base);
    	this.power = power;
    	baseEncPID = new BaseEncPID(pEnc,iEnc,dEnc,encSetpoint,this.power,true);
    	baseGyroPID = new BaseGyroPID(pGyro,iGyro,dGyro,gyroSetpoint,0.5,true);
    	this.endpoint = endpoint;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.base.encPwr = 0;
    	Robot.base.gyroPwr = 0;
    	baseEncPID.enable();
    	baseGyroPID.enable();
    	Robot.base.frontLeft.configOpenloopRamp(0.2, 0);
		Robot.base.backLeft.configOpenloopRamp(0.2, 0);
		Robot.base.frontRight.configOpenloopRamp(0.2, 0);
		Robot.base.backRight.configOpenloopRamp(0.2, 0);
    	System.out.println("Running");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	System.out.println("Running");
    	
//    	if(SmartDashboard.getNumber("Base Enc P", Constants.baseEncP) != baseEncPID.getPIDController().getP()|| 
//    			SmartDashboard.getNumber("Base Enc I", Constants.baseEncI) != baseEncPID.getPIDController().getI() || 
//    			SmartDashboard.getNumber("Base Enc D", Constants.baseEncD) != baseEncPID.getPIDController().getD() ||   
//    			SmartDashboard.getNumber("Base Enc Setpoint", 0.0) != baseEncPID.getPIDController().getSetpoint() ){
//    		baseEncPID.disable();
//    		baseEncPID = new BaseEncPID(SmartDashboard.getNumber("Base Enc P", Constants.baseEncP), SmartDashboard.getNumber("Base Enc I", Constants.baseEncI), 
//        			SmartDashboard.getNumber("Base Enc D", Constants.baseEncD), SmartDashboard.getNumber("Base Enc Setpoint", 0.0),power);
//    		baseEncPID.enable();
//    	}
//    	
//    	if(SmartDashboard.getNumber("Base Gyro P", Constants.gyroP) != baseGyroPID.getPIDController().getP()|| 
//    			SmartDashboard.getNumber("Base Gyro I", Constants.gyroI) != baseGyroPID.getPIDController().getI() || 
//    			SmartDashboard.getNumber("Base Gyro D", Constants.gyroD) != baseGyroPID.getPIDController().getD() ||   
//    			SmartDashboard.getNumber("Base Gyro Setpoint", 0.0) != baseGyroPID.getPIDController().getSetpoint() ){
//    		baseGyroPID.disable();
//    		baseGyroPID = new BaseGyroPID(SmartDashboard.getNumber("Base Gyro P", Constants.gyroP), SmartDashboard.getNumber("Base Gyro I", Constants.gyroI), 
//        			SmartDashboard.getNumber("Base Gyro D", Constants.gyroD), SmartDashboard.getNumber("Base Gyro Setpoint", 0.0));
//    		baseGyroPID.enable();
//    	}
    	
    	Robot.base.drive(Robot.base.encPwr, Robot.base.gyroPwr);
    	System.out.println(baseEncPID.getSetpoint());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (endpoint > 0 && Math.abs(endpoint) <= Math.abs(Robot.base.getEnc())) return true;
        return baseGyroPID.onTarget() && baseEncPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.base.frontLeft.configOpenloopRamp(0, 0);
		Robot.base.backLeft.configOpenloopRamp(0, 0);
		Robot.base.frontRight.configOpenloopRamp(0, 0);
		Robot.base.backRight.configOpenloopRamp(0, 0);
    	Robot.base.drive(0, 0);
    	baseEncPID.disable();
    	baseGyroPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
