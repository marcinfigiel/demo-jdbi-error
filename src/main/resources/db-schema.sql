CREATE TABLE foo
(
    id                   BIGSERIAL PRIMARY KEY,
    "name"               VARCHAR(255)  NOT NULL
);

CREATE TABLE bar
(
    id                   BIGSERIAL PRIMARY KEY,
    "name"               VARCHAR(255)  NOT NULL,
    foo_id               BIGINT        NOT NULL,
    FOREIGN KEY (foo_id) REFERENCES foo (id) ON DELETE SET NULL
);
