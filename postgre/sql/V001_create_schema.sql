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

CREATE TABLE techmaps_platform.dashboard(
    id uuid NOT NULL,
    total_time int NOT NULL
);

ALTER TABLE techmaps_platform.dashboard OWNER TO "techmaps";

ALTER TABLE techmaps_platform.dashboard
    ADD CONSTRAINT dashboard_pkey PRIMARY KEY (id);

CREATE TYPE techmaps_platform.roadmap_type AS ENUM (
    'FRONTEND',
    'BACKEND',
    'ANDROID'
);

ALTER TYPE techmaps_platform.roadmap_type OWNER TO "techmaps";

CREATE TYPE techmaps_platform.roadmap_status AS ENUM (
    'COMPLETE',
    'UNCOMPLETE'
);

ALTER TYPE techmaps_platform.roadmap_status OWNER TO "techmaps";

CREATE TYPE techmaps_platform.roadmap_lang AS ENUM (
    'JAVA',
    'PYTHON',
    'JAVASCRIPT',
    'KOTLIN'
);

ALTER TYPE techmaps_platform.roadmap_lang OWNER TO "techmaps";

CREATE TABLE techmaps_platform.roadmap(
    id uuid NOT NULL,
    title varchar NOT NULL,
    type techmaps_platform.roadmap_type,
    status techmaps_platform.roadmap_status,
    lang techmaps_platform.roadmap_lang,
    start_time timestamp NOT NULL,
    finish_time timestamp,
    commit_counter integer,
    dashboard_id uuid NOT NULL
);

ALTER TABLE techmaps_platform.roadmap OWNER TO "techmaps";

ALTER TABLE techmaps_platform.roadmap
    ADD CONSTRAINT roadmap_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.roadmap
    ADD CONSTRAINT roadmap_dashboard_id_fkey FOREIGN KEY (dashboard_id)
        REFERENCES techmaps_platform.dashboard(id) ON DELETE CASCADE;

CREATE TYPE techmaps_platform.stage_theme AS ENUM (
    'LEARN_PYTHON',
    'LEARN_JAVA',
    'LEARN_KOTLIN',
    'LEARN_HTML',
    'LEARN_CSS',
    'LEARN_JS',
    'LEARN_INTERNET',
    'LEARN_DIAGRAMS',
    'LEARN_WEBSERVERS',
    'LEARN_CLOUD',
    'LEARN_GIT',
    'LEARN_GITHUB',
    'LEARN_API',
    'LEARN_REST',
    'LEARN_SOAP',
    'LEARN_AGILE',
    'LEARN_DEVOPS',
    'LEARN_OOP',
    'LEARN_SOLID',
    'LEARN_CLEAN_CODE',
    'LEARN_TDD',
    'LEARN_CLEAN_ARCHITECTURE',
    'LEARN_VSCODE',
    'LEARN_INTELLIJ',
    'LEARN_ANDROID',
    'LEARN_ANGULAR',
    'LEARN_REACT',
    'LEARN_SPRING',
    'LEARN_MYSQL',
    'LEARN_POSTGRES',
    'LEARN_DOCKER',
    'LEARN_FIREBASE'
);

ALTER TYPE techmaps_platform.stage_theme OWNER TO "techmaps";

CREATE TYPE techmaps_platform.stage_status AS ENUM (
    'DONE',
    'UNDONE'
);

ALTER TYPE techmaps_platform.stage_status OWNER TO "techmaps";

CREATE TABLE techmaps_platform.stage(
    id uuid NOT NULL,
    roadmap_id uuid NOT NULL,
    theme techmaps_platform.stage_theme,
    status techmaps_platform.stage_status,
    commit_counter integer
);

ALTER TABLE techmaps_platform.stage OWNER TO "techmaps";

ALTER TABLE techmaps_platform.stage
    ADD CONSTRAINT stage_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.stage
    ADD CONSTRAINT stage_roadmap_id_fkey FOREIGN KEY (roadmap_id)
        REFERENCES techmaps_platform.roadmap(id) ON DELETE CASCADE;

CREATE TABLE techmaps_platform.task(
    id uuid NOT NULL,
    stage_id uuid NOT NULL,
    title varchar NOT NULL,
    description varchar NOT NULL,
    repository_link varchar NOT NULL,
    date_created timestamp,
    date_finished timestamp,
    dashboard_id uuid NOT NULL
);

ALTER TABLE techmaps_platform.task OWNER TO "techmaps";

ALTER TABLE techmaps_platform.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.task
    ADD CONSTRAINT task_stage_id_fkey FOREIGN KEY (stage_id)
        REFERENCES techmaps_platform.stage(id) ON DELETE CASCADE;

ALTER TABLE techmaps_platform.task
    ADD CONSTRAINT task_dashboard_id_fkey FOREIGN KEY (dashboard_id)
        REFERENCES techmaps_platform.dashboard(id) ON DELETE CASCADE;

CREATE TYPE techmaps_platform.task_commit_status AS ENUM (
    'STAGED',
    'UNSTAGED'
);

ALTER TYPE techmaps_platform.task_commit_status OWNER TO "techmaps";

CREATE TABLE techmaps_platform.task_commit(
    id uuid NOT NULL,
    task_id uuid NOT NULL,
    tag varchar NOT NULL,
    state techmaps_platform.task_commit_status,
    dashboard_id uuid NOT NULL
);

ALTER TABLE techmaps_platform.task OWNER TO "techmaps";

ALTER TABLE techmaps_platform.task_commit
    ADD CONSTRAINT task_commit_pkey PRIMARY KEY (id);

ALTER TABLE techmaps_platform.task_commit
    ADD CONSTRAINT task_commit_task_id_fkey FOREIGN KEY (task_id)
        REFERENCES techmaps_platform.task(id) ON DELETE CASCADE;

ALTER TABLE techmaps_platform.task_commit
    ADD CONSTRAINT task_commit_dashboard_id_fkey FOREIGN KEY (dashboard_id)
        REFERENCES techmaps_platform.dashboard(id) ON DELETE CASCADE;