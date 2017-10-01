// Tariro Kandemiri
//
// This program creates a matching game using
// ColorfulSquares class and prompts the user
// to find the match of each square
// with as few clicks as possible

import objectdraw.*;
import java.awt.*;

public class ColorConcentrationClient extends WindowController {
    
  private ColorfulSquares block;
  private int theCount = 1;
  private int scoreCount = 0;

  private Text results = new Text( "Your score is: 0", 150, 710, canvas );
  private Color [] squareCol;
  private Location p1 = new Location (1250, 1250);

  public static void main(String[] args) {
    new ColorConcentrationClient().startController(680,800);
  }

  // User is prompted to find matches on the canvas where the squares appear
  public void begin(){
    Text prompt = new Text
         ( "Find each square match with as few clicks as possible!", 20, 13,
                                                                    canvas ); 
    prompt.setFontSize(20);

    Location p = new Location (0,0);
    block = new ColorfulSquares(p, 20, 20, canvas);
    block.setColors();
    results.setFontSize(45);
  }

  public void onMouseClick(Location p){
    scoreCount = scoreCount + 1;
    block.redFrame(block.getIndex(p));

    theCount = theCount + 1;
 
    if(theCount%2==1){
      // If the colors match, are not white, & each square does not have the 
      // same index the square will disappear
      if(block.getColors(p) == block.getColors(p1) && (block.getColors(p) 
         != Color.WHITE) && (block.getIndex(p) != block.getIndex(p1)) ){
           block.hideRects(block.getIndex(p1));
           block.hideRects(block.getIndex(p));
      }
    }

    // Frames of squares that have already been matched will not turn red
    if((block.getColors(p) == block.getColors(p1)) && 
        block.getColors(p) == Color.WHITE ){
          block.resetFrame(block.getIndex(p));
    }

    // Frames of squares that have already been matched will not turn red
    if(theCount%2==1){
      block.resetFrame(block.getIndex(p1));
      block.resetFrame(block.getIndex(p));
    }
    results.setText( "Your score is: " + scoreCount);
    p1 = p;
    }

}
