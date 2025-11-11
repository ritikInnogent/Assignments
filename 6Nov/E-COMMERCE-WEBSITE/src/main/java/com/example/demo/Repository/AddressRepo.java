package com.example.demo.Repository;

import com.example.demo.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo  extends JpaRepository<Address, Long> {
}
