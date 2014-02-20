package sequenceAlignment;

import typedef.*;

public class EWMA {

    double lambda;  //parameter of the method, see formula(3) in the report
    
    int p1, p2; //length of two period to be aligned
    int weeks;  //total number of weeks
    //then the number of scores is weeks - p1 - p2 
    
    AlignmentScore[] alignmentScore;    //where alignment results are stored
    double mean;    //mean value of all the data
    double s; //standard deviation of all the data

    double ucl, lcl;    //upper and lower limits

    public EWMA(double lambda, int p1, int p2, int weeks, AlignmentScore[] alignmentScore) {
        this.lambda = lambda;
        this.p1 = p1;
        this.p2 = p2;
        this.weeks = weeks;
        this.alignmentScore = alignmentScore;
        mean = alignmentScore[weeks - p1 - p2].avg;
        s = alignmentScore[weeks - p1 - p2].standardDev;
        
        //formula(13) and (14) in the report
        ucl = mean + 3 * Math.sqrt((lambda / (2 - lambda)) * s * s);
        lcl = mean - 3 * Math.sqrt((lambda / (2 - lambda)) * s * s);
    }

    //Calculate tth of EWMA
    //then stored in alignmentScore[t].eWMAverage
    //see formula(12) in the repport
    public void calEWMA_(int t) {
        if (t == 0) {
            alignmentScore[t].eWMAverage = mean;
        } else {
            alignmentScore[t].eWMAverage = lambda * alignmentScore[t].value + (1 - lambda) * alignmentScore[t - 1].eWMAverage;
        }
    }

    //calculate EWMA for the whole data
    public void calEWMA() {
        int t;
        for (t = 0; t <= weeks - p1 - p2; t++) {
            calEWMA_(t);
        }
    }

    public void printEWMA() {
        System.out.println("EWMA:");
        System.out.println("UCL: " + ucl);
        System.out.println("LCL: " + lcl);

        int i;
        for (i = 0; i <= weeks - p1 - p2; i++) {
            System.out.println(i + "\t" + alignmentScore[i].eWMAverage);
        }
    }
}
