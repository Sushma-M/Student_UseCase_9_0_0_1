/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.student_usecase.studentportal_db.AcademicSubjects;
import com.student_usecase.studentportal_db.Academics;
import com.student_usecase.studentportal_db.AcademicsId;
import com.student_usecase.studentportal_db.StudentAcademics;
import com.student_usecase.studentportal_db.service.AcademicsService;

/**
 * Controller object for domain model class Academics.
 * @see Academics
 */
@RestController("StudentPortal_DB.AcademicsController")
@Api(value = "AcademicsController", description = "Exposes APIs to work with Academics resource.")
@RequestMapping("/StudentPortal_DB/Academics")
public class AcademicsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicsController.class);

    @Autowired
    @Qualifier("StudentPortal_DB.AcademicsService")
    private AcademicsService academicsService;

    @ApiOperation(value = "Creates a new Academics instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Academics createAcademics(@RequestBody Academics academics) {
        LOGGER.debug("Create Academics with information: {}", academics);
        academics = academicsService.create(academics);
        LOGGER.debug("Created Academics with information: {}", academics);
        return academics;
    }

    @ApiOperation(value = "Returns the Academics instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Academics getAcademics(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard) throws EntityNotFoundException {
        AcademicsId academicsId = new AcademicsId();
        academicsId.setAcademicYear(academicYear);
        academicsId.setStandard(standard);
        LOGGER.debug("Getting Academics with id: {}", academicsId);
        Academics academics = academicsService.getById(academicsId);
        LOGGER.debug("Academics details with id: {}", academics);
        return academics;
    }

    @ApiOperation(value = "Updates the Academics instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Academics editAcademics(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard, @RequestBody Academics academics) throws EntityNotFoundException {
        academics.setAcademicYear(academicYear);
        academics.setStandard(standard);
        LOGGER.debug("Academics details with id is updated with: {}", academics);
        return academicsService.update(academics);
    }

    @ApiOperation(value = "Deletes the Academics instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAcademics(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard) throws EntityNotFoundException {
        AcademicsId academicsId = new AcademicsId();
        academicsId.setAcademicYear(academicYear);
        academicsId.setStandard(standard);
        LOGGER.debug("Deleting Academics with id: {}", academicsId);
        Academics academics = academicsService.delete(academicsId);
        return academics != null;
    }

    /**
     * @deprecated Use {@link #findAcademics(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Academics instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Academics> searchAcademicsByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Academics list");
        return academicsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Academics instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Academics> findAcademics(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Academics list");
        return academicsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportAcademics(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return academicsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Academics instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countAcademics(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Academics");
        return academicsService.count(query);
    }

    @RequestMapping(value = "/composite-id/academicSubjectses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the academicSubjectses instance associated with the given composite-id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AcademicSubjects> findAssociatedAcademicSubjectses(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard, Pageable pageable) {
        LOGGER.debug("Fetching all associated academicSubjectses");
        return academicsService.findAssociatedAcademicSubjectses(academicYear, standard, pageable);
    }

    @RequestMapping(value = "/composite-id/studentAcademicses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the studentAcademicses instance associated with the given composite-id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StudentAcademics> findAssociatedStudentAcademicses(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard, Pageable pageable) {
        LOGGER.debug("Fetching all associated studentAcademicses");
        return academicsService.findAssociatedStudentAcademicses(academicYear, standard, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AcademicsService instance
	 */
    protected void setAcademicsService(AcademicsService service) {
        this.academicsService = service;
    }
}
