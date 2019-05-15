package to_db;

public class Default {

    String DROP() {
	return "/*--------DROP--------*/\n" +
"DROP TABLE SICK; \n" +
"DROP TABLE VACATION; \n" +
"DROP TABLE ABSENCE; \n" +
"DROP TABLE RTC; \n" +
"DROP TABLE DAY_FACTORS; \n" +
"DROP TABLE USR_FACTORS; \n" +
"DROP TABLE FAMILY;\n" +
"DROP TABLE DEPARTMENT_TITLE;\n" +
"DROP TABLE COMPANY_PLACE;\n" +
"DROP TABLE USR; \n" +
"DROP TABLE WORK_TIME; \n" +
"DROP TABLE GRADE; \n" +
"DROP TABLE PLACE; \n" +
"DROP TABLE REGION; \n" +
"DROP TABLE SERVICE;\n" +
"DROP TABLE TITLE; \n" +
"DROP TABLE DEPARTMENT; \n" +
"DROP TABLE COMPANY; \n" +
"DROP TABLE CALENDAR";
    }
    String CREATE() {
	return "/*--------CREATE TABLE--------*/\n" +
"/*--------словари--------*/\n" +
"/*календарь*/\n" +
"CREATE TABLE CALENDAR (\n" +
"	DAY DATE NOT NULL ,\n" +
"	HOURS TIME NOT NULL ,\n" +
"	PRIMARY KEY (DAY)\n" +
");\n" +
"/*компании*/\n" +
"CREATE TABLE COMPANY (\n" +
"	ID int NOT NULL,\n" +
"	COMPANY varchar(50) NOT NULL,\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"/*отдел*/\n" +
"create table DEPARTMENT (\n" +
"	ID INTEGER,\n" +
"	DEPARTMENT VARCHAR(50),\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"/*должности*/\n" +
"CREATE TABLE TITLE (\n" +
"	ID int NOT NULL,\n" +
"	TITLE VARCHAR(50) NOT NULL,\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"/*сервисы*/\n" +
"CREATE TABLE SERVICE (\n" +
"	ID int NOT NULL,\n" +
"	SERVICE varchar(50) NOT NULL,\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"\n" +
"/*--------параметры--------*/\n" +
"/*регионы*/\n" +
"CREATE TABLE REGION (\n" +
"	ID int NOT NULL,\n" +
"	REGION varchar(50) NOT NULL,\n" +
"	TIME_ZONE int NOT NULL,\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"/*площадки*/\n" +
"CREATE TABLE PLACE (\n" +
"	ID int,\n" +
"	PLACE varchar(200) NOT NULL,\n" +
"	REGION_ID int NOT NULL,\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"/*уровни*/\n" +
"CREATE TABLE GRADE (\n" +
"	ID int NOT NULL,\n" +
"	GRADE varchar(25) NOT NULL,\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"/*параметры сотрудника*/\n" +
"CREATE TABLE USR_FACTORS (\n" +
"	USR_ID INT NOT NULL ,\n" +
"	CAR BOOLEAN NOT NULL\n" +
");\n" +
"/*параметры дня*/\n" +
"CREATE TABLE DAY_FACTORS (\n" +
"	DAY DATE NOT NULL ,\n" +
"	REGION_ID int,\n" +
"	TEMPERATURE int,\n" +
"	EPIDEMY BOOLEAN,\n" +
"	ICY BOOLEAN DEFAULT FALSE,\n" +
"	RAIN BOOLEAN \n" +
");\n" +
"/*рабочее время*/\n" +
"CREATE TABLE WORK_TIME(\n" +
"	ID INTEGER,\n" +
"	START_TIME TIME default '9:00:00' not null,\n" +
"	END_TIME TIME,\n" +
"	HOURS TIME default '9:00:00' not null,\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"\n" +
"/*--------основное--------*/\n" +
"/*сотрудник*/\n" +
"CREATE TABLE USR (\n" +
"	ID int NOT NULL ,\n" +
"	LAST_NAME varchar(50) NOT NULL,\n" +
"	FIRST_NAME varchar(50) NOT NULL,\n" +
"	MIDDLE_NAME varchar(50),\n" +
"	BIRTH_DATE DATE NOT NULL,\n" +
"	DEPARTMENT_ID int NOT NULL,\n" +
"	TITLE_ID int NOT NULL,\n" +
"	GRADE_ID int NOT NULL,\n" +
"	WORK_TIME_ID int,\n" +
"	PLACE_ID int NOT NULL,\n" +
"	PRIMARY KEY (ID)\n" +
");\n" +
"/*РТК*/\n" +
"CREATE TABLE RTC (\n" +
"	COMPANY_ID int NOT NULL,\n" +
"	SERVICE_ID int NOT NULL,\n" +
"	HOURS TIME NOT NULL,\n" +
"	GRADE_ID int NOT NULL,\n" +
"	DEPARTMENT_ID int NOT NULL,\n" +
"	TITLE_ID int\n" +
");\n" +
"/*семья*/\n" +
"CREATE TABLE FAMILY (\n" +
"	USR_ID int NOT NULL,\n" +
"	CHILD BOOLEAN NOT NULL DEFAULT FALSE,\n" +
"	PARENT BOOLEAN NOT NULL DEFAULT FALSE,\n" +
"	NAME varchar(50) NOT NULL,\n" +
"	BIRTH_DATE DATE NOT NULL\n" +
");\n" +
"\n" +
"/*--------события--------*/\n" +
"/*отпуска*/\n" +
"CREATE TABLE VACATION (\n" +
"	DAY DATE NOT NULL,\n" +
"	USR_ID int NOT NULL\n" +
");\n" +
"/*больничный*/\n" +
"CREATE TABLE SICK (\n" +
"	DAY DATE NOT NULL,\n" +
"	USR_ID int NOT NULL\n" +
");\n" +
"/*невыход*/\n" +
"CREATE TABLE ABSENCE (\n" +
"	DAY DATE NOT NULL,\n" +
"	USR_ID int NOT NULL\n" +
");\n" +
"\n" +
"/*--------связки--------*/\n" +
"/*компания-площадка*/\n" +
"CREATE TABLE COMPANY_PLACE (\n" +
"	COMPANY_ID INTEGER NOT NULL,\n" +
"	PLACE_ID int NOT NULL\n" +
");\n" +
"/*отдел-должность*/\n" +
"create table DEPARTMENT_TITLE (\n" +
"	DEPARTMENT_ID INTEGER NOT NULL,\n" +
"	TITLE_ID INTEGER NOT NULL\n" +
")";
    }
    String ALTER() {
	return "/*--------FK--------*/\n" +
"/*FK USR*/\n" +
"ALTER TABLE USR ADD CONSTRAINT USR_DPT_fk FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT(ID);\n" +
"ALTER TABLE USR ADD CONSTRAINT USR_TTL_fk FOREIGN KEY (TITLE_ID) REFERENCES TITLE(ID);\n" +
"ALTER TABLE USR ADD CONSTRAINT USR_GRD_fk FOREIGN KEY (GRADE_ID) REFERENCES GRADE(ID);\n" +
"ALTER TABLE USR ADD CONSTRAINT USR_WT_fk FOREIGN KEY (WORK_TIME_ID) REFERENCES WORK_TIME(ID);\n" +
"ALTER TABLE USR ADD CONSTRAINT USR_PLC_fk FOREIGN KEY (PLACE_ID) REFERENCES PLACE(ID);\n" +
"/*FK COMPANY_PLACE*/\n" +
"ALTER TABLE COMPANY_PLACE ADD CONSTRAINT CP_COMP_fk FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID);\n" +
"ALTER TABLE COMPANY_PLACE ADD CONSTRAINT CP_DPT_fk FOREIGN KEY (PLACE_ID) REFERENCES PLACE(ID);\n" +
"/*FK DEPARTMENT_TITLE*/\n" +
"ALTER TABLE DEPARTMENT_TITLE ADD CONSTRAINT DT_DPT_fk FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT(ID);\n" +
"ALTER TABLE DEPARTMENT_TITLE ADD CONSTRAINT DT_TTL_fk FOREIGN KEY (TITLE_ID) REFERENCES TITLE(ID);\n" +
"/*FK PLACE*/\n" +
"ALTER TABLE PLACE ADD CONSTRAINT PLC_REG_fk FOREIGN KEY (REGION_ID) REFERENCES REGION(ID);\n" +
"/*--------FK факторы--------*/\n" +
"ALTER TABLE FAMILY ADD CONSTRAINT FML_USR_fk FOREIGN KEY (USR_ID) REFERENCES USR(ID);\n" +
"ALTER TABLE USR_FACTORS ADD CONSTRAINT UF_USR_fk FOREIGN KEY (USR_ID) REFERENCES USR(ID);\n" +
"ALTER TABLE DAY_FACTORS ADD CONSTRAINT DF_USR_fk FOREIGN KEY (DAY) REFERENCES CALENDAR(DAY);\n" +
"/*--------FK РТК--------*/\n" +
"ALTER TABLE RTC ADD CONSTRAINT RTC_CMP_fk FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID);\n" +
"ALTER TABLE RTC ADD CONSTRAINT RTC_SRV_fk FOREIGN KEY (SERVICE_ID) REFERENCES SERVICE(ID);\n" +
"ALTER TABLE RTC ADD CONSTRAINT RTC_GRD_fk FOREIGN KEY (GRADE_ID) REFERENCES GRADE(ID);\n" +
"ALTER TABLE RTC ADD CONSTRAINT RTC_TTL_fk FOREIGN KEY (TITLE_ID) REFERENCES TITLE(ID);\n" +
"ALTER TABLE RTC ADD CONSTRAINT RTC_DPT_fk FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT(ID);\n" +
"/*--------FK события--------*/\n" +
"ALTER TABLE VACATION ADD CONSTRAINT VAC_DAY_fk FOREIGN KEY (DAY) REFERENCES CALENDAR(DAY);\n" +
"ALTER TABLE SICK ADD CONSTRAINT SICK_DAY_fk FOREIGN KEY (DAY) REFERENCES CALENDAR(DAY);\n" +
"ALTER TABLE ABSENCE ADD CONSTRAINT ABS_DAY_fk FOREIGN KEY (DAY) REFERENCES CALENDAR(DAY);\n" +
"ALTER TABLE VACATION ADD CONSTRAINT VAC_USR_fk FOREIGN KEY (USR_ID) REFERENCES USR(ID);\n" +
"ALTER TABLE SICK ADD CONSTRAINT SICK_USR_fk FOREIGN KEY (USR_ID) REFERENCES USR(ID);\n" +
"ALTER TABLE ABSENCE ADD CONSTRAINT ABS_USR_fk FOREIGN KEY (USR_ID) REFERENCES USR(ID)";
    }		
    String INSERT() {
	return "/*TEST DATA*/\n" +
"/*--------словари--------*/\n" +
"/*календарь*/\n" +
"INSERT INTO	CALENDAR	VALUES\n" +
"('1900-01-01', '0:00:00'),		\n" +
"('2019-01-01', '8:15:00'),		\n" +
"('2019-01-02', '8:15:00'),		\n" +
"('2019-01-03', '8:15:00'),		\n" +
"('2019-01-04', '8:15:00'),		\n" +
"('2019-01-05', '0:00:00'),		\n" +
"('2019-01-06', '0:00:00'),		\n" +
"('2019-01-07', '8:15:00'),		\n" +
"('2019-01-08', '8:15:00'),		\n" +
"('2019-01-09', '8:15:00'),		\n" +
"('2019-01-10', '8:15:00'),		\n" +
"('2019-01-11', '8:15:00'),		\n" +
"('2019-01-12', '0:00:00'),		\n" +
"('2019-01-13', '0:00:00'),		\n" +
"('2019-01-14', '8:15:00'),		\n" +
"('2019-01-15', '8:15:00'),		\n" +
"('2019-01-16', '8:15:00'),		\n" +
"('2019-01-17', '8:15:00'),		\n" +
"('2019-01-18', '8:15:00'),		\n" +
"('2019-01-19', '0:00:00'),		\n" +
"('2019-01-20', '0:00:00'),		\n" +
"('2019-01-21', '8:15:00'),		\n" +
"('2019-01-22', '8:15:00'),		\n" +
"('2019-01-23', '8:15:00'),		\n" +
"('2019-01-24', '8:15:00'),		\n" +
"('2019-01-25', '8:15:00'),		\n" +
"('2019-01-26', '0:00:00'),		\n" +
"('2019-01-27', '0:00:00'),		\n" +
"('2019-01-28', '8:15:00'),		\n" +
"('2019-01-29', '8:15:00'),		\n" +
"('2019-01-30', '8:15:00'),		\n" +
"('2019-01-31', '8:15:00'),		\n" +
"('2019-02-01', '8:15:00'),		\n" +
"('2019-02-02', '0:00:00'),		\n" +
"('2019-02-03', '0:00:00'),		\n" +
"('2019-02-04', '8:15:00'),		\n" +
"('2019-02-05', '8:15:00'),		\n" +
"('2019-02-06', '8:15:00'),		\n" +
"('2019-02-07', '8:15:00'),		\n" +
"('2019-02-08', '8:15:00'),		\n" +
"('2019-02-09', '0:00:00'),		\n" +
"('2019-02-10', '0:00:00'),		\n" +
"('2019-02-11', '8:15:00'),		\n" +
"('2019-02-12', '8:15:00'),		\n" +
"('2019-02-13', '8:15:00'),		\n" +
"('2019-02-14', '8:15:00'),		\n" +
"('2019-02-15', '8:15:00'),		\n" +
"('2019-02-16', '0:00:00'),		\n" +
"('2019-02-17', '0:00:00'),		\n" +
"('2019-02-18', '8:15:00'),		\n" +
"('2019-02-19', '8:15:00'),		\n" +
"('2019-02-20', '8:15:00'),		\n" +
"('2019-02-21', '8:15:00'),		\n" +
"('2019-02-22', '8:15:00'),		\n" +
"('2019-02-23', '0:00:00'),		\n" +
"('2019-02-24', '0:00:00'),		\n" +
"('2019-02-25', '8:15:00'),		\n" +
"('2019-02-26', '8:15:00'),		\n" +
"('2019-02-27', '8:15:00'),		\n" +
"('2019-02-28', '8:15:00'),		\n" +
"('2019-03-01', '8:15:00'),		\n" +
"('2019-03-02', '0:00:00'),		\n" +
"('2019-03-03', '0:00:00'),		\n" +
"('2019-03-04', '8:15:00'),		\n" +
"('2019-03-05', '8:15:00'),		\n" +
"('2019-03-06', '8:15:00'),		\n" +
"('2019-03-07', '8:15:00'),		\n" +
"('2019-03-08', '8:15:00'),		\n" +
"('2019-03-09', '0:00:00'),		\n" +
"('2019-03-10', '0:00:00'),		\n" +
"('2019-03-11', '8:15:00'),		\n" +
"('2019-03-12', '8:15:00'),		\n" +
"('2019-03-13', '8:15:00'),		\n" +
"('2019-03-14', '8:15:00'),		\n" +
"('2019-03-15', '8:15:00'),		\n" +
"('2019-03-16', '0:00:00'),		\n" +
"('2019-03-17', '0:00:00'),		\n" +
"('2019-03-18', '8:15:00'),		\n" +
"('2019-03-19', '8:15:00'),		\n" +
"('2019-03-20', '8:15:00'),		\n" +
"('2019-03-21', '8:15:00'),		\n" +
"('2019-03-22', '8:15:00'),		\n" +
"('2019-03-23', '0:00:00'),		\n" +
"('2019-03-24', '0:00:00'),		\n" +
"('2019-03-25', '8:15:00'),		\n" +
"('2019-03-26', '8:15:00'),		\n" +
"('2019-03-27', '8:15:00'),		\n" +
"('2019-03-28', '8:15:00'),		\n" +
"('2019-03-29', '8:15:00'),		\n" +
"('2019-03-30', '0:00:00'),		\n" +
"('2019-03-31', '0:00:00'),		\n" +
"('2019-04-01', '8:15:00'),		\n" +
"('2019-04-02', '8:15:00'),		\n" +
"('2019-04-03', '8:15:00'),		\n" +
"('2019-04-04', '8:15:00'),		\n" +
"('2019-04-05', '8:15:00'),		\n" +
"('2019-04-06', '0:00:00'),		\n" +
"('2019-04-07', '0:00:00'),		\n" +
"('2019-04-08', '8:15:00'),		\n" +
"('2019-04-09', '8:15:00'),		\n" +
"('2019-04-10', '8:15:00'),		\n" +
"('2019-04-11', '8:15:00'),		\n" +
"('2019-04-12', '8:15:00'),		\n" +
"('2019-04-13', '0:00:00'),		\n" +
"('2019-04-14', '0:00:00'),		\n" +
"('2019-04-15', '8:15:00'),		\n" +
"('2019-04-16', '8:15:00'),		\n" +
"('2019-04-17', '8:15:00'),		\n" +
"('2019-04-18', '8:15:00'),		\n" +
"('2019-04-19', '8:15:00'),		\n" +
"('2019-04-20', '0:00:00'),		\n" +
"('2019-04-21', '0:00:00'),		\n" +
"('2019-04-22', '8:15:00'),		\n" +
"('2019-04-23', '8:15:00'),		\n" +
"('2019-04-24', '8:15:00'),		\n" +
"('2019-04-25', '8:15:00'),		\n" +
"('2019-04-26', '8:15:00'),		\n" +
"('2019-04-27', '0:00:00'),		\n" +
"('2019-04-28', '0:00:00'),		\n" +
"('2019-04-29', '8:15:00'),		\n" +
"('2019-04-30', '8:15:00'),		\n" +
"('2019-05-01', '8:15:00'),		\n" +
"('2019-05-02', '8:15:00'),		\n" +
"('2019-05-03', '8:15:00'),		\n" +
"('2019-05-04', '0:00:00'),		\n" +
"('2019-05-05', '0:00:00'),		\n" +
"('2019-05-06', '8:15:00'),		\n" +
"('2019-05-07', '8:15:00'),		\n" +
"('2019-05-08', '8:15:00'),		\n" +
"('2019-05-09', '8:15:00'),		\n" +
"('2019-05-10', '8:15:00'),		\n" +
"('2019-05-11', '0:00:00'),		\n" +
"('2019-05-12', '0:00:00'),		\n" +
"('2019-05-13', '8:15:00'),		\n" +
"('2019-05-14', '8:15:00'),		\n" +
"('2019-05-15', '8:15:00'),		\n" +
"('2019-05-16', '8:15:00'),		\n" +
"('2019-05-17', '8:15:00'),		\n" +
"('2019-05-18', '0:00:00'),		\n" +
"('2019-05-19', '0:00:00'),		\n" +
"('2019-05-20', '8:15:00'),		\n" +
"('2019-05-21', '8:15:00'),		\n" +
"('2019-05-22', '8:15:00'),		\n" +
"('2019-05-23', '8:15:00'),		\n" +
"('2019-05-24', '8:15:00'),		\n" +
"('2019-05-25', '0:00:00'),		\n" +
"('2019-05-26', '0:00:00'),		\n" +
"('2019-05-27', '8:15:00'),		\n" +
"('2019-05-28', '8:15:00'),		\n" +
"('2019-05-29', '8:15:00'),		\n" +
"('2019-05-30', '8:15:00'),		\n" +
"('2019-05-31', '8:15:00'),		\n" +
"('2019-06-01', '0:00:00'),		\n" +
"('2019-06-02', '0:00:00'),		\n" +
"('2019-06-03', '8:15:00'),		\n" +
"('2019-06-04', '8:15:00'),		\n" +
"('2019-06-05', '8:15:00'),		\n" +
"('2019-06-06', '8:15:00'),		\n" +
"('2019-06-07', '8:15:00'),		\n" +
"('2019-06-08', '0:00:00'),		\n" +
"('2019-06-09', '0:00:00'),		\n" +
"('2019-06-10', '8:15:00'),		\n" +
"('2019-06-11', '8:15:00'),		\n" +
"('2019-06-12', '8:15:00'),		\n" +
"('2019-06-13', '8:15:00'),		\n" +
"('2019-06-14', '8:15:00'),		\n" +
"('2019-06-15', '0:00:00'),		\n" +
"('2019-06-16', '0:00:00'),		\n" +
"('2019-06-17', '8:15:00'),		\n" +
"('2019-06-18', '8:15:00'),		\n" +
"('2019-06-19', '8:15:00'),		\n" +
"('2019-06-20', '8:15:00'),		\n" +
"('2019-06-21', '8:15:00'),		\n" +
"('2019-06-22', '0:00:00'),		\n" +
"('2019-06-23', '0:00:00'),		\n" +
"('2019-06-24', '8:15:00'),		\n" +
"('2019-06-25', '8:15:00'),		\n" +
"('2019-06-26', '8:15:00'),		\n" +
"('2019-06-27', '8:15:00'),		\n" +
"('2019-06-28', '8:15:00'),		\n" +
"('2019-06-29', '0:00:00'),		\n" +
"('2019-06-30', '0:00:00'),		\n" +
"('2019-07-01', '8:15:00'),		\n" +
"('2019-07-02', '8:15:00'),		\n" +
"('2019-07-03', '8:15:00'),		\n" +
"('2019-07-04', '8:15:00'),		\n" +
"('2019-07-05', '8:15:00'),		\n" +
"('2019-07-06', '0:00:00'),		\n" +
"('2019-07-07', '0:00:00'),		\n" +
"('2019-07-08', '8:15:00'),		\n" +
"('2019-07-09', '8:15:00'),		\n" +
"('2019-07-10', '8:15:00'),		\n" +
"('2019-07-11', '8:15:00'),		\n" +
"('2019-07-12', '8:15:00'),		\n" +
"('2019-07-13', '0:00:00'),		\n" +
"('2019-07-14', '0:00:00'),		\n" +
"('2019-07-15', '8:15:00'),		\n" +
"('2019-07-16', '8:15:00'),		\n" +
"('2019-07-17', '8:15:00'),		\n" +
"('2019-07-18', '8:15:00'),		\n" +
"('2019-07-19', '8:15:00'),		\n" +
"('2019-07-20', '0:00:00'),		\n" +
"('2019-07-21', '0:00:00'),		\n" +
"('2019-07-22', '8:15:00'),		\n" +
"('2019-07-23', '8:15:00'),		\n" +
"('2019-07-24', '8:15:00'),		\n" +
"('2019-07-25', '8:15:00'),		\n" +
"('2019-07-26', '8:15:00'),		\n" +
"('2019-07-27', '0:00:00'),		\n" +
"('2019-07-28', '0:00:00'),		\n" +
"('2019-07-29', '8:15:00'),		\n" +
"('2019-07-30', '8:15:00'),		\n" +
"('2019-07-31', '8:15:00'),		\n" +
"('2019-08-01', '8:15:00'),		\n" +
"('2019-08-02', '8:15:00'),		\n" +
"('2019-08-03', '0:00:00'),		\n" +
"('2019-08-04', '0:00:00'),		\n" +
"('2019-08-05', '8:15:00'),		\n" +
"('2019-08-06', '8:15:00'),		\n" +
"('2019-08-07', '8:15:00'),		\n" +
"('2019-08-08', '8:15:00'),		\n" +
"('2019-08-09', '8:15:00'),		\n" +
"('2019-08-10', '0:00:00'),		\n" +
"('2019-08-11', '0:00:00'),		\n" +
"('2019-08-12', '8:15:00'),		\n" +
"('2019-08-13', '8:15:00'),		\n" +
"('2019-08-14', '8:15:00'),		\n" +
"('2019-08-15', '8:15:00'),		\n" +
"('2019-08-16', '8:15:00'),		\n" +
"('2019-08-17', '0:00:00'),		\n" +
"('2019-08-18', '0:00:00'),		\n" +
"('2019-08-19', '8:15:00'),		\n" +
"('2019-08-20', '8:15:00'),		\n" +
"('2019-08-21', '8:15:00'),		\n" +
"('2019-08-22', '8:15:00'),		\n" +
"('2019-08-23', '8:15:00'),		\n" +
"('2019-08-24', '0:00:00'),		\n" +
"('2019-08-25', '0:00:00'),		\n" +
"('2019-08-26', '8:15:00'),		\n" +
"('2019-08-27', '8:15:00'),		\n" +
"('2019-08-28', '8:15:00'),		\n" +
"('2019-08-29', '8:15:00'),		\n" +
"('2019-08-30', '8:15:00'),		\n" +
"('2019-08-31', '0:00:00'),		\n" +
"('2019-09-01', '0:00:00'),		\n" +
"('2019-09-02', '8:15:00'),		\n" +
"('2019-09-03', '8:15:00'),		\n" +
"('2019-09-04', '8:15:00'),		\n" +
"('2019-09-05', '8:15:00'),		\n" +
"('2019-09-06', '8:15:00'),		\n" +
"('2019-09-07', '0:00:00'),		\n" +
"('2019-09-08', '0:00:00'),		\n" +
"('2019-09-09', '8:15:00'),		\n" +
"('2019-09-10', '8:15:00'),		\n" +
"('2019-09-11', '8:15:00'),		\n" +
"('2019-09-12', '8:15:00'),		\n" +
"('2019-09-13', '8:15:00'),		\n" +
"('2019-09-14', '0:00:00'),		\n" +
"('2019-09-15', '0:00:00'),		\n" +
"('2019-09-16', '8:15:00'),		\n" +
"('2019-09-17', '8:15:00'),		\n" +
"('2019-09-18', '8:15:00'),		\n" +
"('2019-09-19', '8:15:00'),		\n" +
"('2019-09-20', '8:15:00'),		\n" +
"('2019-09-21', '0:00:00'),		\n" +
"('2019-09-22', '0:00:00'),		\n" +
"('2019-09-23', '8:15:00'),		\n" +
"('2019-09-24', '8:15:00'),		\n" +
"('2019-09-25', '8:15:00'),		\n" +
"('2019-09-26', '8:15:00'),		\n" +
"('2019-09-27', '8:15:00'),		\n" +
"('2019-09-28', '0:00:00'),		\n" +
"('2019-09-29', '0:00:00'),		\n" +
"('2019-09-30', '8:15:00'),		\n" +
"('2019-10-01', '8:15:00'),		\n" +
"('2019-10-02', '8:15:00'),		\n" +
"('2019-10-03', '8:15:00'),		\n" +
"('2019-10-04', '8:15:00'),		\n" +
"('2019-10-05', '0:00:00'),		\n" +
"('2019-10-06', '0:00:00'),		\n" +
"('2019-10-07', '8:15:00'),		\n" +
"('2019-10-08', '8:15:00'),		\n" +
"('2019-10-09', '8:15:00'),		\n" +
"('2019-10-10', '8:15:00'),		\n" +
"('2019-10-11', '8:15:00'),		\n" +
"('2019-10-12', '0:00:00'),		\n" +
"('2019-10-13', '0:00:00'),		\n" +
"('2019-10-14', '8:15:00'),		\n" +
"('2019-10-15', '8:15:00'),		\n" +
"('2019-10-16', '8:15:00'),		\n" +
"('2019-10-17', '8:15:00'),		\n" +
"('2019-10-18', '8:15:00'),		\n" +
"('2019-10-19', '0:00:00'),		\n" +
"('2019-10-20', '0:00:00'),		\n" +
"('2019-10-21', '8:15:00'),		\n" +
"('2019-10-22', '8:15:00'),		\n" +
"('2019-10-23', '8:15:00'),		\n" +
"('2019-10-24', '8:15:00'),		\n" +
"('2019-10-25', '8:15:00'),		\n" +
"('2019-10-26', '0:00:00'),		\n" +
"('2019-10-27', '0:00:00'),		\n" +
"('2019-10-28', '8:15:00'),		\n" +
"('2019-10-29', '8:15:00'),		\n" +
"('2019-10-30', '8:15:00'),		\n" +
"('2019-10-31', '8:15:00'),		\n" +
"('2019-11-01', '8:15:00'),		\n" +
"('2019-11-02', '0:00:00'),		\n" +
"('2019-11-03', '0:00:00'),		\n" +
"('2019-11-04', '8:15:00'),		\n" +
"('2019-11-05', '8:15:00'),		\n" +
"('2019-11-06', '8:15:00'),		\n" +
"('2019-11-07', '8:15:00'),		\n" +
"('2019-11-08', '8:15:00'),		\n" +
"('2019-11-09', '0:00:00'),		\n" +
"('2019-11-10', '0:00:00'),		\n" +
"('2019-11-11', '8:15:00'),		\n" +
"('2019-11-12', '8:15:00'),		\n" +
"('2019-11-13', '8:15:00'),		\n" +
"('2019-11-14', '8:15:00'),		\n" +
"('2019-11-15', '8:15:00'),		\n" +
"('2019-11-16', '0:00:00'),		\n" +
"('2019-11-17', '0:00:00'),		\n" +
"('2019-11-18', '8:15:00'),		\n" +
"('2019-11-19', '8:15:00'),		\n" +
"('2019-11-20', '8:15:00'),		\n" +
"('2019-11-21', '8:15:00'),		\n" +
"('2019-11-22', '8:15:00'),		\n" +
"('2019-11-23', '0:00:00'),		\n" +
"('2019-11-24', '0:00:00'),		\n" +
"('2019-11-25', '8:15:00'),		\n" +
"('2019-11-26', '8:15:00'),		\n" +
"('2019-11-27', '8:15:00'),		\n" +
"('2019-11-28', '8:15:00'),		\n" +
"('2019-11-29', '8:15:00'),		\n" +
"('2019-11-30', '0:00:00'),		\n" +
"('2019-12-01', '0:00:00'),		\n" +
"('2019-12-02', '8:15:00'),		\n" +
"('2019-12-03', '8:15:00'),		\n" +
"('2019-12-04', '8:15:00'),		\n" +
"('2019-12-05', '8:15:00'),		\n" +
"('2019-12-06', '8:15:00'),		\n" +
"('2019-12-07', '0:00:00'),		\n" +
"('2019-12-08', '0:00:00'),		\n" +
"('2019-12-09', '8:15:00'),		\n" +
"('2019-12-10', '8:15:00'),		\n" +
"('2019-12-11', '8:15:00'),		\n" +
"('2019-12-12', '8:15:00'),		\n" +
"('2019-12-13', '8:15:00'),		\n" +
"('2019-12-14', '0:00:00'),		\n" +
"('2019-12-15', '0:00:00'),		\n" +
"('2019-12-16', '8:15:00'),		\n" +
"('2019-12-17', '8:15:00'),		\n" +
"('2019-12-18', '8:15:00'),		\n" +
"('2019-12-19', '8:15:00'),		\n" +
"('2019-12-20', '8:15:00'),		\n" +
"('2019-12-21', '0:00:00'),		\n" +
"('2019-12-22', '0:00:00'),		\n" +
"('2019-12-23', '8:15:00'),		\n" +
"('2019-12-24', '8:15:00'),		\n" +
"('2019-12-25', '8:15:00'),		\n" +
"('2019-12-26', '8:15:00'),		\n" +
"('2019-12-27', '8:15:00'),		\n" +
"('2019-12-28', '0:00:00'),		\n" +
"('2019-12-29', '0:00:00'),		\n" +
"('2019-12-30', '8:15:00'),		\n" +
"('2019-12-31', '8:15:00');\n" +
"/*компании*/\n" +
"INSERT INTO	COMPANY	VALUES\n" +
"(0, 'TEST COMPANY'),		\n" +
"(1, 'ООО \"МИР\"'),		\n" +
"(2, 'ЗАО \"ТЕСТ\"'),		\n" +
"(3, 'ИП \"Подрядчик\"');\n" +
"/*отдел*/\n" +
"INSERT INTO	DEPARTMENT	VALUES\n" +
"(0, 'test DEPARTMENT'),	\n" +
"(1, 'отдел Тестирования'),		\n" +
"(2, 'отдел Настройки');\n" +
"/*должности*/\n" +
"INSERT INTO	TITLE	VALUES\n" +
"(0, 'ТестоваяДолжность'),\n" +
"(1, 'Стажер'),		\n" +
"(2, 'Специалист'),		\n" +
"(3, 'Ведущий специалист'),		\n" +
"(4, 'Руководитель');\n" +
"/*сервисы*/\n" +
"INSERT INTO	SERVICE	VALUES\n" +
"(0, 'ТестовыйСервис'),	\n" +
"(1, 'Обслуживание оборудования'),		\n" +
"(2, 'Обход'),		\n" +
"(3, 'Ответ на звонки');\n" +
"/*--------параметры--------*/\n" +
"/*регионы*/\n" +
"INSERT INTO	REGION	VALUES\n" +
"(0, 'Регион', 0),\n" +
"(1, 'Санкт-Петербург', 3);\n" +
"/*площадки*/\n" +
"INSERT INTO	PLACE	VALUES\n" +
"(0, 'тестовая улица', 0),	\n" +
"(1, 'Пискаревский проспект, дом 2, корпус 2, литер Щ, БЦ «Бенуа»', 1),		\n" +
"(2, 'ул. Комсомола, 41', 1),		\n" +
"(3, 'улица Тамбасова, 5Н', 1);\n" +
"/*уровни*/\n" +
"INSERT INTO	GRADE	VALUES\n" +
"(0, 'LevelT'),	\n" +
"(1, 'L1'),		\n" +
"(2, 'L2'),		\n" +
"(3, 'L3');\n" +
"/*параметры дня*/\n" +
"INSERT INTO	DAY_FACTORS	VALUES\n" +
"('2019-01-01', 0, 0, false, false, false),		\n" +
"('2019-01-01', 1, -10, false, true, false),		\n" +
"('2019-01-02', 1, -6, false, true, false),		\n" +
"('2019-01-03', 1, -5, false, true, false),		\n" +
"('2019-01-04', 1, -2, false, false, false),		\n" +
"('2019-01-05', 1, 0, false, true, false),		\n" +
"('2019-01-06', 1, -1, false, true, true),		\n" +
"('2019-01-07', 1, -2, false, true, true),		\n" +
"('2019-01-08', 1, -2, false, true, true),		\n" +
"('2019-01-09', 1, -1, false, false, true),		\n" +
"('2019-01-10', 1, 0, false, false, true),		\n" +
"('2019-01-11', 1, 0, false, false, true),		\n" +
"('2019-01-12', 1, 1, false, false, true),		\n" +
"('2019-01-13', 1, 1, false, false, true),		\n" +
"('2019-01-14', 1, 1, false, false, false),		\n" +
"('2019-01-15', 1, 2, false, false, false),		\n" +
"('2019-01-16', 1, 2, false, false, false),		\n" +
"('2019-01-17', 1, 3, false, false, false),		\n" +
"('2019-01-18', 1, 3, false, false, false),		\n" +
"('2019-01-19', 1, 3, false, false, false),		\n" +
"('2019-01-20', 1, 4, false, false, false),		\n" +
"('2019-01-21', 1, -2, false, false, false),		\n" +
"('2019-01-22', 1, -3, false, false, false),		\n" +
"('2019-01-23', 1, -4, false, false, false),		\n" +
"('2019-01-24', 1, -6, false, false, false),		\n" +
"('2019-01-25', 1, -8, false, false, false),		\n" +
"('2019-01-26', 1, -9, false, false, false),		\n" +
"('2019-01-27', 1, -11, false, false, true),		\n" +
"('2019-01-28', 1, -13, false, false, true),		\n" +
"('2019-01-29', 1, -15, false, false, true),		\n" +
"('2019-01-30', 1, -2, false, false, true),		\n" +
"('2019-01-31', 1, -2, false, false, false),		\n" +
"('2019-02-01', 1, -1, false, false, false),		\n" +
"('2019-02-02', 1, 0, false, false, false),		\n" +
"('2019-02-03', 1, 0, false, false, false),		\n" +
"('2019-02-04', 1, 1, false, false, false),		\n" +
"('2019-02-05', 1, 1, false, false, false),		\n" +
"('2019-02-06', 1, 1, false, false, true),		\n" +
"('2019-02-07', 1, 2, false, false, false),		\n" +
"('2019-02-08', 1, 2, false, false, false),		\n" +
"('2019-02-09', 1, 0, false, false, false),		\n" +
"('2019-02-10', 1, 1, false, false, false),		\n" +
"('2019-02-11', 1, -1, false, false, false),		\n" +
"('2019-02-12', 1, -1, false, false, false),		\n" +
"('2019-02-13', 1, -2, false, false, true),		\n" +
"('2019-02-14', 1, -3, false, false, false),		\n" +
"('2019-02-15', 1, -4, false, false, true),		\n" +
"('2019-02-16', 1, 1, false, false, true),		\n" +
"('2019-02-17', 1, 2, false, false, true),		\n" +
"('2019-02-18', 1, 2, false, false, true),		\n" +
"('2019-02-19', 1, 0, false, false, true),		\n" +
"('2019-02-20', 1, 1, false, false, true),		\n" +
"('2019-02-21', 1, 0, false, false, false),		\n" +
"('2019-02-22', 1, -1, false, false, false),		\n" +
"('2019-02-23', 1, -1, false, false, false),		\n" +
"('2019-02-24', 1, -2, false, false, false),		\n" +
"('2019-02-25', 1, 0, false, false, false),		\n" +
"('2019-02-26', 1, 1, false, false, false),		\n" +
"('2019-02-27', 1, 1, false, false, false),		\n" +
"('2019-02-28', 1, 1, false, false, false),		\n" +
"('2019-03-01', 1, 1, false, false, false),		\n" +
"('2019-03-02', 1, 1, false, false, false),		\n" +
"('2019-03-03', 1, 1, false, false, false),		\n" +
"('2019-03-04', 1, 1, false, false, false),		\n" +
"('2019-03-05', 1, 1, false, false, false),		\n" +
"('2019-03-06', 1, 1, false, false, false),		\n" +
"('2019-03-07', 1, 1, false, false, false),		\n" +
"('2019-03-08', 1, 1, false, false, false),		\n" +
"('2019-03-09', 1, 2, false, false, false),		\n" +
"('2019-03-10', 1, 2, false, false, false),		\n" +
"('2019-03-11', 1, 2, false, false, false),		\n" +
"('2019-03-12', 1, 2, false, false, false),		\n" +
"('2019-03-13', 1, 3, false, false, false),		\n" +
"('2019-03-14', 1, 3, false, false, false),		\n" +
"('2019-03-15', 1, 3, false, false, false),		\n" +
"('2019-03-16', 1, 3, false, false, false),		\n" +
"('2019-03-17', 1, 3, false, false, false),		\n" +
"('2019-03-18', 1, 4, false, false, false),		\n" +
"('2019-03-19', 1, 4, false, false, false),		\n" +
"('2019-03-20', 1, 4, false, false, false),		\n" +
"('2019-03-21', 1, 4, false, false, false),		\n" +
"('2019-03-22', 1, 4, false, false, false),		\n" +
"('2019-03-23', 1, 5, false, false, false),		\n" +
"('2019-03-24', 1, 5, false, false, false),		\n" +
"('2019-03-25', 1, 5, false, false, false),		\n" +
"('2019-03-26', 1, 5, false, false, false),		\n" +
"('2019-03-27', 1, 5, false, false, false),		\n" +
"('2019-03-28', 1, 6, false, false, false),		\n" +
"('2019-03-29', 1, 6, false, false, false),		\n" +
"('2019-03-30', 1, 6, false, false, false),		\n" +
"('2019-03-31', 1, 6, false, false, false),		\n" +
"('2019-04-01', 1, 6, false, false, false),		\n" +
"('2019-04-02', 1, 7, false, false, false),		\n" +
"('2019-04-03', 1, 7, false, false, false),		\n" +
"('2019-04-04', 1, 7, false, false, false),		\n" +
"('2019-04-05', 1, 7, false, false, false),		\n" +
"('2019-04-06', 1, 7, false, false, false),		\n" +
"('2019-04-07', 1, 8, false, false, false),		\n" +
"('2019-04-08', 1, 8, false, false, false),		\n" +
"('2019-04-09', 1, 8, false, false, false),		\n" +
"('2019-04-10', 1, 8, false, false, false),		\n" +
"('2019-04-11', 1, 8, false, false, false),		\n" +
"('2019-04-12', 1, 9, false, false, false),		\n" +
"('2019-04-13', 1, 9, false, false, false),		\n" +
"('2019-04-14', 1, 9, false, false, false),		\n" +
"('2019-04-15', 1, 9, false, false, false),		\n" +
"('2019-04-16', 1, 9, false, false, false),		\n" +
"('2019-04-17', 1, 9, false, false, false),		\n" +
"('2019-04-18', 1, 10, false, false, false),		\n" +
"('2019-04-19', 1, 10, false, false, false),		\n" +
"('2019-04-20', 1, 10, false, false, false),		\n" +
"('2019-04-21', 1, 10, false, false, false),		\n" +
"('2019-04-22', 1, 10, false, false, false),		\n" +
"('2019-04-23', 1, 11, false, false, false),		\n" +
"('2019-04-24', 1, 11, false, false, false),		\n" +
"('2019-04-25', 1, 11, false, false, false),		\n" +
"('2019-04-26', 1, 11, false, false, false),		\n" +
"('2019-04-27', 1, 11, false, false, false),		\n" +
"('2019-04-28', 1, 12, false, false, false),		\n" +
"('2019-04-29', 1, 12, false, false, false),		\n" +
"('2019-04-30', 1, 12, false, false, false);\n" +
"/*рабочее время*/\n" +
"INSERT INTO	WORK_TIME	VALUES\n" +
"(0, '0:00:00', '0:00:00', '0:00:00'),		\n" +
"(8, '8:00:00', '8:00:00', '8:00:00'),		\n" +
"(9, '9:00:00', '9:00:00', '9:00:00'),		\n" +
"(10, '10:00:00', '10:00:00', '10:00:00'),		\n" +
"(11, '11:00:00', '11:00:00', '11:00:00');\n" +
"/*--------основное--------*/\n" +
"/*сотрудник*/\n" +
"INSERT INTO	USR	VALUES\n" +
"(0, 'TEST', 'Test', null, '1900-01-01', 1, 1, 1, 0, 1),\n" +
"(1, 'Шубкин', 'Тимофей', 'Афанасьевич', '1982-04-21', 1, 1, 1, 8, 1),		\n" +
"(2, 'Анников', 'Шерлок', 'Геннадьевич', '1983-06-03', 1, 1, 1, 8, 2),		\n" +
"(3, 'Новичков', 'Август', 'Борисович', '1990-02-14', 2, 1, 1, 8, 3),		\n" +
"(4, 'Ясенев', 'Махмуд', 'Арсеньевич', '1985-08-27', 2, 2, 1, 9, 1),		\n" +
"(5, 'Шверник', 'Армен', 'Игнатьевич', '1986-10-01', 2, 2, 1, 9, 2),		\n" +
"(6, 'Лапунов', 'Ролан', 'Робертович', '1987-11-21', 1, 2, 2, 9, 3),		\n" +
"(7, 'Карчагин', 'Аким', 'Георгиевич', '1989-01-02', 1, 4, 2, 10, 1),		\n" +
"(8, 'Пименов', 'Назар', 'Артемович', '1990-02-14', 1, 4, 2, 10, 2),		\n" +
"(9, 'Расторгуев', 'Леван', 'Вячеславович', '1991-03-29', 1, 4, 2, 11, 3),		\n" +
"(10, 'Якубов', 'Адриан', 'Илларионович', '1992-05-10', 1, 4, 2, 11, 1);\n" +
"/*параметры сотрудника*/\n" +
"INSERT INTO	USR_FACTORS	VALUES\n" +
"(0,false),\n" +
"(2,true);\n" +
"/*РТК*/\n" +
"INSERT INTO	RTC	VALUES\n" +
"(1, 1, '3:00:00', 1, 1, null),		\n" +
"(1, 1, '1:23:00', 2, 1, null),		\n" +
"(1, 2, '0:53:00', 1, 1, null),		\n" +
"(1, 2, '0:23:00', 2, 1, null),		\n" +
"(2, 1, '0:13:00', 1, 2, null),		\n" +
"(2, 2, '0:05:00', 2, 2, null),		\n" +
"(2, 3, '0:11:00', 3, 2, null),		\n" +
"(2, 3, '0:13:00', 1, 2, null),		\n" +
"(2, 3, '0:05:00', 2, 2, null),		\n" +
"(3, 1, '0:12:00', 3, 2, null),		\n" +
"(3, 1, '0:13:00', 1, 2, null),		\n" +
"(3, 2, '0:05:30', 2, 2, null),		\n" +
"(3, 2, '0:11:00', 3, 2, null),		\n" +
"(3, 2, '0:13:00', 1, 2, null);\n" +
"/*семья*/\n" +
"INSERT INTO	FAMILY	VALUES\n" +
"(2, true, false, 'Алиса', '1996-04-01'),		\n" +
"(2, true, false, 'Алена', '1994-05-08'),		\n" +
"(2, true, false, 'Дмитрий', '1998-06-03'),		\n" +
"(3, false, true, 'Евгения', '1960-04-04');\n" +
"/*--------события--------*/\n" +
"/*отпуска*/\n" +
"INSERT INTO	VACATION	VALUES\n" +
"('1900-01-01', 0),\n" +
"('2019-04-16', 2),		\n" +
"('2019-04-17', 2),		\n" +
"('2019-04-18', 2),		\n" +
"('2019-04-19', 2),		\n" +
"('2019-04-20', 2),		\n" +
"('2019-04-21', 2);\n" +
"/*больничный*/\n" +
"INSERT INTO	SICK	VALUES\n" +
"('1900-01-01', 0),\n" +
"('2019-04-16', 1),		\n" +
"('2019-04-17', 1),		\n" +
"('2019-04-18', 1),		\n" +
"('2019-04-19', 1),		\n" +
"('2019-04-20', 1),		\n" +
"('2019-04-21', 1);\n" +
"/*невыход*/\n" +
"INSERT INTO	ABSENCE	VALUES\n" +
"('1900-01-01', 0);\n" +
"/*компания-площадка*/\n" +
"INSERT INTO	COMPANY_PLACE	VALUES\n" +
"(0, 0),		\n" +
"(1, 1),		\n" +
"(1, 2),		\n" +
"(1, 3),		\n" +
"(2, 1),		\n" +
"(3, 3);\n" +
"/*отдел-должность*/\n" +
"INSERT INTO	DEPARTMENT_TITLE	VALUES\n" +
"(0, 0),		\n" +
"(1, 1),		\n" +
"(1, 2),		\n" +
"(2, 3),		\n" +
"(2, 4)";
    }
}
