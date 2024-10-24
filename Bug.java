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
 * An instantiable class maintains data about a Bug in the Froggie Feeding Frenzie game. These bugs
 * do not move, can be drawn to the screen, and detect if it has been hit (eaten) by a Frog.
 */
public class Bug extends GameActor {
  //path to the image used for bugs, all bugs use the same image
  private static final String IMG_PATH = "images" + File.separator + "bug.png";
  //how many points this bug gives for being eaten
  private int points;

  public Bug(float x, float y, int points) {
    super(x, y, IMG_PATH);
    this.points = points;

  }

  /**
   * Gets how many points this Bug is worth
   *
   * @return amount of points
   */
  public int getPoints() {
    return this.points;
  }

  /**
   * Determines whether or not this bug has been eaten by the Frog.
   *
   * @param f is the frog that is being checked if its eating the bug
   * @return true if it has been eaten
   */
  public boolean isEatenBy(Frog f) {

    try {
      if (f.getTongueHitbox().doesCollide(this.getHitbox())) {
        return true;
      }
      return false;
    } catch (IllegalStateException e) {
      return false;
    }

  }

}
