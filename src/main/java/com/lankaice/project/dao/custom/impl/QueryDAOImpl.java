package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.QueryDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.entity.CustomOrder;
import com.lankaice.project.entity.CustomOrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomOrder> findFullOrderDataByCustomerId(String customerId) throws SQLException, ClassNotFoundException {
        ResultSet rs = SQLUtil.execute(""); // quary
        Map<String,CustomOrder> orderMap = new HashMap<>();
        while (rs.next()){
         String orderId = rs.getString("order_id");
         CustomOrder customOrder = orderMap.get(orderId);
         if(customOrder == null){
             customOrder = new CustomOrder(
                  orderId,
                 rs.getString("customer_id"),
                 rs.getDate("order_date").toString(),
                 rs.getTime("order_time").toString(),
                 rs.getDouble("total_amount"),
                 rs.getString("name"),
                 rs.getString("nic"),
                 rs.getString("email"),
                 rs.getString("contact"),
                 new ArrayList<>()
            );
         orderMap.put(orderId,customOrder);
         }
         customOrder.orderDetailsList.add(
                 new CustomOrderDetails(
                         rs.getString("product_id"),
                         rs.getInt("quantity"),
                         rs.getDouble("unit_price"),
                         rs.getDouble("discount")
                 )
         );
        }
        return new ArrayList<>(orderMap.values());
    }
}
