CREATE TABLE myusers (
    usrcode   VARCHAR(50) NOT NULL,
    password  VARCHAR(100) NOT NULL,
    name      VARCHAR(50) NOT NULL,
    isactive  TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (usrcode)
);
  
CREATE TABLE myroles (
  usrcode   VARCHAR(50) NOT NULL,
  roles     VARCHAR(50) NOT NULL,
  FOREIGN KEY (usrcode) REFERENCES myusers(usrcode)
);

CREATE UNIQUE INDEX ix_myroles on myroles (usrcode, roles);