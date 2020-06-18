package net.codejava.repository;

import net.codejava.model.UserAccess;

import java.util.List;

public interface UserAccessCustomRepository {
    List<UserAccess> selectAllUsersAccess();

    List<UserAccess> selectUsersAccessById(String empId);
}
