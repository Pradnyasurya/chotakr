DROP SEQUENCE IF EXISTS chotakr_url_id;
CREATE SEQUENCE chotakr_url_id;

CREATE TABLE "chotakr_url" (
    "id" VARCHAR(128) NOT NULL,
    "validform" bigint,
    "validto" bigint,
    "url"  VARCHAR(1024) NOT NULL,
    PRIMARY KEY ("id")
);
