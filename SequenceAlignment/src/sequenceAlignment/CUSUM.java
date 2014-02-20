package sequenceAlignment;

import typedef.*;

public class CUSUM {

    int p1, p2; //length of two period to be aligned
    int weeks;  //total number of weeks
    //then the number of scores is weeks - p1 - p2 
    AlignmentScore[] alignmentScore;    //alignmet score
    double xAvg;    //mean value
    double stD;     //standard deviation
    
    double delta;   //the amount of shift that we wish to detect
                    //expressed as a multiple of standard deviation
    double k;       //medium variable
    double h;       //threshold  

    public CUSUM(int p1, int p2, int weeks, AlignmentScore[] alignmentScore) {
        this.p1 = p1;
        this.p2 = p2;
        this.weeks = weeks;
        this.alignmentScore = alignmentScore;
        this.xAvg = alignmentScore[weeks - p1 - p2].avg;
        this.stD = alignmentScore[weeks - p1 - p2].standardDev;
        delta = 1;
        this.k = (delta * stD) / 2;     //see formula(6.1) in the report
        this.h = 10.809 * k;            //see formula(15) in the report
    }

    //Calculate nth term for CUSUM
    //then stored in alignmentScore[n].cuSUMp and alignmentScore[n].cuSUMn
    //see formula(6)and(7) in the report
    public void calCUSUM_(int n) {

        if (n == 0) {
            alignmentScore[n].cuSUMp = 0;
            alignmentScore[n].cuSUMn = 0;
        } else {
            double tp = alignmentScore[n - 1].cuSUMp + alignmentScore[n].value - xAvg - k;
            double tn = alignmentScore[n - 1].cuSUMp - alignmentScore[n].value + xAvg - k;

            if (tp > 0) {
                alignmentScore[n].cuSUMp = tp;
            } else {
                alignmentScore[n].cuSUMp = 0;
            }

            if (tn > 0) {
                alignmentScore[n].cuSUMn = tn;
            } else {
                alignmentScore[n].cuSUMn = 0;
            }
        }
    }

    //calculate EWMA for the whole data
    public void calCUSUM() {
        int n;
        for (n = 0; n <= weeks - p1 - p2; n++) {
            calCUSUM_(n);
        }
    }

    public void printCUSUM() {
        System.out.println("h = " + h);
        int i;
        System.out.println("CUSUM+:");
        for (i = 0; i <= weeks - p1 - p2; i++) {
            System.out.println(i + "\t" + alignmentScore[i].cuSUMp);
        }

        System.out.println("CUSUM-:");
        for (i = 0; i <= weeks - p1 - p2; i++) {
            System.out.println(i + "\t" + alignmentScore[i].cuSUMn);
        }
    }
}
