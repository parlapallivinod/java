-- Get top 3 country, state population
-- Only one state from a country should be in results

create table country(
	id BIGINT,
    name VARCHAR(100),
    primary key(id)
);

create table state(
	id BIGINT,
    name VARCHAR(100),
    country_id BIGINT,
    primary key(id),
    foreign key(country_id) references country(id) ON UPDATE CASCADE ON DELETE CASCADE
);

create table city(
	id BIGINT,
    name VARCHAR(100),
    state_id BIGINT,
    population BIGINT,
    primary key(id),
    foreign key(state_id) references state(id) ON UPDATE CASCADE ON DELETE CASCADE
);


insert into country(id, name) values(1, 'India');
insert into country(id, name) values(2, 'USA');
insert into country(id, name) values(3, 'Australia');
insert into country(id, name) values(4, 'UK');
insert into country(id, name) values(5, 'Pakistam');

insert into state(id, name, country_id) values(101, 'Andhra Pradesh', 1);
insert into state(id, name, country_id) values(102, 'Tamil Nadu', 1);
insert into state(id, name, country_id) values(111, 'Texas', 2);
insert into state(id, name, country_id) values(121, 'New South Wales', 3);
insert into state(id, name, country_id) values(122, 'Old South Wales', 3);
insert into state(id, name, country_id) values(131, 'France', 4);
insert into state(id, name, country_id) values(141, 'Tamil Istambul', 5);

insert into city(id, name, state_id, population) values(1001, 'Nellore', 101, 10000);
insert into city(id, name, state_id, population) values(1002, 'Kadapa', 101, 20000);
insert into city(id, name, state_id, population) values(1003, 'Chennai', 102, 10000);
insert into city(id, name, state_id, population) values(2001, 'texascity1', 111, 5000);
insert into city(id, name, state_id, population) values(3001, 'NS1', 121, 6000);
insert into city(id, name, state_id, population) values(3002, 'OS1', 122, 8000);
insert into city(id, name, state_id, population) values(4001, 'france1', 131, 2000);
insert into city(id, name, state_id, population) values(4002, 'france2', 131, 3000);


select c.id country_id,
	c.name country_name,
    s.id state_id,
    s.name state_name,
    sum(ct.population) population
from country c,
	state s,
    city ct
where c.id = s.country_id and
	s.id = ct.state_id
group by c.id,
	c.name,
    s.id,
    s.name;


select r.country_id,
	r.country_name,
    r.state_id,
    r.state_name,
    r.population,
    rank() over ( partition by r.country_id order by r.population desc ) count
from (
	select c.id country_id,
		c.name country_name,
		s.id state_id,
		s.name state_name,
		sum(ct.population) population
	from country c,
		state s,
		city ct
	where c.id = s.country_id and
		s.id = ct.state_id
	group by c.id,
		c.name,
		s.id,
		s.name
	) r;



select output.country_id,
	output.country_name,
    output.state_id,
    output.state_name,
    output.population
from (
	select r.country_id country_id,
		r.country_name,
		r.state_id,
		r.state_name,
		r.population,
		rank() over ( partition by r.country_id order by r.population desc ) as count
	from (
		select c.id country_id,
			c.name country_name,
			s.id state_id,
			s.name state_name,
			sum(ct.population) population
		from country c,
			state s,
			city ct
		where c.id = s.country_id and
			s.id = ct.state_id
		group by c.id,
			c.name,
			s.id,
			s.name
		) r
	) output
where output.count = 1
order by output.population desc
limit 0, 3;