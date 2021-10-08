package com.mouatamid.logsaver.services;

import com.mouatamid.logsaver.models.AppUser;
import com.mouatamid.logsaver.models.Log;
import com.mouatamid.logsaver.repo.AppUserRepository;
import com.mouatamid.logsaver.repo.LogRepository;
import com.mouatamid.logsaver.responseModels.AppUserResponseModel;
import com.mouatamid.logsaver.responseModels.LogResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements IAppUserService {
    private final AppUserRepository appUserRepository;
    private final LogRepository logRepository;
    private final ModelMapper modelMapper;
    @Override
    public AppUserResponseModel saveUser(String username, String password) {
        AppUser appUser = new AppUser(null, username, password, new ArrayList<>());
        AppUser savedUser = appUserRepository.save(appUser);
        log.info("User saved {}", username);
        return modelMapper.map(savedUser, AppUserResponseModel.class);
    }

    @Override
    public List<LogResponseModel> listLogs(String username) {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(username);
        if (appUserOptional.isEmpty()){
            throw new NoSuchElementException("There no such user with this username");
        }
        AppUser appUser = appUserOptional.get();
        return appUser.getLogs().stream().map(e -> modelMapper.map(e, LogResponseModel.class)).collect(Collectors.toList());
    }

    @Override
    public LogResponseModel createLog(String username, Date startDate, Date endDate, String description) {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(username);
        if (appUserOptional.isEmpty()){
            log.error("Username {} was not found",username);
            throw new NoSuchElementException("There no such user with this username");
        }
        Log logCreated = logRepository.save(new Log(null,startDate,endDate,description));
        AppUser appUser = appUserOptional.get();
        appUser.getLogs().add(logCreated);
        appUserRepository.save(appUser);
        log.info("Log created {} for user {}", logCreated.getId(), username);
        return modelMapper.map(logCreated, LogResponseModel.class);
    }
}
