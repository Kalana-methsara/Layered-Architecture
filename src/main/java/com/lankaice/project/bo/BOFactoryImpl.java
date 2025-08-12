package com.lankaice.project.bo;

import com.lankaice.project.bo.custom.impl.CustomerBOImpl;
import com.lankaice.project.dao.custom.impl.*;

public class BOFactoryImpl implements BOFactory {
    private static BOFactoryImpl boFactory;

    private BOFactoryImpl() {
    }

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactoryImpl();
        }
        return boFactory;
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperBO> T getBO(BOType boType) {
        return switch (boType) {
            case ATTENDANCE -> (T) new AttendanceBOImpl();
            case BILLING -> (T) new BillingBOImpl();
            case BOOKING -> (T) new BookingBOImpl();
            case CUSTOMER -> (T) new CustomerBOImpl();
            case DELIVERY -> (T) new DeliveryBOImpl();
            case EMPLOYEE -> (T) new EmployeeBOImpl();
            case INVENTORY_CART -> (T) new InventoryCartBOImpl();
            case ORDER_DETAIL -> (T) new OrderDetailsBOImpl();
            case ORDER_PAYMENT -> (T) new OrderPaymentBOImpl();
            case ORDERS -> (T) new OrdersBOImpl();
            case PENDING_ORDERS -> (T) new PendingOrderBOImpl();
            case PRODUCT -> (T) new ProductBOImpl();
            case RAW_MATERIAL -> (T) new RawMaterialBOImpl();
            case SALARY -> (T) new SalaryBOImpl();
            case SALARY_PAYMENT -> (T) new SalaryPaymentBOImpl();
            case SMS_EMAIL -> (T) new SMSEmailBOImpl();
            case STOCK -> (T) new StockBOImpl();
            case SUPPLIER -> (T) new SupplierBOImpl();
            case TRANSPORT -> (T) new TransportBOImpl();
            case USER -> (T) new UserBOImpl();
            case VEHICLE -> (T) new VehicleBOImpl();

        };
    }
}
