DROP TABLE IF EXISTS companies;

CREATE TABLE companies
(
  id              SERIAL PRIMARY KEY,
  name            VARCHAR(255) NOT NULL,
  annual_earnings INTEGER      NOT NULL,
  parent_id       INTEGER,
  FOREIGN KEY (parent_id) REFERENCES companies (id) ON DELETE CASCADE
);