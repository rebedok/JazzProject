package com.rebedok.jazz.jazz.robotsInfo;


import com.rebedok.jazz.jazz.converter.RobotConverter;
import com.rebedok.jazz.jazz.dto.RobotDTO;
import com.rebedok.jazz.jazz.robot.Robot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * class implements interface RobotsMonitor
 */
@Service
public class RobotsMonitorImp implements RobotsMonitor {
    private int maxId = 0;
    private ArrayList<Robot> robots = new ArrayList<>();
    private RobotConverter converter = new RobotConverter();
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotsMonitorImp.class);

    /**
     * search the robot by id
     *
     * @param id
     * @return robot or null if id not found
     */
    @Override
    public Robot searchRobot(long id) {
        for (Robot r : robots) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    /**
     * create new robot
     *
     * @param dto
     * @return Data Transfer Object
     */
    @Override
    public RobotDTO createRobot(RobotDTO dto) {
        maxId += 1;
        dto.setId(maxId);
        Robot robot = converter.convert(dto);
        robots.add(robot);
        LOGGER.info("Create new robot, Id: " + maxId);
        return dto;
    }

    /**
     * return all robots
     *
     * @return List RobotDTO
     */
    @Override
    public ArrayList<RobotDTO> findAll() {
        ArrayList<RobotDTO> dtoList = new ArrayList<>();
        converter = new RobotConverter();
        for (Robot r : robots) {
            dtoList.add(converter.convertToDTO(r));
        }
        return dtoList;
    }

    /**
     * add new task by robot id
     *
     * @param dto
     * @return RobotDTO
     */
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

    /**
     * search free robot
     *
     * @return free robot or null if there aren't free robots
     */
    public Robot searchFreeRobot() {
        for (Robot r : robots) {
            if (r.isFree()) {
                return r;
            }
        }
        return null;
    }

    /**
     * add new task for a first free robot
     * @param dto
     * @return RobotDTO
     */
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

    /**
     * Destroy the robot by id
     * @param id
     */
    @Override
    public void robotDestroy(long id) {
        LOGGER.info("Robot " + maxId + " is destroyed");
        Robot robot = searchRobot(id);
        if(robot !=null) {
            robots.remove(searchRobot(id));
        }
    }

}
