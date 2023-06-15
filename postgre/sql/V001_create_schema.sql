CREATE SCHEMA techmaps_platform;

ALTER SCHEMA techmaps_platform OWNER TO "techmaps";

CREATE TABLE techmaps_platform.user(
                                                   id uuid NOT NULL,
                                                   username varchar NOT NULL UNIQUE,
                                                   email varchar NOT NULL UNIQUE,
                                                   password varchar NOT NULL,
                                                   github varchar,
                                                   is_account_non_expired boolean default false,
                                                   is_account_nonLocked boolean default false,
                                                   is_credentials_non_expired boolean default false,
                                                   is_enabled boolean default false
);

ALTER TABLE techmaps_platform.user OWNER TO "techmaps";

ALTER TABLE techmaps_platform.user
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

CREATE TYPE nexushub_platform.subject_color AS ENUM (
    'RED',
    'BLUE',
    'GREEN',
    'YELLOW',
    'ORANGE',
    'PURPLE',
    'PINK',
    'BROWN',
    'BLACK',
    'WHITE',
    'GRAY'
);

ALTER TYPE nexushub_platform.subject_color OWNER TO "nexushub";

CREATE TABLE nexushub_platform.subject(
                                          id uuid not null,
                                          name varchar(255) not null,
                                          difficulty integer not null,
                                          owner_id uuid not null,
                                          color nexushub_platform.subject_color not null
);

ALTER TABLE nexushub_platform.subject OWNER TO "nexushub";

ALTER TABLE nexushub_platform.subject
    ADD CONSTRAINT subject_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.subject
    ADD CONSTRAINT subject_user_fk
        FOREIGN KEY (owner_id) REFERENCES nexushub_platform.application_user(id) ON DELETE CASCADE;

CREATE TYPE nexushub_platform.todo_status AS ENUM (
    'PENDING',
    'DONE'
);

ALTER TYPE nexushub_platform.todo_status OWNER TO "nexushub";

CREATE TABLE nexushub_platform.todo(
                                       id uuid not null,
                                       text varchar(255) not null,
                                       date timestamp not null,
                                       status nexushub_platform.todo_status not null,
                                       subject_id uuid not null
);

ALTER TABLE nexushub_platform.todo OWNER TO "nexushub";

ALTER TABLE nexushub_platform.todo
    ADD CONSTRAINT todo_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.todo
    ADD CONSTRAINT todo_subject_fk
        FOREIGN KEY (subject_id) REFERENCES nexushub_platform.subject(id) ON DELETE CASCADE;

CREATE TABLE nexushub_platform.cycle(
                                        id uuid not null,
                                        name varchar(255) not null,
                                        description varchar(255),
                                        owner_id uuid not null,
                                        amount_hours float not null
);

ALTER TABLE nexushub_platform.cycle OWNER TO "nexushub";

ALTER TABLE nexushub_platform.cycle
    ADD CONSTRAINT cycle_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.cycle
    ADD CONSTRAINT cycle_user_fk
        FOREIGN KEY (owner_id) REFERENCES nexushub_platform.application_user(id) ON DELETE CASCADE;

CREATE TYPE nexushub_platform.sequence_status AS ENUM (
    'RUNNING',
    'FINISHED',
    'SKIPPED'
);

CREATE TABLE nexushub_platform.sequence(
                                           id uuid not null,
                                           step integer not null,
                                           last_sequence_subject_step integer not null,
                                           status nexushub_platform.sequence_status not null,
                                           cycle_id uuid not null
);

ALTER TABLE nexushub_platform.sequence OWNER TO "nexushub";

ALTER TABLE nexushub_platform.sequence
    ADD CONSTRAINT sequence_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.sequence
    ADD CONSTRAINT sequence_cycle_fk
        FOREIGN KEY (cycle_id) REFERENCES nexushub_platform.cycle(id) ON DELETE CASCADE;

CREATE TYPE nexushub_platform.sequence_subject_status AS ENUM (
    'PENDING',
    'STUDYING',
    'FINISHED',
    'SKIPPED'
);

CREATE TABLE nexushub_platform.sequence_subject(
                                                   id uuid not null,
                                                   step integer not null,
                                                   hours float not null,
                                                   studied_hours float not null,
                                                   status nexushub_platform.sequence_subject_status not null,
                                                   sequence_id uuid not null
);

ALTER TABLE nexushub_platform.sequence_subject OWNER TO "nexushub";

ALTER TABLE nexushub_platform.sequence_subject
    ADD CONSTRAINT sequence_subject_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.sequence_subject
    ADD CONSTRAINT sequence_subject_sequence_fk
        FOREIGN KEY (sequence_id) REFERENCES nexushub_platform.sequence(id) ON DELETE CASCADE;

