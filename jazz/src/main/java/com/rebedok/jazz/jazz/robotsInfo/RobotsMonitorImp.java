package com.rebedok.jazz.jazz.robotsInfo;


import com.rebedok.jazz.jazz.converter.RobotConverter;
import com.rebedok.jazz.jazz.dto.RobotDTO;
import com.rebedok.jazz.jazz.robot.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RobotsMonitorImp implements RobotsMonitor {
    private int maxId = 0;
    private ArrayList<Robot> robots = new ArrayList<>();
    private RobotConverter converter = new RobotConverter();
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotsMonitorImp.class);

    @Override
    public Robot searchRobot(long id) {
        for (Robot r : robots) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    @Override
    public RobotDTO createRobot(RobotDTO dto) {
        maxId += 1;
        dto.setId(maxId);
        Robot robot = converter.convert(dto);
        robots.add(robot);
        LOGGER.info("Create new robot, Id: " + maxId);
        return dto;
    }

    @Override
    public ArrayList<RobotDTO> findAll() {
        ArrayList<RobotDTO> dtoList = new ArrayList<>();
        converter = new RobotConverter();
        for (Robot r : robots) {
            dtoList.add(converter.convertToDTO(r));
        }
        return dtoList;
    }

    @Override
    public RobotDTO taskForRobot(RobotDTO dto) {
        Robot robot = searchRobot(dto.getId());
        if(robot == null) {
            return null;
        }
        if(robot.isFree()) {
            robot.setFree(false);
            robot.startJob();
        }
        dto = converter.convertToDTO(robot);
        return dto;
    }

    public Robot searchFreeRobot() {
        for (Robot r : robots) {
            if (r.isFree()) {
                return r;
            }
        }
        return null;
    }

    public RobotDTO newTask(RobotDTO dto) {
        Robot robot = searchFreeRobot();
        if(robot == null) {
            maxId += 1;
            dto.setId(maxId);
            robot = converter.convert(dto);
            robots.add(robot);
        }
        robot.setFree(false);
        robot.startJob();
        dto = converter.convertToDTO(robot);
        return dto;
    }

    @Override
    public void robotDestroy(long id) {
        LOGGER.info("Robot " + maxId + " is destroyed");
        Robot robot = searchRobot(id);
        if(robot !=null) {
            robots.remove(searchRobot(id));
        }
    }

}
