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
import java.io.File;

/**
 * An instantiable class maintains data about a Frog in the Froggie Feeding Frenzie game. They can
 * be drawn to the screen, dragged around by the mouse, and attack Bugs with their Tongue.
 */
public class Frog extends GameActor implements Moveable {
  private int health; // How much health the frog has
  private static final String IMG_PATH = "images" + File.separator + "frog.png";
  // Image path for the frog
  private boolean isDragging; // Keeps track if the frog is being dragged
  private float oldMouseX; // Previous x coordinate of the mouse
  private float oldMouseY; // Previous y coordinate of the mouse
  private Tongue tongue; // The tongue the frog attacks with

  /**
   * Constructor for a new Frog object using the provided parameters. The Frog is NOT dragging by
   * default.
   *
   * @param x      the x coordinate of this frog to start
   * @param y      the y coordinate of this frog to start
   * @param health an int value that represents the frog's starting health
   * @throws IllegalArgumentException if the health is below 1
   */
  public Frog(float x, float y, int health) {
    super(x, y, IMG_PATH);
    if (health < 1) {
      throw new IllegalArgumentException("Health cannot be less than 1");
    } else {
      isDragging = false;
      this.tongue = new Tongue(x, y);
      this.health = health;
    }
  }

  /**
   * Draws the image of the Frog to the screen.
   */
  public void draw() {
    if (tongue.isActive()) {
      tongue.draw();
      tongue.extend(this.getX(), -2);
    }
    super.draw();
  }

  /**
   * Gets the health of this frog.
   *
   * @return the health value.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Gets the hitbox of the tongue of this frog if the tongue is active.
   *
   * @return the hitbox of this tongue.
   * @throws IllegalStateException if the tongue is not active
   */
  public Hitbox getTongueHitbox() {
    if (tongue.isActive()) {
      return tongue.getHitbox();
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * Checks to see if the health of this frog is dead.
   *
   * @return true if health is less than or equal to 0.
   */
  public boolean isDead() {
    return health <= 0;
  }

  /**
   * Checks to see if this frog hits any bugs.
   *
   * @param b a Bug to check for collision
   * @return true if the bug's hitbox collides with the frog's hitbox
   */
  public boolean isHitBy(Bug b) {
    return this.getHitbox().doesCollide(b.getHitbox());
  }

  /**
   * Checks to see if the mouse is over this frog.
   *
   * @return true if the mouse is over the frog.
   */
  public boolean isMouseOver() {
    if ((processing.mouseX >= this.getX() - image.width / 2 && processing.mouseX <= this.getX() + image.width / 2) && (processing.mouseY >= this.getY() - image.height / 2 && processing.mouseY <= this.getY() + image.height / 2)) {
      return true;
    }
    return false;
  }

  /**
   * Decreases the health of this frog by 1.
   */
  public void loseHealth() {
    health--;
  }

  /**
   * Changes isDragging to true.
   */
  public void mousePressed() {
    isDragging = true;
  }

  /**
   * Changes isDragging to false.
   */
  public void mouseReleased() {
    isDragging = false;
  }

  /**
   * Activates the tongue.
   */
  public void startAttack() {
    tongue.reset();
    tongue.activate();
  }

  /**
   * Deactivates the tongue.
   */
  public void stopAttack() {
    tongue.deactivate();
  }

  /**
   * Checks to see if the tongue has hit the boundary of the screen.
   *
   * @return true if the tongue has hit the boundary
   */
  public boolean tongueHitBoundary() {
    return tongue.hitScreenBoundary();
  }

  /**
   * Moves the frog with the mouse if it should move.
   */
  @Override
  public void move() {
    if (shouldMove()) {
      float x = processing.mouseX;
      float y = processing.mouseY;

      float newX = this.getX() - (this.oldMouseX - x);
      float newY = this.getY() - (this.oldMouseY - y);

      this.setX(newX);
      this.setY(newY);
      this.getHitbox().setPosition(newX, newY);


      tongue.updateStartPoint(newX, newY);
      if (!tongue.isActive()) {
        tongue.updateEndPoint(newX, newY);
      }
    }

    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }

  /**
   * Checks if the frog should move based on if isDragging is true.
   *
   * @return true if the frog should move
   */
  @Override
  public boolean shouldMove() {
    return isDragging;
  }
}
