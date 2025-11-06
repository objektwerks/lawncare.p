create table if not exists property (
  id long primary key auto_increment,
  location VARCHAR NOT NULL,
  added VARCHAR(10) NOT NULL
);

create table if not exists session (
  id long primary key auto_increment,
  property_id BIGINT REFERENCES property(id),
  mowed BOOLEAN NOT NULL,
  edged BOOLEAN NOT NULL,
  trimmed BOOLEAN NOT NULL,
  blowed BOOLEAN NOT NULL,
  fertilized BOOLEAN NOT NULL,
  pesticided BOOLEAN NOT NULL,
  weeded BOOLEAN NOT NULL,
  watered BOOLEAN NOT NULL,
  repaired BOOLEAN NOT NULL,
  note VARCHAR NOT NULL,
  occurred VARCHAR NOT NULL
);

create table if not exists issue (
  id long primary key auto_increment,
  property_id BIGINT REFERENCES property(id),
  report VARCHAR NOT NULL,
  resolution VARCHAR NOT NULL,
  reported VARCHAR NOT NULL,
  resolved VARCHAR NOT NULL
);