package com.cmdc.infrastructure.shirorealm;

import com.cmdc.domain.entity.User;
import com.cmdc.domain.mapper.UserMapper;
import com.cmdc.infrastructure.shirotoken.CustomizedToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 不需要关注PasswordRealm的授权
 * 用户登录之后的权限校验全部发生在token层面，由jwtRealm进行
 * PasswordRealm要做的就是用户的认证
 * @author : wuwensheng
 * @date : 10:57 2020/7/1
 */
@Slf4j
public class PasswordRealm extends AuthorizingRealm {

    //认为realm所做的身份认证、权限校验都属于最底部的进入接口前的准备 尽量让其贴近领域层
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomizedToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("PasswordRealm权限认证开始,传递的token:{}",authenticationToken);
        //找出数据库中的对象  给定用户输入的对象做出对比
        CustomizedToken token = (CustomizedToken) authenticationToken;
        log.info("PasswordRealm转换的自定义token是:{}",token);
        // 根据userId查询用户
        User user=userMapper.selectById(token.getUsername());
        if (user == null) {
            // 抛出账号不存在异常
            throw new UnknownAccountException();
        }
        Object credentials = user.getPassword();
        //param1:数据库用户 param2:密码 param3:加密所用盐值 param4:当前realm的名称
        return new SimpleAuthenticationInfo(user, credentials, ByteSource.Util.bytes(user.getUserId()),getName());
    }
}
