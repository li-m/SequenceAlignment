package sequenceAlignment;

import java.sql.*;
import java.text.DecimalFormat;
import typedef.*;

//In order to facilitate test, the database connect process is omitted
public class ConnSQL {

    DecimalFormat df = new DecimalFormat("0.00");
    double data[][] = {
        {4.84, 0.00, 0.00, 0.00, 0.00, 0.00,},
        {1.10, 0.00, 0.00, 3.63, 0.00, 0.00,},
        {7.04, 0.00, 0.00, 1.87, 0.00, 0.00,},
        {6.60, 0.00, 0.00, 0.00, 3.52, 0.00,},
        {4.29, 0.00, 0.00, 3.63, 0.00, 0.00,},
        {2.97, 0.00, 0.00, 1.87, 0.00, 0.00,},
        {7.04, 0.00, 0.00, 1.87, 1.87, 0.00,},
        {10.34, 0.00, 0.00, 1.87, 0.00, 0.00,},
        {10.00, 0.00, 1.98, 3.63, 0.00, 0.00,},//
        {4.07, 0.00, 0.00, 0.66, 0.00, 0.00,},
        {3.41, 0.00, 0.00, 3.63, 3.96, 0.00,},
        {1.10, 0.00, 0.00, 3.63, 0.00, 0.00,},
        {15.84, 0.00, 0.00, 0.00, 0.00, 0.00,},
        {0.00, 0.00, 0.00, 1.87, 0.44, 0.00,},
        {7.48, 0.00, 1.98, 3.63, 0.00, 0.00,},
        {1.32, 0.00, 0.00, 0.00, 0.00, 0.00,},
        {0.00, 0.00, 0.00, 3.63, 0.00, 0.00,},
        {9.79, 0.00, 0.00, 3.63, 3.52, 0.00,},
        {0.00, 0.00, 0.00, 0.00, 1.87, 0.00,},
        {10.34, 0.00, 1.54, 3.63, 0.00, 0.00,},
        {4.73, 8.80, 0.00, 0.00, 0.00, 0.00,},
        {0.00, 0.00, 0.00, 3.74, 0.00, 0.00,},
        {6.71, 0.00, 1.54, 0.00, 2.42, 0.00,},
        {3.96, 0.00, 2.31, 0.66, 3.52, 0.00,},
        {0.00, 0.00, 0.00, 1.87, 0.00, 0.00,},
        {9.46, 2.31, 0.77, 0.00, 0.00, 0.00,},
        {1.10, 0.00, 0.00, 1.54, 0.44, 0.00,},
        {8.47, 0.00, 0.22, 1.65, 0.00, 0.00,},
        {0.00, 0.00, 0.00, 1.54, 0.00, 0.00,},
        {1.10, 0.00, 0.00, 1.65, 0.00, 0.00,},
        {6.49, 2.31, 0.00, 1.65, 3.96, 0.00,},
        {0.00, 2.42, 0.00, 0.00, 0.00, 0.00,},
        {15.07, 0.00, 0.00, 0.00, 0.99, 0.00,},
        {0.00, 0.00, 1.98, 1.54, 0.00, 0.00,},
        {1.10, 9.79, 0.00, 1.65, 0.00, 0.00,},
        {7.26, 0.00, 0.00, 0.00, 0.00, 0.00,},
        {0.00, 0.00, 0.00, 0.00, 3.52, 0.00,},
        {3.63, 0.00, 2.09, 0.00, 0.00, 0.00,},
        {3.19, 0.00, 0.00, 0.00, 0.44, 0.00,},
        {0.99, 0.00, 0.00, 0.00, 0.00, 0.00,},
        {7.92, 2.97, 1.32, 1.54, 0.00, 0.00,},
        {10.23, 0.00, 0.00, 1.54, 4.62, 0.00,},
        {2.97, 0.00, 0.00, 0.00, 1.65, 0.00,},
        {0.00, 0.00, 0.00, 0.00, 2.75, 0.00,},
        {9.35, 0.00, 0.00, 0.00, 0.00, 0.00,},
        {4.51, 0.00, 0.00, 1.54, 0.00, 0.00,},
        {4.51, 0.00, 0.00, 1.54, 0.00, 0.00,},
        {4.51, 0.00, 0.00, 8.80, 0.00, 0.00,},
        {0.00, 0.00, 2.09, 0.00, 0.00, 0.00,},
        {1.10, 0.00, 0.00, 3.19, 0.00, 0.00,},
        {2.75, 2.42, 0.00, 0.66, 0.00, 0.00,},
        {1.10, 0.00, 0.00, 0.00, 0.00, 0.00,},
        {6.16, 0.00, 1.32, 0.00, 2.42, 0.00,},
        {14.19, 0.00, 0.00, 0.00, 1.21, 0.00,},
        {0.00, 0.00, 0.00, 0.00, 3.52, 0.00,},
        {2.86, 2.42, 0.00, 0.00, 1.21, 0.00,},
        {0.00, 0.00, 0.00, 0.00, 1.21, 0.00,},
        {1.10, 0.00, 2.09, 0.00, 0.00, 0.00,},
        {13.97, 0.00, 0.00, 0.00, 1.21, 0.00,},
        {1.76, 2.42, 0.00, 0.00, 0.00, 0.00,},
        {1.10, 0.00, 0.00, 0.00, 1.21, 0.00,},
        {0.00, 0.00, 0.00, 0.00, 0.00, 0.00,},};

    public double[][] readData() {
        return data;
    }

    public void printData() {
        int i, j;

        System.out.println(62);
        for (i = 0; i < 62; i++) {
            System.out.print(i);
            System.out.print('\t');
            for (j = 0; j < 6; j++) {
                System.out.print(df.format(data[i][j]) + "\t");
            }
            System.out.println();
        }
    }
    /*
     public int y;
     public int week;
     private int i, j;
     Connection conn = null;
     PurchaseData[][] purchaseData;
     DecimalFormat df;

     public ConnSQL(int y) {
     this.y = y;
     df = new DecimalFormat("0.00");

     try {
     Class.forName("com.mysql.jdbc.Driver").newInstance();
     String url = "jdbc:mysql://localhost/purchaseinfo1";
     conn = DriverManager.getConnection(url, "root1", "root1");
     } catch (ClassNotFoundException ex) {
     System.err.println(ex.getMessage());
     } catch (IllegalAccessException ex) {
     System.err.println(ex.getMessage());
     } catch (InstantiationException ex) {
     System.err.println(ex.getMessage());
     } catch (SQLException ex) {
     System.err.println(ex.getMessage());
     }
     }

     public PurchaseData[][] readData() {
     i = 0;
     j = 0;
     String query = "SELECT value FROM data";
     purchaseData = new PurchaseData[200][y];



     try {
     Statement st = conn.createStatement();
     ResultSet rs = st.executeQuery(query);

     while (rs.next()) {
     double f = rs.getDouble("value");
     purchaseData[i][j] = new PurchaseData();
     purchaseData[i][j].value = f;

     if (j == y - 1) {
     j = 0;
     i = i + 1;
     } else {
     j = j + 1;
     }
     }
     } catch (SQLException ex) {
     System.err.println(ex.getMessage());
     }
     week = i;
     return purchaseData;
     }

     public void printData() {
     int i, j;

     System.out.println(week);
     System.out.println("{");
     for (i = 0; i < week; i++) {
     System.out.print("{");
     for (j = 0; j < y; j++) {
     System.out.print(df.format(purchaseData[i][j].value) + ",\t");
     }
     System.out.println("},");
     }
     System.out.println("}");
     }
     */
}
