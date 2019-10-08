CREATE TABLE arrangement
(
    arrangement_id        uuid PRIMARY KEY,
    overskrift            text,
    arrangør              text,
    arrangert             text,
    adresse               text,
    postnr                text,
    poststed              text,
    land                  text,
    fylke                 text,
    kommune               text,
    dato                  text,
    start                 text,
    kart                  text,
    mål                   text,
    parkering             text,
    garderobe             text,
    etteranmeldingsgebyr  text,
    kontonummer           text,
    påmelding             text,
    påmelding_postadresse text,
    påmelding_postnr      text,
    påmelding_poststed    text,
    kvinneløp             text,
    motbakkeløp           text,
    nfif_lisens           text,
    inngår_i              text,
    link                  text,
    underlag              text,
    underlag_kommentar    text,
    hjemmesider           text,
    premier               text,
    kommentar             text,
    klasseinndeling       text,
    beskrivelse           text
);


CREATE TABLE distanse
(
    distanse_id                  uuid PRIMARY KEY,
    arrangement_id               uuid references arrangement (arrangement_id) not null,
    distanse_lengde              text,
    distanse_starttid            text,
    distanse_startkontingent     text,
    distanse_fullforte_aaret_for text,
    distanse_barnelop            text,
    distanse_stafett             text,
    distanse_etapper             text,
    distanse_kontrollmålt        text
);


CREATE TABLE kontaktperson
(
    kontaktperson_id uuid PRIMARY KEY,
    arrangement_id   uuid references arrangement (arrangement_id) not null,
    navn             text,
    telefon          text,
    mobil            text,
    epost            text
);

