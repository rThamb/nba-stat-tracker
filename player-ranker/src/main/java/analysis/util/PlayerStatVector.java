package analysis.util;

import model.PlayerStat;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class PlayerStatVector implements Comparable{

    private PlayerStat player;
    private double[] vector;
    private double magnitude;

    public PlayerStatVector(PlayerStat player, double[] data, double[] weights){
        this.player = player;
        this.vector = data;
        this.getVectorMagnitude(weights);
    }

    public PlayerStat getPlayer() {
        return player;
    }

    public double[] getVector() {
        return vector;
    }

    public double getMagnitude() {
        return magnitude;
    }

    private void getVectorMagnitude(double[] statWeight){
        //similar to calculate final mark for a course/class.
        double overall_percent = 0.0;
        for(int i = 0; i < vector.length; i++){
            overall_percent += vector[i] * statWeight[i];
        }
        magnitude = overall_percent;
    }


    private double squared(double num){
        return num * num;
    }
    private double squareRoot(double num){
        return Math.sqrt(num);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof PlayerStatVector){
            if (this.magnitude == ((PlayerStatVector) o).magnitude)
                return 0;
            if (this.magnitude < ((PlayerStatVector) o).magnitude)
                return 1;
            else
                return -1;
        }else
            return -1;
    }
}
