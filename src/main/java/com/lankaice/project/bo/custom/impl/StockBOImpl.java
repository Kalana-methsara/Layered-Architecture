package com.lankaice.project.bo.custom.impl;

import com.lankaice.project.bo.custom.StockBO;
import com.lankaice.project.bo.util.EntityDTOConverter;
import com.lankaice.project.dao.custom.StockDAO;
import com.lankaice.project.dao.util.DAOFactoryImpl;
import com.lankaice.project.dao.util.DAOType;
import com.lankaice.project.dto.StockDto;
import com.lankaice.project.entity.Stock;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {

    private final StockDAO stockDAO = DAOFactoryImpl.getInstance().getDAO(DAOType.STOCK);
    private final EntityDTOConverter converter = new EntityDTOConverter();

    @Override
    public List<StockDto> getAllStocks() throws SQLException, ClassNotFoundException {
        List<Stock> stockList = stockDAO.getAll();
        List<StockDto> dtoList = new ArrayList<>();
        for (Stock stock : stockList) {
            dtoList.add(converter.getStockDto(stock));
        }
        return dtoList;
    }

    @Override
    public void saveStock(String productId, String productName, int quantity, LocalDate date, LocalTime time) throws SQLException, ClassNotFoundException{
        stockDAO.save(productId, productName, quantity, date, time);
    }

    @Override
    public boolean updateStock(StockDto dto) throws SQLException, ClassNotFoundException {
        return stockDAO.update(converter.getStock(dto));
    }

    @Override
    public boolean deleteStock(String stockId) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(stockId);
    }

    @Override
    public StockDto findStockById(int stockId) throws SQLException, ClassNotFoundException {
        Stock stock = stockDAO.getStockById(stockId);
        return stock != null ? converter.getStockDto(stock) : null;
    }

    @Override
    public boolean reduceQty(String productId, int quantity) throws SQLException, ClassNotFoundException {
        return stockDAO.reduceQty(productId, quantity);
    }

    @Override
    public int currentStock() throws SQLException, ClassNotFoundException {
        return stockDAO.currentStock();
    }

    @Override
    public int todayAddedStock() throws SQLException, ClassNotFoundException {
        return stockDAO.todayAddedStock();
    }

    @Override
    public List<StockDto> getStockByMonthAndYear(String year, String monthName) throws SQLException, ClassNotFoundException {
        List<Stock> stockList = stockDAO.getStockByMonthAndYear(year, monthName);
        List<StockDto> dtoList = new ArrayList<>();
        for (Stock stock : stockList) {
            dtoList.add(converter.getStockDto(stock));
        }
        return dtoList;
    }

    @Override
    public List<StockDto> searchStock(String text) throws SQLException, ClassNotFoundException {
        List<Stock> stockList = stockDAO.searchStock(text);
        List<StockDto> dtoList = new ArrayList<>();
        for (Stock stock : stockList) {
            dtoList.add(converter.getStockDto(stock));
        }
        return dtoList;
    }
}
