package net.codejava.controller;

import net.codejava.exception.ResourceNotFoundException;
import net.codejava.repository.UserRepository;
import net.codejava.model.User;
import net.codejava.service.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserAccessController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAccessService userAccessService;

    @GetMapping("/getAll")
    public Iterable<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public @Valid User createEmployee(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/createUserAccess/{empid}")
    public Map< String, Boolean > insertUserAccess(@PathVariable(value = "empid") String empid)
            throws ResourceNotFoundException {
        return  validateInsert(userAccessService.insertUserAccess(empid), empid);
    }

    @PostMapping("/createAllAccessForUser/{empid}")
    public Map< String, Boolean > insertAllAccessForUser(@PathVariable(value = "empid") String empid)
            throws ResourceNotFoundException{
        return validateInsert(userAccessService.insertAllAccessForUser(empid), empid);
    }

    @PostMapping("/insertAllUsersAccess")
    public boolean insertAllUsersAccess(){
        return userAccessService.insertAllUsersAccess();
    }

    private Map < String, Boolean > validateInsert(boolean insertFlag, String empid) throws ResourceNotFoundException {
        Map < String, Boolean > response = new HashMap< >();
        if(insertFlag){
            response.put("insert success", Boolean.TRUE);
        }
        else{
            throw new ResourceNotFoundException("Employee not found for this id :: " + empid);
        }
        return response;
    }

}
