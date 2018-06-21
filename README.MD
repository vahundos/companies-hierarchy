# Company hierarchy Spring Boot Application

Company hierarchy application developed with using Maven, Spring Boot (MVC, DataJpa), Hibernate, Bootstrap (CSS), AJAX

## Running

You can see application on heroku (first run after a long delay may take about 30 seconds): <https://companies-hierarchy.herokuapp.com/>  

For running locally

```
    git clone https://github.com/vahundos/companies-hierarchy.git
    cd companies-hierarchy
    mvn -Phsqldb spring-boot:run
```

Make sure that `MAVEN_HOME/bin` added into PATH variable. You can then access here: <http://localhost:8080/>


## About task

**Problem**  
You are designing a web application that manages organizational structure for parent and child companies.
Each company has two properties, they are company name and estimated annual earnings.
There are two types of companies: 1- Main company, 2 - Subsidiary company.
The company can belong only to one company but can have a few child companies.


**Requirements**  
- The application should allow a user to view/add/edit/delete any company.
- Show companies tree
- Solution should have database, backend, frontend. (for example sql + node.js + react)
- Data about companies should be stored in database

**Example:**  
Name | Company Estimated Earnings | Company Estimated Earnings + Child Companies(on all levels) Estimated Earnings
If you edit earnings of Company3 from 5 to 10, total earnings of Company1 should be updated automatically from 53 to 58. If you add Company8 as child of Company3, total of Company1 should be updated as well.
 
-Company1 | 25K$ | 53K$  
--Company2 | 13K$ | 18K$  
---Company3 | 5K$  
--Company4 | 10K$  
-Company5 | 10K$ | 30K$  
--Company6 | 15K$  
--Company7 | 5K$   

- Nesting level is not limited
- Companies quantity on each level is unlimited
-  Single page approach should be used (using react/angular/vue.js framework would be a big plus). 
- Any solution should demonstrate best practices where appropriate such as SOLID principles.