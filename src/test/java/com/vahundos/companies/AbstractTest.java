package com.vahundos.companies;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"/db/init_db.sql", "/db/populate_db.sql"})
public abstract class AbstractTest {
}
