import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setBlue(255 - pixelObj.getBlue());
        pixelObj.setGreen(255 - pixelObj.getGreen());
      }
    }
  }
  public void sepia()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int red = pixelObj.getRed();
        int green = pixelObj.getGreen();
        int blue = pixelObj.getBlue();
        int average = (red + green + blue) / 3;
        pixelObj.setRed(average);
        pixelObj.setGreen(average);
        pixelObj.setBlue(average);
        int newRed = pixelObj.getRed();
        int newGreen = pixelObj.getGreen();
        int newBlue = pixelObj.getBlue();
        if (newRed < 60)
          {
              newRed *= .9;
              newGreen *= .9;
              newBlue *= .9;
              pixelObj.setRed(newRed);
              pixelObj.setGreen(newGreen);
              pixelObj.setBlue(newBlue);
            }
            else if (newRed < 190)
            {
                newBlue *= .8;
                pixelObj.setBlue(newBlue);
            }
            else
            {
                newBlue *= .9;
                pixelObj.setBlue(newBlue);
            }
      }
    }
  }
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int red = pixelObj.getRed();
        int green = pixelObj.getGreen();
        int blue = pixelObj.getBlue();
        int average = (red + green + blue) / 3;
        pixelObj.setRed(average);
        pixelObj.setGreen(average);
        pixelObj.setBlue(average);
      }
    }
  }
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int green = pixelObj.getGreen();
        int blue = pixelObj.getBlue();
        if (green > blue)
        {
            int difference = Math.abs(blue - green);
            pixelObj.setGreen(green - difference);
            pixelObj.setBlue(blue - difference);
        }
        else if (blue > green)
        {
            int difference = blue - green;
            pixelObj.setBlue(blue + difference);
        }
      }
    }
  }
  
  /** Methods that mirrors the picture
      using various methods*/
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        rightPixel = pixels[row][col];
        leftPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
    }
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int length = pixels.length;
        for (int row = 0; row < length / 2; row++)
        {
          for (int col = 0; col < pixels[row].length; col++)
          {
            topPixel = pixels[row][col];
            bottomPixel = pixels[length - 1 - row][col];
            bottomPixel.setColor(topPixel.getColor());
          }
        } 
    }
    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        int length = pixels.length;
        for (int row = 0; row < length / 2; row++)
        {
          for (int col = 0; col < pixels[row].length; col++)
          {
            bottomPixel = pixels[row][col];
            topPixel = pixels[length - 1 - row][col];
            bottomPixel.setColor(topPixel.getColor());
          }
        } 
    }
    public void mirrorDiagonal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel pixel1 = null;
        Pixel pixel2 = null;
        for (int row = 0; row < pixels.length; row++)
        {
          for (int col = 0; col < pixels[row].length; col++)
          {
            if (col < pixels.length)
            {
                pixel1 = pixels[row][col];
                pixel2 = pixels[col][row];
                pixel1.setColor(pixel2.getColor());
            }
          }
        } 
    }
    
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
        count++;
      }
    }
    System.out.println(count);
  }
    public void mirrorArms()
  {
    //mirror from row 170 to 195 and column 120 to 293
    int mirrorPoint = 196;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 170; row < mirrorPoint; row++)
    {
      for (int col = 120; col < 294; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[mirrorPoint - row + mirrorPoint][col];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    }
    public void mirrorGull()
  {
    //mirror from row 244 to 291 and column 235 to 345
    int mirrorPoint = 346;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 232; row < 323; row++)
    {
      for (int col = 235; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    }
    public void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
    int startDestRow, int startDestCol )
    {
        Pixel[][] pixels = sourcePicture.getPixels2D();
        Pixel[][] copying = new Pixel[endSourceRow - startSourceRow + 1][endSourceCol - startSourceCol + 1];
        Pixel[][] pixels2 = this.getPixels2D();
        Pixel pixela = null;
        Pixel pixelb = null;
        Pixel pixelc = null;
        int rowz = 0;
        int r = 0;
        for (int row = startSourceRow; row <= endSourceRow; row++)
        {
            int colz = 0;
            for (int col = startSourceCol; col <= endSourceCol; col++)
            {
                pixela = pixels[row][col];
                copying[rowz][colz] = pixela;
                copying[rowz][colz].setColor(pixela.getColor());
                colz++;
            }
            rowz++;
        }
        for (int row = startDestRow; row < copying.length + startDestRow; row++)
        {
            int c = 0;
            for (int col = startDestCol; col < copying[0].length + startDestCol; col++)
            {
                if ((row <= pixels2.length && row >= 0) && (col <= pixels2[0].length && col >= 0))
                {
                    pixelb = copying[r][c];
                    pixelc = pixels2[row][col];
                    pixelc.setColor(pixelb.getColor());
                }
                c++;
            }
            r++;
        }
    }
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
   /*    
       Picture flower1 = new Picture("flower1.jpg");
       Picture flower2 = new Picture("flower2.jpg");
       this.copy(flower1,0,0);
       this.copy(flower2,100,0);
       this.copy(flower1,200,0);
       Picture flowerNoBlue = new Picture(flower2);
       flowerNoBlue.zeroBlue();
       this.copy(flowerNoBlue,300,0);
       this.copy(flower1,400,0);
       this.copy(flower2,500,0);
       this.mirrorVertical();
       this.write("collage.jpg");
   */
   Picture beach = new Picture("beach.jpg");
   Picture negativeBeach = new Picture(beach);
   Picture sepiaBeach = new Picture(beach);
   Picture gull = new Picture("seagull.jpg");
   Picture chair = new Picture("lawnchair.jpg");
   negativeBeach.negate();
   sepiaBeach.sepia();
   this.copy(beach,0,0);
   this.copy(negativeBeach,0,640);
   this.copy(beach,480,0);
   this.cropAndCopy(gull, 232, 323, 235, 345, 750, 500);
   this.copy(sepiaBeach, 480, 640);
   this.write("C:\\Users\\ngloo.HS\\Desktop\\GitHub\\unit6MediaComp\\PictureLab\\images\\MyCollage.jpg");
  }
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
