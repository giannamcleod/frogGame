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
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.Random;

//  This is the class that runs the program. It sets up
// and draw all of the background and basic implementations.
public class FrogGame extends PApplet {
  private ArrayList<GameActor> gameActors; // ArrayList to store game actors
  private int score; // Player's current score
  private PImage backgroundImg; // Background image
  private boolean isGameOver; // Indicates whether the game is over
  private Random randGen; // Random number generator
  private static final int BUG_COUNT = 5; // Number of bugs on the screen

  // Main method to start the application
  public static void main(String[] args) {
    PApplet.main("FrogGame");
  }

  // Set the display size for the game window
  @Override
  public void settings() {
    size(800, 600);
  }

  // Setup the initial state of the game
  @Override
  public void setup() {
    // Set the window title
    this.getSurface().setTitle("Froggie Feeding Frenzie");

    // Set drawing and alignment modes
    this.imageMode(PApplet.CENTER);
    this.rectMode(PApplet.CENTER);
    this.focused = true;
    this.textAlign(PApplet.CENTER);
    this.textSize(30);

    // Initialize a random number generator
    randGen = new Random();

    // Loads the background image
    backgroundImg = loadImage("images" + File.separator + "background.jpg");

    // Initializes the ArrayList to store game actors
    gameActors = new ArrayList<GameActor>();

    // Initialize the game components (Hitbox, GameActor, Tongue)
    Hitbox.setProcessing(this);
    GameActor.setProcessing(this);
    Tongue.setProcessing(this);

    // Initializes the game
    initGame();
  }

  // Main game loop, responsible for rendering and updating game state
  @Override
  public void draw() {
    // If the game is over, display "GAME OVER" in the center of the screen
    if (isGameOver) {
      this.text("GAME OVER", width / 2, height / 2);
    } else {
      // Draw the background image
      image(backgroundImg, width - backgroundImg.width / 2, height - backgroundImg.height / 2);

      // Draw all game actors
      for (int i = 0; i < gameActors.size(); i++) {
        gameActors.get(i).draw();
      }

      // Move moveable game actors
      for (int i = 0; i < gameActors.size(); i++) {
        if (gameActors.get(i) instanceof Moveable) {
          ((Moveable) gameActors.get(i)).move();
        }
      }

      // Run game logic checks
      runGameLogicChecks();

      // Display health and score
      this.text("Health" + getFrog().getHealth(), 80, 40);
      this.text("Score" + this.score, 240, 40);

    }
  }

  // Create a new bug and add it to the game
  private void addNewBug() {
    // Generate a random number to determine the type of bug to create
    int num = randGen.nextInt(4);
    // Generate random x and y coordinates for the bug
    float x = randGen.nextFloat(800);
    float y = randGen.nextFloat(600);

    // Create and add a bug of the determined type
    if (num == 0) {
      // Create a regular Bug with 25 points
      gameActors.add(new Bug(x, y, 25));
    } else if (num == 1) {
      // Create a BouncingBug with specified dx and dy
      gameActors.add(new BouncingBug(x, y, 2, 5));
    } else if (num == 2) {
      // Create a CirclingBug with specified radius and random RGB color
      int[] randColor =
          new int[] {randGen.nextInt(256), randGen.nextInt(256), randGen.nextInt(256)};
      gameActors.add(new CirclingBug(x, y, 25, randColor));
    } else if (num == 3) {
      // Create a StrongBug with an initial health of 3
      gameActors.add(new StrongBug(x, y, 3));
    }
  }

  // Retrieve the Frog game actor from the list
  private Frog getFrog() {
    Frog frog = null;
    for (int i = 0; i < gameActors.size(); i++) {
      if (gameActors.get(i) instanceof Frog) {
        frog = (Frog) gameActors.get(i);
        return frog;
      }
    }
    return frog;
  }

  // Handle mouse click events
  @Override
  public void mousePressed() {
    Frog frog = getFrog();
    if (frog != null && frog.isMouseOver()) {
      frog.mousePressed();
    }
  }

  // Handle mouse release events
  @Override
  public void mouseReleased() {
    Frog frog = getFrog();
    if (frog != null) {
      frog.mouseReleased();
    }
  }

  // Handle key press events
  @Override
  public void keyPressed() {
    Frog frog = getFrog();
    if (frog != null && key == ' ') {
      frog.startAttack();
    }

    if (frog != null && key == 'r') {
      // Restart the game
      initGame();
    }
  }

  // Initialize the game state
  public void initGame() {
    // Set the score to 0
    this.score = 0;
    // Set the game as not over
    this.isGameOver = false;
    // Clear the list of game actors
    gameActors.clear();
    // Create and add a Frog with 100 health
    Frog frog = new Frog(width / 2, 500, 100);
    this.gameActors.add(frog);
    // Add new bugs (of random varieties) up to the BUG_COUNT
    for (int i = 0; i < BUG_COUNT; i++) {
      addNewBug();
    }
  }

  //private helper method for checking if a bug was hit
  private void frogHitBug(int i, Frog frog) {
    // If a non-StrongBug is hit, do the following
    if (((Bug) gameActors.get(i)).isEatenBy(frog)) {
      // Stop the frog's attack
      frog.stopAttack();
      // Update the score
      score = ((Bug) gameActors.get(i)).getPoints() + score;
      // Remove the bug from the game
      gameActors.remove(i);
      // Add a new bug (of a random variety) to the game
      addNewBug();
    }
  }

  //private helper method for checking if a strong bug was hit
  private void hitStrongBug(int j, Frog frog) {
    if (((StrongBug) gameActors.get(j)).isEatenBy(frog)) {
      // Stop the frog's attack
      frog.stopAttack();
      // The StrongBug takes damage and loses health
      ((StrongBug) gameActors.get(j)).loseHealth();
      // If the StrongBug is dead, do steps for non-StrongBug
      if (((StrongBug) gameActors.get(j)).isDead()) {
        // Stop the frog's attack
        frog.stopAttack();
        // Update the score
        score = ((Bug) gameActors.get(j)).getPoints() + score;
        // Remove the bug from the game
        gameActors.remove(j);
        // Add a new bug (of a random variety) to the game
        addNewBug();
      }
    }
  }

  //This method implments all the logisitics of the game
  private void runGameLogicChecks() {
    // If the Frog's tongue hits the edge of the screen, then it stops attacking
    Frog frog = getFrog();
    if (frog.tongueHitBoundary()) {
      frog.stopAttack();
    }

    // Check every bug to see if it has been hit by the Frog.
    for (int i = 0; i < gameActors.size(); i++) {
      if (gameActors.get(i) instanceof StrongBug) {
        // If a StrongBug is hit, do the following
        hitStrongBug(i, frog);
      } else {
        if (gameActors.get(i) instanceof Bug) {
          // Check if the frog hits any of the bugs
          frogHitBug(i, frog);
        }
      }
    }

    for (int i = 0; i < gameActors.size(); i++) {
      if (gameActors.get(i) instanceof Bug) {
        // Check if the frog is hit by any of the bugs
        if (frog.isHitBy((Bug) gameActors.get(i))) {
          // If it is hit by any of the bugs, it takes damage and loses health
          frog.loseHealth();
          // If the frog is dead, update the game so it is over
          if (frog.isDead()) {
            isGameOver = true;
          }
        }
      }
    }
  }
}

