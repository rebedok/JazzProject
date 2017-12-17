package com.rebedok.jazz.jazz.converter;

import com.rebedok.jazz.jazz.dto.RobotDTO;
import com.rebedok.jazz.jazz.robot.Robot;

public class RobotConverter {
    public RobotDTO convertToDTO(Robot robot) {
        RobotDTO dto = new RobotDTO();
        dto.setFree(robot.isFree());
        dto.setId(robot.getId());
        return dto;
    }
    public Robot convert(RobotDTO dto) {
        Robot robot = new Robot();
        robot.setId(dto.getId());
        robot.setFree(dto.isFree());
        return robot;
    }
}
