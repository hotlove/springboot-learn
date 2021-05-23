package com.guo.springboot.design.proxy;

/**
 * @Auther: hotlove_linx
 * @Date: 2021/4/25 23:03
 * @Description:
 */
public class ProfileServiceImpl implements ProfileService{
    @Override
    public String getProfileName(String name) {
        return name;
    }

    @Override
    public String getProfileSex(String sex) {
        return sex;
    }
}
