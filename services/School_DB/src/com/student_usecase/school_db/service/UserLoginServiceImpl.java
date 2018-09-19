/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.student_usecase.school_db.UserLogin;


/**
 * ServiceImpl object for domain model class UserLogin.
 *
 * @see UserLogin
 */
@Service("School_DB.UserLoginService")
@Validated
public class UserLoginServiceImpl implements UserLoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginServiceImpl.class);


    @Autowired
    @Qualifier("School_DB.UserLoginDao")
    private WMGenericDao<UserLogin, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<UserLogin, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "School_DBTransactionManager")
    @Override
	public UserLogin create(UserLogin userLogin) {
        LOGGER.debug("Creating a new UserLogin with information: {}", userLogin);
        UserLogin userLoginCreated = this.wmGenericDao.create(userLogin);
        return userLoginCreated;
    }

	@Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public UserLogin getById(Integer userloginId) throws EntityNotFoundException {
        LOGGER.debug("Finding UserLogin by id: {}", userloginId);
        UserLogin userLogin = this.wmGenericDao.findById(userloginId);
        if (userLogin == null){
            LOGGER.debug("No UserLogin found with id: {}", userloginId);
            throw new EntityNotFoundException(String.valueOf(userloginId));
        }
        return userLogin;
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public UserLogin findById(Integer userloginId) {
        LOGGER.debug("Finding UserLogin by id: {}", userloginId);
        return this.wmGenericDao.findById(userloginId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "School_DBTransactionManager")
	@Override
	public UserLogin update(UserLogin userLogin) throws EntityNotFoundException {
        LOGGER.debug("Updating UserLogin with information: {}", userLogin);
        this.wmGenericDao.update(userLogin);

        Integer userloginId = userLogin.getUserId();

        return this.wmGenericDao.findById(userloginId);
    }

    @Transactional(value = "School_DBTransactionManager")
	@Override
	public UserLogin delete(Integer userloginId) throws EntityNotFoundException {
        LOGGER.debug("Deleting UserLogin with id: {}", userloginId);
        UserLogin deleted = this.wmGenericDao.findById(userloginId);
        if (deleted == null) {
            LOGGER.debug("No UserLogin found with id: {}", userloginId);
            throw new EntityNotFoundException(String.valueOf(userloginId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public Page<UserLogin> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all UserLogins");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Page<UserLogin> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all UserLogins");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service School_DB for table UserLogin to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "School_DBTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}

