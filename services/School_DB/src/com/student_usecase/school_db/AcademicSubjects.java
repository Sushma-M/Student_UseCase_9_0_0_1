/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.school_db;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * AcademicSubjects generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`ACADEMIC_SUBJECTS`")
@IdClass(AcademicSubjectsId.class)
public class AcademicSubjects implements Serializable {

    private String academicYear;
    private Integer standardId;
    private Integer subjectId;
    private String subjectTeacher;
    private Academics academics;
    private SubjectDetails subjectDetails;
    private List<AcademicTestSubjects> academicTestSubjectses = new ArrayList<>();

    @Id
    @Column(name = "`ACADEMIC_YEAR`", nullable = false, length = 255)
    public String getAcademicYear() {
        return this.academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @Id
    @Column(name = "`STANDARD_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getStandardId() {
        return this.standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    @Id
    @Column(name = "`SUBJECT_ID`", nullable = false, scale = 0, precision = 10)
    public Integer getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    @Column(name = "`SUBJECT_TEACHER`", nullable = true, length = 255)
    public String getSubjectTeacher() {
        return this.subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    
    
    public Academics getAcademics() {
        return this.academics;
    }

    public void setAcademics(Academics academics) {
        if(academics != null) {
            this.academicYear = academics.getAcademicYear();
            this.standardId = academics.getStandardId();
        }

        this.academics = academics;
    }

    
    
    public SubjectDetails getSubjectDetails() {
        return this.subjectDetails;
    }

    public void setSubjectDetails(SubjectDetails subjectDetails) {
        if(subjectDetails != null) {
            this.subjectId = subjectDetails.getSubjectId();
        }

        this.subjectDetails = subjectDetails;
    }

    
    public List<AcademicTestSubjects> getAcademicTestSubjectses() {
        return this.academicTestSubjectses;
    }

    public void setAcademicTestSubjectses(List<AcademicTestSubjects> academicTestSubjectses) {
        this.academicTestSubjectses = academicTestSubjectses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcademicSubjects)) return false;
        final AcademicSubjects academicSubjects = (AcademicSubjects) o;
        return Objects.equals(getAcademicYear(), academicSubjects.getAcademicYear()) &&
                Objects.equals(getStandardId(), academicSubjects.getStandardId()) &&
                Objects.equals(getSubjectId(), academicSubjects.getSubjectId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAcademicYear(),
                getStandardId(),
                getSubjectId());
    }
}