CREATE TABLE nexushub_platform.deck(
                                       id uuid not null,
                                       name varchar(255) not null,
                                       owner_id uuid not null,
                                       subject_id uuid,
                                       parent_deck_id uuid
);

ALTER TABLE nexushub_platform.deck OWNER TO "nexushub";

ALTER TABLE nexushub_platform.deck
    ADD CONSTRAINT deck_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.deck
    ADD CONSTRAINT deck_parent_fk
        FOREIGN KEY (parent_deck_id) REFERENCES nexushub_platform.deck(id) ON DELETE CASCADE;

ALTER TABLE nexushub_platform.deck
    ADD CONSTRAINT deck_user_fk
        FOREIGN KEY (owner_id) REFERENCES nexushub_platform.application_user(id) ON DELETE CASCADE;

ALTER TABLE nexushub_platform.deck
    ADD CONSTRAINT deck_subject_fk
        FOREIGN KEY (subject_id) REFERENCES nexushub_platform.subject(id);

CREATE TYPE nexushub_platform.flashcard_status AS ENUM (
    'NEW',
    'LEARNING',
    'REVIEWING',
    'SUSPENDED'
);

ALTER TYPE nexushub_platform.flashcard_status OWNER TO "nexushub";

CREATE TABLE nexushub_platform.flashcard(
                                            id uuid not null,
                                            question varchar(255) not null,
                                            answer varchar(255) not null,
                                            next_revision_date timestamp not null,
                                            last_revision_date timestamp not null,
                                            status nexushub_platform.flashcard_status not null,
                                            maturity float not null,
                                            deck_id uuid not null
);

ALTER TABLE nexushub_platform.flashcard OWNER TO "nexushub";

ALTER TABLE nexushub_platform.flashcard
    ADD CONSTRAINT flashcard_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.flashcard
    ADD CONSTRAINT flashcard_deck_fk
        FOREIGN KEY (deck_id) REFERENCES nexushub_platform.deck(id) ON DELETE CASCADE;

CREATE TABLE nexushub_platform.tag(
                                      id uuid not null,
                                      name varchar(255) not null,
                                      owner_id uuid not null
);

ALTER TABLE nexushub_platform.tag OWNER TO "nexushub";

ALTER TABLE nexushub_platform.tag
    ADD CONSTRAINT tag_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.tag
    ADD CONSTRAINT tag_user_fk
        FOREIGN KEY (owner_id) REFERENCES nexushub_platform.application_user(id) ON DELETE CASCADE;

CREATE TABLE nexushub_platform.flashcard_tag(
                                                flashcard_id uuid not null,
                                                tag_id uuid not null
);

ALTER TABLE nexushub_platform.flashcard_tag OWNER TO "nexushub";

ALTER TABLE nexushub_platform.flashcard_tag
    ADD CONSTRAINT flashcard_tag_pk PRIMARY KEY (flashcard_id, tag_id);

ALTER TABLE nexushub_platform.flashcard_tag
    ADD CONSTRAINT flashcard_tag_flashcard_fk
        FOREIGN KEY (flashcard_id) REFERENCES nexushub_platform.flashcard(id) ON DELETE CASCADE;

ALTER TABLE nexushub_platform.flashcard_tag
    ADD CONSTRAINT flashcard_tag_tag_fk
        FOREIGN KEY (tag_id) REFERENCES nexushub_platform.tag(id) ON DELETE CASCADE;

CREATE TABLE nexushub_platform.session(
                                          id uuid not null,
                                          start_time timestamp not null,
                                          end_time timestamp not null,
                                          owner_id uuid not null,
                                          subject_id uuid,
                                          sequence_subject_id uuid,
                                          deck_id uuid
);

ALTER TABLE nexushub_platform.session OWNER TO "nexushub";

ALTER TABLE nexushub_platform.session
    ADD CONSTRAINT session_pk PRIMARY KEY (id);

ALTER TABLE nexushub_platform.session
    ADD CONSTRAINT session_deck_fk
        FOREIGN KEY (deck_id) REFERENCES nexushub_platform.deck(id);

ALTER TABLE nexushub_platform.session
    ADD CONSTRAINT session_sequence_subject_fk
        FOREIGN KEY (sequence_subject_id) REFERENCES nexushub_platform.sequence_subject(id);

ALTER TABLE nexushub_platform.session
    ADD CONSTRAINT session_subject_fk
        FOREIGN KEY (subject_id) REFERENCES nexushub_platform.subject(id);

ALTER TABLE nexushub_platform.session
    ADD CONSTRAINT session_user_fk
        FOREIGN KEY (owner_id) REFERENCES nexushub_platform.application_user(id) ON DELETE CASCADE;
