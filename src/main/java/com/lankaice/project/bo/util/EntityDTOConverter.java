package com.lankaice.project.bo.util;

import com.lankaice.project.dto.*;
import com.lankaice.project.dto.tm.CartItemTM;
import com.lankaice.project.entity.*;

public class EntityDTOConverter {

    public Customer getCustomer(CustomerDto dto) {
        if (dto == null) return null;
        return new Customer(
                dto.getCustomerId(),
                dto.getName(),
                dto.getNic(),
                dto.getEmail(),
                dto.getContact(),
                dto.getAddress(),
                dto.getDescription()
        );
    }

    public CustomerDto getCustomerDto(Customer entity) {
        if (entity == null) return null;
        return new CustomerDto(
                entity.getCustomerId(),
                entity.getName(),
                entity.getNic(),
                entity.getEmail(),
                entity.getContact(),
                entity.getAddress(),
                entity.getDescription()
        );
    }

    public Attendance getAttendance(AttendanceDto dto) {
        if (dto == null) return null;
        return new Attendance(
                dto.getAttendanceId(),
                dto.getEmployeeId(),
                dto.getName(),
                dto.getDate(),
                dto.getShift(),
                dto.getStatus(),
                dto.getInTime(),
                dto.getOutTime()
        );
    }

    public AttendanceDto getAttendanceDto(Attendance attendance) {
        if (attendance == null) return null;
        return new AttendanceDto(
                attendance.getEmployeeId(),
                attendance.getName(),
                attendance.getDate(),
                attendance.getShift(),
                attendance.getStatus(),
                attendance.getInTime(),
                attendance.getOutTime()
        );
    }

    public BookingDto getBookingDto(Booking booking) {
        if (booking == null) return null;
        return new BookingDto(
                booking.getBookingId(),
                booking.getCustomerId(),
                booking.getProductId(),
                booking.getRequestDate(),
                booking.getRequestTime(),
                booking.getQuantity(),
                booking.getStatus()
        );
    }

    public Booking getBooking(BookingDto dto) {
        if (dto == null) return null;
        return new Booking(
                dto.getBookingId(),
                dto.getCustomerId(),
                dto.getProductId(),
                dto.getRequestDate(),
                dto.getRequestTime(),
                dto.getQuantity(),
                dto.getStatus()
        );
    }

    public EmployeeDto getEmployeeDto(Employee employee) {
        if (employee == null) return null;
        return new EmployeeDto(
                employee.getEmployeeId(),
                employee.getName(),
                employee.getNic(),
                employee.getContact(),
                employee.getEmail(),
                employee.getJobRole(),
                employee.getAddress(),
                employee.getJoinDate(),
                employee.getDateOfBirth(),
                employee.getGender(),
                employee.getBankAccountNo(),
                employee.getBankBranch(),
                employee.getLicenseNumber()
        );
    }

    public Employee getEmployee(EmployeeDto dto) {
        if (dto == null) return null;
        return new Employee(
                dto.getEmployeeId(),
                dto.getName(),
                dto.getNic(),
                dto.getContact(),
                dto.getEmail(),
                dto.getJobRole(),
                dto.getAddress(),
                dto.getJoinDate(),
                dto.getDateOfBirth(),
                dto.getGender(),
                dto.getBankAccountNo(),
                dto.getBankBranch(),
                dto.getLicenseNumber()
        );
    }

    public InventoryCart getInventoryCart(CartItemTM dto) {
        if (dto == null) return null;
        return new InventoryCart(
                dto.getCartId(),
                dto.getSupplierId(),
                dto.getMaterialId(),
                dto.getName(),
                dto.getUnitType(),
                dto.getUnitPrice(),
                dto.getQuantity(),
                dto.getTotal()
        );
    }

    public CartItemTM getInventoryCartDto(InventoryCart entity) {
        if (entity == null) return null;
        return new CartItemTM(
                entity.getCartId(),
                entity.getSupplierId(),
                entity.getMaterialId(),
                entity.getName(),
                entity.getUnitType(),
                entity.getUnitPrice(),
                entity.getQuantity(),
                entity.getTotal()
        );
    }

    public RawMaterialsDto getRawMaterialsDto(RawMaterials entity) {
        return new RawMaterialsDto(
                entity.getMaterialId(),
                entity.getSupplierId(),
                entity.getName(),
                entity.getUnitType(),
                entity.getUnitCost(),
                entity.getLastUpdate(),
                entity.getQuantityAvailable()
        );
    }


