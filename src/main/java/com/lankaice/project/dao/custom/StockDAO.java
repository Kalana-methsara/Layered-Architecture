package com.lankaice.project.dao.custom;

import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.dto.OrderDetailsDto;
import com.lankaice.project.dto.StockDto;
import com.lankaice.project.entity.Stock;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface StockDAO extends CrudDAO<Stock> {
    boolean reduceQty(String productId,int quantity) throws SQLException, ClassNotFoundException;

    int currentStock() throws SQLException, ClassNotFoundException;

    int todayAddedStock() throws SQLException, ClassNotFoundException;

    List<Stock> getStockByMonthAndYear(String year, String monthName) throws SQLException, ClassNotFoundException;

    List<Stock> searchStock(String text) throws SQLException, ClassNotFoundException;

    Stock getStockById(int stockId) throws SQLException, ClassNotFoundException;
}
