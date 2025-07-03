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
            case CUSTOMER -> (T) new CustomerDAOImpl();
            case ORDERS -> (T) new OrdersDAOImpl();
            case ORDER_DETAIL -> (T) new OrderDetailsDAOImpl();
            case PRODUCT -> (T) new ProductDAOImpl();
            case QUERY -> (T) new QueryDAOImpl();
        };
    }
}
