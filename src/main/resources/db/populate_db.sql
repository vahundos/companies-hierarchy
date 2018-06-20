DELETE FROM companies;

INSERT INTO companies (name, annual_earnings, parent_id) VALUES
  ('Company1', 25, null),
  ('Company2', 13, 1),
  ('Company3', 5, 2),
  ('Company4', 10, 1),
  ('Company5', 10, null),
  ('Company6', 15, 5),
  ('Company7', 5, 5)