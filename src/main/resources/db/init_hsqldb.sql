DROP TABLE companies IF EXISTS;

CREATE TABLE companies
(
  id              INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  name            VARCHAR(255) NOT NULL,
  annual_earnings INTEGER      NOT NULL,
  parent_id       INTEGER,
  FOREIGN KEY (parent_id) REFERENCES companies (id) ON DELETE CASCADE
);