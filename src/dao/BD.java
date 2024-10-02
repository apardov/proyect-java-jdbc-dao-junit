package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {

    private static final String SQL_CREATE_TABLE_DENTIST = "DROP TABLE IF EXISTS DENTIST; " +
            "CREATE TABLE DENTIST" +
            "(" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "REGISTRATION INT NOT NULL," +
            "NAME VARCHAR(100) NOT NULL ," +
            "LASTNAME VARCHAR(100) NOT NULL" +
            ")";

    private static final String SQL_CREATE_TABLE_PATIENT = "DROP TABLE IF EXISTS PATIENT; " +
            "CREATE TABLE PATIENT" +
            "(" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NAME VARCHAR(100) NOT NULL ," +
            "LASTNAME VARCHAR(100) NOT NULL," +
            "CARDIDENTITY VARCHAR(100) NOT NULL," +
            "ADMISSIONOFDATE DATE NOT NULL," +
            ")";


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/DentalClinicDAO");
    }

    public static void createTables() {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement smt = conn.createStatement();
            smt.execute(SQL_CREATE_TABLE_DENTIST);
            smt.execute(SQL_CREATE_TABLE_PATIENT);

        } catch (Exception e) {
            e.fillInStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }
}
