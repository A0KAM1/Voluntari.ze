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

INSERT INTO PRESENCE (ID, NAME) VALUES (1, 'Pendente');
INSERT INTO PRESENCE (ID, NAME) VALUES (2, 'Compareceu');
INSERT INTO PRESENCE (ID, NAME) VALUES (3, 'Não Compareceu');
INSERT INTO PRESENCE (ID, NAME) VALUES (4, 'Desistiu');

INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, DESCRIPTION, NAME, PHONE_NUMBER, PROFILE_PICTURE, CITY, STATE, COUNTRY) VALUES ('amigosanimais@gmail.com', 'ong_password', 'anigos', 'Nossa ong resgata animais de rua, e ajuda os pets que precisam de cuidados especiais, ajude-nos!', 'Amigos dos Animais', '11111111111', 'ong.url', 'Matão', 'São Paulo', 'Brazil');
INSERT INTO ONGS (GOVERNMENT_CODE, ADDRESS, QR_CODE, USER_ID) VALUES ('132124432432', 'Av Quinze de Novembro, 180', 'qrcode.url', 1);
INSERT INTO TAGS (ID, ONG_ID, CATEGORY_ID) VALUES (1, 1, 4);
INSERT INTO TAGS (ID, ONG_ID, CATEGORY_ID) VALUES (2, 1, 7);

INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, DESCRIPTION, NAME, PHONE_NUMBER, PROFILE_PICTURE, CITY, STATE, COUNTRY) VALUES ('juliaribeiro@gmail.com', 'volunteer_password', 'julia12', 'Quero ajudar animais em risco e crianças sem acesso a necessidades basicas', 'Julia', '12312312312', 'volunteer.url', 'Taquaritinga', 'São Paulo', 'Brazil');
INSERT INTO VOLUNTEERS (CPF, LAST_NAME, BIRTHDAY, LEVEL, USER_ID) VALUES ('33333333333', 'Ribeiro', '2003-12-16', 1, 2);

INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, DESCRIPTION, NAME, PHONE_NUMBER, PROFILE_PICTURE, CITY, STATE, COUNTRY) VALUES ('stub1@gmail.com', 'stub1pass', 'stubb1tag', 'stub ong1 descrip', 'Crianças Carentes', '22222222222', 'stub1', 'Araraquara', 'São Paulo', 'Brazil');
INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, DESCRIPTION, NAME, PHONE_NUMBER, PROFILE_PICTURE, CITY, STATE, COUNTRY) VALUES ('stub2@gmail.com', 'stub2pass', 'stubb2tag', 'stub ong2 descrip', 'Help RS', '44444444444', 'stub1', 'Santa Catarina', 'São Paulo', 'Brazil');

INSERT INTO ONGS (GOVERNMENT_CODE, ADDRESS, QR_CODE, USER_ID) VALUES ('000000000000', 'Rua Stub 1', 'stub1.url', 3);
INSERT INTO ONGS (GOVERNMENT_CODE, ADDRESS, QR_CODE, USER_ID) VALUES ('999999999999', 'Av Quinze de Novembro, 180', 'qrcode.url', 4);

INSERT INTO EVENTS (ADDRESS, DATE, REQUIREMENTS, TIME, STATUS_ID) VALUES ('Nossas redes sociais', '2024-05-02', 'Nenhum', '08:00:00', 2);
INSERT INTO EVENTS (ADDRESS, DATE, REQUIREMENTS, TIME, STATUS_ID) VALUES ('Em frente à igreja Matriz', '2024-06-14', 'Disponibilidade de voluntariar durante a tarde', '12:00:00', 1);

INSERT INTO POSTS (CONTENT, IS_EVENT, CREATED_AT, UPDATED_AT, ONG_ID) VALUES ('Live de doações para o RS. Entre, curta um som, e ajude o RS. Participação de vários cantores e DJs, terá duração', 1, '2024-05-01 08:55:33.704', null, 2);
INSERT INTO POSTS (CONTENT, IS_EVENT, CREATED_AT, UPDATED_AT, ONG_ID) VALUES ('Conseguimos arrecadar bastante dinheiro na live de ontem, obrigado a todos que participaram, seja apenas assistindo, divulgando, ou doando para o caixinha <3. Todo esse dinheiro será usado para mandar remédios e alimentos não perecíveis para as famílias que estão desabrigadas ainda devido a grande enchente de maio.', null, '2024-05-03 08:55:33.704', null, 2);
INSERT INTO POSTS (CONTENT, IS_EVENT, CREATED_AT, UPDATED_AT, ONG_ID) VALUES ('Nós atualmente abrigamos 16 crianças e adolescentes que se encontravam em situações precárias. Precisamos muito da doação de vocês, o dinheiro é convertido principalmete em alimentos e remédios, assim como aluguel e contas do nosso abrigo. Obrigado <3', null, '2024-06-01 08:55:33.704', null, 3);
INSERT INTO POSTS (CONTENT, IS_EVENT, CREATED_AT, UPDATED_AT, ONG_ID) VALUES ('Pulga é nossa nova integrante! Foi resgatada semana passada e estava com muito frio e fome... Foi tratada e hoje está muito bem, fazendo amizade com todos <3', null, '2024-06-04 08:55:33.704', null, 1);
INSERT INTO POSTS (CONTENT, IS_EVENT, CREATED_AT, UPDATED_AT, ONG_ID) VALUES ('Vamos fazer um evento para arrecadar fundos na próxima sexta feira. Nossos amigos vão estar lá para que vocês possam conhece-los, e quem sabe levar um novo integrante para a família! Precisamos de voluntarios para fazer cachorro quente, e preencher formulários', 2, '2024-06-05 08:55:33.704', null, 1);

INSERT INTO PICTURES (URL, POST_ID) VALUES ('foto1', 1);
INSERT INTO PICTURES (URL, POST_ID) VALUES ('foto2', 1);
INSERT INTO PICTURES (URL, POST_ID) VALUES ('foto3', 1);
INSERT INTO PICTURES (URL, POST_ID) VALUES ('foto4', 1);

INSERT INTO PICTURES (URL, POST_ID) VALUES ('agradecimento1', 2);
INSERT INTO PICTURES (URL, POST_ID) VALUES ('agradecimento2', 2);

INSERT INTO PICTURES (URL, POST_ID) VALUES ('pulga1', 4);
INSERT INTO PICTURES (URL, POST_ID) VALUES ('pulga2', 4);
INSERT INTO PICTURES (URL, POST_ID) VALUES ('pulga3', 4);

INSERT INTO PICTURES (URL, POST_ID) VALUES ('divulgação do evento cachorrinho', 5);
