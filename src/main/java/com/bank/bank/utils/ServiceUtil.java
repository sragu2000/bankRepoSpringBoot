package com.bank.bank.utils;

import com.bank.bank.config.MessageConfig;
import com.bank.bank.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtil {

    @Autowired
    MessageConfig messageConfig;
    public ResponseDto getServiceResponse(Object responseObject) {
        ResponseDto serviceResponseDto;
        serviceResponseDto = new ResponseDto();
        serviceResponseDto.setStatus(Boolean.TRUE);
        serviceResponseDto.setResponseDto(responseObject);
        return serviceResponseDto;
    }

    public ResponseDto getExceptionServiceResponseByProperties(String errorCode) {
        ResponseDto serviceResponseDto;
        serviceResponseDto = new ResponseDto();
        serviceResponseDto.setStatus(Boolean.FALSE);
        serviceResponseDto.setErrorCode(Integer.parseInt(messageConfig.getErrorCode(errorCode)));
        serviceResponseDto.setErrorDescription(messageConfig.getMessage(errorCode));
        return serviceResponseDto;
    }
}
