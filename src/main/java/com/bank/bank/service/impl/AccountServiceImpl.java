package com.bank.bank.service.impl;

import com.bank.bank.constants.ApplicationMessageConstants;
import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.AccountDomain;
import com.bank.bank.repository.AccountRepository;
import com.bank.bank.service.AccountService;
import com.bank.bank.service.bl.AccountServiceBL;
import com.bank.bank.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountServiceBL accountServiceBL;

    @Autowired
    ServiceUtil serviceUtil;

    @Override
    public ResponseDto createAccount(AccountDomain accountDomain) {
        ResponseDto responseDto;
        try {
            AccountDomain saveAccountDomain = accountServiceBL.createAccount(accountDomain);
            responseDto= serviceUtil.getServiceResponse(saveAccountDomain);
        } catch (Exception e) {
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_CREATE_ACCOUNT);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getAllAccounts() {
        ResponseDto responseDto;
        try {
            List<AccountDomain> getAll = accountServiceBL.getAllAccounts();
            responseDto = serviceUtil.getServiceResponse(getAll);
        } catch (Exception e) {
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_ACCOUNT);
        }
        return responseDto;
    }

    @Override
    public ResponseDto deleteAccountById( Long id) {
        ResponseDto responseDto;
        try{
            AccountDomain optionalAccountDomain = accountServiceBL.deleteAccountById(id);
            if(optionalAccountDomain == null){
                responseDto = serviceUtil.getServiceResponse(optionalAccountDomain);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            }else{
                responseDto = serviceUtil.getServiceResponse(optionalAccountDomain);
            }
        } catch (Exception e){
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_DELETE_ACCOUNT);
        }
        return responseDto;
    }

    @Override
    public ResponseDto updateAccountById( Long id,  AccountDomain accountDomain){
        ResponseDto responseDto;
        try{
            AccountDomain account = accountServiceBL.updateAccountById(id, accountDomain);
            if(account==null){
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            }else{
                responseDto = serviceUtil.getServiceResponse(account);
            }
        } catch (Exception e){
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_ACCOUNT);
        }
        return responseDto;
    }

    @Override
    public ResponseDto getBalanceById( Long id){
        ResponseDto responseDto;
        try{
            Double balance = accountServiceBL.getBalanceById(id);
            if (balance == null){
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            }else{
                responseDto = serviceUtil.getServiceResponse(balance);
            }
        } catch (Exception e){
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_BALANCE);
        }
        return responseDto;
    }
}
