package com.mouatamid.logsaver.services;

import com.mouatamid.logsaver.responseModels.AppUserResponseModel;
import com.mouatamid.logsaver.responseModels.LogResponseModel;

import java.util.Date;
import java.util.List;


public interface IAppUserService {
    AppUserResponseModel saveUser(String username, String password);
    List<LogResponseModel> listLogs(String username);
    LogResponseModel createLog(String username, Date startDate, Date endDate, String description);
}
