package sequenceAlignment;

import java.text.DecimalFormat;
import typedef.*;

public class SequenceAlignment {

    int p1, p2;     //length of two period to be aligned
    int weeks;      //total number of weeks
    CalSimilarity calSimilarity;    //calculate similarity. see CalSimilarity.java
    DecimalFormat df;
    AlignmentScore[] alignmentScore;    //where the results will be stored

    public SequenceAlignment(int p1, int p2, int weeks, CalSimilarity calSimilarity) {
        this.p1 = p1;
        this.p2 = p2;
        this.weeks = weeks;
        this.calSimilarity = calSimilarity;
        alignmentScore = new AlignmentScore[200];
        df = new DecimalFormat("0.00");

    }

    //alignment between
    //[week(k) week(k + 1) week(k + 2) week(k + 3)] and
    //[week(k + 4)]
    //result will be stored in alignmentScore[k].value 
    //see listing 2 in the report
    public void alignment_(int k) {
        int i, j;
        double[][] M = new double[p1 + 1][p2 + 1];  //matrix for alignment
        double match;
        double delete, insert;
        double gap = 0;
        for (j = 0; j <= p2; j++) {
            M[0][j] = gap * j;
        }

        for (i = 0; i <= p1; i++) {
            M[i][0] = gap * i;
        }

        for (i = 1; i <= p1; i++) {
            for (j = 1; j <= p2; j++) {
                match = M[i - 1][j - 1] + calSimilarity.similarity(k + i - 1, k + p1 + j - 1);
                delete = M[i - 1][j] + gap;
                insert = M[i][j - 1] + gap;
                if ((match > delete) && (match > insert)) {
                    M[i][j] = match;
                } else if (delete > insert) {
                    M[i][j] = delete;
                } else {
                    M[i][j] = insert;
                }
            }
        }

        //print the matrix
        for (i = 0; i <= p1; i++) {
            for (j = 0; j <= p2; j++) {
                System.out.print(df.format(M[i][j]) + "\t");
            }
            System.out.println();
        }

        //trace back the alignment
        String Alignments1 = "";
        String Alignments2 = "";
        i = p1;
        j = p2;
        while ((i > 0) && (j > 0)) {
            double score = M[i][j];
            double scoreDiag = M[i - 1][j - 1];
            double scoreAbove = M[i - 1][j];
            if (score == scoreDiag + calSimilarity.similarity(k + i - 1, k + p1 + j - 1)) {
                Alignments1 = (k + i - 1) + Alignments1;
                Alignments2 = (k + p1 + j - 1) + Alignments2;
                i = i - 1;
                j = j - 1;
            } else if (score == scoreAbove + gap) {
                Alignments1 = (k + i - 1) + Alignments1;
                Alignments2 = "-" + Alignments2;
                i = i - 1;
            } else {
                Alignments1 = "-" + Alignments1;
                Alignments2 = (k + p1 + j - 1) + Alignments2;
                j = j - 1;
            }
        }

        while (i > 0) {
            Alignments1 = (k + i - 1) + Alignments1;
            Alignments2 = "-" + Alignments2;
            i = i - 1;
        }

        while (j > 0) {
            Alignments1 = "-" + Alignments1;
            Alignments2 = (k + p1 + j - 1) + Alignments2;
            j = j - 1;
        }

        System.out.println("Alignment score = " + M[p1][p2]);
        System.out.println("Sequence1 = " + Alignments1);
        System.out.println("Sequence2 = " + Alignments2);

        alignmentScore[k] = new AlignmentScore();
        //result
        alignmentScore[k].value = M[p1][p2];
    }

    //align for the whole data
    public void alignment() {
        calSimilarity.CalCap();
        int k;
        for (k = 0; k <= weeks - p1 - p2; k++) {
            alignment_(k);
        }
    }

    public void printScore() {
        System.out.println("AlignmentScore:");
        int i;
        for (i = 0; i <= weeks - p1 - p2; i++) {
            System.out.println(i + "\t" + alignmentScore[i].value);
        }
    }
}
