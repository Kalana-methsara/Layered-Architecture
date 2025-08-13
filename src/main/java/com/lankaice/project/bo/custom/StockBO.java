package com.lankaice.project.bo.custom;

import com.lankaice.project.bo.SuperBO;
import com.lankaice.project.dto.StockDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface StockBO extends SuperBO {
    List<StockDto> getAllStocks() throws SQLException, ClassNotFoundException;

    void saveStock(String productId, String productName, int quantity, LocalDate date, LocalTime time) throws SQLException, ClassNotFoundException;

    boolean updateStock(StockDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException;

    StockDto findStockById(int stockId) throws SQLException, ClassNotFoundException;

    boolean reduceQty(String productId, int quantity) throws SQLException, ClassNotFoundException;

    int currentStock() throws SQLException, ClassNotFoundException;

    int todayAddedStock() throws SQLException, ClassNotFoundException;

    List<StockDto> getStockByMonthAndYear(String year, String monthName) throws SQLException, ClassNotFoundException;

    List<StockDto> searchStock(String text) throws SQLException, ClassNotFoundException;


}
