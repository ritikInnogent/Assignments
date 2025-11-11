package com.example.demo.Service;

import com.example.demo.Dto.AddressRequest;
import com.example.demo.Dto.AddressResponse;
import com.example.demo.Model.Address;
import com.example.demo.Model.OrderItem;

import java.util.List;

public interface IAddressService {
    AddressResponse addAddress(AddressRequest addressRequest);

    List<Address> getAddresses();

    AddressResponse getAddressById(Long id);

    AddressResponse updateAddress(Long id, AddressRequest addressRequest);

    String deleteAddress(Long id);
}
