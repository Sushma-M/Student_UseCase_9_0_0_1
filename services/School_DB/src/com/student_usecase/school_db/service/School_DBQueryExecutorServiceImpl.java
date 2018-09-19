/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/

package com.student_usecase.school_db.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.file.model.Downloadable;

import com.student_usecase.school_db.models.query.*;

@Service
public class School_DBQueryExecutorServiceImpl implements School_DBQueryExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(School_DBQueryExecutorServiceImpl.class);

    @Autowired
    @Qualifier("School_DBWMQueryExecutor")
    private WMQueryExecutor queryExecutor;

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<SvTop3studentsFromAllStandardsResponse> executeSV_Top3StudentsFromAllStandards(Integer testid, String academicyear, Pageable pageable) {
        Map params = new HashMap(2);

        params.put("TESTID", testid);
        params.put("ACADEMICYEAR", academicyear);

        return queryExecutor.executeNamedQuery("SV_Top3StudentsFromAllStandards", params, SvTop3studentsFromAllStandardsResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Downloadable exportSV_Top3StudentsFromAllStandards(ExportType exportType, Integer testid, String academicyear, Pageable pageable) {
        Map params = new HashMap(2);

        params.put("TESTID", testid);
        params.put("ACADEMICYEAR", academicyear);

        return queryExecutor.exportNamedQueryData("SV_Top3StudentsFromAllStandards", params, exportType, SvTop3studentsFromAllStandardsResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<SvAcademicSubjectsByStandardResponse> executeSV_AcademicSubjectsByStandard(String year, Integer standard, Pageable pageable) {
        Map params = new HashMap(2);

        params.put("year", year);
        params.put("standard", standard);

        return queryExecutor.executeNamedQuery("SV_AcademicSubjectsByStandard", params, SvAcademicSubjectsByStandardResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Downloadable exportSV_AcademicSubjectsByStandard(ExportType exportType, String year, Integer standard, Pageable pageable) {
        Map params = new HashMap(2);

        params.put("year", year);
        params.put("standard", standard);

        return queryExecutor.exportNamedQueryData("SV_AcademicSubjectsByStandard", params, exportType, SvAcademicSubjectsByStandardResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<SvCountOfStudentPerGradeResponse> executeSV_CountOfStudentPerGrade(String academicYear, Integer standardid, Pageable pageable) {
        Map params = new HashMap(2);

        params.put("academicYear", academicYear);
        params.put("standardid", standardid);

        return queryExecutor.executeNamedQuery("SV_CountOfStudentPerGrade", params, SvCountOfStudentPerGradeResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Downloadable exportSV_CountOfStudentPerGrade(ExportType exportType, String academicYear, Integer standardid, Pageable pageable) {
        Map params = new HashMap(2);

        params.put("academicYear", academicYear);
        params.put("standardid", standardid);

        return queryExecutor.exportNamedQueryData("SV_CountOfStudentPerGrade", params, exportType, SvCountOfStudentPerGradeResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<SvCountOfStudentsResponse> executeSV_CountOfStudents(Integer standard, String year, Pageable pageable) {
        Map params = new HashMap(2);

        params.put("standard", standard);
        params.put("year", year);

        return queryExecutor.executeNamedQuery("SV_CountOfStudents", params, SvCountOfStudentsResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Downloadable exportSV_CountOfStudents(ExportType exportType, Integer standard, String year, Pageable pageable) {
        Map params = new HashMap(2);

        params.put("standard", standard);
        params.put("year", year);

        return queryExecutor.exportNamedQueryData("SV_CountOfStudents", params, exportType, SvCountOfStudentsResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<SvGetStudentResultsForStandardResponse> executeSV_GetSTudentResultsForStandard(String academicYear, Integer standardid, Integer testid, Pageable pageable) {
        Map params = new HashMap(3);

        params.put("academicYear", academicYear);
        params.put("standardid", standardid);
        params.put("testid", testid);

        return queryExecutor.executeNamedQuery("SV_GetSTudentResultsForStandard", params, SvGetStudentResultsForStandardResponse.class, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Downloadable exportSV_GetSTudentResultsForStandard(ExportType exportType, String academicYear, Integer standardid, Integer testid, Pageable pageable) {
        Map params = new HashMap(3);

        params.put("academicYear", academicYear);
        params.put("standardid", standardid);
        params.put("testid", testid);

        return queryExecutor.exportNamedQueryData("SV_GetSTudentResultsForStandard", params, exportType, SvGetStudentResultsForStandardResponse.class, pageable);
    }

}


