package springSecurity.service.security.impl;

import org.springframework.stereotype.Service;

import springSecurity.bean.security.SystemUser;
import springSecurity.service.security.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Override
	public SystemUser getUserByLogin(String userName) throws Exception {
		SystemUser user = new SystemUser();
		return user;
	}
}
