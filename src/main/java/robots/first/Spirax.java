/**
 * MAVEN-ROBOCODE note: 
 *   Remember that all your robots should be developped under the 'robots'
 *   package! Subpackages are allowed, like this one.
 */
package robots.first;

import robocode.*;

/**
 * Spirax
 */
public class Spirax extends Robot
{
	/**
	 * run: Spirax default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		while(true) {
			ahead(100); 
			// Go ahead 100 pixels
			turnGunRight(360); 
			// Scan
			back(75); 
			// Go back 75 pixels
			turnGunRight(360); 
			// scan
			// For each second the robot go ahead 25 pixels.
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		// Assuming radar and gun are aligned...
		if (e.getDistance() < 100) {
			fire(1);
		} else {
			fire(3);
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		// Assuming radar and gun are aligned
		double bearing = e.getBearing(); 
		// Get the direction which is arrived the bullet.
		if(energy < 100) { 
			// If the energy is low, the robot go away from the enemy
			turnRight(-bearing); 
			// This isn't accurate but release your robot.
			ahead(100); 
			// The robot goes away from the enemy.
		} else {
			turnRight(360); 
			// Scan
		}
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		double bearing = e.getBearing(); 
		// get the bearing of the wall
		turnRight(-bearing); 
		// This isn't accurate but release your robot.
		ahead(100); 
		// The robot goes away from the wall.
	}	
}
