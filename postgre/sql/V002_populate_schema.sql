--! User Queries --!

insert into techmaps_platform.user(id, username, email, password,
    is_account_non_expired, is_account_non_locked, is_credentials_non_expired,
        is_enabled) values ('1b185f9e-6c0f-4617-bd77-dd4620a41a0f'::uuid,
            'miguel', 'miguel@gmail.com',
                '$2a$10$6ntpsnNIXuFuPkIU08EiueXZh2qu4cUZQ0McdaujdfZUgWjZ0JPGq', --! password !--
                    true, true, true, true);


insert into techmaps_platform.user(id, username, email, password,
    is_account_non_expired, is_account_non_locked, is_credentials_non_expired,
        is_enabled) values ('cc171e74-5dc3-4a38-9c86-5844ed673b5f'::uuid,
            'Hector', 'hector@gmail.com',
                '$2a$10$LzzhmD5vaUQEe2nXvarWsOqpzxkrON9u20dooYrMTesQLk4zzBV5S', --! senha !--
                    true, true, true, true);

--! Dashboard Queries --!

insert into techmaps_platform.dashboard(id, total_roadmaps, total_tasks, total_commits, total_time, user_id) values
    ('582c710f-849c-4a9f-a10f-0b68c784f240'::uuid, 0, 0, 0, null, 'cc171e74-5dc3-4a38-9c86-5844ed673b5f'::uuid);

insert into techmaps_platform.dashboard(id, total_roadmaps, total_tasks, total_commits, total_time, user_id) values
    ('436ee536-93a3-4c20-9271-18a19c01efdb'::uuid, 0, 0, 0, null, '1b185f9e-6c0f-4617-bd77-dd4620a41a0f'::uuid);

--! Roadmap Queries --!

insert into techmaps_platform.roadmap(id, title, type, is_completed, lang, color,
    start_time, finish_time, total_time, commit_counter, dashboard_id) values
    ('3c7b7683-bfeb-4a49-80b8-4080abb49dac'::uuid, 'Java Roadmap',
    'BACKEND', false, 'JAVA', 'ORANGE', '2023-06-09 08:00:00',
    null, '10 days 10:00:00'::interval, 0, '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid);

insert into techmaps_platform.roadmap(id, title, type, is_completed, lang, color,
    start_time, finish_time, total_time, commit_counter, dashboard_id) values
        ('50241d67-68b6-4db5-977b-91dda87750bd'::uuid, 'JS Roadmap',
        'FRONTEND', false, 'JAVASCRIPT', 'YELLOW', '2023-02-19 08:00:00',
         '2023-06-28 16:00:00', '129 days 08:00:00'::interval, 18,
        '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid);

--! Stage Queries --!

insert into techmaps_platform.stage(id, roadmap_id, theme, status, stage_number,
    commit_counter) values ('962542e7-6813-48b6-8e20-7bfc181146bf'::uuid,
    '3c7b7683-bfeb-4a49-80b8-4080abb49dac'::uuid, 'LEARN_JAVA', 'UNDONE', 1, 0);

insert into techmaps_platform.stage(id, roadmap_id, theme, status, stage_number,
    commit_counter) values ('4c391609-5082-4dff-9994-a500f83e2419'::uuid,
    '3c7b7683-bfeb-4a49-80b8-4080abb49dac'::uuid, 'LEARN_SPRING', 'UNDONE', 2, 2);

insert into techmaps_platform.stage(id, roadmap_id, theme, status, stage_number,
    commit_counter) values ('38fe1240-c5d8-44ed-a73d-432c52b1076b'::uuid,
    '50241d67-68b6-4db5-977b-91dda87750bd'::uuid, 'LEARN_JS', 'DONE', 1, 2);

insert into techmaps_platform.stage(id, roadmap_id, theme, status, stage_number,
    commit_counter) values ('e25ce4ef-6b92-4f4b-961e-56e00d55278c'::uuid,
    '50241d67-68b6-4db5-977b-91dda87750bd'::uuid, 'LEARN_REACT', 'DONE', 2, 0);

--! Task Queries --!

insert into techmaps_platform.task(id, stage_id, theme, info,
    repository_link, date_created, date_finished, dashboard_id)
    values ('13acdae6-fef5-4d1c-8ab4-23b4cf169350'::uuid,
    '4c391609-5082-4dff-9994-a500f83e2419'::uuid, 'LEARN_SPRING',
    'SPG1', 'https://github.com/yellowisk/TechMaps-API', '2023-06-09 07:45:00',
    '2023-06-13 08:00:00', '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid);

insert into techmaps_platform.task(id, stage_id, theme, info, task_number,
    repository_link, date_created, date_finished, dashboard_id)
    values ('b061df33-90c7-4b8e-b976-7909528f0dc0'::uuid,
    '4c391609-5082-4dff-9994-a500f83e2419'::uuid, 'LEARN_SPRING',
    'SPG2', 1, 'https://github.com/yellowisk/TechMaps-API', '2023-06-09 07:45:00',
    null, '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid);

insert into techmaps_platform.task(id, stage_id, theme, info, task_number,
    repository_link, date_created, date_finished, dashboard_id)
    values ('beefb09f-1c66-4d82-9e97-dfe5cad7d580'::uuid,
    '38fe1240-c5d8-44ed-a73d-432c52b1076b'::uuid, 'LEARN_JS',
    'JS1', 1, 'https://github.com/yellowisk/TechMaps-API', '2023-04-09 07:45:00',
    '2023-04-13 08:00:00', '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid);

insert into techmaps_platform.task(id, stage_id, theme, info, task_number,
    repository_link, date_created, date_finished, dashboard_id)
    values ('c8001af2-e2b8-4c22-bee3-92036902edf9'::uuid,
    '38fe1240-c5d8-44ed-a73d-432c52b1076b'::uuid, 'LEARN_JS',
    'JS2', 2, 'https://github.com/yellowisk/TechMaps-API', '2023-04-12 07:45:00',
    null, '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid);

--! Task Commit Queries --!

insert into techmaps_platform.task_commit(id, task_id, tag, state, dashboard_id)
    values ('45a399c4-6b0d-4acf-bccc-1f97f29198ec'::uuid,
    '13acdae6-fef5-4d1c-8ab4-23b4cf169350'::uuid, 'final: OOP | techmap', 'UNSTAGED',
    '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid);

insert into techmaps_platform.task_commit(id, task_id, tag, state, dashboard_id)
    values ('1b379934-c9d3-475d-aaaf-be369a741257'::uuid,
    'b061df33-90c7-4b8e-b976-7909528f0dc0', 'final: lambda | techmap', 'UNSTAGED',
    '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid);

insert into techmaps_platform.task_commit(id, task_id, tag, state, dashboard_id)
    values ('a346279e-ea42-480f-a8f9-48995d235a8b'::uuid,
    'beefb09f-1c66-4d82-9e97-dfe5cad7d580', 'final: animations | techmap', 'STAGED',
    '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid);

insert into techmaps_platform.task_commit(id, task_id, tag, state, dashboard_id)
    values ('66767a71-7596-4754-8529-37b8e9bf1ca1'::uuid,
    'c8001af2-e2b8-4c22-bee3-92036902edf9', 'final: hooks | techmap', 'STAGED',
    '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid);

