package com.rebedok.jazz.jazz.robot;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Robot {
    private long id;
    private boolean isFree = true;

    /**
     * metod start new job
     *
     *@see RobotJob#RobotJob(Robot) ()
     */
    public void startJob(){
        new RobotJob(this);
    }

}
