INSERT INTO myusers (
    name,
    usrcode, 
    password, 
    isactive
) values (
    'cyrus',
    'cyrus',
    '$2a$12$819BcFYb/dUKgmEtEkWZpOJQazLkuj6QkwgybCQ0qvhHTvX8vp9B6', --abc
    1
);

INSERT INTO myroles (
    usrcode, 
    roles
) values (
    'cyrus', 
    'ROLE_USER'
);