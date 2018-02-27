INSERT INTO locale (idLocale, codeISO) VALUES ((SELECT nextval('locale')), 'EN');

select * from locale;

SELECT * FROM locale WHERE codeISO = 'ES' AND idLocale = 142;