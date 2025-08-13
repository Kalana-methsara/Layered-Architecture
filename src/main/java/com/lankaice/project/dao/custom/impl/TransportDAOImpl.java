package com.lankaice.project.dao.custom.impl;

import com.lankaice.project.dao.custom.TransportDAO;
import com.lankaice.project.dao.util.SQLUtil;
import com.lankaice.project.dto.OrderPaymentDto;
import com.lankaice.project.entity.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransportDAOImpl implements TransportDAO {
    @Override
    public List<Transport> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Transport ORDER BY transport_date DESC";
        ResultSet rs = SQLUtil.execute(sql);
        List<Transport> transportList = new ArrayList<>();

        while (rs.next()) {
            transportList.add(new Transport(
                    rs.getString("transport_id"),
                    rs.getString("product_id"),
                    rs.getString("vehicle_number"),
                    rs.getString("transport_date"),
                    rs.getTime("start_time") != null ? rs.getTime("start_time").toLocalTime() : null,
                    rs.getTime("end_time") != null ? rs.getTime("end_time").toLocalTime() : null,
                    rs.getInt("quantity"),
                    rs.getString("location"),
                    rs.getString("status")
            ));
        }
        return transportList;
    }

    @Override
    public boolean save(Transport transport) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Transport (transport_id, product_id, vehicle_number, transport_date, start_time, end_time, quantity, location, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return SQLUtil.execute(sql,
                transport.getTransportId(),
                transport.getProductId(),
                transport.getVehicleNumber(),
                transport.getTransportDate(),
                transport.getDeliveryBeginTime() != null ? Time.valueOf(transport.getDeliveryBeginTime()) : null,
                transport.getDeliveryEndTime() != null ? Time.valueOf(transport.getDeliveryEndTime()) : null,
                transport.getQuantity(),
                transport.getLocation(),
                transport.getStatus()
        );
    }

    @Override
    public boolean update(Transport transport) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Transport SET product_id = ?, vehicle_number = ?, transport_date = ?, start_time = ?, end_time = ?, quantity = ?, location = ?, status = ? WHERE transport_id = ?";

        return SQLUtil.execute(sql,
                transport.getProductId(),
                transport.getVehicleNumber(),
                transport.getTransportDate(),
                transport.getDeliveryBeginTime() != null ? Time.valueOf(transport.getDeliveryBeginTime()) : null,
                transport.getDeliveryEndTime() != null ? Time.valueOf(transport.getDeliveryEndTime()) : null,
                transport.getQuantity(),
                transport.getLocation(),
                transport.getStatus(),
                transport.getTransportId()
        );
    }

    @Override
    public boolean delete(String transportId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Transport WHERE transport_id = ?";
        return SQLUtil.execute(sql, transportId);    }

    @Override
    public Optional<Transport> findById(String id) throws SQLException, ClassNotFoundException {
        return Optional.empty();
    }

    @Override
    public int getTodayTransportTotal() throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(quantity) AS total_quantity FROM Transport WHERE transport_date = CURDATE()";
        ResultSet rs = SQLUtil.execute(sql);
        if (rs.next()) {
            return rs.getInt("total_quantity");
        }
        return 0;
    }

    @Override
    public List<Transport> getTransportByDate(String date) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Transport WHERE transport_date = ?";
        ResultSet rs = SQLUtil.execute(sql, date);

        List<Transport> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Transport(
                    rs.getString("transport_id"),
                    rs.getString("product_id"),
                    rs.getString("vehicle_number"),
                    rs.getString("transport_date"),
                    rs.getTime("start_time") != null ? rs.getTime("start_time").toLocalTime() : null,
                    rs.getTime("end_time") != null ? rs.getTime("end_time").toLocalTime() : null,
                    rs.getInt("quantity"),
                    rs.getString("location"),
                    rs.getString("status")
            ));
        }
        return list;    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        String prefix = "TP";
        ResultSet rs = SQLUtil.execute("SELECT transport_id FROM Transport ORDER BY transport_id DESC LIMIT 1");

        if (rs.next()) {
            String lastId = rs.getString("transport_id");
            int nextNum = Integer.parseInt(lastId.substring(2)) + 1;
            return String.format("%s%03d", prefix, nextNum);
        }
        return prefix + "001";
    }
}
