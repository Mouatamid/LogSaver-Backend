package com.mouatamid.logsaver.repo;

import com.mouatamid.logsaver.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
}
