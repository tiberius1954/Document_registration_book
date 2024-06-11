BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "country" (
	"countrycode"	TEXT NOT NULL,
	"countryname"	TEXT NOT NULL,
	"code"	TEXT DEFAULT NULL,
	PRIMARY KEY("countrycode")
);
CREATE TABLE IF NOT EXISTS "partners" (
	"pid"	INTEGER,
	"name"	TEXT DEFAULT ' ',
	"postalcodeh"	TEXT DEFAULT ' ',
	"addressh"	TEXT DEFAULT ' ',
	"cityh"	TEXT DEFAULT ' ',
	"countryh"	TEXT DEFAULT ' ',
	"postalcodem"	TEXT DEFAULT ' ',
	"addressm"	TEXT DEFAULT ' ',
	"citym"	TEXT DEFAULT ' ',
	"countrym"	TEXT DEFAULT ' ',
	"email"	TEXT DEFAULT ' ',
	"phone"	TEXT DEFAULT ' ',
	"homepage"	TEXT DEFAULT ' ',
	"note"	TEXT DEFAULT ' ',
	"category"	INTEGER DEFAULT 0,
	PRIMARY KEY("pid" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "reg_book" (
	"rid"	INTEGER,
	"direction"	TEXT DEFAULT ' ',
	"regnumber"	TEXT DEFAULT ' ',
	"pid"	INTEGER NOT NULL DEFAULT 0,
	"receive_date"	TEXT NOT NULL DEFAULT ' ',
	"partner_regnum"	TEXT DEFAULT ' ',
	"doc_category"	INTEGER NOT NULL DEFAULT 0,
	"send_date"	TEXT DEFAULT ' ',
	"arrive_mode"	INTEGER NOT NULL DEFAULT 0,
	"subject"	TEXT DEFAULT ' ',
	"package_type"	INTEGER DEFAULT 0,
	"note"	TEXT DEFAULT ' ',
	"administrator"	INTEGER DEFAULT 0,
	"partner_admin"	TEXT DEFAULT ' ',
	PRIMARY KEY("rid" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "params" (
	"parid"	INTEGER,
	"name"	TEXT DEFAULT ' ',
	"postal_code"	TEXT DEFAULT ' ',
	"address"	TEXT DEFAULT ' ',
	"city"	TEXT DEFAULT ' ',
	"country"	TEXT DEFAULT ' ',
	"phone"	TEXT DEFAULT ' ',
	"email"	TEXT DEFAULT ' ',
	"in_regnumber"	INTEGER DEFAULT 0,
	"out_regnumber"	INTEGER DEFAULT 0,
	"prefix"	TEXT DEFAULT ' ',
	"currentyear"	TEXT DEFAULT ' ',
	PRIMARY KEY("parid" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "codes" (
	"cid"	INTEGER,
	"type"	TEXT DEFAULT ' ',
	"name"	TEXT DEFAULT ' ',
	PRIMARY KEY("cid" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "documents" (
	"did"	INTEGER,
	"dname"	TEXT DEFAULT ' ',
	"dpath"	TEXT DEFAULT ' ',
	"dext"	TEXT DEFAULT ' ',
	"rid"	INTEGER NOT NULL DEFAULT 0,
	PRIMARY KEY("did" AUTOINCREMENT)
);
INSERT INTO "country" ("countrycode","countryname","code") VALUES ('AFG','Afghanistan','AF'),
 ('ALA','Åland','AX'),
 ('ALB','Albania','AL'),
 ('DZA','Algeria','DZ'),
 ('ASM','American Samoa','AS'),
 ('AND','Andorra','AD'),
 ('AGO','Angola','AO'),
 ('AIA','Anguilla','AI'),
 ('ATA','Antarctica','AQ'),
 ('ATG','Antigua and Barbuda','AG'),
 ('ARG','Argentina','AR'),
 ('ARM','Armenia','AM'),
 ('ABW','Aruba','AW'),
 ('AUS','Australia','AU'),
 ('AUT','Austria','AT'),
 ('AZE','Azerbaijan','AZ'),
 ('BHS','Bahamas','BS'),
 ('BHR','Bahrain','BH'),
 ('BGD','Bangladesh','BD'),
 ('BRB','Barbados','BB'),
 ('BLR','Belarus','BY'),
 ('BEL','Belgium','BE'),
 ('BLZ','Belize','BZ'),
 ('BEN','Benin','BJ'),
 ('BMU','Bermuda','BM'),
 ('BTN','Bhutan','BT'),
 ('BOL','Bolivia','BO'),
 ('BES','Bonaire','BQ'),
 ('BIH','Bosnia and Herzegovina','BA'),
 ('BWA','Botswana','BW'),
 ('BVT','Bouvet Island','BV'),
 ('BRA','Brazil','BR'),
 ('IOT','British Indian Ocean Territory','IO'),
 ('VGB','British Virgin Islands','VG'),
 ('BRN','Brunei','BN'),
 ('BGR','Bulgaria','BG'),
 ('BFA','Burkina Faso','BF'),
 ('BDI','Burundi','BI'),
 ('KHM','Cambodia','KH'),
 ('CMR','Cameroon','CM'),
 ('CAN','Canada','CA'),
 ('CPV','Cape Verde','CV'),
 ('CYM','Cayman Islands','KY'),
 ('CAF','Central African Republic','CF'),
 ('TCD','Chad','TD'),
 ('CHL','Chile','CL'),
 ('CHN','China','CN'),
 ('CXR','Christmas Island','CX'),
 ('CCK','Cocos [Keeling] Islands','CC'),
 ('COL','Colombia','CO'),
 ('COM','Comoros','KM'),
 ('COK','Cook Islands','CK'),
 ('CRI','Costa Rica','CR'),
 ('HRV','Croatia','HR'),
 ('CUB','Cuba','CU'),
 ('CUW','Curacao','CW'),
 ('CYP','Cyprus','CY'),
 ('CZE','Czech Republic','CZ'),
 ('COD','Democratic Republic of the Congo','CD'),
 ('DNK','Denmark','DK'),
 ('DJI','Djibouti','DJ'),
 ('DMA','Dominica','DM'),
 ('DOM','Dominican Republic','DO'),
 ('TLS','East Timor','TL'),
 ('ECU','Ecuador','EC'),
 ('EGY','Egypt','EG'),
 ('SLV','El Salvador','SV'),
 ('GNQ','Equatorial Guinea','GQ'),
 ('ERI','Eritrea','ER'),
 ('EST','Estonia','EE'),
 ('ETH','Ethiopia','ET'),
 ('FLK','Falkland Islands','FK'),
 ('FRO','Faroe Islands','FO'),
 ('FJI','Fiji','FJ'),
 ('FIN','Finland','FI'),
 ('FRA','France','FR'),
 ('GUF','French Guiana','GF'),
 ('PYF','French Polynesia','PF'),
 ('ATF','French Southern Territories','TF'),
 ('GAB','Gabon','GA'),
 ('GMB','Gambia','GM'),
 ('GEO','Georgia','GE'),
 ('DEU','Germany','DE'),
 ('GHA','Ghana','GH'),
 ('GIB','Gibraltar','GI'),
 ('GRC','Greece','GR'),
 ('GRL','Greenland','GL'),
 ('GRD','Grenada','GD'),
 ('GLP','Guadeloupe','GP'),
 ('GUM','Guam','GU'),
 ('GTM','Guatemala','GT'),
 ('GGY','Guernsey','GG'),
 ('GIN','Guinea','GN'),
 ('GNB','Guinea-Bissau','GW'),
 ('GUY','Guyana','GY'),
 ('HTI','Haiti','HT'),
 ('HMD','Heard Island and McDonald Islands','HM'),
 ('HND','Honduras','HN'),
 ('HKG','Hong Kong','HK'),
 ('HUN','Hungary','HU'),
 ('ISL','Iceland','IS'),
 ('IND','India','IN'),
 ('IDN','Indonesia','ID'),
 ('IRN','Iran','IR'),
 ('IRQ','Iraq','IQ'),
 ('IRL','Ireland','IE'),
 ('IMN','Isle of Man','IM'),
 ('ISR','Israel','IL'),
 ('ITA','Italy','IT'),
 ('CIV','Ivory Coast','CI'),
 ('JAM','Jamaica','JM'),
 ('JPN','Japan','JP'),
 ('JEY','Jersey','JE'),
 ('JOR','Jordan','JO'),
 ('KAZ','Kazakhstan','KZ'),
 ('KEN','Kenya','KE'),
 ('KIR','Kiribati','KI'),
 ('XKX','Kosovo','XK'),
 ('KWT','Kuwait','KW'),
 ('KGZ','Kyrgyzstan','KG'),
 ('LAO','Laos','LA'),
 ('LVA','Latvia','LV'),
 ('LBN','Lebanon','LB'),
 ('LSO','Lesotho','LS'),
 ('LBR','Liberia','LR'),
 ('LBY','Libya','LY'),
 ('LIE','Liechtenstein','LI'),
 ('LTU','Lithuania','LT'),
 ('LUX','Luxembourg','LU'),
 ('MAC','Macao','MO'),
 ('MKD','Macedonia','MK'),
 ('MDG','Madagascar','MG'),
 ('MWI','Malawi','MW'),
 ('MYS','Malaysia','MY'),
 ('MDV','Maldives','MV'),
 ('MLI','Mali','ML'),
 ('MLT','Malta','MT'),
 ('MHL','Marshall Islands','MH'),
 ('MTQ','Martinique','MQ'),
 ('MRT','Mauritania','MR'),
 ('MUS','Mauritius','MU'),
 ('MYT','Mayotte','YT'),
 ('MEX','Mexico','MX'),
 ('FSM','Micronesia','FM'),
 ('MDA','Moldova','MD'),
 ('MCO','Monaco','MC'),
 ('MNG','Mongolia','MN'),
 ('MNE','Montenegro','ME'),
 ('MSR','Montserrat','MS'),
 ('MAR','Morocco','MA'),
 ('MOZ','Mozambique','MZ'),
 ('MMR','Myanmar [Burma]','MM'),
 ('NAM','Namibia','NA'),
 ('NRU','Nauru','NR'),
 ('NPL','Nepal','NP'),
 ('NLD','Netherlands','NL'),
 ('NCL','New Caledonia','NC'),
 ('NZL','New Zealand','NZ'),
 ('NIC','Nicaragua','NI'),
 ('NER','Niger','NE'),
 ('NGA','Nigeria','NG'),
 ('NIU','Niue','NU'),
 ('NFK','Norfolk Island','NF'),
 ('PRK','North Korea','KP'),
 ('MNP','Northern Mariana Islands','MP'),
 ('NOR','Norway','NO'),
 ('OMN','Oman','OM'),
 ('PAK','Pakistan','PK'),
 ('PLW','Palau','PW'),
 ('PSE','Palestine','PS'),
 ('PAN','Panama','PA'),
 ('PNG','Papua New Guinea','PG'),
 ('PRY','Paraguay','PY'),
 ('PER','Peru','PE'),
 ('PHL','Philippines','PH'),
 ('PCN','Pitcairn Islands','PN'),
 ('POL','Poland','PL'),
 ('PRT','Portugal','PT'),
 ('PRI','Puerto Rico','PR'),
 ('QAT','Qatar','QA'),
 ('COG','Republic of the Congo','CG'),
 ('REU','Réunion','RE'),
 ('ROU','Romania','RO'),
 ('RUS','Russia','RU'),
 ('RWA','Rwanda','RW'),
 ('BLM','Saint Barthélemy','BL'),
 ('SHN','Saint Helena','SH'),
 ('KNA','Saint Kitts and Nevis','KN'),
 ('LCA','Saint Lucia','LC'),
 ('MAF','Saint Martin','MF'),
 ('SPM','Saint Pierre and Miquelon','PM'),
 ('VCT','Saint Vincent and the Grenadines','VC'),
 ('WSM','Samoa','WS'),
 ('SMR','San Marino','SM'),
 ('STP','São Tomé and Príncipe','ST'),
 ('SAU','Saudi Arabia','SA'),
 ('SEN','Senegal','SN'),
 ('SRB','Serbia','RS'),
 ('SYC','Seychelles','SC'),
 ('SLE','Sierra Leone','SL'),
 ('SGP','Singapore','SG'),
 ('SXM','Sint Maarten','SX'),
 ('SVK','Slovakia','SK'),
 ('SVN','Slovenia','SI'),
 ('SLB','Solomon Islands','SB'),
 ('SOM','Somalia','SO'),
 ('ZAF','South Africa','ZA'),
 ('SGS','South Georgia and the South Sandwich Islands','GS'),
 ('KOR','South Korea','KR'),
 ('SSD','South Sudan','SS'),
 ('ESP','Spain','ES'),
 ('LKA','Sri Lanka','LK'),
 ('SDN','Sudan','SD'),
 ('SUR','Suriname','SR'),
 ('SJM','Svalbard and Jan Mayen','SJ'),
 ('SWZ','Swaziland','SZ'),
 ('SWE','Sweden','SE'),
 ('CHE','Switzerland','CH'),
 ('SYR','Syria','SY'),
 ('TWN','Taiwan','TW'),
 ('TJK','Tajikistan','TJ'),
 ('TZA','Tanzania','TZ'),
 ('THA','Thailand','TH'),
 ('TGO','Togo','TG'),
 ('TKL','Tokelau','TK'),
 ('TON','Tonga','TO'),
 ('TTO','Trinidad and Tobago','TT'),
 ('TUN','Tunisia','TN'),
 ('TUR','Turkey','TR'),
 ('TKM','Turkmenistan','TM'),
 ('TCA','Turks and Caicos Islands','TC'),
 ('TUV','Tuvalu','TV'),
 ('UMI','U.S. Minor Outlying Islands','UM'),
 ('VIR','U.S. Virgin Islands','VI'),
 ('UGA','Uganda','UG'),
 ('UKR','Ukraine','UA'),
 ('ARE','United Arab Emirates','AE'),
 ('GBR','United Kingdom','GB'),
 ('USA','United States','US'),
 ('URY','Uruguay','UY'),
 ('UZB','Uzbekistan','UZ'),
 ('VUT','Vanuatu','VU'),
 ('VAT','Vatican City','VA'),
 ('VEN','Venezuela','VE'),
 ('VNM','Vietnam','VN'),
 ('WLF','Wallis and Futuna','WF'),
 ('ESH','Western Sahara','EH'),
 ('YEM','Yemen','YE'),
 ('ZMB','Zambia','ZM'),
 ('ZWE','Zimbabwe','ZW');
INSERT INTO "partners" ("pid","name","postalcodeh","addressh","cityh","countryh","postalcodem","addressm","citym","countrym","email","phone","homepage","note","category") VALUES (5,'Maurice Andre','p1234','Saint Michelle street 10. ','Paris','France','p1234','Saint Michelle street 10. ','Paris','France','maurice.andre@gmail.com','22/1234-667','','oke',0),
 (6,'James Cook','20001','4414th street, NW','Washington','United States','20001','4414th street, NW','Washington','United States','','','','',0);
INSERT INTO "reg_book" ("rid","direction","regnumber","pid","receive_date","partner_regnum","doc_category","send_date","arrive_mode","subject","package_type","note","administrator","partner_admin") VALUES (15,'In','DCIn2024 /4',5,'2024-05-30','',6,'',17,'',12,'',11,''),
 (16,'In','DCIn2024 /5',5,'2024-05-28','',8,'',19,'',0,'',11,''),
 (17,'Out','DCOut2024 /1',5,'2024-05-29','',7,'',18,'',0,'',11,'');
INSERT INTO "params" ("parid","name","postal_code","address","city","country","phone","email","in_regnumber","out_regnumber","prefix","currentyear") VALUES (1,'My firm','233444','Downing 10.','London',' ','655/123-1234','myfirm@gmail.com',5,1,'DC','2024 ');
INSERT INTO "codes" ("cid","type","name") VALUES (5,'D','Invoice'),
 (6,'D','Documents'),
 (7,'D','Bank statement'),
 (8,'D','Agreement'),
 (9,'D','Document sending'),
 (10,'A','Mr. Andreas Cavalcanty'),
 (11,'A','Anne Bergman'),
 (12,'C','Express delivery'),
 (13,'C','Registered post'),
 (14,'C','Postal matter'),
 (15,'C','General delivery'),
 (16,'C','Special delivery'),
 (17,'S','Post letter'),
 (18,'S','Email'),
 (19,'S','Courier'),
 (20,'S','Personally');
COMMIT;