import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TimerDemo {

    /** This panel uses a timer to make a circle bounce around. */
    public static class BouncePanel
        extends JPanel implements ActionListener {
        
        private int x, y;
        private int xDir, yDir;
        private int diameter = 30;

        public BouncePanel(int width, int height) {
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.BLACK);

            x = width/2;
            y = height/2;
            xDir = 1;
            yDir = 2;

            Timer timer = new Timer(10, this);
            timer.setInitialDelay(0);
            timer.start();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.YELLOW);
            g.fillOval(x, y, diameter, diameter);
        }

        public void actionPerformed(ActionEvent ev) {
            if(x + xDir < 0 ||
               x + xDir + diameter > getWidth()) {
                xDir = -xDir;
            }

            if(y + yDir < 0 ||
               y + yDir + diameter > getHeight()) {
                yDir = -yDir;
            }
            x += xDir;
            y += yDir;
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JFrame frame = new JFrame("Timer Demo");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    frame.add(new BouncePanel(200, 300));
                    frame.pack();
                    frame.setVisible(true);
                }
            });
    }
}
