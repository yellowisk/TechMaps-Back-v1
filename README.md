# TechMaps - Spring Boot API
TechMaps is a mobile application which strives towards providing students roadmaps that cover topics of the software development world.

## Creating the Database

In IntelliJ Ultimate, open the database tab and connect to your machine's postgresql database.

Then go to the directory postgre/sql where you'll execute the following files:
- ``V000_setup.sql``
- ``V001_create_schema.sql``
- ``V002_populate_schema.sql``
- ``V004_populate_step_description.sql``

Afterwards, select and run all the code in ``V000_setup.sql`` in a session in the DB connection you just made. After successfully running the code in this file you'll have created a database called "techmaps" with an owner "techmaps-app". You shoul be able to verify it in pgAdmin4. 

Now connect to the "techmaps" database in the same way you connected to the postgresql one. Afterwards, select and run all the code in ``V001_create_schema.sql`` in a session in the DB connection you just made. Then repeat the same proccess with the ``V001_populate_schema.sql`` file.

## Running the Application

After maing sure your database is set up and populated, you can run the java file ``src/main/java/br.ifsp.techmaps/TechMapsApplication.java``

Log in with one of the users created and send the generated JWT in all your other requests when using the application. Every 30 minutes, refresh the JWT to keep using the application normally.
