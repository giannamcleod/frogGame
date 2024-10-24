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


/**
 * A subclass of Bug that is Movable and moves in a circle. When drawn to the screen they also have
 * a tint applied to the image
 *
 * @authors Gianna & Jason
 */
public class CirclingBug extends Bug implements Moveable {

  //the x,y-coordinates for the center of the circle path
  private float[] circleCenter;

  //constant, number of points ALL CirclingBugs are worth 200
  private static final int POINTS = 200;

  //the radius of the circle path this bug moves on
  private double radius;

  //keeps track of how long the bug has been moving
  private double ticks;

  //color used to tint the image when being drawn [Red,Green,Blue]
  private int[] tintColor;

  /**
   * Constructor for this circling bug. Sets the x, y, radius of the circle, and color.
   *
   * @param circleX   the x coordinate of the center of the circle
   * @param circleY   the y coordinate of the center of the circle
   * @param radius    the radius that this bug will circle around
   * @param tintColor the color that this bug will be
   */
  public CirclingBug(float circleX, float circleY, double radius, int[] tintColor) {
    super(circleX, circleY, POINTS);
    this.circleCenter = new float[] {circleX, circleY};
    this.radius = radius;
    this.tintColor = tintColor;
    this.ticks = 0;
  }

  /**
   * This method draws the bug to the screen and tints the bug.
   *
   * @return void
   */
  public void draw() {

    this.processing.tint(tintColor[0], tintColor[1], tintColor[2]);
    processing.image(image, this.getX(), this.getY());
    this.processing.tint(255, 255, 255);

  }

  /**
   * The method that makes this bug move in a circle.
   *
   * @return void
   */
  @Override
  public void move() {
    if (shouldMove()) {
      ticks = ticks + 0.05;
      this.setX((float) (radius * Math.cos(ticks) + circleCenter[0]));
      this.setY((float) (radius * Math.sin(ticks) + circleCenter[1]));
      this.moveHitbox(this.getX(), this.getY());

    }
  }

  /**
   * this method tells the program if the bug should move or not, it is always true.
   *
   * @return true because the bug is always moving.
   */

  @Override
  public boolean shouldMove() {
    return true;
  }
}
