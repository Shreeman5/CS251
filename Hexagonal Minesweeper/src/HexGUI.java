/**
 * This class makes the GUI of the hexagonal
 * minesweeper in a rhombus structure, along with 
 * labels.
 * Author: Shreeman Gautam
 * Date: Dec 7, 2018
 */
import java.awt.*;
import java.util.ArrayList;

public class HexGUI{
  
    /**
     * global variables used in the class
     */
    static int separation = 15;
    static int length = 0; 
    static int shortSide = 0; 
    static int radius =0; 
    static int distance =0; 
    static int size = 0;
    static ArrayList<Point> center = new ArrayList<Point>();
    static Color mechColor = new Color(204,204,204,200);
    
    /**
     * specify the height of the hexagon to be made,
     * along with other helper variables
     * @param height
     */
    public static void HexSpecification(int height) {
        distance = height;         
        radius = distance / 2;           
        length = (int) (distance / 1.73205);   
        shortSide = (int) (radius / 1.73205); 
        size = (int)((2*shortSide+length)/2);
    }
    
    /**
     * make a hexagon at starting point x0 and y0 using
     * the helper variables of HexSpecification
     * @param x0
     * @param y0
     * @return a hexagon at this coordinate in the GUI
     */
    public static Polygon drawOneHex(int x0, int y0) {
        int y = y0 + separation;
        int x = x0 + separation; 
                   
        int xCoordinates[];
        int yCoordinates[];
        xCoordinates = new int[] {x+radius,x+radius+radius,x+radius+radius,x+radius,x,x}; 
        yCoordinates = new int[] {y,y+shortSide,y+length+shortSide,y+length+shortSide+shortSide,y+length+shortSide,y+shortSide};
        int a = (y+y+length+shortSide+shortSide)/2;
        center.add(new Point(x + radius, a));
        return new Polygon(xCoordinates,yCoordinates,6);
    }
    
    /**
     * send coordinates to drawOneHex, get a hexagon
     * and fill it with color and give it a border
     * @param i
     * @param j
     * @param g
     */
    public static void drawAllHex(int i, int j, Graphics g) {
        int x = j * distance + i * distance/2;
        int y = i * (length+shortSide);
        Polygon poly = drawOneHex(x,y);
        
        g.setColor(Color.GRAY);     
        g.fillPolygon(poly);
        g.setColor(Color.BLACK);
        g.drawPolygon(poly);
    }
    
    /**
     * convert clicked pixel coordiantes to hexagonal coordinates
     * @param mx
     * @param my
     * @return hexgaonal coordinates according to row and column
     */
    public static Point pixToHex(int mx, int my) {
        Point p = new Point(-1,-1);
        int xDiff = 0;
        int yDiff = 0;
        int b = 10000;
        for (Point q : center) {
            xDiff = Math.abs(q.x - mx);
            yDiff = Math.abs(q.y - my);
            int a = xDiff + yDiff;
            int c =(int) Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
            if (a < b) {
                p.x = (int)((2*q.y/3) / size);
                p.y = (int)(((Math.sqrt(3) * q.x /3) - (q.y / 3)) / size);
                b = a;
            }  
            if (c < 35) {
                return p;
            }
        }
        return new Point(-1, -1); 
      }
    
    /**
     * change the board after every click and subsequent repaint
     * @param i
     * @param j
     * @param n
     * @param g
     */
    public static void finalHexBoard(int i, int j, int n, Graphics g) {
        char c='o';
        int x = j * distance + i * distance/2;
        int y = i * (length+shortSide);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        if (MineSweeperGame.board[i][j] == (int) 'F') {
            c = (char)n;
            g.setColor(Color.YELLOW);
            g.fillPolygon(drawOneHex(x,y));
            g.setColor(Color.BLACK);
            g.drawPolygon(drawOneHex(x,y));
            g.setColor(Color.RED);
            g.drawString(""+c, x+radius+separation - 6, y+radius+separation+8);
        }
        else if (MineSweeperGame.board[i][j] == (int) ',') {
            g.setColor(Color.GRAY);
            g.fillPolygon(drawOneHex(x,y));
            g.setColor(Color.BLACK);
            g.drawPolygon(drawOneHex(x,y));
        }
        else {
            if (n > 0) {
                g.setColor(mechColor);
                g.fillPolygon(drawOneHex(x,y));
                g.setColor(Color.BLACK);
                g.drawPolygon(drawOneHex(x,y));
                if (MineSweeperGame.board[i][j] == '.') {
                    
                }
                else if (MineSweeperGame.board[i][j] == (int) 'M') {
                    c = (char)n;
                    g.setColor(Color.RED);
                    g.fillPolygon(drawOneHex(x,y));
                    g.setColor(Color.BLACK);
                    g.drawPolygon(drawOneHex(x,y));
                    g.setColor(Color.BLACK);
                    g.drawString(""+c, x+radius+separation-10, y+radius+separation+9);
                }
                else {
                    c = (char)n;
                    g.setColor(Color.WHITE);
                    g.fillPolygon(drawOneHex(x,y));
                    g.setColor(Color.BLACK);
                    g.drawPolygon(drawOneHex(x,y));
                    g.setColor(Color.BLACK);
                    g.drawString(""+c, x+radius+separation-6, y+radius+separation+8); 
                }
            }
        }        
    }

    /**
     * Colored rectangles which hold labels of high scores, mine numbers,
     * flag numbers and timer after every click and
     * repaint.
     * @param labelX
     * @param labelY
     * @param g
     */
    public static void additionalGUI(int labelX, int labelY, Graphics g) {
        
        g.setColor(Color.WHITE);
        g.fillRect(labelX - 2, labelY + 150, 115, 130);
        g.setColor(Color.BLACK);
        g.drawRect(labelX - 2, labelY + 150, 115, 130);

        g.setColor(Color.WHITE);
        g.fillRect(labelX + 100, labelY, 115, 130);
        g.setColor(Color.BLACK);
        g.drawRect(labelX + 100, labelY, 115, 130);
        
        g.setColor(Color.WHITE);
        g.fillRect(labelX - 2, labelY, 72, 30);
        g.setColor(Color.BLACK);
        g.drawRect(labelX - 2, labelY, 72, 30);
        
        g.setColor(Color.WHITE);
        g.fillRect(labelX - 2, labelY + 50, 75, 30);
        g.setColor(Color.BLACK);
        g.drawRect(labelX - 2, labelY + 50, 75, 30);
        
        g.setColor(Color.WHITE);
        g.fillRect(labelX - 2, labelY + 100, 75, 30);
        g.setColor(Color.BLACK);
        g.drawRect(labelX - 2, labelY + 100, 75, 30);
        
    }
}
