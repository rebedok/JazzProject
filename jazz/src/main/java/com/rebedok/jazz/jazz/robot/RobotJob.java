package com.rebedok.jazz.jazz.robot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RobotJob extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotJob.class);
    Robot robot;
    RobotJob(Robot robot) {
        this.robot = robot;
        start();
    }

    @Override
    public void run() {
        LOGGER.info("Robot " + robot.getId() + " got a job");
        try {
            sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.setFree(true);
        LOGGER.info("Robot " + robot.getId() + " has finished work");
    }
}
