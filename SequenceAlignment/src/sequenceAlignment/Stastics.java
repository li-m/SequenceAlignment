package sequenceAlignment;

import typedef.*;

//Calculate average and standard deviation of the alignment score
public class Stastics {

    int p1, p2; //length of two period to be aligned
    int weeks;  //total number of weeks
    //then the number of scores is weeks - p1 - p2 
    
    AlignmentScore[] alignmentScore;

    public Stastics(int p1, int p2, int weeks, AlignmentScore[] alignmentScore) {
        this.p1 = p1;
        this.p2 = p2;
        this.weeks =weeks;
        this.alignmentScore = alignmentScore;
    }

    //average of first n terms, stored in alignmentScore[n].avg
    public void average_(int n) {
        if (n == 0) {
            alignmentScore[n].avg = alignmentScore[n].value;
        } else {
            alignmentScore[n].avg = (alignmentScore[n - 1].avg * n + alignmentScore[n].value) / (n + 1);
        }
    }

    //each average
    public void average() {
        int i;
        for (i = 0; i <= weeks - p1 - p2; i++) {
            average_(i);
        }
    }

    public void printAverage() {
        System.out.println("Average:");
        int i;
        for (i = 0; i <= weeks - p1 - p2; i++) {
            System.out.println(i + "\t" + alignmentScore[i].avg);
        }
    }
    
    //standard deviation of first n terms, stored in alignmentScore[n].standardDev
    public void standardDeviation_(int n) {
        if (n == 0) {
            alignmentScore[n].standardDev = 0;
        } else {
            int i;
            double t;
            double sum = 0;
            for (i = 0; i <= n; i++) {
                t = alignmentScore[i].value - alignmentScore[n].avg;
                sum = sum + t * t;
            }
            alignmentScore[n].standardDev = Math.sqrt(sum / n);

        }
    }

    //each standard deviation
    public void standardDeviation() {
        int i;
        for (i = 0; i <= weeks - p1 - p2; i++) {
            standardDeviation_(i);
        }
    }
    
        public void printDeviation() {
        System.out.println("Standard Deviation:");
        int i;
        for (i = 0; i <= weeks - p1 - p2; i++) {
            System.out.println(i + "\t" + alignmentScore[i].standardDev);
        }
    }
}
