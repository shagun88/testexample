package net.codejava.repository;

import net.codejava.model.UserAccess;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserAccessCustomRepositoryImpl implements UserAccessCustomRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<UserAccess> selectAllUsersAccess() {
            String SQL = USERACCESSSQL;
            return selectAllRelations(SQL, null);
    }

    @Override
    public List<UserAccess> selectUsersAccessById(String empId) {
        String SQL = USERACCESSSQL + " where manager_id = ? ";
        return selectAllRelations(SQL, empId);
    }

    private List<UserAccess> selectAllRelations(String sql, String empId) {
        try {
            System.out.println("@@@@ EMPID" + empId);
            Query q = em.createNativeQuery(sql);
            if(empId != null) {
                q.setParameter(1, empId);
            }
            List<Object[]> objectList = q.getResultList();
            System.out.println("@@@@ SQL" + sql);
            List<UserAccess> useraccessList = null;
            if (objectList != null) {
                useraccessList = new ArrayList<>();
                for (int i = 0; i <= objectList.size() - 1; i++) {
                    UserAccess userAccessObj = new UserAccess();
                    String empid = (String) ((objectList.get(i))[0]);
                    String accessKey = (String) ((objectList.get(i))[1]);
                    String country = (String) ((objectList.get(i))[2]);
                    String subuser = (String) ((objectList.get(i))[3]);
                    String subuser_accesskey = (String) ((objectList.get(i))[4]);
                    String subuser_country = (String) ((objectList.get(i))[5]);
                    userAccessObj.setEmpid(empid);
                    userAccessObj.setAccessKey(accessKey);
                    userAccessObj.setCountry(country);
                    userAccessObj.setSubuser(subuser);
                    userAccessObj.setSubuser_accesskey(subuser_accesskey);
                    userAccessObj.setSubuser_country(subuser_country);
                    useraccessList.add(userAccessObj);
                }
            }
            return useraccessList;
        }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    private static String USERACCESSSQL = "select b.manager_id, u.access_key, u.country, b.empid, c.access_key as subuser_access_key, c.country as subuser_country from user u join \n" +
            "(with descendants\n" +
            "  (manager_id,  empid, lvl) \n" +
            "as\n" +
            "  ( select manager_id, empid, 1\n" +
            "    from teamhierarchy \n" +
            "  union all\n" +
            "    select d.manager_id, s.empid, d.lvl + 1\n" +
            "    from descendants  d\n" +
            "      join teamhierarchy s\n" +
            "        on d.empid = s.manager_id\n" +
            "  ) select *\n" +
            "from descendants \n" +
            "union all\n" +
            "select emp_id,  emp_id, 1 from user \n" +
            "union all\n" +
            "select h1.empid, h2.empid, 1 from teamhierarchy h1, teamhierarchy h2 where h1.manager_id=h2.empid \n" +
            "union all\n" +
            "select h1.empid, h2.empid, 2 from teamhierarchy h1, teamhierarchy h2 where h1.manager_id=h2.manager_id and h1.empid not in (h2.empid) \n" +
            "and h1.manager_id in (select empid from teamhierarchy)) b on b.manager_id = u.emp_id\n" +
            "join user c on b.empid = c.emp_id ";
}
