/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentresults;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.security.SecurityService;
import com.student_usecase.school_db.StudentAcademics;
import com.student_usecase.school_db.service.StudentAcademicsService;
import com.student_usecase.school_db.TestConducted;
import com.student_usecase.school_db.service.TestConductedService;
import com.student_usecase.school_db.Results;
import com.student_usecase.school_db.service.ResultsService;

import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;


/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 */
@ExposeToClient
public class StudentResults {

    private static final Logger logger = LoggerFactory.getLogger(StudentResults.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private StudentAcademicsService studentAcademicsService;

    @Autowired
    private TestConductedService testConductedService;

    @Autowired
    private ResultsService resultsService;
    
    private Page<StudentAcademics> getAcademicStudentDetails(String academicYear, int standardId){
        // get Students based on Academic Year and Standard
        String  query = "academicYear startswith "+academicYear+" AND standardId = '" +standardId+"'";
        Page<StudentAcademics> filteredStudents = studentAcademicsService.findAll(query, null);
        return filteredStudents;
    }
        
    private Page<TestConducted> getTestConductedDetails(String academicYear, int standardId, int subjectId, int testId){
        // get Test Conducted details based on Academic Year, Standard, SubjectId and TestId
      String query = "academicYear startswith "+academicYear+" AND standardId = '" +standardId+"' AND subjectId = '"+subjectId+"' AND testId = '"+testId+"'";
        Page<TestConducted> filteredTests = testConductedService.findAll(query, null) ;
        return filteredTests;
    }
    
    private Page<Results> getResults(int testConductedId, String academicYear, int standardId){
        // get Results based TestConductedId, AcademicYear, Standard
        String query="testConductedId = '"+testConductedId+"' AND academicYear startswith "+academicYear+" AND standardId = '"+standardId+"'";
        Page<Results> filteredResults = resultsService.findAll(query, null) ;
        return filteredResults;
    }
    
    public Results insertResults(String academicYear, int standardId, int subjectId, int testId, int testConductedId){
        //insert student records into Results Table
        Page<StudentAcademics> filteredStudents = getAcademicStudentDetails( academicYear, standardId);
        Page<TestConducted> filteredTests = getTestConductedDetails(academicYear,  standardId,  subjectId,  testId);
        Page<Results> filterResults=getResults(testConductedId,academicYear,standardId);
        Results results = new Results();
        
        List<StudentAcademics> studentAcademicList = filteredStudents.getContent();
        List<TestConducted> testConductedList = filteredTests.getContent();
        List<Results> resultsList = filterResults.getContent();
        if(resultsList.size()==0||resultsList.equals(null)){
            for(int i=0; i<testConductedList.size();i++){
                for(int j=0;j<studentAcademicList.size();j++){
                    results.setTestConductedId(testConductedList.get(i).getTestConductedId());
                    results.setAcademicYear(testConductedList.get(i).getAcademicYear()); 
                    results.setStandardId(testConductedList.get(i).getStandardId());
                    results.setStudentId(studentAcademicList.get(j).getStudentId());
                    resultsService.create(results);
                }
            }
        }
        return results;
    }
}
