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
public class Spirax extends Robot
{
	private double _heading;
	private double _gunHeading;
	private double _energy;
	private Target[] _targets;
	private int _currentTarget;
	private boolean _validTarget;
	private final int MAX_TARGET_ID = 100;
	
	private void checkTargets(String name) {
		// Sets the current target
		int nextTarget = this.MAX_TARGET_ID;
		for(int i = 0; i < this._targets.length; i++) {
			if(this._targets[i].getName().equals(name)) {
				nextTarget = i;
			}
		}
		if(nextTarget != this.MAX_TARGET_ID) {
			this._currentTarget = nextTarget;
			this._validTarget = true;
		} else {
			this._currentTarget = this.MAX_TARGET_ID;
			this._validTarget = false;
		}
	}

	/**
	 * run: Spirax default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		this._gunHeading = getGunHeading();
		this._heading = getGunHeading();
		this._energy = getEnergy();
		while(true) {
			turnGunRight(1);
			this._gunHeading = getGunHeading();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		// Assuming radar and gun are aligned...
		
		double enemy = e.getHeading();
		if(enemy > this._gunHeading) {
			double turn = enemy - this._gunHeading;
			turnGunRight(turn);
		} else if (enemy < this._gunHeading) {
			double turn = this._gunHeading - enemy;
			turnGunLeft(turn);
		}
		this._gunHeading = getGunHeading();
		fire(3);
	}

		/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onBulletHit(BulletHitEvent e) {
		// Replace the next line with any behavior you would like
		// Assuming radar and gun are aligned...
		this._gunHeading = getGunHeading();
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {

	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {

	}	
}
