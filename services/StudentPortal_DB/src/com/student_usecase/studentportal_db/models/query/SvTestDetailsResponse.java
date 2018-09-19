/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.student_usecase.studentportal_db.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class SvTestDetailsResponse implements Serializable {


    @JsonProperty("TEST_ID")
    @ColumnAlias("TEST_ID")
    private Integer testId;

    @JsonProperty("TEST_NAME")
    @ColumnAlias("TEST_NAME")
    private String testName;

    @JsonProperty("count(TEST_ID)")
    @ColumnAlias("count(TEST_ID)")
    private BigInteger count_testId_;

    public Integer getTestId() {
        return this.testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return this.testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public BigInteger getCount_testId_() {
        return this.count_testId_;
    }

    public void setCount_testId_(BigInteger count_testId_) {
        this.count_testId_ = count_testId_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SvTestDetailsResponse)) return false;
        final SvTestDetailsResponse svTestDetailsResponse = (SvTestDetailsResponse) o;
        return Objects.equals(getTestId(), svTestDetailsResponse.getTestId()) &&
                Objects.equals(getTestName(), svTestDetailsResponse.getTestName()) &&
                Objects.equals(getCount_testId_(), svTestDetailsResponse.getCount_testId_());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTestId(),
                getTestName(),
                getCount_testId_());
    }
}