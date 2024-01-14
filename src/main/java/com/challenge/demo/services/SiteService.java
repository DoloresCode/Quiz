package com.challenge.demo.services;

import com.challenge.demo.entities.Site;
import com.challenge.demo.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    // Create a new Site
    public Site save(Site site) {
        site.setSiteUUID(UUID.randomUUID());
        return siteRepository.save(site);
    }

    // Get Site by ID
    public Optional<Site> findById(Long id) {
        return siteRepository.findById(id);
    }

    // Get Site by Uuid
    public Site findByUuid(UUID uuid) {
        return siteRepository.findByUuid(uuid);
    }

    // Get all Sites
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    // Update Site
    public Site updateSite(Site siteDetails) {
        return siteRepository.save(siteDetails);
    }

    // Delete Site
    public void delete(Site site) {
        siteRepository.delete(site);
    }
}
