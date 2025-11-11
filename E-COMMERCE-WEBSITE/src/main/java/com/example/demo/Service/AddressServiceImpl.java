package com.example.demo.Service;

import com.example.demo.Dto.AddressRequest;
import com.example.demo.Dto.AddressResponse;
import com.example.demo.Model.Address;
import com.example.demo.Repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepo repo;

    @Override
    public AddressResponse addAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setFullName(addressRequest.getFullName());
        address.setPhone(addressRequest.getPhone());
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPincode(addressRequest.getPincode());

        repo.save(address);

        AddressResponse response = new AddressResponse();
        response.setFullName(address.getFullName());
        response.setPhone(address.getPhone());
        response.setAddressLine1(address.getAddressLine1());
        response.setAddressLine2(address.getAddressLine2());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setPincode(address.getPincode());

        return response;
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
        Address address = repo.findById(id).orElseThrow(() -> new RuntimeException("Address not exist"));

        address.setFullName(addressRequest.getFullName());
        address.setPhone(addressRequest.getPhone());
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPincode(addressRequest.getPincode());

        repo.save(address);

        AddressResponse response = new AddressResponse();
        response.setFullName(address.getFullName());
        response.setPhone(address.getPhone());
        response.setAddressLine1(address.getAddressLine1());
        response.setAddressLine2(address.getAddressLine2());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setPincode(address.getPincode());

        return response;
    }

    @Override
    public String deleteAddress(Long id) {
        Address address = repo.findById(id).orElseThrow(() -> new RuntimeException("Address not exist"));
        repo.delete(address);
        return "Address deleted successfully!";
    }

    @Override
    public List<Address> getAddresses() {
        return repo.findAll();
    }

    @Override
    public AddressResponse getAddressById(Long id) {
        Address address = repo.findById(id).orElseThrow(() -> new RuntimeException("Address not exist"));

        AddressResponse response = new AddressResponse();
        response.setFullName(address.getFullName());
        response.setPhone(address.getPhone());
        response.setAddressLine1(address.getAddressLine1());
        response.setAddressLine2(address.getAddressLine2());
        response.setCity(address.getCity());
        response.setState(address.getState());
        response.setPincode(address.getPincode());

        return response;
    }
}
