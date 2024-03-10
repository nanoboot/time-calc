CREATE TABLE "WORKING_DAY" (
	"ID" TEXT,
	"YEAR" NUMBER NOT NULL,
	"MONTH" NUMBER NOT NULL,
	"DAY" NUMBER NOT NULL,
--
	"ARRIVAL_HOUR" NUMBER NOT NULL,
        "ARRIVAL_MINUTE" NUMBER NOT NULL,
        "OVERTIME_HOUR" NUMBER NOT NULL,
        "OVERTIME_MINUTE" NUMBER NOT NULL,
--
        "WORKING_TIME_IN_MINUTES" NUMBER NOT NULL,
        "PAUSE_TIME_IN_MINUTES" NUMBER NOT NULL,
--
        "NOTE" TEXT NOT NULL,
        "TIME_OFF" NUMBER,
	PRIMARY KEY("ID")
);