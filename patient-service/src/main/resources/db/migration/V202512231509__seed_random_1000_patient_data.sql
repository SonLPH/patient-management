CREATE EXTENSION IF NOT EXISTS pgcrypto;

INSERT INTO patient (
    id,
    name,
    email,
    address,
    date_of_birth,
    registered_date,
    created_at,
    updated_at,
    created_by,
    updated_by
)
SELECT
    gen_random_uuid(),
    'Patient ' || gs,
    'patient' || gs || '@example.com',
    'Address ' || (gs % 100) || ', City ' || (gs % 10),
    DATE '1960-01-01' + (random() * 20000)::int,
    DATE '2020-01-01' + (random() * 1500)::int,
    NOW() - (random() * INTERVAL '365 days'),
    NOW() - (random() * INTERVAL '30 days'),
    'system',
    'system'
FROM generate_series(1, 1000) AS gs;
