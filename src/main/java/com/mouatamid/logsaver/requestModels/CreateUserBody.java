package com.mouatamid.logsaver.requestModels;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public
class CreateUserBody{
    private String email;
    private String password;
}