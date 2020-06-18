package net.codejava.service;

public interface UserAccessService {

    boolean insertUserAccess(String empId);

    boolean insertAllUsersAccess();

    boolean insertAllAccessForUser(String empId);
}
