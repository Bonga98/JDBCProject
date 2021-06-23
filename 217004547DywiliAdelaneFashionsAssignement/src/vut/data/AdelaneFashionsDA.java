package vut.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author
 */
public class AdelaneFashionsDA {

    private static Connection connectionObj;
    private static ArrayList<AdelaneFashionsPD> arAdelaneFashions = new ArrayList<>();
    private static PreparedStatement preparedStatementObj;
    private static ResultSet resultsetObj;
    private static Statement st;

    public static void initConnection() throws DataStorageException {
        final String USERNAME = "root";
        final String PASSWORD = "";
        final String URL = "jdbc:mysql://localhost/AdelaneFashionsDB";
        final String DRIVER = "com.mysql.jdbc.Driver";
        try {

            Class.forName(DRIVER);
            connectionObj = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new DataStorageException("Database driver is missing\n" + e.getMessage());
        } catch (SQLException err) {
            throw new DataStorageException("Connection failed" + err.getMessage());
        }
    }
    public static void closeConnection() throws DataStorageException{
        try {
            if(connectionObj!= null){
                connectionObj.close();
            
            }
        } catch (Exception e) {
            throw new  DataStorageException("Failed to terminate the connection" + e.getMessage());
        }
        
    }

    public static ArrayList<AdelaneFashionsPD> returnAllData() throws NotFoundException {
        arAdelaneFashions.clear();
        String qry = "SELECT * FROM tblAdelaneFashions";
        try {
            preparedStatementObj = connectionObj.prepareStatement(qry);

            resultsetObj = preparedStatementObj.executeQuery();
            while (resultsetObj.next()) {

                arAdelaneFashions.add(new AdelaneFashionsPD(resultsetObj.getString(1), resultsetObj.getString(2), AdelaneFashionsPD.Province.valueOf(resultsetObj.getString(3)), AdelaneFashionsPD.Ownership.valueOf(resultsetObj.getString(4)), resultsetObj.getDouble(5)));
            }
        } catch (SQLException e) {
            throw new NotFoundException(e.getMessage());
        }
        return arAdelaneFashions;
    }

    public static double calculateTotalSales() throws NotFoundException {
        int totSales = 0;
        String qry = "SELECT Sale FROM tblAdelaneFashions";
        try {
            preparedStatementObj = connectionObj.prepareStatement(qry);

            resultsetObj = preparedStatementObj.executeQuery();

            while (resultsetObj.next()) {

                totSales += resultsetObj.getDouble(1);
            }
        } catch (SQLException e) {
            throw new NotFoundException(e.getMessage());
        }
        return totSales;
    }

    public static double calculateTotalCompanyOwnedSales() throws NotFoundException {
        int totSales = 0;
        String qry = "SELECT Sale FROM tblAdelaneFashions WHERE Ownership = 'C'";
        try {
            preparedStatementObj = connectionObj.prepareStatement(qry);

            resultsetObj = preparedStatementObj.executeQuery();

            while (resultsetObj.next()) {

                totSales += resultsetObj.getDouble(1);
            }
        } catch (SQLException e) {
            throw new NotFoundException(e.getMessage());
        }
        return totSales;
    }

    public static double calculateTotalFranchiseeSales() throws NotFoundException {
        int totSales = 0;
        String qry = "SELECT Sale FROM tblAdelaneFashions WHERE Ownership = 'F'";
        try {
            preparedStatementObj = connectionObj.prepareStatement(qry);

            resultsetObj = preparedStatementObj.executeQuery();

            while (resultsetObj.next()) {

                totSales += resultsetObj.getDouble(1);
            }
        } catch (SQLException e) {
            throw new NotFoundException(e.getMessage());
        }
        return totSales;
    }

    public static void addStore(AdelaneFashionsPD boutique) throws DuplicateException {
        String qry = "INSERT INTO tblAdelaneFashions  VALUES (?,?,?,?,?)";
        try {
            preparedStatementObj = connectionObj.prepareStatement(qry);
            preparedStatementObj.setString(1, boutique.getStoreNumber());
            preparedStatementObj.setString(2, boutique.getCity());
            preparedStatementObj.setString(3, boutique.getProvince().toString());
            preparedStatementObj.setString(4, boutique.getOwnership().toString());
            preparedStatementObj.setDouble(5, boutique.getSales());

            preparedStatementObj.executeUpdate();
        } catch (SQLException e) {
            throw new DuplicateException(boutique.getStoreNumber() + " Not added\n" + e.getMessage());
        }
    }

    public static void updatePrice(double perc) throws NotFoundException {

        String qry = "UPDATE tblAdelaneFashions  SET Sale= Sale + (Sale * ('" + perc / 100 + "')) WHERE Province LIKE 'KZN'  AND Ownership LIKE 'C'";
        try {
            st = connectionObj.createStatement();
            st.executeUpdate(qry);

        } catch (SQLException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public static void removeStore(String store) throws NotFoundException {
        String qry = "DELETE FROM tblAdelaneFashions  WHERE Store_Number =?";
        try {
            preparedStatementObj= connectionObj.prepareStatement(qry);
            preparedStatementObj.setString(1, store);
            preparedStatementObj.executeUpdate();

        } catch (SQLException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public static ArrayList<AdelaneFashionsPD> returnProvince(String province) throws NotFoundException {
        String qry = "SELECT * FROM tblAdelaneFashions WHERE Province = ?";
        arAdelaneFashions.clear();
        try {
            preparedStatementObj = connectionObj.prepareStatement(qry);

            preparedStatementObj.setString(1, province);
            resultsetObj = preparedStatementObj.executeQuery();
            while (resultsetObj.next()) {
                arAdelaneFashions.add(new AdelaneFashionsPD(resultsetObj.getString(1), resultsetObj.getString(2), AdelaneFashionsPD.Province.valueOf(resultsetObj.getString(3)), AdelaneFashionsPD.Ownership.valueOf(resultsetObj.getString(4)), resultsetObj.getDouble(5)));
            }
        } catch (SQLException e) {

            throw new NotFoundException("No record(s) retrived. \n" + e.getMessage());

        }
        return arAdelaneFashions;
    }

}
