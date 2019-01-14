# ETL_Scriptella

This is a ETL script to import data from a tab separated file containing unnormalized data. 
The objective is to create a report listing all departments containing 100+ employees.
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

Steps to run the program
==========================
Unzip the contents into folder say, <INSTALL_FOLDER>

1. Copy mysql-connector-java-8.0.13.jar (or appropriate version) into <SCRIPTELLA_HOME>\lib folder
2. Copy janino.jar and janino-commons-compiler.jar (or appropriate versions) into <SCRIPTELLA_HOME>\lib
3. Copy etlhelper.jar into <SCRIPTELLA_HOME>\lib
4. Modify the following configurations in etl.properties file to point to their corresponding folders and files
	input_url
	log_url
	report_url
5. Go to INSTALL_FOLDER. Run the following command from the command prompt 
	scriptella data_import
	 -- or--
	scriptella data_import -debug
	
