package com.mouatamid.logsaver.responseModels;

import com.mouatamid.logsaver.models.Log;
import lombok.Data;

import java.util.Collection;

@Data
public class AppUserResponseModel {
    private long id;
    private String username;
    private Collection<Log> logs;
}
