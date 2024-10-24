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
import processing.core.PImage;

import java.io.File;

/**
 * An instantiable class for all game actors in the Froggie Feeding Frenzie game. Game actors are
 * images that are drawn the screen that also have hitboxes.
 *
 * @authors Gianna & Jason
 */
public class GameActor {

  //the x,y-coordinates of the center stored as [x,y]
  private float[] coordinates;

  //the hitbox associated with this GameActor
  private Hitbox hitbox;

  //the image associated with this GameActor
  protected processing.core.PImage image;

  //PApplet to use to draw GameActors to the screen
  protected static processing.core.PApplet processing;


  /**
   * Constructor for a new GameActor object by setting the coordinates, loading the image, and
   * creating the hitbox.
   *
   * @param x       is the x value that the GameActor will be spawned at
   * @param y       is the y value that the GameActor will be spawned at
   * @param imgPath the image path that will be used to load in the image
   * @throws IllegalStateException with the message "Processing is null. setProcessing() must be
   *                               called before creating any GameActor objects."
   */
  public GameActor(float x, float y, String imgPath) {

    if (GameActor.processing == null) {
      throw new IllegalStateException(
          "Processing is null. setProcessing() must be called before " + "creating any GameActor objects.");
    }
    this.coordinates = new float[] {x, y};
    this.image = this.processing.loadImage(imgPath);
    this.hitbox = new Hitbox(x, y, image.width, image.height);
  }

  /**
   * Sets up the processing for this class.
   *
   * @param processing the processing to set up this GameActor
   * @return void
   */
  public static void setProcessing(processing.core.PApplet processing) {
    GameActor.processing = processing;
  }

  /**
   * Draws the game actor to the screen.
   *
   * @return void
   */
  public void draw() {
    processing.image(image, coordinates[0], coordinates[1]);
  }

  /**
   * Gets the hitbox for this GameActor
   *
   * @return Hitbox for this GameActor
   */
  public Hitbox getHitbox() {
    return this.hitbox;
  }

  /**
   * Gets the x coordinate of this GameActor
   *
   * @return x coordinate
   */
  public float getX() {
    return coordinates[0];
  }

  /**
   * Gets the y coordinate of this GameActor
   *
   * @return y coordinate
   */
  public float getY() {
    return coordinates[1];
  }

  /**
   * Moves the Hitbox for this GameActor
   *
   * @param x the x position where the Hitbox will be moved to
   * @param y the y position where the Hitbox will be moved to
   * @return void
   */
  public void moveHitbox(float x, float y) {
    this.hitbox.setPosition(x, y);
  }

  /**
   * Sets the x of this GameActor to the new x value
   *
   * @param newX the value that will be the new x coordinate
   * @return void
   */
  public void setX(float newX) {
    coordinates[0] = newX;
  }

  /**
   * Sets the y of this GameActor to the new y value
   *
   * @param newY the value that will be the new y coordinate
   * @return void
   */
  public void setY(float newY) {
    coordinates[1] = newY;
  }
}
