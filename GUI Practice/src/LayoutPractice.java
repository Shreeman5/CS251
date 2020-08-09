/**
 * This program demonstrates the use of panels,
 * borders and labels within frames. In this 
 * program, there are two panels, with the first
 * panel having some artwork and the second 
 * panel has a label and button.
 * Date: Nov 12, 2018
 * Name: Shreeman Gautam
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LayoutPractice implements ActionListener{
    
    /** 
     * global variable that increases 
     * the pop-up button every time
     * a button is clicked
     */
    static int buttonPress = 0;
    /**
     * global variable that updates the label
     * every time a button is clicked
     */
    static int labelUpdate = 0;

    /**
     * createAndShowGui is called from the
     * event dispatch thread here.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }    
        });
    }

    /**
     * A frame/window called Layout Practice is
     * called here. Panels dialogPanel and 
     * shapePanel are called here. shapePanel
     * makes the art and dialogPanel contains 
     * the button and the label. Every time
     * the button is pressed, using addActionListener
     * and the showMessageDialog method, a pop-up
     * window appears with a message displaying how 
     * many times the button has been clicked.
     * Using the setText method, every time the
     * button is clicked, the label is updated. 
     * Also, the layout of the panel is set to null
     * to ensure that the dialogButton and updateText
     * are in place. The frame adds both the panels
     * and while the frame is visible, this method
     * runs.
     */
    protected static void createAndShowGUI() {
        JFrame frame = new JFrame("Layout Practice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ShapePanel shapePanel = new ShapePanel();
        DialogPanel dialogPanel = new DialogPanel();
        JButton dialogButton = new JButton("Click Me For A Dialog");
        JLabel updateText = new JLabel("Button Clicks = " + getLabelCount());
        dialogPanel.setLayout(null);
        updateText.setBounds(0, 100, 180, 30);
        dialogButton.setBounds(120, 700, 180, 30);
        dialogButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JOptionPane.showMessageDialog(null, "You have clicked " + 
                        getButtonCount() + " times !", "Button Count", 
                        JOptionPane.INFORMATION_MESSAGE);
                updateText.setText("Button Clicks = " + getLabelCount());
            }
        });

        dialogPanel.add(updateText);
        dialogPanel.add(dialogButton);
        
        frame.add(shapePanel, BorderLayout.CENTER);
        frame.add(dialogPanel, BorderLayout.EAST);
         
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * 
     * @return the number of times
     * a button is clicked, seen
     * using labels.
     */
    public static int getLabelCount() {
        return labelUpdate++;
    }

    /**
     * 
     * @return the number of times
     * a button is clicked, seen using
     * the pop-up window.
     */
    public static int getButtonCount() {
        return ++buttonPress;
    }
    
    /**
     * sets the background
     * for dialogPanel
     *
     */
    public static class DialogPanel extends JPanel{
        public DialogPanel() {
            setPreferredSize(new Dimension(400, 100));
            setBackground(Color.gray);       
        }
    }
    
    /**
     * sets the background of ShapePanel.
     * paintComponent creates a triangle 
     * and some rectangles to form the
     * shape of a house. 
     */
    public static class ShapePanel extends JPanel{
        public ShapePanel() {
            setPreferredSize(new Dimension(1050, 800));
            setBackground(Color.CYAN);
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int xpoints[] = {600, 800, 400};
            int ypoints[] = {100, 300, 300};
            int n = 3;
            
            Polygon p = new Polygon(xpoints,ypoints,n);
            g.setColor(Color.BLUE);
            g.fillPolygon(p);
            
            g.setColor(Color.RED);
            g.fillRect(450, 300, 300, 400);
            
            g.setColor(Color.BLACK);
            g.fillRect(500, 350, 50, 50);
            
            g.setColor(Color.BLACK);
            g.fillRect(650, 350, 50, 50);
            
            g.setColor(Color.ORANGE);
            g.fillRect(565, 600, 70, 100);
        }
    }

    /**
     * instance of the actionPerformed method
     */
    public void actionPerformed(ActionEvent arg0) {
    }

}
