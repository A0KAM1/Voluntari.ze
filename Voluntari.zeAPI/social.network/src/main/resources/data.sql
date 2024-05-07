INSERT INTO CATEGORIES (ID, NAME) VALUES (1, 'Adoção');
INSERT INTO CATEGORIES (ID, NAME) VALUES (2, 'Alimentação');
INSERT INTO CATEGORIES (ID, NAME) VALUES (3, 'Agasalho');
INSERT INTO CATEGORIES (ID, NAME) VALUES (4, 'Animais');
INSERT INTO CATEGORIES (ID, NAME) VALUES (5, 'Crianças');
INSERT INTO CATEGORIES (ID, NAME) VALUES (6, 'Idosos');
INSERT INTO CATEGORIES (ID, NAME) VALUES (7, 'Meio Ambiente');
INSERT INTO CATEGORIES (ID, NAME) VALUES (8, 'PCDs');

INSERT INTO STATUS (ID, STATUS_NAME) VALUES (1, 'Agendado');
INSERT INTO STATUS (ID, STATUS_NAME) VALUES (2, 'Concluído');
INSERT INTO STATUS (ID, STATUS_NAME) VALUES (3, 'Cancelado');

INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, DESCRIPTION, NAME, PHONE_NUMBER, PROFILE_PICTURE, CITY, STATE, COUNTRY) VALUES ('amigosanimais@gmail.com', 'ong_password', 'anigos', 'Nossa ong resgata animais de rua, e ajuda os pets que precisam de cuidados especiais, ajude-nos!', 'Amigos dos Animais', '1111111-1111', 'ong.url', 'Matão', 'São Paulo', 'Brazil');
INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, DESCRIPTION, NAME, PHONE_NUMBER, PROFILE_PICTURE, CITY, STATE, COUNTRY) VALUES ('juliaribeiro@gmail.com', 'volunteer_password', 'julia12', 'Quero ajudar animais em risco e crianças sem acesso a necessidades basicas', 'Julia', '2222222-2222', 'volunteer.url', 'Taquaritinga', 'São Paulo', 'Brazil');

INSERT INTO ONGS (GOVERNMENT_CODE, ADDRESS, QR_CODE, USER_ID) VALUES ('132124432432', 'Av Quinze de Novembro, 180', 'qrcode.url', 1);
INSERT INTO VOLUNTEERS (CPF, LAST_NAME, BIRTHDAY, LEVEL, USER_ID) VALUES ('33333333333', 'Ribeiro', '2003-12-16', 1, 2);

INSERT INTO TAGS (ID, ONG_ID, CATEGORY_ID) VALUES (1, 1, 4);
INSERT INTO TAGS (ID, ONG_ID, CATEGORY_ID) VALUES (2, 1, 7);

INSERT INTO PRESENCE (NAME) VALUES ('Pendente');
INSERT INTO PRESENCE (NAME) VALUES ('Compareceu');
INSERT INTO PRESENCE (NAME) VALUES ('Não Compareceu');
INSERT INTO PRESENCE (NAME) VALUES ('Desistiu');
