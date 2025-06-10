package com.bni.bni.service;

import com.bni.bni.entity.Profile;
import com.bni.bni.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository repository;

    public Profile createProfile(Profile profile) {
        profile.setCreatedAt(OffsetDateTime.now());
        profile.setUpdatedAt(OffsetDateTime.now());
        return repository.save(profile);
    }

    public Optional<Profile> getProfileById(Long id) {
        return repository.findById(id);
    }
}
