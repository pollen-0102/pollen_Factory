package com.oracle.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealm extends AuthorizingRealm{
	


	@Override
	public String getName() {
		return "customRealm";
	}
	
	//认证
		@Override
		protected AuthenticationInfo doGetAuthenticationInfo(
				AuthenticationToken token) throws AuthenticationException {
			
			//从token中获取用户身份信息, 相当于从界面输入的账号
			String username = (String) token.getPrincipal();//zhangsan
			//zhangsan模拟从数据库取得的账号
			if(!"zhangsan".equals(username)){ 
				return null;
			}
			//模拟从数据库获取查询出来的用户密码 
			String password = "824a67f29e97b8798a9df7f00189f3e1";
			
			String salt="qwert";
			
			//返回认证信息由父类AuthenticatingRealm进行认证
			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
					username, password,ByteSource.Util.bytes(salt),getName());

			
			return simpleAuthenticationInfo;
		}

		//授权
		@Override
		protected AuthorizationInfo doGetAuthorizationInfo(
				PrincipalCollection principals) {
			// 获取身份信息
			String username = (String) principals.getPrimaryPrincipal();
			System.out.println("登录的用户名称="+username);
			// 根据身份信息从数据库中查询权限数据,使用账号为username查询出权限信息,通过用户名查出角色，使用角色获取权限（查看权限设计的模型数据表格）
			// 使用静态数据模拟从数据库中查询到的权限数据
			List<String> permissions = new ArrayList<String>();
			permissions.add("user:create");
			permissions.add("user:delete");
			
			//将权限信息封装为AuthorizationInfo
			SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			for(String permission:permissions){
				simpleAuthorizationInfo.addStringPermission(permission);
			}
			
			return simpleAuthorizationInfo;
		}



}
