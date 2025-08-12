package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.StockDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.StockDto;
import com.lankaice.project.entity.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockDAOImpl implements StockDAO {
    @Override
    public List<Stock> getAll() throws SQLException, ClassNotFoundException {
        List<Stock> list = new ArrayList<>();
        String sql = "SELECT * FROM Stock ORDER BY stock_date DESC, stock_time DESC";
        ResultSet rs = SQLUtil.execute(sql);

        while (rs.next()) {
            list.add(new Stock(
                    rs.getInt("stock_id"),
                    rs.getString("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("stock_quantity"),
                    rs.getDate("stock_date").toString(),
                    rs.getTime("stock_time").toString()
            ));
        }

        return list;
    }

    @Override
    public boolean save(Stock stock) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Stock (product_id, product_name, stock_quantity, stock_date, stock_time) VALUES (?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, stock.getProductId(), stock.getProductName(), stock.getQty(), stock.getDate(), stock.getTime());    }

    @Override
    public boolean update(Stock stock) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Stock SET product_id = ?, product_name = ?, stock_quantity = ?, stock_date = ?,stock_time  = ? WHERE stock_id = ?";
        return SQLUtil.execute(sql, stock.getProductId(), stock.getProductName(), stock.getQty(), stock.getDate(), stock.getTime(), stock.getStockId());
    }

    @Override
    public boolean delete(String stockId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Stock WHERE stock_id = ?";
        return SQLUtil.execute(sql, stockId);
    }

    @Override
    public Optional<Stock> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public boolean reduceQty(String productId, int quantity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "UPDATE Stock SET stock_quantity = stock_quantity - ? WHERE product_id = ?",
                quantity,productId
        );
    }

    @Override
    public int currentStock() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COALESCE(SUM(stock_quantity), 0) AS total FROM Stock";
        ResultSet rs = SQLUtil.execute(sql);
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }

    @Override
    public int todayAddedStock() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COALESCE(SUM(stock_quantity), 0) AS total FROM Stock WHERE DATE(stock_date) = CURDATE()";
        ResultSet rs = SQLUtil.execute(sql);
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }

    @Override
    public List<Stock> getStockByMonthAndYear(String year, String monthName) throws SQLException, ClassNotFoundException {
        List<Stock> list = new ArrayList<>();
        int month = Month.valueOf(monthName.toUpperCase()).getValue();

        String sql = "SELECT * FROM Stock WHERE YEAR(stock_date) = ? AND MONTH(stock_date) = ? ORDER BY stock_date DESC";
        ResultSet rs = SQLUtil.execute(sql, Integer.parseInt(year), month);

        while (rs.next()) {
            list.add(new Stock(
                    rs.getInt("stock_id"),
                    rs.getString("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("stock_quantity"),
                    rs.getDate("stock_date").toString(),
                    rs.getTime("stock_time").toString()
            ));
        }

        return list;    }

    @Override
    public List<Stock> searchStock(String text) throws SQLException, ClassNotFoundException {
        List<Stock> list = new ArrayList<>();
        String sql = "SELECT * FROM Stock WHERE product_name LIKE ? OR product_id LIKE ? OR stock_id LIKE ?";
        String pattern = "%" + text + "%";
        ResultSet rs = SQLUtil.execute(sql, pattern, pattern, pattern);

        while (rs.next()) {
            list.add(new Stock(
                    rs.getInt("stock_id"),
                    rs.getString("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("stock_quantity"),
                    rs.getDate("stock_date").toString(),
                    rs.getTime("stock_time").toString()
            ));
        }

        return list;
    }

    @Override
    public Stock getStockById(int stockId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Stock WHERE stock_id = ?";
        ResultSet rs = SQLUtil.execute(sql, stockId);
        if (rs.next()) {
            return new Stock(
                    rs.getInt("stock_id"),
                    rs.getString("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("stock_quantity"),
                    rs.getString("stock_date"),
                    rs.getString("stock_time")
            );
        }
        return null;
    }
}
