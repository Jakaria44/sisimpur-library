CREATE TABLE user_books
(
    user_id SERIAL NOT NULL,
    book_id SERIAL NOT NULL,
    PRIMARY KEY (user_id, book_id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
                ON DELETE CASCADE,
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
            REFERENCES books (id)
                ON DELETE CASCADE
);
