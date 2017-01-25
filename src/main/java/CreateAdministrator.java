import com.asap.catalog.dao.User;
import com.asap.security.Role;
import util.HibernateUtil;
import com.asap.security.UserManager;
/*
 * CreateAdministrator.java
 *
 * Created on 22. marts 2007, 17:02
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Jens Rosenberg
 */
public class CreateAdministrator {
    
    /** Creates a new instance of CreateAdministrator */
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("jens");
        user.setPassword("start");
        user.setEmail("jens@blobcom.com");
        user.setRole(Role.ADMINISTRATOR);
        user.setStreet("Bananvej 3");
        user.setCity("Appelsinby C");
        
        UserManager.saveOrUpdateUser(user);
    }
    
}
