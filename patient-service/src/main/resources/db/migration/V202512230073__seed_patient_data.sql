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
VALUES
    (
        '123e4567-e89b-12d3-a456-426614174000',
        'John Doe',
        'john.doe@example.com',
        '123 Main St, Springfield',
        '1985-06-15',
        '2024-01-10',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'SYSTEM',
        'SYSTEM'
    ),
    (
        '123e4567-e89b-12d3-a456-426614174001',
        'Jane Smith',
        'jane.smith@example.com',
        '456 Elm St, Shelbyville',
        '1990-09-23',
        '2023-12-01',
        CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP,
        'SYSTEM',
        'SYSTEM'
    );
