CREATE TABLE Location_Dimension(
idLocation VARCHAR(12),
nivel1 VARCHAR(1),
nivel2 VARCHAR(1),
nivel3 VARCHAR(1),
nivel4 VARCHAR(1),
nivel5 VARCHAR(1),
nivel6 VARCHAR(1),
nivel7 VARCHAR(1),
nivel8 VARCHAR(1),
nivel9 VARCHAR(1),
nivel10 VARCHAR(1),
nivel11 VARCHAR(1),
nivel12 VARCHAR(1),

latitude INT NOT NULL,
longitude INT NOT NULL,
CONSTRAINT Location_Dimension_PK PRIMARY KEY(idLocation)
);

CREATE TABLE Time_Dimension(
idTime BIGINT,
anyo NUMERIC NOT NULL,
mes NUMERIC NOT NULL,
dia NUMERIC NOT NULL,
CONSTRAINT Time_Dimension_PK PRIMARY KEY(idTime)
);

CREATE TABLE MeteoFact(
idLocation VARCHAR(12) REFERENCES Location_Dimension,
idTime BIGINT REFERENCES Time_Dimension,
HorizonalSolarRadiation  FLOAT CONSTRAINT valide_sun_radiation CHECK (HorizonalSolarRadiation >=0),
RelativeHumidity FLOAT,
DewFrost FLOAT,
Temperature FLOAT,
WindSpeed FLOAT,
Precipitation FLOAT,

CONSTRAINT MeteoFact_PK PRIMARY KEY(idLocation,idTime)
)