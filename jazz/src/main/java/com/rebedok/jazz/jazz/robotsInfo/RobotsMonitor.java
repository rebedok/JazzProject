package com.rebedok.jazz.jazz.robotsInfo;


import com.rebedok.jazz.jazz.dto.RobotDTO;
import com.rebedok.jazz.jazz.robot.Robot;

import java.util.ArrayList;

public interface RobotsMonitor {
    Robot searchRobot(long id);
    RobotDTO createRobot(RobotDTO dto);
    ArrayList<RobotDTO> findAll();
    RobotDTO taskForRobot(RobotDTO dto);
    RobotDTO newTask(RobotDTO dto);
    void robotDestroy(long id);
}
