# ETL_Tool

This is a ETL script to import data from a tab separated file containing unnormalized data, validate fields, normalize data and execute few queries on the normalized data. 
The end goal is to create a report listing all departments containing 100+ employees.
This has been developed using Scriptella v1.1 (http://scriptella.org/) which is an open source ETL (Extract-Transform-Load) 
and script execution tool.


Note :
1. MySQL has been used to import the raw data and create the required normalized tables.
	   Database scripts needs to be altered for other databases
2. If testing on Linux, change settings in etl.properties accordingly


Input format:
DepartmentId	DepartmentName	EmployeeId	EmployeeFullName

Required output:
"department_name","number_of_employees"

Additionally, a log is created which lists total records processed, records imported, and a list of all
records that failed during import.

Steps to run the program
==========================
Unzip the contents into folder say, <INSTALL_FOLDER>

1. Copy mysql-connector-java-8.0.13.jar (or appropriate version) into <SCRIPTELLA_HOME>\lib folder
2. Copy janino.jar and janino-commons-compiler.jar (or appropriate versions) into <SCRIPTELLA_HOME>\lib
3. Copy etlhelper.jar into <SCRIPTELLA_HOME>\lib
4. Modify the following configurations in etl.properties file to point to their corresponding folders and files

<p>a.input_url
<p>b.log_url
<p>c.report_url

<p>5. Go to INSTALL_FOLDER. Run the following command from the command prompt 
<br>scriptella data_import
 <br>-- or--
<br>scriptella data_import -debug
	
