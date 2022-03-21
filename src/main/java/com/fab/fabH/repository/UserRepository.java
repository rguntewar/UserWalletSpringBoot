package com.fab.fabH.repository;

import com.fab.fabH.models.UserDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends CrudRepository<UserDetail, Integer>
{

}

