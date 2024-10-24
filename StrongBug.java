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
 * A subclass of Bug that is Movable. These bugs only move when they are not at max health. When hit
 * they become smaller and start moving horizontally across the screen.
 *
 * @authors Gianna & Jason
 */
public class StrongBug extends Bug implements Moveable {

  //the current health of this StringBug, updates when Bug takes damage
  private int currentHealth;

  //the max health of this StrongBug
  private final int MAX_HEALTH;

  //the number of points ALL StrongBugs are worth, 500
  private static final int POINTS = 500;

  /**
   * Constructor for the StrongBug class. Creates a bug that takes multiple hits to die. It shrinks
   * after the first hit.
   *
   * @param x      is the x value this bug is spawned in at
   * @param y      is the y value that this bug is spanwed in at
   * @param health is the max health that this bug has
   */
  public StrongBug(float x, float y, int health) {
    super(x, y, POINTS);
    if (health < 1) {
      throw new IllegalArgumentException("Health cannot be below 1!");
    } else {
      this.MAX_HEALTH = health;
      currentHealth = MAX_HEALTH;
    }
  }

  /**
   * Checks to see if this bug is dead.
   *
   * @return true if this bugs health is 0 or below
   */
  public boolean isDead() {
    if (currentHealth <= 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks to see if this bug has been eaten by the frog
   *
   * @return true if this bug has been hit by the frog tongue
   */
  public boolean isEatenBy(Frog f) {
    try {
      if (f.getTongueHitbox().doesCollide(this.getHitbox())) {
        image.resize((int) (image.width * (0.75)), (int) (image.height * (0.75)));
        this.getHitbox()
            .changeDimensions((int) (image.width * (0.75)), (int) (image.height * (0.75)));
        return true;

      }

      return false;
    } catch (IllegalStateException e) {
      return false;
    }
  }

  /**
   * Decreases the health of this bug by 1.
   *
   * @return void
   */
  public void loseHealth() {
    currentHealth = currentHealth - 1;
  }

  /**
   * Moves the bug if it has been hit by the tongue.
   *
   * @return void
   */
  @Override
  public void move() {
    //TODO
    if (shouldMove()) {
      if (this.getX() >= 800) {
        this.setX(0);
      }
      this.setX(this.getX() + 3);
      this.moveHitbox(this.getX(), this.getY());
    }
  }

  /**
   * Checks to see if this bug should move (or if the health is not full)
   *
   * @return true if the bug should move
   */
  @Override
  public boolean shouldMove() {
    if (currentHealth == MAX_HEALTH) {
      return false;
    } else {
      return true;
    }
  }
}
