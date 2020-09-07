CREATE TABLE "USER" (
    User_Name VARCHAR(100)  PRIMARY KEY,
    Password VARCHAR(100) NOT NULL,
    Phone_Number VARCHAR(10),
    Address VARCHAR(100),
    Gender CHAR,
    Profile_Pic BLOB,
    First_Name VARCHAR(100),
    Last_Name VARCHAR(100),
    Birth_Date DATE
);
CREATE TABLE CLINIC (
    ID INTEGER PRIMARY KEY ,
    Name VARCHAR(100),
    Address VARCHAR(100),
    phone_number varchar(10),
    Profile_Pic blob,
    Balance INTEGER NOT NULL,
    Type VARCHAR(30)
);
CREATE TABLE CONAS (
   Appointment_ID INTEGER,
   Services_ID INTEGER
);
CREATE TABLE APPOINTMENT (
    ID INTEGER PRIMARY KEY,
    Start_Time DATE,
    Duration VARCHAR(10),
    Teeth_Location varchar(40),
    Description varchar(300),
    Patient_UserName varchar(100),
    Clinic_ID INTEGER
);
CREATE TABLE SERVICES (
    ID INTEGER PRIMARY KEY,
    Name VARCHAR(50),
    Duration varchar(10),
    Price INTEGER
);

CREATE TABLE DOCTOR(
    Clinic_ID INTEGER,
    Salary INTEGER,
    DOCTOR_USER_NAME varchar(100)
);
CREATE TABLE PATIENT(
    Blood_Type VARCHAR(3),
    Debt INTEGER,
    Allergies VARCHAR(100),
    PATIENT_USER_NAME varchar(100)
);
CREATE TABLE POSTS(
    Text VARCHAR(500),
    Image BLOB,
    Post_Clinic_ID INTEGER
);