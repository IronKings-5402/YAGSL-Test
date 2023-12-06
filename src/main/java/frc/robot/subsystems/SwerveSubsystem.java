// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveController;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;

public class SwerveSubsystem extends SubsystemBase {
  // Creates a new ExampleSubsystem.
  SwerveDrive swerveDrive;
  public double maximumSpeed;
  public SwerveSubsystem(){
    this.maximumSpeed = Units.feetToMeters(4.5);
    File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
    try {
      this.swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(this.maximumSpeed);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  public Rotation2d getHeading()
  {
    return swerveDrive.getYaw();
  }

  public void drive(Translation2d translation, double rotation, boolean field, boolean openLoop){
    swerveDrive.drive(translation, rotation, field, openLoop);
  }
  public SwerveController getSwerveController()
  {
    return swerveDrive.swerveController;
  }

  public ChassisSpeeds getTargetSpeeds(double xInput, double yInput, Rotation2d angle)
  {
    xInput = Math.pow(xInput, 3);
    yInput = Math.pow(yInput, 3);
    return swerveDrive.swerveController.getTargetSpeeds(xInput,
                                                        yInput,
                                                        angle.getRadians(),
                                                        getHeading().getRadians(),
                                                        maximumSpeed);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}
