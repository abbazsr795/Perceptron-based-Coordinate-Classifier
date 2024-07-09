import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Panel extends JPanel{
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int DELAY = 1;
    static final int BIAS = 1;
    Point[] points = new Point[100];
    Random random;
    int FixedGradient;
    int FixedIntercept;
    double LearningRate;
    double[] weights;

    Panel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);

        this.FixedGradient = 2;
        this.FixedIntercept = 200;
        this.LearningRate = 0.01;

        this.weights = new double[3];
        for (int i = 0; i < 3; i++){
            this.weights[i] = -1 + random.nextDouble() * 2;
        }

        Timer timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
        this.setFocusable(true);
        requestFocusInWindow();
        for(int i = 0; i < points.length; i++){
            points[i] = new Point();
            fixedFunction(points[i]);
        }
    }

    public void fixedFunction(Point p){
        if (mapToScreenY(p.y) > SCREEN_HEIGHT - ((FixedGradient *mapToScreenX( p.x)) + FixedIntercept)){
            p.label = 1;
        }else {
            p.label = -1;
        }
    }

    public int drawFixedFunction(int x){
        return SCREEN_HEIGHT - ((FixedGradient * x) + FixedIntercept);
    }

    public int sign(double n){
        if (n < 0){
            return -1;
        }else{
            return 1;
        }
    }

    public void predictLabel(Point p, Graphics g){
        double result = (mapToScreenX(p.x) * weights[0]) + (mapToScreenY(p.y) * weights[1]) + (BIAS*weights[2]);
        p.prediction = sign(result);
        p.error = p.label - p.prediction;
        if (p.error != 0){
            g.setColor(Color.red);
            g.fillOval(mapToScreenX(p.x),mapToScreenY(p.y),10,10 );
        }else{
            g.setColor(Color.green);
            g.fillOval(mapToScreenX(p.x),mapToScreenY(p.y),10,10 );
        }
    }

    public int drawTrainedFunction(int x){
        double m = (-1 * weights[0]) / (weights[1]);
        double c = (-1 * weights[2] * BIAS) / (weights[1]);
        return (int)((x * m) + c);
    }

    public int mapToScreenX(double x){
        return (int) Math.round(((SCREEN_WIDTH * (x + 1))/2));
    }
    public int mapToScreenY(double y){
        return (int) Math.round(((SCREEN_HEIGHT * ((-1* y) + 1))/2));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Draws the points
        for(int i = 0; i < points.length; i++){
            g.setColor(Color.white);
            if(points[i].label == 1){
                g.fillRect(mapToScreenX(points[i].x),mapToScreenY(points[i].y),10,10 );
            }else{
                g.fillOval(mapToScreenX(points[i].x),mapToScreenY(points[i].y),10,10 );
            }
            g.fillOval(mapToScreenX(points[i].x),mapToScreenY(points[i].y),10,10 );
        }

        //Draws the correct equation
        g.setColor(Color.MAGENTA);
        for(int i = 0; i <= SCREEN_WIDTH; i++){
            g.fillRect(i,drawFixedFunction(i),2,2);
        }

        for(int i = 0; i < points.length; i++){

            predictLabel(points[i], g);

            weights[0] += mapToScreenX(points[i].x) * LearningRate * points[i].error;
            weights[1] += mapToScreenY(points[i].y) * LearningRate * points[i].error;
            weights[2] += LearningRate * points[i].error;
        }

        //Draw predicted line
        g.setColor(Color.ORANGE);
        for(int i = 0; i <= SCREEN_WIDTH; i++){
            g.fillRect(i,drawTrainedFunction(i),2,2);
        }
    }
}