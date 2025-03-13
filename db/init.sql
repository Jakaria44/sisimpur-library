-- Create User table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Create Author table
CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    bio TEXT
);

-- Create Book table
CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INT NOT NULL,
    published_year INT,
    genre VARCHAR(100),
    FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);


-- Insert sample users
INSERT INTO users (name, email) VALUES
('Alice', 'alice@example.com'),
('Bob', 'bob@example.com');

-- Insert sample authors
INSERT INTO authors (name, bio) VALUES
('J.K. Rowling', 'British author, best known for Harry Potter.'),
('George R.R. Martin', 'American novelist and creator of Game of Thrones.');

-- Insert sample books
INSERT INTO books (title, author_id, published_year) VALUES
('Harry Potter and the Sorcerers Stone', 1, 1997),
('Harry Potter and the Chamber of Secrets', 1, 1998),
('A Game of Thrones', 2, 1996),
('A Clash of Kings', 2, 1998);

