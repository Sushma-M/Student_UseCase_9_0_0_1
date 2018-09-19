/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

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
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.student_usecase.school_db.AcademicSubjects;
import com.student_usecase.school_db.SubjectDetails;
import com.student_usecase.school_db.service.SubjectDetailsService;


/**
 * Controller object for domain model class SubjectDetails.
 * @see SubjectDetails
 */
@RestController("School_DB.SubjectDetailsController")
@Api(value = "SubjectDetailsController", description = "Exposes APIs to work with SubjectDetails resource.")
@RequestMapping("/School_DB/SubjectDetails")
public class SubjectDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectDetailsController.class);

    @Autowired
	@Qualifier("School_DB.SubjectDetailsService")
	private SubjectDetailsService subjectDetailsService;

	@ApiOperation(value = "Creates a new SubjectDetails instance.")
@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
public SubjectDetails createSubjectDetails(@RequestBody SubjectDetails subjectDetails) {
		LOGGER.debug("Create SubjectDetails with information: {}" , subjectDetails);

		subjectDetails = subjectDetailsService.create(subjectDetails);
		LOGGER.debug("Created SubjectDetails with information: {}" , subjectDetails);

	    return subjectDetails;
	}

    @ApiOperation(value = "Returns the SubjectDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SubjectDetails getSubjectDetails(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting SubjectDetails with id: {}" , id);

        SubjectDetails foundSubjectDetails = subjectDetailsService.getById(id);
        LOGGER.debug("SubjectDetails details with id: {}" , foundSubjectDetails);

        return foundSubjectDetails;
    }

    @ApiOperation(value = "Updates the SubjectDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SubjectDetails editSubjectDetails(@PathVariable("id") Integer id, @RequestBody SubjectDetails subjectDetails) throws EntityNotFoundException {
        LOGGER.debug("Editing SubjectDetails with id: {}" , subjectDetails.getSubjectId());

        subjectDetails.setSubjectId(id);
        subjectDetails = subjectDetailsService.update(subjectDetails);
        LOGGER.debug("SubjectDetails details with id: {}" , subjectDetails);

        return subjectDetails;
    }

    @ApiOperation(value = "Deletes the SubjectDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteSubjectDetails(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting SubjectDetails with id: {}" , id);

        SubjectDetails deletedSubjectDetails = subjectDetailsService.delete(id);

        return deletedSubjectDetails != null;
    }

    @RequestMapping(value = "/subjectName/{subjectName}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching SubjectDetails with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SubjectDetails getBySubjectName(@PathVariable("subjectName") String subjectName) {
        LOGGER.debug("Getting SubjectDetails with uniques key SubjectName");
        return subjectDetailsService.getBySubjectName(subjectName);
    }

    /**
     * @deprecated Use {@link #findSubjectDetails(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of SubjectDetails instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SubjectDetails> searchSubjectDetailsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering SubjectDetails list");
        return subjectDetailsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of SubjectDetails instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SubjectDetails> findSubjectDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering SubjectDetails list");
        return subjectDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of SubjectDetails instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SubjectDetails> filterSubjectDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering SubjectDetails list");
        return subjectDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSubjectDetails(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return subjectDetailsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of SubjectDetails instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countSubjectDetails( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting SubjectDetails");
		return subjectDetailsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getSubjectDetailsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return subjectDetailsService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/academicSubjectses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the academicSubjectses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<AcademicSubjects> findAssociatedAcademicSubjectses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated academicSubjectses");
        return subjectDetailsService.findAssociatedAcademicSubjectses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SubjectDetailsService instance
	 */
	protected void setSubjectDetailsService(SubjectDetailsService service) {
		this.subjectDetailsService = service;
	}

}

