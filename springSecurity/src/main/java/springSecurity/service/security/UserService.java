package springSecurity.service.security;

import springSecurity.bean.security.SystemUser;

public interface UserService {

	public SystemUser getUserByLogin(String userName) throws Exception;
}
