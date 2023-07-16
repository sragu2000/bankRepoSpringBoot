package com.bank.bank.service.impl;

import com.bank.bank.constants.ApplicationMessageConstants;
import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.CustomerDomain;
import com.bank.bank.repository.CustomerRepository;
import com.bank.bank.service.CustomerService;
import com.bank.bank.service.bl.CustomerServiceBL;
import com.bank.bank.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerServiceBL customerServiceBL;
    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public ResponseDto createCustomer(CustomerDomain customerDomain) {
        ResponseDto responseDto;
        try {
            CustomerDomain saveCustomerDomain = customerServiceBL.createCustomer(customerDomain);
            responseDto = serviceUtil.getServiceResponse(saveCustomerDomain);
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_CREATE_CUSTOMER);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllCustomers() {
        ResponseDto responseDto;
        try {
            List<CustomerDomain> getAll = customerServiceBL.getAllCustomers();
            responseDto = serviceUtil.getServiceResponse(getAll);
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_CUSTOMER);
        }
        return responseDto;
    }

    @Override
    public ResponseDto findByID(long id) {
        ResponseDto responseDto;
        try {
            CustomerDomain optionalCustomer = customerServiceBL.findByID(id);
            if (optionalCustomer == null) {
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            } else {
                responseDto = serviceUtil.getServiceResponse(optionalCustomer);
            }
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_FIND_CUSTOMER);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteCustomerById(Long id) {
        ResponseDto responseDto;
        try {
            CustomerDomain optionalCustomerDomain = customerServiceBL.deleteCustomerById(id);
            if (optionalCustomerDomain == null) {
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            } else {
                responseDto = serviceUtil.getServiceResponse(optionalCustomerDomain);
            }
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_DELETE_CUSTOMER_BY_ID);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateCustomerById(Long id, CustomerDomain customerDomain) {
        ResponseDto responseDto;
        try {
            CustomerDomain optionalCustomer = customerServiceBL.updateCustomerById(id, customerDomain);
            if (optionalCustomer == null) {
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            } else {
                responseDto = serviceUtil.getServiceResponse(optionalCustomer);
            }
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_CUSTOMER_BY_ID);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllCustomersByName(String name) {
        ResponseDto responseDto;
        try {
            List<CustomerDomain> getAll = customerServiceBL.getAllCustomersByName(name);
            responseDto = serviceUtil.getServiceResponse(getAll);
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_CUSTOMER_BY_NAME);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllCustomersByNic(String nic) {
        ResponseDto responseDto;
        try {
            List<CustomerDomain> getAll = customerServiceBL.getAllCustomersByNic(nic);
            responseDto = serviceUtil.getServiceResponse(getAll);
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_CUSTOMER_BY_NIC);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllCustomersByAddress(String address) {
        ResponseDto responseDto;
        try {
            List<CustomerDomain> getAll = customerServiceBL.getAllCustomersByAddress(address);
            responseDto = serviceUtil.getServiceResponse(getAll);
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_CUSTOMER_BY_ADDRESS);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllCustomersByProperties(String name, String nic, String address) {
        ResponseDto responseDto;
        try {
            List<CustomerDomain> getAll = customerServiceBL.getAllCustomersByProperties(name, nic, address);
            responseDto = serviceUtil.getServiceResponse(getAll);
        } catch (Exception e) {
            responseDto = serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_CUSTOMER_BY_PROPERTIES);
        }
        return responseDto;
    }
}
