CREATE TABLE public."users"
(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(60) NOT NULL,
    data VARCHAR(60) DEFAULT NULL
);
CREATE UNIQUE INDEX users_username_uindex ON public."users" (username);