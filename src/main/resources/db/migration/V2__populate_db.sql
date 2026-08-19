
USE space_travel;

INSERT INTO client(name) VALUES
    ('Jason'),
    ('Andrew'),
    ('Max'),
    ('Nicolas'),
    ('Paul'),
    ('John'),
    ('Mary'),
    ('George'),
    ('Antony'),
    ('ForestWizard')
;

INSERT INTO planet(id, name) VALUES
    ('K218B', 'EPIC'),
    ('K186F', 'Kepler'),
    ('GJ3378B', 'Gliese'),
    ('R508B', 'Ross'),
    ('S2398BC', 'Struve')
;

INSERT INTO ticket(created_at, client_id, from_planet_id, to_planet_id) VALUES
    ('2079-08-30', 1, 'K218B', 'GJ3378B'),
    ('2079-09-4', 3, 'K218B', 'S2398BC'),
    ('2079-09-6', 7, 'K218B', 'K186F'),
    ('2079-09-10', 1, 'GJ3378B', 'K218B'),
    ('2079-09-15', 5, 'GJ3378B', 'K186F'),
    ('2079-09-19', 2, 'R508B', 'K218B'),
    ('2079-09-23', 7, 'S2398BC', 'GJ3378B'),
    ('2079-10-3', 8, 'K218B', 'S2398BC'),
    ('2079-10-21', 7, 'GJ3378B', 'S2398BC'),
    ('2079-10-22', 10, 'S2398BC', 'GJ3378B')
;