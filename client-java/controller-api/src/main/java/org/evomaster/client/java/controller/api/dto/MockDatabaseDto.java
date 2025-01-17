package org.evomaster.client.java.controller.api.dto;

/**
 * mock object for database
 */
public class MockDatabaseDto {

    /**
     * a key refers to the Web API
     */
    public String appKey;

    /**
     * it refers to a type of database or related framework, eg, mybatis
     * nullable
     *
     * such info might be used by mocking technique for handling mock object
     */
    public String databaseOrFrameworkType;

    /**
     * it refers to a type of SQL, eg, select
     * nullable
     */
    public String commandType;

    /**
     * sql command
     * nullable
     */
    public String sqlCommand;

    /**
     * it refers to a command to be mocked
     * not nullable
     */
    public String commandName;

    /**
     * it represents what requests are accepted for enabling
     * this mock object
     * null represents any request is acceptable
     */
    public String requests;

    /**
     * response to return
     */
    public String response;

    /**
     * it represents the datatype of response
     */
    public String responseFullType;

}
