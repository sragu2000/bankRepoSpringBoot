package com.bank.bank.service.impl;

import com.bank.bank.constants.ApplicationMessageConstants;
import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.AccountTypeDomain;
import com.bank.bank.repository.AccountTypeRepository;
import com.bank.bank.service.AccountTypeService;
import com.bank.bank.service.bl.AccountTypeServiceBL;
import com.bank.bank.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    @Autowired
    AccountTypeRepository accountTypeRepository;

    @Autowired
    AccountTypeServiceBL accountTypeServiceBL;

    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public ResponseDto createAccountType(AccountTypeDomain accountTypeDomain){
        ResponseDto responseDto;
        try {
            AccountTypeDomain saveAccountTypeDomain = accountTypeServiceBL.createAccountType(accountTypeDomain);
            responseDto= serviceUtil.getServiceResponse(saveAccountTypeDomain);
        } catch (Exception e) {
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_CREATE_ACCOUNT_TYPE);
        }
        return  responseDto;
    }

    @Override
    public ResponseDto getAllAccountTypes() {
        ResponseDto responseDto;
        try{
            List<AccountTypeDomain> getAll = accountTypeServiceBL.getAllAccountTypes();
            responseDto= serviceUtil.getServiceResponse(getAll);
        } catch (Exception e){
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_ACCOUNT_TYPE);
        }
        return  responseDto;
    }

    @Override
    public ResponseDto deleteAccountTypeById(@PathVariable Long id) {
        ResponseDto responseDto;
        try{
            AccountTypeDomain optionalAccountType = accountTypeServiceBL.deleteAccountTypeById(id);
            if(optionalAccountType==null){
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            }else{
                responseDto= serviceUtil.getServiceResponse(optionalAccountType);
            }
        } catch (Exception e){
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_DELETE_ACCOUNT_TYPE);
        }
        return  responseDto;
    }

    @Override
    public ResponseDto updateAccountById(Long id, AccountTypeDomain accountTypeDomain){
        ResponseDto responseDto;
        try{
            AccountTypeDomain optionalAccount = accountTypeServiceBL.updateAccountById(id, accountTypeDomain);
            if(optionalAccount==null){
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            }else{
                responseDto= serviceUtil.getServiceResponse(optionalAccount);
            }
        } catch (Exception e){
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ACCOUNT_TYPE);
        }
        return  responseDto;
    }
}
