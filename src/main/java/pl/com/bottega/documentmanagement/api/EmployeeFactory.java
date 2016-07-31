package pl.com.bottega.documentmanagement.api;

import com.google.common.base.Charsets;
import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import pl.com.bottega.documentmanagement.domain.Employee;
import pl.com.bottega.documentmanagement.domain.EmployeeId;
import pl.com.bottega.documentmanagement.domain.Role;

//import java.nio.charset.Charset;

/**
 * Created by Piotr on 31.07.2016.
 */
public class EmployeeFactory {

    private  String INITIAL_ROLE = "STAFF" ;
    private PassworHasher passwordHasher;

    public Employee create (String login, String password, EmployeeId employeeId){
        Employee employee = new Employee(login, hashedPasswor(password), employeeId);
        employee.updateRoles(Sets.newHashSet(new Role(INITIAL_ROLE)));


    }


}
