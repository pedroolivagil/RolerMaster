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

SELECT
  t1.*,
  t2.*
FROM locale t1 LEFT JOIN locale_trans t2 ON t1.idLocale = t2.idLocaleGroup
WHERE t1.codeISO = 'ES' AND t1.idLocale = 142;


SHOW COLUMNS FROM locale;

SELECT
  locale.idLocale,
  locale.codeISO,
  locale_trans.idTrans,
  locale_trans.idLocale,
  locale_trans.idLocaleGroup,
  locale_trans.text
FROM locale locale LEFT JOIN locale_trans locale_trans ON locale.idLocale = locale_trans.idLocaleGroup
WHERE locale.codeISO = 'ES' AND locale.codeISO = 'ES' AND locale.idLocale = 142 AND locale.idLocale = 142;

SELECT
  locale.idLocale            AS "locale.idLocale",
  locale.codeISO             AS "locale.codeISO",
  locale_trans.idTrans       AS "locale_trans.idTrans",
  locale_trans.idLocale      AS "locale_trans.idLocale",
  locale_trans.idLocaleGroup AS "locale_trans.idLocaleGroup",
  locale_trans.text          AS "locale_trans.text"
FROM locale locale LEFT JOIN locale_trans locale_trans ON locale.idLocale = locale_trans.idLocaleGroup
WHERE locale.codeISO = 'ES' AND locale.codeISO = 'ES' AND locale.idLocale = 142 AND locale.idLocale = 142;