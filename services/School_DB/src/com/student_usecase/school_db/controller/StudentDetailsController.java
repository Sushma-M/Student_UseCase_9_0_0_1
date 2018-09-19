/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.TypeMismatchException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.student_usecase.school_db.Results;
import com.student_usecase.school_db.StudentAcademics;
import com.student_usecase.school_db.StudentDetails;
import com.student_usecase.school_db.service.StudentDetailsService;


/**
 * Controller object for domain model class StudentDetails.
 * @see StudentDetails
 */
@RestController("School_DB.StudentDetailsController")
@Api(value = "StudentDetailsController", description = "Exposes APIs to work with StudentDetails resource.")
@RequestMapping("/School_DB/StudentDetails")
public class StudentDetailsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDetailsController.class);

    @Autowired
	@Qualifier("School_DB.StudentDetailsService")
	private StudentDetailsService studentDetailsService;

	@ApiOperation(value = "Creates a new StudentDetails instance.")
@RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
public StudentDetails createStudentDetails(@RequestPart("wm_data_json") StudentDetails studentDetails, @RequestPart(value = "profilePic", required = false) MultipartFile _profilePic) {
		LOGGER.debug("Create StudentDetails with information: {}" , studentDetails);

    studentDetails.setProfilePic(WMMultipartUtils.toByteArray(_profilePic));
		studentDetails = studentDetailsService.create(studentDetails);
		LOGGER.debug("Created StudentDetails with information: {}" , studentDetails);

	    return studentDetails;
	}

    @ApiOperation(value = "Returns the StudentDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentDetails getStudentDetails(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting StudentDetails with id: {}" , id);

        StudentDetails foundStudentDetails = studentDetailsService.getById(id);
        LOGGER.debug("StudentDetails details with id: {}" , foundStudentDetails);

        return foundStudentDetails;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in StudentDetails instance" )
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces="application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getStudentDetailsBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value="download", defaultValue = "false") boolean download) {

        LOGGER.debug("Retrieves content for the given BLOB field {} in StudentDetails instance" , fieldName);

        if(!WMRuntimeUtils.isLob(StudentDetails.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        StudentDetails studentDetails = studentDetailsService.getById(id);

        return WMMultipartUtils.buildDownloadResponseForBlob(studentDetails, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the StudentDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentDetails editStudentDetails(@PathVariable("id") Integer id, @RequestBody StudentDetails studentDetails) throws EntityNotFoundException {
        LOGGER.debug("Editing StudentDetails with id: {}" , studentDetails.getStudentId());

        studentDetails.setStudentId(id);
        studentDetails = studentDetailsService.update(studentDetails);
        LOGGER.debug("StudentDetails details with id: {}" , studentDetails);

        return studentDetails;
    }

    @ApiOperation(value = "Updates the StudentDetails instance associated with the given id.This API should be used when StudentDetails instance fields that require multipart data.") 
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentDetails editStudentDetails(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        StudentDetails newStudentDetails = WMMultipartUtils.toObject(multipartHttpServletRequest, StudentDetails.class, "School_DB");
        newStudentDetails.setStudentId(id);

        StudentDetails oldStudentDetails = studentDetailsService.getById(id);
        WMMultipartUtils.updateLobsContent(oldStudentDetails, newStudentDetails);
        LOGGER.debug("Updating StudentDetails with information: {}" , newStudentDetails);

        return studentDetailsService.update(newStudentDetails);
    }

    @ApiOperation(value = "Deletes the StudentDetails instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteStudentDetails(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting StudentDetails with id: {}" , id);

        StudentDetails deletedStudentDetails = studentDetailsService.delete(id);

        return deletedStudentDetails != null;
    }

    @RequestMapping(value = "/studentIdentificationId/{studentIdentificationId}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching StudentDetails with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StudentDetails getByStudentIdentificationId(@PathVariable("studentIdentificationId") Integer studentIdentificationId) {
        LOGGER.debug("Getting StudentDetails with uniques key StudentIdentificationId");
        return studentDetailsService.getByStudentIdentificationId(studentIdentificationId);
    }

    /**
     * @deprecated Use {@link #findStudentDetails(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of StudentDetails instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StudentDetails> searchStudentDetailsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering StudentDetails list");
        return studentDetailsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of StudentDetails instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StudentDetails> findStudentDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering StudentDetails list");
        return studentDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of StudentDetails instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StudentDetails> filterStudentDetails(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering StudentDetails list");
        return studentDetailsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportStudentDetails(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return studentDetailsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of StudentDetails instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countStudentDetails( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting StudentDetails");
		return studentDetailsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getStudentDetailsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return studentDetailsService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/resultses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the resultses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Results> findAssociatedResultses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated resultses");
        return studentDetailsService.findAssociatedResultses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/studentAcademicses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the studentAcademicses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<StudentAcademics> findAssociatedStudentAcademicses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated studentAcademicses");
        return studentDetailsService.findAssociatedStudentAcademicses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service StudentDetailsService instance
	 */
	protected void setStudentDetailsService(StudentDetailsService service) {
		this.studentDetailsService = service;
	}

}

