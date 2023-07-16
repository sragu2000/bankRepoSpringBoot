package com.bank.bank.dto;
import java.io.Serializable;
import lombok.Data;

@Data
public class ResponseDto implements Serializable {
    private boolean status = true;
    private int errorCode;
    private String errorDescription;
    private Object responseDto;
}