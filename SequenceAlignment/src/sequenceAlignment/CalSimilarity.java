package sequenceAlignment;

import typedef.*;

public class CalSimilarity {

    double[][] purchaseData;    //raw data
    int items;  //total number of goods
    int weeks;  //total number of weeks
    double cap; //a introduced constant, see formula(10) in the report

    public CalSimilarity(int items, int weeks, double[][] purchaseData) {
        this.items = items;
        this.weeks = weeks;
        this.purchaseData = purchaseData;
    }

    public void CalCap() {   //calculate cap, formula(10) in the report
        int i, j;
        for (i = 0; i < weeks; i++) {    //mode for each vector
            for (j = 0; j < weeks; j++) {
                if (cap < distance(i, j)) { //find out the biggest mode
                    cap = distance(i, j);
                }
            }
        }
    }

    public double distance(int a, int b) {
        //calculate the similarity value between ath and bth week
        int j;
        double d;   //Euclidean distance between ath and bth week
        double dSquare = 0; //square of the distance
        for (j = 0; j < items; j++) {
            dSquare = dSquare + (purchaseData[a][j] - purchaseData[b][j])
                    * (purchaseData[a][j] - purchaseData[b][j]);
        }
        d = Math.sqrt(dSquare); //formula(9) in the report
        return d;
    }

    public double similarity(int a, int b) {                
        //calculate the similarity value between ath and bth week
        //see formula(11)in the report
        double similarityVal;
        similarityVal = cap - distance(a,b);
        return similarityVal;
    }
}
