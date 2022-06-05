package dao.custom.impl;

//import dao.CrudUtil;
import dao.custom.CustomerDAO;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean add(Customer c) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(c);

        transaction.commit();
        session.close();
        return true;
//        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)", c.getCustomerID(),
//                c.getCustomerTitle(), c.getCustomerName(), c.getCustomerAddress(), c.getCity(), c.getProvince(), c.getPostalCode());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, s);

        session.delete(customer);

        transaction.commit();
        session.close();
        return true;
        //        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE CustomerID='" + s + "'");
    }

    @Override
    public boolean update(entity.Customer customer) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public ArrayList<entity.Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Customer> list = null;

        Query customers = session.createQuery("from Customer");
        list = customers.list();

        transaction.commit();

        session.close();
        return (ArrayList<Customer>) list;
        //        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
//        ArrayList<entity.Customer> customers = new ArrayList<>();
//
//        while (rst.next()) {
//            customers.add(new Customer(
//                            rst.getString(1),
//                            rst.getString(2),
//                            rst.getString(3),
//                            rst.getString(4),
//                            rst.getString(5),
//                            rst.getString(6),
//                            rst.getString(7)
//                    )
//            );
//        }
//        return customers;
    }

    @Override
    public String getLastCustId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT CustomerID FROM Customer ORDER BY CustomerID DESC ";
        List<String> rst = session.createQuery(hql).list();
        transaction.commit();
        session.close();
        return String.valueOf(rst);
//        ResultSet rs = CrudUtil.executeQuery("SELECT CustomerID FROM Customer ORDER BY CustomerID DESC LIMIT 1");
//        if (rs.next()) {
//            return rs.getString("CustomerID");
//        }
//        return null;}
    }

    @Override
    public List<String> getCustIds() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT CustomerID FROM Customer";
        List<String> rst = session.createQuery(hql).list();
        List<String> ids = new ArrayList<>();


            transaction.commit();
            session.close();
            return rst;
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
//        List<String> ids = new ArrayList<>();
//        while (rst.next()) {
//            ids.add(
//                    rst.getString(1)
//            );
//        }
//        return ids;
    }
}


