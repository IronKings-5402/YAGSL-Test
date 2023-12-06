package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SwerveSubsystem;
import swervelib.SwerveController;

public class TeleDrive extends CommandBase{
    SwerveSubsystem swerve;
    double xVelocity;
    double yVelocity;
    double angVelocity;
    SwerveController controller;
    public TeleDrive(SwerveSubsystem swerve, DoubleSupplier xVelocity, DoubleSupplier yVelocity, DoubleSupplier angVelocity){
        this.swerve = swerve;
        this.xVelocity = xVelocity.getAsDouble();
        this.yVelocity = yVelocity.getAsDouble();
        this.angVelocity = angVelocity.getAsDouble();
        this.controller = swerve.getSwerveController();
        addRequirements(this.swerve);
    }

    @Override
    public void execute() {
        ChassisSpeeds desiredSpeeds = swerve.getTargetSpeeds(xVelocity, yVelocity,
                                                         new Rotation2d(angVelocity * Math.PI));
        Translation2d translation = SwerveController.getTranslation2d(desiredSpeeds);
        swerve.drive(translation,
        desiredSpeeds.omegaRadiansPerSecond, true, false);
    }

    
}
