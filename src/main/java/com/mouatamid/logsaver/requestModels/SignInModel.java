package com.mouatamid.logsaver.requestModels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInModel {
    private String username;
    private String password;
}
