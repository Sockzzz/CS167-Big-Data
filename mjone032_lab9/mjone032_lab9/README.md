# Lab 10

## Student information

* Full name: Marshall Jones
* E-mail: mjone032@ucr.edu
* UCR NetID: mjone032
* Student ID: 862062375

## Answers

* (Q1) What is the result?

  ```text
  [
        3
  ]
  ```

* (Q2) Which query did you use and what is the answer?

  ```sql
  select distinct(primary_type)
  from ChicagoCrimes
  where location_description="GAS STATION";

  ```

  ```text
  [
        {
                "primary_type": "DECEPTIVE PRACTICE"
        },
        {
                "primary_type": "ROBBERY"
        },
        {
                "primary_type": "THEFT"
        },
        {
                "primary_type": "BATTERY"
        },
        {
                "primary_type": "MOTOR VEHICLE THEFT"
        }
  ]
  ```

* (Q3) Include the query in your README file

  ```sql
  select year, count(*) as count from 
  (select get_year(parse_datetime(date_value, "MM/DD/YYY hh:mm:ss a")) as year
  from ChicagoCrimes)as years
  group by year
  order by count desc;
  ```

* (Q4) Which `district` has the most number of crimes? Include the query and the answer in the README file.

  ```sql
  select district, count(*) 
  as count from ChicagoCrimes
  group by district
  order by count desc;
  ```

  ```text
  [
        {
                "district": 18,
                "count": 108587
        },
        {
                "district": 1,
                "count": 16482
        },
        {
                "district": 12,
                "count": 1
        }
  ]
  ```

* (Q5) Include the query in your submission.

  ```sql
  SELECT year_month, COUNT(*) AS count FROM (
  SELECT print_datetime(parse_datetime(date_value, "MM/DD/YYYY hh:mm:ss a"), "YYYY/MM") AS year_month
  FROM ChicagoCrimes)
  AS months
  GROUP BY year_month
  ORDER BY year_month;
  ```

* (Q6) What is the total number of results produced by this query (not only the shown ones)?
  232