package com.guo.springboot.service;

import com.guo.springboot.dao.ProfileMapper;
import com.guo.springboot.domain.Profile;
import com.guo.springboot.domain.ProfileExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ProfileServiceImpl implements ProfileService{

    @Resource
    private ProfileMapper profileMapper;

    @Override
    public void insertProfile(Profile profile) {
        profileMapper.insertSelective(profile);
    }

    @Override
    public void deleteProfile(int profileId) {
        ProfileExample example = new ProfileExample();
        example.createCriteria().andIdEqualTo(profileId);

        Profile record = new Profile();
        record.setId(profileId);

        profileMapper.updateByExampleSelective(record, example);
    }
}
