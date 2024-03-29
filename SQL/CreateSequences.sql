CREATE SEQUENCE APPOINTMENT_ID_sequence;
CREATE TRIGGER new_appointment
  BEFORE INSERT ON APPOINTMENT
  FOR EACH ROW
BEGIN
  SELECT APPOINTMENT_ID_sequence.nextval
  INTO :new.ID
  FROM dual;
END;

CREATE SEQUENCE Clinic_ID_sequence;
CREATE TRIGGER new_clinic
  BEFORE INSERT ON CLINIC
  FOR EACH ROW
BEGIN
  SELECT Clinic_ID_sequence.nextval
  INTO :new.ID
  FROM dual;
END;