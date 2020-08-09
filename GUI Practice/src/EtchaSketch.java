import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import javax.swing.Timer;

/** Etch-a-Sketch program, controlled with arrow keys. */
public class EtchaSketch extends JFrame {

    private static class SketchPanel
        extends JPanel
        implements KeyListener, ActionListener {

        private Deque<Point> points = new LinkedList<>();

        private int xSpeed;
        private int ySpeed;

        public SketchPanel(int width, int height) {
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.LIGHT_GRAY);

            points.add(new Point(width/2, height/2));

            addKeyListener(this);

            Timer timer = new Timer(10, this);
            timer.setInitialDelay(0);
            timer.start();
        }

        @Override
        public void keyPressed(KeyEvent ev) {
            switch(ev.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                xSpeed = -1;
                break;
            case KeyEvent.VK_RIGHT:
                xSpeed = 1;
                break;
            case KeyEvent.VK_UP:
                ySpeed = -1;
                break;
            case KeyEvent.VK_DOWN:
                ySpeed = 1;
                break;
            }
        }

        @Override
        public void keyReleased(KeyEvent ev) {
            switch(ev.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                xSpeed = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                ySpeed = 0;
                break;
            }
        }

        @Override
        public void keyTyped(KeyEvent ev) { }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            Point a = null;
            for(Point b : points) {
                if(a != null) {
                    g.drawLine(a.x, a.y, b.x, b.y);
                }
                a = b;
            }
            g.fillOval(a.x-2, a.y-2, 4, 4);
        }

        @Override
        public void actionPerformed(ActionEvent ev) {
            if(xSpeed != 0 || ySpeed != 0) {
                Point p = points.getLast();
                Point newPoint = new Point(p.x + xSpeed, p.y + ySpeed);
                points.add(newPoint);
                repaint();
            }
        }

        public void clear() {
            Point p = points.getLast();
            points.clear();
            points.add(p);
            repaint();
        }
    }

    public EtchaSketch() {
        super("Etch-A-Sketch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SketchPanel sketchPanel = new SketchPanel(400, 300);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    sketchPanel.clear();
                    sketchPanel.requestFocusInWindow();
                }
            });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearButton);

        add(sketchPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
        pack();

        sketchPanel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JFrame frame = new EtchaSketch();
                    frame.setVisible(true);
                }
            });
    }
}
