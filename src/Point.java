import java.util.Random;

public class Point {
    Random random = new Random();
    double x;
    double y;
    int label; //1 means above the line; 0 means below the line
    int prediction; //1 means above the line; 0 means below the line
    double error;

    Point(){
        this.x = -1 + random.nextDouble() * 2;
        this.y = -1 + random.nextDouble() * 2;
    }
}