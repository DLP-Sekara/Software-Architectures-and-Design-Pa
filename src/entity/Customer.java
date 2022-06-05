package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Customer {
    @Id
   private String CustomerID;
    private String CustomerTitle;
    private String  CustomerName;
    private String CustomerAddress;
    private String  City;
    private String Province;
    private String PostalCode;
    @OneToMany(mappedBy = "CustomerID")
    private List<Order> orders;

    public Customer(List<Order> orders) {
        this.orders = orders;
    }

    public Customer() {
    }

    public Customer(String customerID, String customerTitle, String customerName, String customerAddress, String city, String province, String postalCode) {
        CustomerID = customerID;
        CustomerTitle = customerTitle;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
        City = city;
        Province = province;
        PostalCode = postalCode;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getCustomerTitle() {
        return CustomerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        CustomerTitle = customerTitle;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerID='" + CustomerID + '\'' +
                ", CustomerTitle='" + CustomerTitle + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                ", City='" + City + '\'' +
                ", Province='" + Province + '\'' +
                ", PostalCode='" + PostalCode + '\'' +
                ", orders=" + orders +
                '}';
    }
}
