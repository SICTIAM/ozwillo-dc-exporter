package org.ozwillo.dcexporter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
public class SynchronizerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizerService.class);

    @Autowired
    private DatacoreService datacoreService;

    @Autowired
    private CkanService ckanService;

    @Autowired
    private SystemUserService systemUserService;

    public enum SyncType {
        POI,
        ORG
    };

    @Scheduled(fixedDelayString = "${application.syncDelay}")
    public void synchronizeOrgs() {
        systemUserService.runAs(() -> this.sync(SynchronizerService.SyncType.ORG));
    }

    public String sync(SyncType syncType) {
        Optional<File> optionalResourceCsvFile = syncType.equals(SyncType.ORG) ?
            datacoreService.exportResourceToCsv("org_1", "org:Organization_0") :
            datacoreService.exportResourceToCsv("poi_0", "poi:Geoloc_0");

        if (!optionalResourceCsvFile.isPresent()) {
            LOGGER.error("Did not get the resource's CSV file, stopping");
            return "KO";
        }

        File csvFile = optionalResourceCsvFile.get();

        if (syncType.equals(SyncType.ORG))
            ckanService.updateResourceData("organisations", "f7d1d5dc-45c3-48fc-bca4-d9d98ba50d3a", csvFile);
        else
            ckanService.updateResourceData("points-interet-poi", "b4fca7f7-773a-4bca-87f0-f54437082817", csvFile);

        return "OK";
    }
}