package com.bni.bni.service;

import com.bni.bni.entity.Profile;
import com.bni.bni.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateProfile(Long id, Profile newProfile) {
        return profileRepository.findById(id)
            .map(existing -> {
                existing.setUserId(newProfile.getUserId());
                existing.setFirstName(newProfile.getFirstName());
                existing.setLastName(newProfile.getLastName());
                existing.setPlaceOfBirth(newProfile.getPlaceOfBirth());
                existing.setDateOfBirth(newProfile.getDateOfBirth());
                return profileRepository.save(existing);
            }).orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}

