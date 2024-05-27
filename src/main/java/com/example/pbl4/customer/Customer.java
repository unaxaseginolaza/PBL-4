package com.example.pbl4.customer;

import com.example.pbl4.employee.Employee;
import com.example.pbl4.user.User;
import jakarta.persistence.*;

@Entity
@Table
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    private String companyName;

    public Customer(Long id, String username, String password, Integer permisos, String companyName) {
        super(id, username, password, permisos);
        this.companyName = companyName;
    }

    public Customer(String companyName) {
        this.companyName = companyName;
    }

    public Customer(String username, String password, Integer permisos, String companyName) {
        super(username, password, permisos);
        this.companyName = companyName;
    }


    public Customer() {

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
