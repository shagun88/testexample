package net.codejava.service;

import net.codejava.repository.HierarchyRepository;
import net.codejava.repository.UserAccessRepository;
import net.codejava.repository.UserRepository;
import net.codejava.model.User;
import net.codejava.model.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccessServiceImpl implements UserAccessService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAccessRepository userAccessRepository;

    @Autowired
    HierarchyRepository hierarchyRepository;

    @Override
    public boolean insertUserAccess(String empId){
        UserAccess userAccess = new UserAccess();
        boolean insertUserAccess = false;

        Optional<User> user = userRepository.findById(empId);
        if(user.isPresent()) {
            userAccess.setEmpid(empId);
            userAccess.setAccessKey(user.get().getAccessKey());
            userAccess.setCountry(user.get().getCountry());
            userAccess.setSubuser(empId);
            userAccess.setSubuser_accesskey(user.get().getAccessKey());
            userAccess.setSubuser_country(user.get().getCountry());
            userAccessRepository.save(userAccess);
            insertUserAccess = true;
        }
        return  insertUserAccess;
    }

    @Override
    @Transactional
    public boolean insertAllAccessForUser(String empId){
        boolean insertUserAccess = false;

        List<UserAccess> userAccessList = userAccessRepository.selectUsersAccessById(empId);
        for(UserAccess userAccessItr : userAccessList){
            userAccessRepository.save(userAccessItr);
            insertUserAccess = true;
        }
        return  insertUserAccess;
    }

    @Override
    @Transactional
    public boolean insertAllUsersAccess(){
        boolean insertFlag = false;

        userAccessRepository.deleteAll();    // If all users need to be inserted at once then from clean teh table
        List<UserAccess> userAccessList = userAccessRepository.selectAllUsersAccess();
        for(UserAccess userAccessItr : userAccessList){
            userAccessRepository.save(userAccessItr);
            insertFlag = true;
        }
        return insertFlag;

    }
}
