package MyPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {
    private Connection myConnnection = null;
    private Statement myStatement = null;
    private PreparedStatement myPreparedStatement = null;
    private ResultSet myResultSet = null;

    public void runQueries() throws Exception {
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.jdbc.Driver");
            // Connection
            myConnnection = DriverManager
                    .getConnection("jdbc:mysql://localhost/classicmodels?" + "user=user1&password=pass1");

            // Statement for queries
            myStatement = myConnnection.createStatement();

            // Result set - the result of the SQL query
            // executeQuery - for DQL
            PreparedStatement stat = myConnnection.prepareStatement("INSERT INTO payments (checkNumber, customerNumber, paymentDate, amount) VALUES (?,?,?,?)");
            stat.setString(1, Payments.checkNumber);
            stat.setInt(2, Payments.customerNumber);
            stat.setDate(3, Payments.paymentDate);
            stat.setFloat(4, Payments.amount);
            stat.executeUpdate();

            //myResultSet = myStatement.executeUpdate("INSERT INTO payments (checkNumber, customerNumber, paymentDate, amount) VALUES (?,?,?,?)");
            printResults(myResultSet);
            System.out.println("-------------------------------");

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private void printMetaData(ResultSet resultSet) throws SQLException {
        // Metadata from the database
        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " "
                    + resultSet.getMetaData().getColumnName(i));
        }
    }

    private String[] getColumnNames(ResultSet resultSet) throws SQLException {
        // Metadata from the database
        String[] columns = new String[resultSet.getMetaData().getColumnCount()];

        for (int i = 0; i < columns.length; i++) {
            columns[i] = resultSet.getMetaData().getColumnName(i + 1);
        }
        return columns;
    }

    private String[] getColumnTypes(ResultSet resultSet) throws SQLException {
        // Metadata from the database
        String[] types = new String[resultSet.getMetaData().getColumnCount()];

        for (int i = 0; i < types.length; i++) {
            types[i] = resultSet.getMetaData().getColumnTypeName(i + 1);
        }
        return types;
    }

    private String getData(ResultSet resultSet, String columnName,
                           String columnType) {
        String res;
        try {
            switch (columnType) {
                case "INT":
                case "BIGINT":
                    res = "" + resultSet.getInt(columnName);
                    break;
                case "DATE":
                    res = "" + resultSet.getDate(columnName);
                    break;
                case "CHAR":
                case "VARCHAR":
                    res = "" + resultSet.getString(columnName);
                    break;
                case "FLOAT":
                    res = "" + resultSet.getFloat(columnName);
                    break;
                case "DOUBLE":
                    res = "" + resultSet.getDouble(columnName);
                    break;
                case "BOOLEAN":
                    res = "" + resultSet.getBoolean(columnName);
                    break;
                default:
                    res = "";
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void printResults(ResultSet resultSet) throws SQLException {
        String[] columns = getColumnNames(resultSet);
        String[] types = getColumnTypes(resultSet);
        int cnt = 0;
        for (int i = 0; i < columns.length; i++) {
            System.out.print(columns[i] + " | ");
            cnt += columns[i].length();
        }
        System.out.println("");
        for (int i = 0; i < cnt + 3 * columns.length - 1; i++)
            System.out.print("-");
        System.out.println("");

        while (resultSet.next()) {
            for (int i = 0; i < columns.length; i++) {
                System.out.print(getData(resultSet, columns[i], types[i])
                        + " | ");
            }
            System.out.println("");
        }
    }



    // closing
    private void close() {
        try {
            if (myResultSet != null) {
                myResultSet.close();
            }
            if (myStatement != null) {
                myStatement.close();
            }
            if (myPreparedStatement != null) {
                myPreparedStatement.close();
            }
            if (myConnnection != null) {
                myConnnection.close();
            }
        } catch (Exception e) {
        }
    }
}
