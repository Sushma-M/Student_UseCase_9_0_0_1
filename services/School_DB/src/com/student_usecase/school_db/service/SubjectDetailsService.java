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

import com.student_usecase.school_db.AcademicSubjects;
import com.student_usecase.school_db.SubjectDetails;

/**
 * Service object for domain model class {@link SubjectDetails}.
 */
public interface SubjectDetailsService {

    /**
     * Creates a new SubjectDetails. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on SubjectDetails if any.
     *
     * @param subjectDetails Details of the SubjectDetails to be created; value cannot be null.
     * @return The newly created SubjectDetails.
     */
	SubjectDetails create(SubjectDetails subjectDetails);


	/**
	 * Returns SubjectDetails by given id if exists.
	 *
	 * @param subjectdetailsId The id of the SubjectDetails to get; value cannot be null.
	 * @return SubjectDetails associated with the given subjectdetailsId.
     * @throws EntityNotFoundException If no SubjectDetails is found.
	 */
	SubjectDetails getById(Integer subjectdetailsId) throws EntityNotFoundException;

    /**
	 * Find and return the SubjectDetails by given id if exists, returns null otherwise.
	 *
	 * @param subjectdetailsId The id of the SubjectDetails to get; value cannot be null.
	 * @return SubjectDetails associated with the given subjectdetailsId.
	 */
	SubjectDetails findById(Integer subjectdetailsId);

    /**
	 * Find and return the SubjectDetails for given subjectName  if exists.
	 *
	 * @param subjectName value of subjectName; value cannot be null.
	 * @return SubjectDetails associated with the given inputs.
     * @throws EntityNotFoundException if no matching SubjectDetails found.
	 */
    SubjectDetails getBySubjectName(String subjectName)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing SubjectDetails. It replaces all fields of the existing SubjectDetails with the given subjectDetails.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on SubjectDetails if any.
     *
	 * @param subjectDetails The details of the SubjectDetails to be updated; value cannot be null.
	 * @return The updated SubjectDetails.
	 * @throws EntityNotFoundException if no SubjectDetails is found with given input.
	 */
	SubjectDetails update(SubjectDetails subjectDetails) throws EntityNotFoundException;

    /**
	 * Deletes an existing SubjectDetails with the given id.
	 *
	 * @param subjectdetailsId The id of the SubjectDetails to be deleted; value cannot be null.
	 * @return The deleted SubjectDetails.
	 * @throws EntityNotFoundException if no SubjectDetails found with the given id.
	 */
	SubjectDetails delete(Integer subjectdetailsId) throws EntityNotFoundException;

	/**
	 * Find all SubjectDetails matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching SubjectDetails.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<SubjectDetails> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all SubjectDetails matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching SubjectDetails.
     *
     * @see Pageable
     * @see Page
	 */
    Page<SubjectDetails> findAll(String query, Pageable pageable);

    /**
	 * Exports all SubjectDetails matching the given input query to the given exportType format.
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
	 * Retrieve the count of the SubjectDetails in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the SubjectDetails.
	 */
	long count(String query);

    /*
     * Returns the associated academicSubjectses for given SubjectDetails id.
     *
     * @param subjectId value of subjectId; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated AcademicSubjects instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<AcademicSubjects> findAssociatedAcademicSubjectses(Integer subjectId, Pageable pageable);

}

