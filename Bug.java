
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
