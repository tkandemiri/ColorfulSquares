// Tariro Kandemiri

// This class creates an 8 by 8 grid of squares
// Each square is assigned a random color
// that only appears twice

import objectdraw.*;
import java.awt.*;

public class ColorfulSquares{

  private FilledRect [] rects;
  private FramedRect [] frames;

  // Creates new array of squares  
  public ColorfulSquares(Location pt, double width, double height,
                                            DrawingCanvas canvas){
    rects = new FilledRect [64];  
    frames = new FramedRect [64];  

    Location p =new Location(16, 50); 

    for(int i=0; i<rects.length;i++){
      rects[i] = new FilledRect(p, 80, 80, canvas);
      frames[i] = new FramedRect(p, 80, 80, canvas);
      rects[i].setColor(Color.WHITE);
      p.translate(80,0);
        if(i%8==7)
        p.translate(-80*8,80);
    }

    // Creates surprise background for when all matches have been found
    FramedOval [] polkaBG = new FramedOval [64];
    FramedRect bg = new FramedRect (rects[0].getLocation(), 640, 640, canvas);

    Location polkaLoc = new Location(30, 65);

    for(int i=0; i<polkaBG.length;i++){
      polkaBG [i] = new FramedOval(polkaLoc, 50, 50, canvas);
      polkaBG[i].sendToBack();
      polkaLoc.translate(80,0);
        if(i%8==7)
        polkaLoc.translate(-80*8,80);
    }

  }
  
  // Sets a random color for each individual square
  public void setColors(){
    Color newCol = (Color.BLACK);
    RandomIntGenerator color = new RandomIntGenerator(0,255);
    RandomIntGenerator randomRects = new RandomIntGenerator(0,63);

    for(int f=0; f<8; f++){
      for(int i=0; i<rects.length;i++){
        if(i%2==0){
        newCol = new Color(color.nextValue(), 
                             color.nextValue(), 
                             color.nextValue());
        }
     rects[i].setColor(newCol);
    }

    for(int t =0; t < rects.length; t++){
      int squareNum1 = randomRects.nextValue();
      int squareNum2 = randomRects.nextValue();  

      Color swapColor = rects[squareNum1].getColor();
      rects[squareNum1].setColor(rects[squareNum2].getColor());
      rects[squareNum2].setColor(swapColor);
    }
  }
 
 }

  // Returns index of square that is clicked
  public int getIndex(Location p){
    int squareIndex = 0;
    for(int i=0; i<rects.length;i++){
        if(rects[i].contains(p))
        squareIndex = i;
    }
    return squareIndex;
  }

  // Returns index of square that is clicked
  public Color getColors(Location p){
    Color squareColor = (Color.BLACK);
    for(int i=0; i<rects.length;i++){
        if(rects[i].contains(p)){
           squareColor = rects[i].getColor();
        }
    }
    return squareColor;
  }

  // Hides squares by hiding the squares and frames around them
  public void hideRects(int i){
    rects[i].hide();
    frames[i].hide();
  }

  // Sets frame of square to red if it is first square clicked
  public void redFrame(int i){
    frames[i].setColor(Color.RED);
  }

  // Resets color of frame for when two squares do not match
  public void resetFrame(int i){
    frames[i].setColor(Color.BLACK);
  }
 
}
