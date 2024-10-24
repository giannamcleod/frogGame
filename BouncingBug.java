//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Froggie game
// Course:   CS 300 Fall 2023
//
// Author:   Gianna McLeod & Jason Antonellis
// Email:    gmmcleod@wisc.edu & jantonellis@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Jason Antonellis
// Partner Email:   jantonellis@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         Went to office hours for help on the circling bug logic
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;

/**
 * A subclass of Bug that is Movable. These bugs bounce around the screen like a DVD player logo.
 *
 * @author Gianna & Jason
 */
public class BouncingBug extends Bug implements Moveable {

  // Keeps track if bug is moving up or down
  private boolean goDown;

  // Keeps track if bug is moving left or right
  private boolean goLeft;

  // A constant, number of points ALL BouncingBugs are worth, 100
  private static final int POINTS = 100;

  // A random generator to determine the initial directions
  private Random randGen;

  // The number of pixels to move horizontally and vertically, formatted [dx,dy]
  private int[] speedNums;

  /**
   * Constructs a new BouncingBug object with the specified initial position and movement speed.
   *
   * @param x  The initial x coordinate of the bug.
   * @param y  The initial y coordinate of the bug.
   * @param dx The horizontal movement speed (pixels per frame).
   * @param dy The vertical movement speed (pixels per frame).
   */
  public BouncingBug(float x, float y, int dx, int dy) {
    super(x, y, POINTS);
    randGen = new Random();
    this.speedNums = new int[] {dx, dy};
    this.goDown = randGen.nextBoolean();
    this.goLeft = randGen.nextBoolean();
  }

  /**
   * Moves the BouncingBug based on its current position and direction.
   */
  @Override
  public void move() {
    if (shouldMove()) {
      if (this.goDown) {
        this.setY(this.getY() + speedNums[1]);
        this.moveHitbox(this.getX(), this.getY());
      } else {
        this.setY(this.getY() - speedNums[1]);
        this.moveHitbox(this.getX(), this.getY());
      }
      if (this.goLeft) {
        this.setX(this.getX() - speedNums[0]);
        this.moveHitbox(this.getX(), this.getY());
      } else {
        this.setX(this.getX() + speedNums[0]);
        this.moveHitbox(this.getX(), this.getY());
      }
      if (this.getX() >= 800) {
        goLeft = true;
      } else if (this.getX() <= 0) {
        goLeft = false;
      }
      if (this.getY() >= 600) {
        goDown = false;
      } else if (this.getY() <= 0) {
        goDown = true;
      }
    }
  }

  /**
   * Determines if the BouncingBug should move.
   *
   * @return true if the BouncingBug should move; otherwise, false.
   */
  @Override
  public boolean shouldMove() {
    return true;
  }
}
