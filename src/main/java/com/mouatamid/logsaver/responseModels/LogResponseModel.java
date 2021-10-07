package com.mouatamid.logsaver.responseModels;

import lombok.Data;

import java.util.Date;

@Data
public class LogResponseModel {
    private long id;
    private Date startDate;
    private Date endDate;
    private String description;
}
