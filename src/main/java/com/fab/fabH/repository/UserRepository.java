package com.fab.fabH.repository;

import com.fab.fabH.models.UserDetail;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDetail, Integer>
{

}
