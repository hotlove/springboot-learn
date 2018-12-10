package com.guo.springboot.shiro;

import com.guo.springboot.dao.ProfileMapper;
import com.guo.springboot.domain.Profile;
import com.guo.springboot.domain.ProfileExample;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

import javax.annotation.Resource;
import java.util.List;

// 权限验证
public class MyRealm extends AuthenticatingRealm {

    @Resource
    private ProfileMapper profileMapper;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 用户名
        String userName = (String) authenticationToken.getPrincipal();


        ProfileExample example = new ProfileExample();
        example.createCriteria().andUsernameEqualTo(userName);

        List<Profile> profileList = profileMapper.selectByExample(example);

        if (profileList != null && profileList.size() != 0) {

            Profile profile = profileList.get(0);

            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, authenticationToken.getCredentials(), getName() );
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
