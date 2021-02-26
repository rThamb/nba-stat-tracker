package analysis.util;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class StatDistribution {

    private DescriptiveStatistics ds;
    private double b1;
    private double b2;
    private double b3;
    private double b4;
    private double b5;

    private double b1_per = 0.1;
    private double b2_per = 0.3;
    private double b3_per = 0.65;
    private double b4_per = 0.8;
    private double b5_per = 0.95;
    private double b6_per = 1;

    public StatDistribution(double[] dataEntries){
        this.ds = new DescriptiveStatistics(dataEntries);
        b1 = this.ds.getMean() - this.ds.getStandardDeviation();
        b2 = this.ds.getMean() - (2 * this.ds.getStandardDeviation());
        b3 = this.ds.getMean();
        b4 = this.ds.getMean() + this.ds.getStandardDeviation();
        b5 = this.ds.getMean() + (2 * this.ds.getStandardDeviation());
    }

    public double normalize_value(double stat){

        if(stat > b5)
            return b6_per;
        if(stat >= b4 && stat < b5)
            return amplifier(b4_per, b4, b5, stat);
        if(stat >= b3 && stat < b4)
            return amplifier(b3_per, b3, b4, stat);
        if(stat >= b2 && stat < b3)
            return amplifier(b2_per, b2, b3, stat);
        if(stat >= b1 && stat < b2)
            return amplifier(b1_per, b1, b2, stat);

        return amplifier(0.0, 0, b1, stat);
    }

    private double amplifier(double startingPer, double minbr, double maxBr, double stat){

        double n = maxBr - minbr;
        double a = stat - minbr;
        double metric = (((a / n) * (maxBr - minbr)) / 100) + startingPer;
        return metric;
    }
}
