import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Prints a Sierpinski Triangle iteratively or recursively to the screen.
 *
 * @author Justin Largo
 * @version (1.0)
 */

public class SierpinskiTriangle extends JPanel {
    // Instance variables
    private static final long serialVersionUID = 4742367089554814358L;
    private int width, height;
    private static int points;
    private static String generationType;
    private Random r = new Random();

    public SierpinskiTriangle() {
        super();
        setOpaque(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(800, 600));
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 215, 0));
        // Top point
        Point c1 = new Point(width / 2, 0);
        g.fillArc(width / 2, 0, 1, 1, 0, 360);
        // Bottom left point
        Point c2 = new Point(0, height);
        g.fillArc(0, height, 1, 1, 0, 360);
        // Bottom Right point
        Point c3 = new Point(width, height);
        g.fillArc(width, height, 1, 1, 0, 360);

        // First point located inside triangle
        Point p = new Point(getWidth() / 2, getHeight() / 2);
        g.fillArc(p.x, p.y, 1, 1, 0, 360);

        // Iterative
        if (generationType.equalsIgnoreCase("iterative")) {
            // Generate the triangle fractals
            for (int i = 0; i < points; i++) {
                int newPoint = r.nextInt(3) + 1;

                if (newPoint == 1) {

                    int midx = (p.x + c1.x) / 2;
                    int midy = (p.y + c1.y) / 2;
                    p = new Point(midx, midy);
                    g.fillArc(p.x, p.y, 1, 1, 0, 360);
                } else if (newPoint == 2) {

                    int midx = (p.x + c2.x) / 2;
                    int midy = (p.y + c2.y) / 2;
                    p = new Point(midx, midy);
                    g.fillArc(p.x, p.y, 1, 1, 0, 360);
                } else {
                    int midx = (p.x + c3.x) / 2;
                    int midy = (p.y + c3.y) / 2;
                    p = new Point(midx, midy);
                    g.fillArc(p.x, p.y, 1, 1, 0, 360);
                }
            }
        } else {
            // Recursive
            drawTriangle(g, p, points);
        }

    }

    private void drawTriangle(Graphics g, Point p, int n) {
        if (n == 0) {
            return;
        } else {
            Point c1 = new Point(width / 2, 0);
            Point c2 = new Point(0, height);
            Point c3 = new Point(width, height);
            int newPoint = r.nextInt(3) + 1;

            if (newPoint == 1) {

                int midx = (p.x + c1.x) / 2;
                int midy = (p.y + c1.y) / 2;
                p = new Point(midx, midy);
                g.fillArc(p.x, p.y, 1, 1, 0, 360);
                drawTriangle(g, p, n - 1);
            } else if (newPoint == 2) {

                int midx = (p.x + c2.x) / 2;
                int midy = (p.y + c2.y) / 2;
                p = new Point(midx, midy);
                g.fillArc(p.x, p.y, 1, 1, 0, 360);
                drawTriangle(g, p, n - 1);
            } else {
                int midx = (p.x + c3.x) / 2;
                int midy = (p.y + c3.y) / 2;
                p = new Point(midx, midy);
                g.fillArc(p.x, p.y, 1, 1, 0, 360);
                drawTriangle(g, p, n - 1);
            }
        }
    }

    public static void main(String[] args) {

        // Sanitize data and set instance variables
        generationType = JOptionPane.showInputDialog(null, "Iterative or Recursive: ", "Generate A Sierpinski Triangle",
                JOptionPane.QUESTION_MESSAGE);
        ;
        String strPoints = JOptionPane.showInputDialog(null, "Amount of points: ", "Generate A Sierpinski Triangle",
                JOptionPane.QUESTION_MESSAGE);
        points = Integer.parseInt(strPoints);

        if (generationType.equalsIgnoreCase("iterative") || generationType.equalsIgnoreCase("recursive")) {

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    // Create and set up the window.
                    JFrame frame = new JFrame("Sierpinski Triangle");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    SierpinskiTriangle panel = new SierpinskiTriangle();
                    frame.getContentPane().add(panel);

                    // Display the window.
                    frame.pack();
                    frame.setVisible(true);
                }
            });
        } else {
            System.err.println("usage error: generation type must be iterative or recursive");
        }
    }
}