/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/

package com.student_usecase.school_db.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.student_usecase.school_db.service.School_DBQueryExecutorService;
import com.student_usecase.school_db.models.query.*;

@RestController(value = "School_DB.QueryExecutionController")
@RequestMapping("/School_DB/queryExecutor")
@Api(value = "QueryExecutionController", description = "controller class for query execution")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private School_DBQueryExecutorService queryService;

    @RequestMapping(value = "/queries/SV_Top3StudentsFromAllStandards", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Top3StudentsFromAllStandards")
    public Page<SvTop3studentsFromAllStandardsResponse> executeSV_Top3StudentsFromAllStandards(@RequestParam(value = "TESTID", required = false) Integer testid, @RequestParam(value = "ACADEMICYEAR", required = false) String academicyear, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: SV_Top3StudentsFromAllStandards");
        Page<SvTop3studentsFromAllStandardsResponse> _result = queryService.executeSV_Top3StudentsFromAllStandards(testid, academicyear, pageable);
        LOGGER.debug("got the result for named query: SV_Top3StudentsFromAllStandards, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query SV_Top3StudentsFromAllStandards")
    @RequestMapping(value = "/queries/SV_Top3StudentsFromAllStandards/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSV_Top3StudentsFromAllStandards(@PathVariable("exportType") ExportType exportType, @RequestParam(value = "TESTID", required = false) Integer testid, @RequestParam(value = "ACADEMICYEAR", required = false) String academicyear, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: SV_Top3StudentsFromAllStandards");

        return queryService.exportSV_Top3StudentsFromAllStandards(exportType, testid, academicyear, pageable);
    }

    @RequestMapping(value = "/queries/SV_AcademicSubjectsByStandard", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "AcademicSubjectsByStandard")
    public Page<SvAcademicSubjectsByStandardResponse> executeSV_AcademicSubjectsByStandard(@RequestParam(value = "year", required = false) String year, @RequestParam(value = "standard", required = false) Integer standard, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: SV_AcademicSubjectsByStandard");
        Page<SvAcademicSubjectsByStandardResponse> _result = queryService.executeSV_AcademicSubjectsByStandard(year, standard, pageable);
        LOGGER.debug("got the result for named query: SV_AcademicSubjectsByStandard, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query SV_AcademicSubjectsByStandard")
    @RequestMapping(value = "/queries/SV_AcademicSubjectsByStandard/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSV_AcademicSubjectsByStandard(@PathVariable("exportType") ExportType exportType, @RequestParam(value = "year", required = false) String year, @RequestParam(value = "standard", required = false) Integer standard, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: SV_AcademicSubjectsByStandard");

        return queryService.exportSV_AcademicSubjectsByStandard(exportType, year, standard, pageable);
    }

    @RequestMapping(value = "/queries/SV_CountOfStudentPerGrade", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Performance Students Per Grade")
    public Page<SvCountOfStudentPerGradeResponse> executeSV_CountOfStudentPerGrade(@RequestParam(value = "academicYear", required = false) String academicYear, @RequestParam(value = "standardid", required = false) Integer standardid, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: SV_CountOfStudentPerGrade");
        Page<SvCountOfStudentPerGradeResponse> _result = queryService.executeSV_CountOfStudentPerGrade(academicYear, standardid, pageable);
        LOGGER.debug("got the result for named query: SV_CountOfStudentPerGrade, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query SV_CountOfStudentPerGrade")
    @RequestMapping(value = "/queries/SV_CountOfStudentPerGrade/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSV_CountOfStudentPerGrade(@PathVariable("exportType") ExportType exportType, @RequestParam(value = "academicYear", required = false) String academicYear, @RequestParam(value = "standardid", required = false) Integer standardid, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: SV_CountOfStudentPerGrade");

        return queryService.exportSV_CountOfStudentPerGrade(exportType, academicYear, standardid, pageable);
    }

    @RequestMapping(value = "/queries/SV_CountOfStudents", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Count Of Students")
    public Page<SvCountOfStudentsResponse> executeSV_CountOfStudents(@RequestParam(value = "standard", required = false) Integer standard, @RequestParam(value = "year", required = false) String year, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: SV_CountOfStudents");
        Page<SvCountOfStudentsResponse> _result = queryService.executeSV_CountOfStudents(standard, year, pageable);
        LOGGER.debug("got the result for named query: SV_CountOfStudents, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query SV_CountOfStudents")
    @RequestMapping(value = "/queries/SV_CountOfStudents/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSV_CountOfStudents(@PathVariable("exportType") ExportType exportType, @RequestParam(value = "standard", required = false) Integer standard, @RequestParam(value = "year", required = false) String year, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: SV_CountOfStudents");

        return queryService.exportSV_CountOfStudents(exportType, standard, year, pageable);
    }

    @RequestMapping(value = "/queries/SV_GetSTudentResultsForStandard", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "Performance Chart")
    public Page<SvGetStudentResultsForStandardResponse> executeSV_GetSTudentResultsForStandard(@RequestParam(value = "academicYear", required = false) String academicYear, @RequestParam(value = "standardid", required = false) Integer standardid, @RequestParam(value = "testid", required = false) Integer testid, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: SV_GetSTudentResultsForStandard");
        Page<SvGetStudentResultsForStandardResponse> _result = queryService.executeSV_GetSTudentResultsForStandard(academicYear, standardid, testid, pageable);
        LOGGER.debug("got the result for named query: SV_GetSTudentResultsForStandard, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file for query SV_GetSTudentResultsForStandard")
    @RequestMapping(value = "/queries/SV_GetSTudentResultsForStandard/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSV_GetSTudentResultsForStandard(@PathVariable("exportType") ExportType exportType, @RequestParam(value = "academicYear", required = false) String academicYear, @RequestParam(value = "standardid", required = false) Integer standardid, @RequestParam(value = "testid", required = false) Integer testid, Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Exporting named query: SV_GetSTudentResultsForStandard");

        return queryService.exportSV_GetSTudentResultsForStandard(exportType, academicYear, standardid, testid, pageable);
    }

}


