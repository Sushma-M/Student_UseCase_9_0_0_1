/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
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
import com.student_usecase.studentportal_db.UserLogin;
import com.student_usecase.studentportal_db.service.UserLoginService;

/**
 * Controller object for domain model class UserLogin.
 * @see UserLogin
 */
@RestController("StudentPortal_DB.UserLoginController")
@Api(value = "UserLoginController", description = "Exposes APIs to work with UserLogin resource.")
@RequestMapping("/StudentPortal_DB/UserLogin")
public class UserLoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    @Qualifier("StudentPortal_DB.UserLoginService")
    private UserLoginService userLoginService;

    @ApiOperation(value = "Creates a new UserLogin instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserLogin createUserLogin(@RequestBody UserLogin userLogin) {
        LOGGER.debug("Create UserLogin with information: {}", userLogin);
        userLogin = userLoginService.create(userLogin);
        LOGGER.debug("Created UserLogin with information: {}", userLogin);
        return userLogin;
    }

    @ApiOperation(value = "Returns the UserLogin instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserLogin getUserLogin(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting UserLogin with id: {}", id);
        UserLogin foundUserLogin = userLoginService.getById(id);
        LOGGER.debug("UserLogin details with id: {}", foundUserLogin);
        return foundUserLogin;
    }

    @ApiOperation(value = "Updates the UserLogin instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public UserLogin editUserLogin(@PathVariable("id") Integer id, @RequestBody UserLogin userLogin) throws EntityNotFoundException {
        LOGGER.debug("Editing UserLogin with id: {}", userLogin.getUserId());
        userLogin.setUserId(id);
        userLogin = userLoginService.update(userLogin);
        LOGGER.debug("UserLogin details with id: {}", userLogin);
        return userLogin;
    }

    @ApiOperation(value = "Deletes the UserLogin instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteUserLogin(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting UserLogin with id: {}", id);
        UserLogin deletedUserLogin = userLoginService.delete(id);
        return deletedUserLogin != null;
    }

    /**
     * @deprecated Use {@link #findUserLogins(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of UserLogin instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserLogin> searchUserLoginsByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering UserLogins list");
        return userLoginService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of UserLogin instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserLogin> findUserLogins(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering UserLogins list");
        return userLoginService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportUserLogins(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return userLoginService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of UserLogin instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countUserLogins(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting UserLogins");
        return userLoginService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserLoginService instance
	 */
    protected void setUserLoginService(UserLoginService service) {
        this.userLoginService = service;
    }
}
