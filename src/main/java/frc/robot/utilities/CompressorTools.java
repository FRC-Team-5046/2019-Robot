/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utilities;

import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class CompressorTools {

    public void StopCompressor()
    {
      Robot.c.stop();
    }

    public void StartCompressor()
    {
        Robot.c.start();
    }

}
