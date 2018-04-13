package com.guo.springboot.service;

import com.guo.springboot.domain.Profile;

public interface ProfileService {

    void insertProfile(Profile profile) throws Exception;

    void deleteProfile(int profileId) throws Exception;

    Profile getProfileById(int profileId);
}
