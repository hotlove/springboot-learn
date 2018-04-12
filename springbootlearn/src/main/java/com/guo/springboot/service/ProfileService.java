package com.guo.springboot.service;

import com.guo.springboot.domain.Profile;

public interface ProfileService {

    void insertProfile(Profile profile);

    void deleteProfile(int profileId);
}
