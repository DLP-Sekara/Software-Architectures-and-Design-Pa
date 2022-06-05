package dao.custom.impl;

//import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item it) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(it);

        transaction.commit();
        session.close();
        return true;
//        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?,?)",
//        it.getItemCode(), it.getDescription(), it.getPackSize(), it.getUnitPrice(), it.getQtyOnHand(), it.getDiscount());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class, s);

        session.delete(item);

        transaction.commit();
        session.close();
        return true;
//        return CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode='" + s + "'");
    }

    @Override
    public boolean update(Item items) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean search(String s) throws SQLException, ClassNotFoundException {
        //return CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?", s).next();
        return true;
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<Item> list = null;

        Query item = session.createQuery("from Item");
        list = item.list();

        transaction.commit();
        session.close();
        return (ArrayList<Item>) list;
//        ArrayList<Item> allItems = new ArrayList();
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
//        while (rst.next()) {
//            allItems.add(new Item(
//                    rst.getString("itemCode"),
//                    rst.getString("description"),
//                    rst.getString("packSize"),
//                    rst.getDouble("unitPrice"),
//                    rst.getInt("qtyOnHand"),
//                    rst.getDouble("discount")));
//        }
//        return allItems;
    }

    public boolean checkItems(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Item WHERE ItemCode = : Item_code";
        Query query = session.createQuery(hql);
        query.setParameter("Item_code", code);


        transaction.commit();
        session.close();
        return true;
//        return CrudUtil.executeQuery("FROM Item WHERE ItemCode = : code);
//        return CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?", code).next();
    }

    public Item getItem(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Item WHERE Description = : code";
        Query item = session.createQuery(hql);
        item.setParameter("code", s);
        transaction.commit();
        session.close();
        return (Item) item;

//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE Description='" + s + "'");
//        if (rst.next()) {
//            return new Item(
//                    rst.getString(1),
//                    rst.getString(2),
//                    rst.getString(3),
//                    rst.getDouble(4),
//                    rst.getInt(5),
//                    rst.getDouble(6)
//            );
//        } else {
//            return null;
//        }
    }

    public boolean updateItem(Item i, String ItemId) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "UPDATE Item SET ItemCode = :Item_Code ,Description= : Item_Description,PackSize=:Item_pack,UnitPrice=:unit_price,QtyOnHand=:qty_onHand,Discount=:discount WHERE ItemCode = :item_id";
        Query query = session.createQuery(hql);
        query.setParameter("item_id", ItemId);
        query.setParameter("Item_Code", i.getItemCode());
        query.setParameter("Item_Description",i.getDescription());
        query.setParameter("Item_pack",i.getPackSize());
        query.setParameter("unit_price",i.getUnitPrice());
        query.setParameter("qty_onHand", i.getQtyOnHand());
        query.setParameter("discount",i.getDiscount());

        transaction.commit();
        session.close();
        return true;

//        return CrudUtil.executeUpdate("UPDATE Item SET ItemCode=?,Description=?, PackSize=?,UnitPrice=?,QtyOnHand=?,Discount=? Where ItemCode='" + ItemId + "'",
//                i.getItemCode(), i.getDescription(), i.getPackSize(), i.getUnitPrice(), i.getQtyOnHand(), i.getDiscount());
    }

    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT ItemCode FROM Item";
        List<String> rst = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return rst;
    }

    public boolean updateQty(String itemCode, int orderQty) throws SQLException, ClassNotFoundException {
//        return CrudUtil.executeUpdate("UPDATE Item SET qtyOnHand=(QtyOnHand-'" + orderQty + "')WHERE Description='" + itemCode + "'");
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String sql = "UPDATE Item SET qtyOnHand=(QtyOnHand-'" + orderQty + "')WHERE Description='" + itemCode + "'";
        NativeQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.addEntity(Item.class);
        transaction.commit();
        session.close();
        return true;

    }

}

