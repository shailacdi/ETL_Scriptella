package com.etlhelper;

import java.util.Arrays;

/**
 * This class extracts and handles all the validations required for carrying out ETL of employee_unnormalized data from
 * a tsv file. The getFields method extracts fields and invokes validation methods such as isNumeric and isValidFieldName for fields 
 * such as id and names respectively
 * @author      Shaila
 * @since       1.0
 */
public class ETLValidator {
    //default constructor 
    public ETLValidator(){
    }
    
    /**
     * This method checks if the ID fields are numeric data type
     * @param input - parsed input from the tsv file
     * @return  - true if validation succeeds, else false
     */
    public boolean isNumeric(String input) {
        //use try catch should the conversion fail
        try {
            int num = Integer.parseInt(input);
            if (num < 0)
                return false;
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * This method 
     * @param input - parsed input from tsv file
     * @return - true if validation succeeds else false
     */
    public boolean isValidFieldName(String input) {
        String field = input.trim();
        if (field.length() > 0)
            return true;
        else
            return false;
    }

    /**
     * This method breaks down a line input from a tsv file, parses them, validates them and 
     * sets the values as java system properties
     * @param record - a line from the input data file
     * @param num_fields - number of fields per record as specified in the etl.properties file
     * @return - true is all good, else false
     */
    public boolean getFields(String record, String num_fields){
        int number_of_fields = 0;
        try{
            number_of_fields = Integer.parseInt(num_fields);
        }catch (Exception e) {
            return false;
        }

        //if empty or blank spaces string in tsv
        if (record.length() == 0)
            return false;

        String[] fields = record.split("\t");

        //check if there are fewer or extra fields than expected to load
        if (fields.length != number_of_fields)
            return false;
        
        //check if department id is numeric  
        if (isNumeric(fields[0]))
            java.lang.System.setProperty("departmentID", fields[0]);
        else 
            return false;
        //check if employee id is numeric
        if (isNumeric(fields[2]))
            java.lang.System.setProperty("employeeID", fields[2]);          
        else 
            return false;
        
        //check if department name is not empty
        if (isValidFieldName(fields[1]))
            java.lang.System.setProperty("departmentName", fields[1]);
        else 
            return false;
        //if employee name has no quotes
        if (!isValidFieldName(fields[3]))
            return false;
        /*employee name is handled considering the following formats
            Donald Trump => Trump, Donald
            Donald J. Trump => Trump, Donald J.
            Donald J. Trump Jr. => Trump, Donald J. Jr.
            Donald John Trump => Trump, Donald John 
            first name is not null, no constraint on last name
        */
        
        //name might have apostrophe O'Connor which needs to use escape character
        String fullName = fields[3].trim().replace("\"", "").replace("'", "\'");
        //check again after stripping quotes
        if (!isValidFieldName(fullName))
            return false;
        
        //finally split into first and last names
        String[] name = fullName.split(",");    
        String firstName = null;
        String lastName = null;
        if (name.length > 1) {
            //trim all words in name
            for (int i = 0; i < name.length; i++)
                name[i] = name[i].trim();
            
            lastName = name[0]; 
            //for firstname, use all remaining names    
            String[] fname = Arrays.copyOfRange(name, 1, name.length);
            firstName = String.join(" ",fname).trim();
        } else {
            firstName = name[0].trim();
            lastName = "";
        } 
        
        java.lang.System.setProperty("employeeFirstName", firstName);        
        java.lang.System.setProperty("employeeLastName", lastName);        
       
        return true ;
    }

}