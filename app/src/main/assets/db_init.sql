PRAGMA foreign_keys = ON;

CREATE TABLE "accounts" (
	"id" 			INTEGER PRIMARY KEY,
	"username"		TEXT NOT NULL UNIQUE,
	"email"			TEXT NOT NULL UNIQUE COLLATE NOCASE,
	"password"		TEXT NOT NULL,
	"created_at" 	INTEGER NOT NULL
);

CREATE TABLE "boxes"(
	"id"			INTEGER PRIMARY KEY,
	"color_name"	TEXT NOT NULL,
	"color_value"	TEXT NOT NULL
);

CREATE TABLE "accounts_boxes_settings" (
	"account_id"	INTEGER NOT NULL,
	"box_id"		INTEGER NOT NULL,
	"is_active"		INTEGER NOT NULL,
	FOREIGN KEY("account_id") REFERENCES "accounts" ("id")
		ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY("box_id") REFERENCES "boxes" ("id")
		ON UPDATE CASCADE ON DELETE CASCADE,
	UNIQUE("account_id","box_id")
);

INSERT INTO "accounts" ("username","email","password","created_at")
VALUES
	("admin", "admin@gmail.com","123456", 0),
	("neadmin", "neadmin@gmail.com","654321",0);

	INSERT INTO "boxes" ("color_name","color_value")
    VALUES
    	("Green","#00FF00"),
    	("Red","#FF0000"),
    	("Blue","#0000FF"),
    	("Yellow","#888800"),
    	("Black","#000000"),
    	("White","#FFFFFF");