    public Transport getTransport(TransportDto dto) {
        if (dto == null) return null;
        return new Transport(
                dto.getTransportId(),
                dto.getProductId(),
                dto.getVehicleNumber(),
                dto.getTransportDate(),
                dto.getDeliveryBeginTime(),
                dto.getDeliveryEndTime(),
                dto.getQuantity(),
                dto.getLocation(),
                dto.getStatus()
        );
    }

    public TransportDto getTransportDto(Transport entity) {
        if (entity == null) return null;
        return new TransportDto(
                entity.getTransportId(),
                entity.getProductId(),
                entity.getVehicleNumber(),
                entity.getTransportDate(),
                entity.getDeliveryBeginTime(),
                entity.getDeliveryEndTime(),
                entity.getQuantity(),
                entity.getLocation(),
                entity.getStatus()
        );
    }
    public Stock getStock(StockDto dto) {
        if (dto == null) return null;
        return new Stock(
                dto.getStockId(),
                dto.getProductId(),
                dto.getProductName(),
                dto.getQty(),
                dto.getDate(),
                dto.getTime()
        );
    }

    public StockDto getStockDto(Stock entity) {
        if (entity == null) return null;
        return new StockDto(
                entity.getStockId(),
                entity.getProductId(),
                entity.getProductName(),
                entity.getQty(),
                entity.getDate(),
                entity.getTime()
        );
    }
    public ProductDto getProductDTO(Product entity) {
        if (entity == null) return null;
        return new ProductDto(
                entity.getProductId(),
                entity.getName(),
                entity.getWeight(),
                entity.getPricePerUnit()
        );
    }

    public Product getProduct(ProductDto dto) {
        if (dto == null) return null;
        return new Product(
                dto.getProductId(),
                dto.getName(),
                dto.getWeight(),
                dto.getPricePerUnit()
        );
    }
    public Supplier getSupplier(SupplierDto dto) {
        if (dto == null) return null;
        return new Supplier(
                dto.getSupplierId(),
                dto.getName(),
                dto.getNic(),
                dto.getContact(),
                dto.getEmail(),
                dto.getAddress()
        );
    }

    public SupplierDto getSupplierDto(Supplier entity) {
        if (entity == null) return null;
        return new SupplierDto(
                entity.getSupplierId(),
                entity.getName(),
                entity.getNic(),
                entity.getContact(),
                entity.getEmail(),
                entity.getAddress()
        );
    }
    public OrderPaymentDto getOrderPaymentDto(OrderPayment entity) {
        if (entity == null) return null;
        return new OrderPaymentDto(
                entity.getPaymentId(),
                entity.getOrderId(),
                entity.getPaymentMethod(),
                entity.getItemCount(),
                entity.getSubtotal(),
                entity.getDiscount(),
                entity.getNetTotal(),
                entity.getPaymentDate(),
                entity.getStatus()
        );
    }

    public OrderPayment getOrderPayment(OrderPaymentDto dto) {
        if (dto == null) return null;
        return new OrderPayment(
                dto.getPaymentId(),
                dto.getOrderId(),
                dto.getPaymentMethod(),
                dto.getItemCount(),
                dto.getSubtotal(),
                dto.getDiscount(),
                dto.getNetTotal(),
                dto.getPaymentDate(),
                dto.getStatus()
        );
    }
    public OrdersDto getOrdersDto(Orders entity) {
        if (entity == null) return null;
        return new OrdersDto(
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getOrderDate(),
                entity.getOrderTime(),
                entity.getDescription(),
                entity.getVehicle_number(),
                entity.getTotalAmount()
        );
    }

    public Orders getOrders(OrdersDto dto) {
        if (dto == null) return null;
        return new Orders(
                dto.getOrderId(),
                dto.getCustomerId(),
                dto.getOrderDate(),
                dto.getOrderTime(),
                dto.getDescription(),
                dto.getVehicle_number(),
                dto.getTotalAmount()
        );
    }

    public OrderDetails getOrderDetailsEntity(OrderDetailsDto dto) {
        if (dto == null) return null;
        return new OrderDetails(
                dto.getOrderId(),
                dto.getProductId(),
                dto.getQuantity(),
                dto.getUnitPrice(),
                dto.getDiscount()
        );
    }
}
