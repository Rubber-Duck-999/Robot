/**
 * MAVEN-ROBOCODE note: 
 *   Remember that all your robots should be developped under the 'robots'
 *   package! Subpackages are allowed, like this one.
 */
package robots;

import robocode.*;

/**
 * Spirax
 */
public class Spirax extends AdvancedRobot
{
    int moveDirection = 1;
	// Which way to move
    /**
     * run:  Tracker's main run function
     */
    public void run() 
	{
        setAdjustRadarForRobotTurn(true);
		// keep the radar still while we turn
        setAdjustGunForRobotTurn(true); 
		// Keep the gun still when we turn
        turnRadarRightRadians(Double.POSITIVE_INFINITY);
		// Keep turning radar right
    }

    /**
     * onScannedRobot:  Here's the good stuff
     */
    public void onScannedRobot(ScannedRobotEvent e) 
	{
        double absBearing = e.getBearingRadians() + getHeadingRadians();
		// Enemies absolute bearing
        double latVel = e.getVelocity() * Math.sin(e.getHeadingRadians() -absBearing);
		// Enemies later velocity
        double gunTurnAmt;
		// Amount to turn our gun
        setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
		// Lock on the radar
        if(Math.random()>.9)
		{
            setMaxVelocity((12 * Math.random()) + 12);
			// randomly change speed
        }
        if (e.getDistance() > 150) 
		{
			// if distance is greater than 150
            gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel/22);
			// Amount to turn our gun, lead just a little bit
            setTurnGunRightRadians(gunTurnAmt);
			// Turn our gun
            setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(absBearing - getHeadingRadians() + latVel/getVelocity()));
			// Drive towards the enemies predicted future location
        }
        else
		{
			// If we are close enough...
            gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel/15);
			// Amount to turn our gun, lead just a little bit
            setTurnGunRightRadians(gunTurnAmt);
			// Turn our gun
            setTurnLeft(-90 - e.getBearing());
			// Turn perpendicular to the enemy
        }
		setAhead((e.getDistance() - 140) * moveDirection);
		// Move forward
		setFire(2);
		// Fire
    }

    public void onHitWall(HitWallEvent e)
	{
        moveDirection =- moveDirection;
		// reverse direction upon hitting a wall
    }

    /**
     * onWin:  Do a victory dance
     */
    public void onWin(WinEvent e) 
	{
        for (int i = 0; i < 50; i++) 
		{
            turnRight(30);
            turnLeft(30);
        }
    }
}
