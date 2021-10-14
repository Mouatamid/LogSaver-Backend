package com.mouatamid.logsaver.responseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogResponseModel {
    private long id;
    private Date startDate;
    private Date endDate;
    private String description;
}
