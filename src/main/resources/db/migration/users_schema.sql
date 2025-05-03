-- Create users table
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

-- Create authorities table
CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);

-- Add a unique constraint on the username and authority columns
ALTER TABLE authorities ADD CONSTRAINT unique_auth UNIQUE (username, authority);