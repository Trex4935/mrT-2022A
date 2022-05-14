// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.c_driveWithController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.commands.c_startLauncher;
import frc.robot.subsystems.Launcher;
import frc.robot.commands.c_reverseLauncher;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController controller;
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain;
  private final c_driveWithController driveWithController;
  private final c_startLauncher runLauncher;
  private final c_reverseLauncher reverseLauncher;
  private final Launcher launch;

  private JoystickButton button_a, button_b, button_x, button_y;

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    // controller
    controller = new XboxController(Constants.xboxID);
    // subsystems
    driveTrain = new DriveTrain();
    launch = new Launcher();
    // commands
    driveWithController = new c_driveWithController(driveTrain, controller);
    runLauncher = new c_startLauncher(launch);
    reverseLauncher = new c_reverseLauncher(launch);

    driveTrain.setDefaultCommand(driveWithController);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    button_a = new JoystickButton(controller, XboxController.Button.kA.value);
    button_a.toggleWhenPressed(runLauncher);

    button_b = new JoystickButton(controller, XboxController.Button.kB.value);
    button_b.toggleWhenPressed(reverseLauncher);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
