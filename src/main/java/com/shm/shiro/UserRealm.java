package com.shm.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.shm.domain.User;
import com.shm.service.UserService;

/**
 * 自定义Realm
 * @author SHM
 *
 */
public class UserRealm extends AuthorizingRealm{

	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");
		
		//给资源授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//添加资源授权的字符串
		//info.addStringPermission("user:add");
		
		//到数据库中查询当前授权登录的字符串
		//获取当前登录用户
		Subject  subject = SecurityUtils.getSubject();
		User user = (User)subject.getPrincipal();
		User dbUser = userService.findById(user.getId());
		info.addStringPermission(dbUser.getPerms());
		return info;
	}

	@Autowired
	private UserService userService;
	/**
	 * 执行认证逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken argo) throws AuthenticationException {
		System.out.println("执行认证逻辑");
				
		
		//编写shiro判断逻辑，判断用户名和密码
		UsernamePasswordToken token = (UsernamePasswordToken)argo;
		
		User user = userService.findByName(token.getUsername());
		
		if(user == null) {
			//用户名不存在
			return null;//Shiro底层会抛出UnknownAccountException
		}
		//判断密码
		
		return new SimpleAuthenticationInfo(user,user.getPassword(),"");
		
		
	}

}
