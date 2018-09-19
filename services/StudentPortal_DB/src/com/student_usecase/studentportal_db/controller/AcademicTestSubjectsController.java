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
import com.student_usecase.studentportal_db.AcademicTestSubjects;
import com.student_usecase.studentportal_db.AcademicTestSubjectsId;
import com.student_usecase.studentportal_db.Results;
import com.student_usecase.studentportal_db.service.AcademicTestSubjectsService;

/**
 * Controller object for domain model class AcademicTestSubjects.
 * @see AcademicTestSubjects
 */
@RestController("StudentPortal_DB.AcademicTestSubjectsController")
@Api(value = "AcademicTestSubjectsController", description = "Exposes APIs to work with AcademicTestSubjects resource.")
@RequestMapping("/StudentPortal_DB/AcademicTestSubjects")
public class AcademicTestSubjectsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AcademicTestSubjectsController.class);

    @Autowired
    @Qualifier("StudentPortal_DB.AcademicTestSubjectsService")
    private AcademicTestSubjectsService academicTestSubjectsService;

    @ApiOperation(value = "Creates a new AcademicTestSubjects instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AcademicTestSubjects createAcademicTestSubjects(@RequestBody AcademicTestSubjects academicTestSubjects) {
        LOGGER.debug("Create AcademicTestSubjects with information: {}", academicTestSubjects);
        academicTestSubjects = academicTestSubjectsService.create(academicTestSubjects);
        LOGGER.debug("Created AcademicTestSubjects with information: {}", academicTestSubjects);
        return academicTestSubjects;
    }

    @ApiOperation(value = "Returns the AcademicTestSubjects instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AcademicTestSubjects getAcademicTestSubjects(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard, @RequestParam(value = "testId", required = true) Integer testId, @RequestParam(value = "subjectId", required = true) Integer subjectId) throws EntityNotFoundException {
        AcademicTestSubjectsId academictestsubjectsId = new AcademicTestSubjectsId();
        academictestsubjectsId.setAcademicYear(academicYear);
        academictestsubjectsId.setStandard(standard);
        academictestsubjectsId.setTestId(testId);
        academictestsubjectsId.setSubjectId(subjectId);
        LOGGER.debug("Getting AcademicTestSubjects with id: {}", academictestsubjectsId);
        AcademicTestSubjects academicTestSubjects = academicTestSubjectsService.getById(academictestsubjectsId);
        LOGGER.debug("AcademicTestSubjects details with id: {}", academicTestSubjects);
        return academicTestSubjects;
    }

    @ApiOperation(value = "Updates the AcademicTestSubjects instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public AcademicTestSubjects editAcademicTestSubjects(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard, @RequestParam(value = "testId", required = true) Integer testId, @RequestParam(value = "subjectId", required = true) Integer subjectId, @RequestBody AcademicTestSubjects academicTestSubjects) throws EntityNotFoundException {
        academicTestSubjects.setAcademicYear(academicYear);
        academicTestSubjects.setStandard(standard);
        academicTestSubjects.setTestId(testId);
        academicTestSubjects.setSubjectId(subjectId);
        LOGGER.debug("AcademicTestSubjects details with id is updated with: {}", academicTestSubjects);
        return academicTestSubjectsService.update(academicTestSubjects);
    }

    @ApiOperation(value = "Deletes the AcademicTestSubjects instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteAcademicTestSubjects(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard, @RequestParam(value = "testId", required = true) Integer testId, @RequestParam(value = "subjectId", required = true) Integer subjectId) throws EntityNotFoundException {
        AcademicTestSubjectsId academictestsubjectsId = new AcademicTestSubjectsId();
        academictestsubjectsId.setAcademicYear(academicYear);
        academictestsubjectsId.setStandard(standard);
        academictestsubjectsId.setTestId(testId);
        academictestsubjectsId.setSubjectId(subjectId);
        LOGGER.debug("Deleting AcademicTestSubjects with id: {}", academictestsubjectsId);
        AcademicTestSubjects academicTestSubjects = academicTestSubjectsService.delete(academictestsubjectsId);
        return academicTestSubjects != null;
    }

    /**
     * @deprecated Use {@link #findAcademicTestSubjects(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of AcademicTestSubjects instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AcademicTestSubjects> searchAcademicTestSubjectsByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering AcademicTestSubjects list");
        return academicTestSubjectsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of AcademicTestSubjects instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AcademicTestSubjects> findAcademicTestSubjects(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering AcademicTestSubjects list");
        return academicTestSubjectsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportAcademicTestSubjects(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return academicTestSubjectsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of AcademicTestSubjects instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countAcademicTestSubjects(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting AcademicTestSubjects");
        return academicTestSubjectsService.count(query);
    }

    @RequestMapping(value = "/composite-id/resultses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the resultses instance associated with the given composite-id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Results> findAssociatedResultses(@RequestParam(value = "academicYear", required = true) Date academicYear, @RequestParam(value = "standard", required = true) String standard, @RequestParam(value = "testId", required = true) Integer testId, @RequestParam(value = "subjectId", required = true) Integer subjectId, Pageable pageable) {
        LOGGER.debug("Fetching all associated resultses");
        return academicTestSubjectsService.findAssociatedResultses(academicYear, standard, testId, subjectId, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service AcademicTestSubjectsService instance
	 */
    protected void setAcademicTestSubjectsService(AcademicTestSubjectsService service) {
        this.academicTestSubjectsService = service;
    }
}
