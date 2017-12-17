package com.rebedok.jazz.jazz.controller;


import com.rebedok.jazz.jazz.converter.RobotConverter;
import com.rebedok.jazz.jazz.dto.RobotDTO;
import com.rebedok.jazz.jazz.robot.Robot;
import com.rebedok.jazz.jazz.robotsInfo.RobotsMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api/robot")
public class RobotController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotController.class);

    @Autowired
    RobotsMonitor robotsMonitor;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RobotDTO> getById(@PathVariable Long id) {
        LOGGER.info("REST request. Path:/api/robot/{id}  method: GET");
        RobotConverter converter = new RobotConverter();
        Robot robot =  robotsMonitor.searchRobot(id);
        if(robot == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RobotDTO robotDTO = converter.convertToDTO(robot);
        return new ResponseEntity<>(robotDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ArrayList<RobotDTO>> getAllRobots() {
        LOGGER.info("REST request. Path:/api/robot  method: GET");
        return new ResponseEntity<>(robotsMonitor.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RobotDTO> createRobot(@RequestBody RobotDTO dto) {
        LOGGER.info("REST request. Path:/api/robot/create  method: POST");
        return new ResponseEntity<>(robotsMonitor.createRobot(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RobotDTO> addTaskForRobot(@PathVariable Long id ,@RequestBody RobotDTO dto) {
        LOGGER.info("REST request. Path:/api/robot/{id}  method: POST");
        dto.setId(id);
        RobotDTO robotDTO = robotsMonitor.taskForRobot(dto);
        if(robotDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(robotDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RobotDTO> addTask(@RequestBody RobotDTO dto) {
        LOGGER.info("REST request. Path:/api/robot  method: POST");
        return new ResponseEntity<>(robotsMonitor.newTask(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void destroyRobot(@PathVariable Long id) {
        LOGGER.info("REST request. Path:/api/robot  method: DELETE");
        robotsMonitor.robotDestroy(id);
    }
}
