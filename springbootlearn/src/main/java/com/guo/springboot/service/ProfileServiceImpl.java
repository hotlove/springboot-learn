package com.guo.springboot.service;

import com.guo.springboot.dao.ProfileMapper;
import com.guo.springboot.domain.Profile;
import com.guo.springboot.domain.ProfileExample;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class ProfileServiceImpl implements ProfileService{

    @Resource
    private ProfileMapper profileMapper;

    @Override
    public void insertProfile(Profile profile) throws Exception {
        profileMapper.insertSelective(profile);
        throw new Exception("测试得");
    }

    @Override
    public void deleteProfile(int profileId) throws Exception {
        ProfileExample example = new ProfileExample();
        example.createCriteria().andIdEqualTo(profileId);

        Profile record = new Profile();
        record.setId(profileId);

        profileMapper.updateByExampleSelective(record, example);

    }

    @Cacheable(value = "PROFILEINFO", key = "'profileId_'+#profileId")
    @Override
    public Profile getProfileById(int profileId) {
        return null;
    }
}
