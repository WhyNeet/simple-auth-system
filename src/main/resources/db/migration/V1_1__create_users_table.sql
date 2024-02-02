CREATE TABLE IF NOT EXISTS "users" (
    id UUID PRIMARY KEY DEFAULT (uuid_generate_v4()),
    username VARCHAR (255) NOT NULL UNIQUE,
    full_name VARCHAR (255) NOT NULL,
    password VARCHAR (255) NOT NULL
);