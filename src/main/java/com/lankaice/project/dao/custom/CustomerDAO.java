package com.lankaice.project.dao.custom;


import com.lankaice.project.dao.CrudDAO;
import com.lankaice.project.entity.Customer;

import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    List<Customer> search(String text);
}
