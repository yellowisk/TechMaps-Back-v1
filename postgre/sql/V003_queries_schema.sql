--! USER QUERIES !--

--Select User By Id
SELECT * FROM techmaps_platform.user WHERE id = 'cc171e74-5dc3-4a38-9c86-5844ed673b5f'::uuid;
SELECT * FROM techmaps_platform.user WHERE id = '1b185f9e-6c0f-4617-bd77-dd4620a41a0f'::uuid;

--Select User By Username
SELECT * FROM techmaps_platform.user WHERE username = 'hector';
SELECT * FROM techmaps_platform.user WHERE username = 'Miguel';

--! DASHBOARD QUERIES !--

--Select Dashboard By Id
SELECT * FROM techmaps_platform.dashboard WHERE id = '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid;
SELECT * FROM techmaps_platform.dashboard WHERE id = '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid;

--Select total_time By DashBoard Id
SELECT total_time FROM techmaps_platform.dashboard WHERE id = '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid;
SELECT total_time FROM techmaps_platform.dashboard WHERE id = '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid;

--! ROADMAP QUERIES !--

--Select Roadmap By Id
SELECT * FROM techmaps_platform.roadmap WHERE id = '3c7b7683-bfeb-4a49-80b8-4080abb49dac'::uuid;
SELECT * FROM techmaps_platform.roadmap WHERE id = '50241d67-68b6-4db5-977b-91dda87750bd'::uuid;

--Select Roadmap By Title
SELECT * FROM techmaps_platform.roadmap WHERE title = 'Java Roadmap';
SELECT * FROM techmaps_platform.roadmap WHERE title = 'JS Roadmap';

--Select Roadmap By Dashboard Id
SELECT * FROM techmaps_platform.roadmap WHERE dashboard_id = '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid;
SELECT * FROM techmaps_platform.roadmap WHERE dashboard_id = '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid;

--Select Roadmap By Type
SELECT * FROM techmaps_platform.roadmap WHERE type = 'BACKEND';
SELECT * FROM techmaps_platform.roadmap WHERE type = 'FRONTEND';

--Select Roadmap By Status
SELECT * FROM techmaps_platform.roadmap WHERE is_completed = true;
SELECT * FROM techmaps_platform.roadmap WHERE is_completed = false;

--Select Roadmap By Language
SELECT * FROM techmaps_platform.roadmap WHERE lang = 'JAVA';
SELECT * FROM techmaps_platform.roadmap WHERE lang = 'JAVASCRIPT';

--! STAGE QUERIES !--

--Select Stage By Id
SELECT * FROM techmaps_platform.stage WHERE id = '4c391609-5082-4dff-9994-a500f83e2419'::uuid;
SELECT * FROM techmaps_platform.stage WHERE id = '38fe1240-c5d8-44ed-a73d-432c52b1076b'::uuid;

--Select Stage By Roadmap Id
SELECT * FROM techmaps_platform.stage WHERE roadmap_id = '3c7b7683-bfeb-4a49-80b8-4080abb49dac'::uuid;
SELECT * FROM techmaps_platform.stage WHERE roadmap_id = '50241d67-68b6-4db5-977b-91dda87750bd'::uuid;

--Select Stage By Theme
SELECT * FROM techmaps_platform.stage WHERE theme = 'LEARN_JAVA';
SELECT * FROM techmaps_platform.stage WHERE theme = 'LEARN_JS';

--Select Stage By Status
SELECT * FROM techmaps_platform.stage WHERE is_done = false;
SELECT * FROM techmaps_platform.stage WHERE is_done = true;

--Select commit_counter By Stage Id
SELECT commit_counter FROM techmaps_platform.stage WHERE id = '4c391609-5082-4dff-9994-a500f83e2419'::uuid;
SELECT commit_counter FROM techmaps_platform.stage WHERE id = '38fe1240-c5d8-44ed-a73d-432c52b1076b'::uuid;

--! TASK QUERIES !--

--Select Task By Id
SELECT * FROM techmaps_platform.task WHERE id = '13acdae6-fef5-4d1c-8ab4-23b4cf169350'::uuid;
SELECT * FROM techmaps_platform.task WHERE id = 'beefb09f-1c66-4d82-9e97-dfe5cad7d580'::uuid;

--Select Task By Stage Id
SELECT * FROM techmaps_platform.task WHERE stage_id = '4c391609-5082-4dff-9994-a500f83e2419'::uuid;
SELECT * FROM techmaps_platform.task WHERE stage_id = '38fe1240-c5d8-44ed-a73d-432c52b1076b'::uuid;

--Select Task By Info
SELECT * FROM techmaps_platform.task WHERE info = 'JV7';
SELECT * FROM techmaps_platform.task WHERE info = 'JS1';

--Select Task By Dashboard Id
SELECT * FROM techmaps_platform.task WHERE dashboard_id = '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid;
SELECT * FROM techmaps_platform.task WHERE dashboard_id = '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid;

--! COMMIT QUERIES !--

--Select Commit By Id
SELECT * FROM techmaps_platform.task_commit WHERE id = '45a399c4-6b0d-4acf-bccc-1f97f29198ec'::uuid;
SELECT * FROM techmaps_platform.task_commit WHERE id = 'a346279e-ea42-480f-a8f9-48995d235a8b'::uuid;

--Select Commit By Task Id
SELECT * FROM techmaps_platform.task_commit WHERE task_id = '13acdae6-fef5-4d1c-8ab4-23b4cf169350'::uuid;
SELECT * FROM techmaps_platform.task_commit WHERE task_id = 'beefb09f-1c66-4d82-9e97-dfe5cad7d580'::uuid;

--Select Commit By State
SELECT * FROM techmaps_platform.task_commit WHERE state = 'STAGED';
SELECT * FROM techmaps_platform.task_commit WHERE state = 'UNSTAGED';

--Select Commit By Dashboard Id
SELECT * FROM techmaps_platform.task_commit WHERE dashboard_id = '582c710f-849c-4a9f-a10f-0b68c784f240'::uuid;
SELECT * FROM techmaps_platform.task_commit WHERE dashboard_id = '436ee536-93a3-4c20-9271-18a19c01efdb'::uuid;
