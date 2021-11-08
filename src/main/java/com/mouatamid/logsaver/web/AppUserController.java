package com.mouatamid.logsaver.web;

import com.mouatamid.logsaver.requestModels.CreateUserBody;
import com.mouatamid.logsaver.responseModels.AppUserResponseModel;
import com.mouatamid.logsaver.responseModels.LogResponseModel;
import com.mouatamid.logsaver.services.IAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AppUserController {
    private final IAppUserService appUserService;

    @PostMapping("/create")
    ResponseEntity<AppUserResponseModel> createUser(@RequestBody CreateUserBody createUserBody){
        AppUserResponseModel appUserResponseModel = appUserService.saveUser(createUserBody.getEmail(),createUserBody.getPassword());
        if (appUserResponseModel != null){
            return new ResponseEntity<>(appUserResponseModel, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/logs")
    List<LogResponseModel> listLogs(@RequestParam String username){
        return appUserService.listLogs(username);
    }

    @PostMapping("/savelog")
    ResponseEntity<LogResponseModel> saveLog(@RequestParam String username, @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate, @RequestParam String description){
        LogResponseModel logResponseModel = appUserService.createLog(username, startDate, endDate, description);
        if (logResponseModel != null){
            return new ResponseEntity<>(logResponseModel, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
