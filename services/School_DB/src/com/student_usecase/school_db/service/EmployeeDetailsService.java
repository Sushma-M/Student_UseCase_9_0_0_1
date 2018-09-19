/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.student_usecase.school_db.EmployeeDetails;

/**
 * Service object for domain model class {@link EmployeeDetails}.
 */
public interface EmployeeDetailsService {

    /**
     * Creates a new EmployeeDetails. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on EmployeeDetails if any.
     *
     * @param employeeDetails Details of the EmployeeDetails to be created; value cannot be null.
     * @return The newly created EmployeeDetails.
     */
	EmployeeDetails create(EmployeeDetails employeeDetails);


	/**
	 * Returns EmployeeDetails by given id if exists.
	 *
	 * @param employeedetailsId The id of the EmployeeDetails to get; value cannot be null.
	 * @return EmployeeDetails associated with the given employeedetailsId.
     * @throws EntityNotFoundException If no EmployeeDetails is found.
	 */
	EmployeeDetails getById(Integer employeedetailsId) throws EntityNotFoundException;

    /**
	 * Find and return the EmployeeDetails by given id if exists, returns null otherwise.
	 *
	 * @param employeedetailsId The id of the EmployeeDetails to get; value cannot be null.
	 * @return EmployeeDetails associated with the given employeedetailsId.
	 */
	EmployeeDetails findById(Integer employeedetailsId);


	/**
	 * Updates the details of an existing EmployeeDetails. It replaces all fields of the existing EmployeeDetails with the given employeeDetails.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on EmployeeDetails if any.
     *
	 * @param employeeDetails The details of the EmployeeDetails to be updated; value cannot be null.
	 * @return The updated EmployeeDetails.
	 * @throws EntityNotFoundException if no EmployeeDetails is found with given input.
	 */
	EmployeeDetails update(EmployeeDetails employeeDetails) throws EntityNotFoundException;

    /**
	 * Deletes an existing EmployeeDetails with the given id.
	 *
	 * @param employeedetailsId The id of the EmployeeDetails to be deleted; value cannot be null.
	 * @return The deleted EmployeeDetails.
	 * @throws EntityNotFoundException if no EmployeeDetails found with the given id.
	 */
	EmployeeDetails delete(Integer employeedetailsId) throws EntityNotFoundException;

	/**
	 * Find all EmployeeDetails matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching EmployeeDetails.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<EmployeeDetails> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all EmployeeDetails matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching EmployeeDetails.
     *
     * @see Pageable
     * @see Page
	 */
    Page<EmployeeDetails> findAll(String query, Pageable pageable);

    /**
	 * Exports all EmployeeDetails matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
	 */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the EmployeeDetails in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the EmployeeDetails.
	 */
	long count(String query);

    /*
     * Returns the associated employeeDetailsesForManagerId for given EmployeeDetails id.
     *
     * @param empId value of empId; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated EmployeeDetails instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<EmployeeDetails> findAssociatedEmployeeDetailsesForManagerId(Integer empId, Pageable pageable);

}

