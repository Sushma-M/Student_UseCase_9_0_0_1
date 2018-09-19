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

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.student_usecase.school_db.StudentDetails;
import com.student_usecase.school_db.StudentIdentification;


/**
 * ServiceImpl object for domain model class StudentIdentification.
 *
 * @see StudentIdentification
 */
@Service("School_DB.StudentIdentificationService")
public class StudentIdentificationServiceImpl implements StudentIdentificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentIdentificationServiceImpl.class);

    @Autowired
	@Qualifier("School_DB.StudentDetailsService")
	private StudentDetailsService studentDetailsService;

    @Autowired
    @Qualifier("School_DB.StudentIdentificationDao")
    private WMGenericDao<StudentIdentification, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<StudentIdentification, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
	public StudentIdentification create(StudentIdentification studentIdentification) {
        LOGGER.debug("Creating a new StudentIdentification with information: {}", studentIdentification);
        StudentIdentification studentIdentificationCreated = this.wmGenericDao.create(studentIdentification);
        if(studentIdentificationCreated.getStudentDetails() != null) {
            StudentDetails studentDetails = studentIdentificationCreated.getStudentDetails();
            LOGGER.debug("Creating a new child StudentDetails with information: {}", studentDetails);
            studentDetails.setStudentIdentification(studentIdentificationCreated);
            studentDetailsService.create(studentDetails);
        }
        return studentIdentificationCreated;
    }

	@Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public StudentIdentification getById(Integer studentidentificationId) throws EntityNotFoundException {
        LOGGER.debug("Finding StudentIdentification by id: {}", studentidentificationId);
        StudentIdentification studentIdentification = this.wmGenericDao.findById(studentidentificationId);
        if (studentIdentification == null){
            LOGGER.debug("No StudentIdentification found with id: {}", studentidentificationId);
            throw new EntityNotFoundException(String.valueOf(studentidentificationId));
        }
        return studentIdentification;
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public StudentIdentification findById(Integer studentidentificationId) {
        LOGGER.debug("Finding StudentIdentification by id: {}", studentidentificationId);
        return this.wmGenericDao.findById(studentidentificationId);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public StudentIdentification getByIdentificationNumber(String identificationNumber) {
        Map<String, Object> identificationNumberMap = new HashMap<>();
        identificationNumberMap.put("identificationNumber", identificationNumber);

        LOGGER.debug("Finding StudentIdentification by unique keys: {}", identificationNumberMap);
        StudentIdentification studentIdentification = this.wmGenericDao.findByUniqueKey(identificationNumberMap);

        if (studentIdentification == null){
            LOGGER.debug("No StudentIdentification found with given unique key values: {}", identificationNumberMap);
            throw new EntityNotFoundException(String.valueOf(identificationNumberMap));
        }

        return studentIdentification;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "School_DBTransactionManager")
	@Override
	public StudentIdentification update(StudentIdentification studentIdentification) throws EntityNotFoundException {
        LOGGER.debug("Updating StudentIdentification with information: {}", studentIdentification);
        this.wmGenericDao.update(studentIdentification);

        Integer studentidentificationId = studentIdentification.getIdentificationId();

        return this.wmGenericDao.findById(studentidentificationId);
    }

    @Transactional(value = "School_DBTransactionManager")
	@Override
	public StudentIdentification delete(Integer studentidentificationId) throws EntityNotFoundException {
        LOGGER.debug("Deleting StudentIdentification with id: {}", studentidentificationId);
        StudentIdentification deleted = this.wmGenericDao.findById(studentidentificationId);
        if (deleted == null) {
            LOGGER.debug("No StudentIdentification found with id: {}", studentidentificationId);
            throw new EntityNotFoundException(String.valueOf(studentidentificationId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public Page<StudentIdentification> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all StudentIdentifications");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<StudentIdentification> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all StudentIdentifications");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service School_DB for table StudentIdentification to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
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

