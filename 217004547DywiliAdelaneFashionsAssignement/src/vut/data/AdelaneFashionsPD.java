package vut.data;

import java.util.ArrayList;

/**
 *
 * @author
 */
public class AdelaneFashionsPD {

    public enum Province {GP, MP, FS, L, EC, WC, NW, KZN, NC}

    public enum Ownership {F, C}

    private String storeNumber, city;
    private Province province;
    private Ownership ownership;
    private double sales;

    public AdelaneFashionsPD() {
        storeNumber = "000";
        province = Province.GP;
        ownership = Ownership.C;
        city = "Vanderbijlpark";
        sales = 0.00;
    }

    public AdelaneFashionsPD(String storeNumber, String city, Province province, Ownership ownership, double sales) {
        setStoreNumber(storeNumber);
        setProvince(province);
        setOwnership(ownership);
        setSales(sales);
        setCity(city);
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Ownership getOwnership() {
        return ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public String getCity() {
        
        return city;
    }

    public void setCity(String city) {      
        if (city.length()>4)            
            this.city = city;
      else  throw new IllegalArgumentException("City name too shot");       
    }

    @Override
    public String toString() {
        return String.format("%-4s\t%-15s\t%-10s\t%-10s\tR %-7.2f\n", storeNumber, city, province, ownership, sales);
    }

    public void initConnection() throws DataStorageException {
        AdelaneFashionsDA.initConnection();
    }
    public void closeConnection() throws DataStorageException{
    AdelaneFashionsDA.closeConnection();
    }

    public ArrayList<AdelaneFashionsPD> returnAllData() throws NotFoundException {
        return AdelaneFashionsDA.returnAllData();
    }

    public ArrayList<AdelaneFashionsPD> returnProvince(String province) throws NotFoundException {
        return AdelaneFashionsDA.returnProvince(province);
    }

    public double calculateTotalFranchiseeSales() throws NotFoundException {
        return AdelaneFashionsDA.calculateTotalFranchiseeSales();
    }

    public double calculateTotalCompanyOwnedSales() throws NotFoundException {
        return AdelaneFashionsDA.calculateTotalCompanyOwnedSales();
    }

    public  double calculateTotalSales() throws NotFoundException {
        return AdelaneFashionsDA.calculateTotalSales();
    }

    public void addStore(AdelaneFashionsPD boutique) throws DuplicateException {
        AdelaneFashionsDA.addStore(boutique);
    }

    public void removeStore(String store) throws NotFoundException {
        AdelaneFashionsDA.removeStore(store);
    }

    public void updatePrice(double perc) throws NotFoundException {
        AdelaneFashionsDA.updatePrice(perc);
    }

}
