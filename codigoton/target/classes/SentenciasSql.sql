-- Sentencias de tablas temporal y eliminacion para ejecutar progaram
DROP TEMPORARY TABLE IF EXISTS evalart_reto._filteTable;

CREATE TEMPORARY TABLE evalart_reto._filteTable
select client_id, sum(balance) as Tbalance,
code, male, type, location,company,encrypt
from evalart_reto.account a, evalart_reto.client c 
where a.client_id = c.id
GROUP by client_id;

select * from evalart_reto._filteTable
where encrypt =1;

-- mesa general
select  * from evalart_reto._filteTable where type=1
ORDER by Tbalance desc

select count(company), company from evalart_reto._filteTable where type=1
GROUP by company
ORDER by Tbalance desc

-- mesa 1

select  count(company), company from evalart_reto._filteTable 
where location=2 and Tbalance >= 500000
GROUP by company

select  * from evalart_reto._filteTable where location=2 and Tbalance >= 500000
GROUP by company

-- mesa 1

select  count(company), company from evalart_reto._filteTable 
where location=2 and Tbalance >= 500000
GROUP by company

select  * from evalart_reto._filteTable where location=2 and Tbalance >= 500000
GROUP by company

-- mesa 2
select  * from evalart_reto._filteTable where location=1 and Tbalance <= 500000
ORDER by Tbalance desc

select  count(company), company from evalart_reto._filteTable 
where location=1 and Tbalance <= 500000
GROUP by company


-- mesa 3
select  * from evalart_reto._filteTable where location=3 AND type=5
and Tbalance <= 10000
ORDER by Tbalance desc

select  count(company), company from evalart_reto._filteTable 
where location=1 and Tbalance <= 500000
GROUP by company

-- mesa 4
select  * from evalart_reto._filteTable where location=1
and Tbalance <= 100000
ORDER by Tbalance desc

select  count(company), company from evalart_reto._filteTable 
where location=1 and Tbalance <= 500000
GROUP by company

-- mesa 5
select  * from evalart_reto._filteTable where location=99
ORDER by Tbalance desc

select  count(company), company from evalart_reto._filteTable 
where location=99
GROUP by company

-- mesa 6
select  * from evalart_reto._filteTable where type=11 and Tbalance >= 10000
ORDER by Tbalance desc

select  count(company), company from evalart_reto._filteTable 
where type=11 and Tbalance >= 10000
GROUP by company
