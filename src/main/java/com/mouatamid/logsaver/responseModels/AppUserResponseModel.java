package com.mouatamid.logsaver.responseModels;

import com.mouatamid.logsaver.models.Log;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponseModel {
    private long id;
    private String username;
    private Collection<Log> logs;
}
