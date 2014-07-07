/**
 * 
 */
package org.simple.user;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.suning.framework.page.QueryParam;
import com.suning.framework.page.QueryResult;

/**
 * @author Johnny
 * 
 */
@WebService
public interface UserService {

    User getUser(String code);

    void saveUser(User user);

    // List<OrganizationInfo> getOrganizationByRole(String roleName);

    // List<UserInfo> getUserByRole(Role role);

    QueryResult<UserInfo> searchUser(UserQueryParam param);

    // List<OrganizationInfo> getOrgByRole(Role role);

    List<Role> getRoles();

    Role getRole(String code);

    QueryResult<UserInfo> pageQuery(QueryParam<Map<String, Object>> param);
}
