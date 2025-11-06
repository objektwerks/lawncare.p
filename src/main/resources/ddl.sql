create table if not exists property (
  id long primary key auto_increment,
  location varchar NOT NULL,
  added varchar(10) NOT NULL
);
create index if not exists property_location_idx ON property(location);

create table if not exists session (
  id long primary key auto_increment,
  property_id long references property(id),
  mowed boolean NOT NULL,
  edged boolean NOT NULL,
  trimmed boolean NOT NULL,
  blowed boolean NOT NULL,
  fertilized boolean NOT NULL,
  pesticided boolean NOT NULL,
  weeded boolean NOT NULL,
  watered boolean NOT NULL,
  repaired boolean NOT NULL,
  note varchar NOT NULL,
  occurred varchar NOT NULL
);
create index if not exists session_occurred_idx ON session(occurred);

create table if not exists issue (
  id long primary key auto_increment,
  property_id long references property(id),
  report varchar NOT NULL,
  resolution varchar NOT NULL,
  reported varchar NOT NULL,
  resolved varchar NOT NULL
);
create index if not exists issue_reported_idx ON issue(reported);
create index if not exists issue_resolved_idx ON issue(resolved);