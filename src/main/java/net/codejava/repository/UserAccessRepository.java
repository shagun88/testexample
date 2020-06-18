package net.codejava.repository;

import net.codejava.model.UserAccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessRepository
        extends CrudRepository<UserAccess, Long>, UserAccessCustomRepository {

}
