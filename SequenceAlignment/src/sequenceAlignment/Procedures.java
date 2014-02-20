package sequenceAlignment;

import typedef.*;

public class Procedures {

    double[][] purchaseData;    //raw data retrieved from database
    int p1 = 4, p2 = 1; //length of two period to be aligned
    int items = 6;  //total number of goods
    int weeks = 62; //total number of weeks
    
    AlignmentScore[] alignmentScore;    //alignmet score

    public Procedures() {
        int i, j;

        //retrieve data from database
        ConnSQL connSQL = new ConnSQL();
        purchaseData = connSQL.readData();

        connSQL.printData();

        //calculate similarity
        CalSimilarity calSimilarity = new CalSimilarity(items, weeks, purchaseData);

        //sequence alignment
        SequenceAlignment sequenceAlignment = new SequenceAlignment(p1, p2, weeks, calSimilarity);
        sequenceAlignment.alignment();
        alignmentScore = sequenceAlignment.alignmentScore;
        sequenceAlignment.printScore();

        //calculate average and standard deviation
        Stastics stastics = new Stastics(p1, p2, weeks, alignmentScore);
        stastics.average();
        stastics.printAverage();
        stastics.standardDeviation();
        stastics.printDeviation();
        alignmentScore = stastics.alignmentScore;

        //calculate CUSUM
        CUSUM cusum = new CUSUM(p1, p2, weeks, alignmentScore);
        cusum.calCUSUM();
        alignmentScore = cusum.alignmentScore;
        cusum.printCUSUM();
        
        //calculate EWMA
        EWMA ewma = new EWMA(0.2, p1, p2, weeks, alignmentScore);
        ewma.calEWMA();
        alignmentScore = ewma.alignmentScore;
        ewma.printEWMA();
    }
}
