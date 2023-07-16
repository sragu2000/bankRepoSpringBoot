package com.bank.bank.service.impl;

import com.bank.bank.constants.ApplicationMessageConstants;
import com.bank.bank.dto.ResponseDto;
import com.bank.bank.domain.BankDomain;
import com.bank.bank.repository.BankRepository;
import com.bank.bank.service.BankService;
import com.bank.bank.service.bl.BankServiceBL;
import com.bank.bank.utils.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;


@Service
public class BankServiceImpl implements BankService {
    @Autowired
    BankRepository bankRepository;

    @Autowired
    BankServiceBL bankServiceBL;

    @Autowired
    ServiceUtil serviceUtil;
    @Override
    public ResponseDto createBank(@RequestBody BankDomain bankDomain) {
        ResponseDto responseDto;
        try {
            BankDomain saveBankDomain = bankServiceBL.createBank(bankDomain);
            responseDto= serviceUtil.getServiceResponse(saveBankDomain);
        } catch (Exception e) {
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_CREATE_BANK);
        }
        return  responseDto;
    }

    @Override
    public ResponseDto getAllBanks() {
        ResponseDto responseDto;
        try {
            List<BankDomain> getAll = bankServiceBL.getAllBanks();
            responseDto= serviceUtil.getServiceResponse(getAll);
        } catch (Exception e) {
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_ALL_BANKS );
        }
        return  responseDto;
    }

    @Override
    public ResponseDto getBankByLocation(String location) {
        ResponseDto responseDto;
        try {
            List<BankDomain> getBanks = bankServiceBL.getBankByLocation(location);
            responseDto= serviceUtil.getServiceResponse(getBanks);
        } catch (Exception e) {
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_BANK_BY_LOCATION );
        }
        return  responseDto;
    }

    @Override
    public ResponseDto deleteBankById( Long id) {
        ResponseDto responseDto;
        try{
            BankDomain optionalBankDomain = bankServiceBL.deleteBankById(id);
            if(optionalBankDomain ==null){
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            }else{
                responseDto= serviceUtil.getServiceResponse(optionalBankDomain);
            }
        }catch (Exception e){
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_DELETE_BANK_BY_ID );
        }
        return  responseDto;
    }

    @Override
    public ResponseDto updateBankById( Long id,  BankDomain bankDomain) {
        ResponseDto responseDto;
        try {
            BankDomain optionalBank = bankServiceBL.updateBankById(id, bankDomain);
            if(optionalBank==null){
                responseDto = serviceUtil.getServiceResponse(null);
                responseDto.setErrorDescription("Account Not Found");
                responseDto.setStatus(Boolean.FALSE);
            }else{
                responseDto= serviceUtil.getServiceResponse(optionalBank);
            }
        } catch (Exception e) {
            responseDto= serviceUtil.getExceptionServiceResponseByProperties(ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_BANK_BY_ID );
        }
        return  responseDto;
    }
}
