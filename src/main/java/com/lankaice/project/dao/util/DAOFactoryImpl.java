package com.lankaice.project.dao.util;

import com.lankaice.project.dao.DAOFactory;
import com.lankaice.project.dao.SuperDAO;
import com.lankaice.project.dao.custom.impl.*;

public class DAOFactoryImpl implements DAOFactory {
    private static DAOFactoryImpl daoFactory;

    private DAOFactoryImpl() {
        
    }

    public static DAOFactoryImpl getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactoryImpl();
        }
        return daoFactory;
    }
  /*  public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER: return new CustomerDAOImpl();
            case ORDERS:  return new OrdersDAOImpl();
            case ORDER_DETAIL:  return new OrderDetailsDAOImpl();
            case PRODUCT:  return new ProductDAOImpl();
            case QUERY: return new QueryDAOImpl();
            default: return null; //default -> throw new IllegalArgumentException("Invalid DAO type: " + daoType);


        }
    }*/

    @SuppressWarnings("unchecked")
    public <T extends SuperDAO> T getDAO(DAOType daoType) {
        return switch (daoType) {
            case ATTENDANCE -> (T) new AttendanceDAOImpl();
            case BILLING ->  (T) new BillingDAOImpl();
            case BOOKING ->   (T) new BookingDAOImpl();
            case CUSTOMER -> (T) new CustomerDAOImpl();
            case DELIVERY -> (T) new DeliveryDAOImpl();
            case EMPLOYEE ->  (T) new EmployeeDAOImpl();
            case INVENTORY_CART ->  (T) new InventoryCartDAOImpl();
            case ORDER_DETAIL -> (T) new OrderDetailsDAOImpl();
            case ORDER_PAYMENT -> (T) new OrderPaymentDAOImpl();
            case ORDERS -> (T) new OrdersDAOImpl();
            case PENDING_ORDERS ->  (T) new PendingOrderDAOImpl();
            case PRODUCT -> (T) new ProductDAOImpl();
            case RAW_MATERIAL ->  (T) new RawMaterialDAOImpl();
            case SALARY ->  (T) new SalaryDAOImpl();
            case SALARY_PAYMENT ->   (T) new SalaryPaymentDAOImpl();
            case SMS_EMAIL -> (T) new SMSEmailDAOImpl();
            case STOCK -> (T) new StockDAOImpl();
            case SUPPLIER -> (T) new SupplierDAOImpl();
            case TRANSPORT ->  (T) new TransportDAOImpl();
            case USER -> (T) new UserDAOImpl();
            case VEHICLE -> (T) new VehicleDAOImpl();
            case QUERY -> (T) new QueryDAOImpl();
        };
    }
}
