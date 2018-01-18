package com.rebedok.jazz.jazz;

import com.rebedok.jazz.jazz.dto.RobotDTO;
import com.rebedok.jazz.jazz.robot.Robot;
import com.rebedok.jazz.jazz.robotsInfo.RobotsMonitorImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

public class TestRobotMonitorImpl {
    private RobotsMonitorImp monitor = new RobotsMonitorImp();
    private ArrayList<Robot> robots = new ArrayList<>();
    @Before
    public void init() {
        for(int i = 0; i < 10; i++) {
            RobotDTO dto = new RobotDTO();
            if(i % 3 == 0) {
                dto.setFree(false);
            }
            monitor.createRobot(dto);
        }
    }

    @After
    public void tearDown() {
        robots = null;
    }

    @Test
    public void searchRobot() {
        assertNotNull(monitor.searchRobot(5));
        assertNotNull(monitor.searchRobot(9));
        assertNull(monitor.searchRobot(11));
        monitor.robotDestroy(5);
        assertNull(monitor.searchRobot(5));
    }

    @Test
    public void findRobot() {
        assertTrue(monitor.findAll().size() == 10);
        monitor.robotDestroy(2);
        assertFalse(monitor.findAll().size() == 10);

    }

    @Test
    public void searchFree() {
        assertNotNull(monitor.searchFreeRobot().getId());
        for (int i = 0; i < 10; i++) {
            if (i % 3 != 0) {
                monitor.robotDestroy(i + 1);
            }
        }
        assertNull(monitor.searchFreeRobot());
    }


}
