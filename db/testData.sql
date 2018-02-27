INSERT INTO locale (idLocale, codeISO) VALUES ((SELECT nextval('locale')), 'EN');

INSERT INTO locale_trans (idTrans, idLocale, idLocaleGroup, text)
VALUES ((SELECT nextval('locale')), 142, 142, 'Español');

SELECT *
FROM locale;

SELECT *
FROM locale
WHERE codeISO = 'ES' AND idLocale = 142;

SELECT *
FROM locale t1
WHERE t1.codeISO = 'ES' AND t1.idLocale = 142;

SELECT *
FROM locale t1 LEFT JOIN locale_trans t2 ON t1.idLocale = t2.idLocaleGroup
WHERE t1.codeISO = 'ES' AND t1.idLocale = 142;