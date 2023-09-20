package br.ifsp.techmaps.domain.entities.task;

public enum TaskBody {
    PY1("Learn Python", "Learn basic syntax", "Familiarize yourself with Python's syntax, variables, data types, and control flow statements."),
    PY2("Learn Python", "Data Structure", "Learn about Python's built-in data structures"),
    PY3("Learn Python", "Algorithms", "Learn how to create algorithms in Python through sorting and recursions"),
    PY4("Learn Python", "Advanced Topics", "Learn different advanced topics in Python such as decorators, RegEx and generators"),
    PY5("Learn Python", "OOP", "Learn about object-oriented programming (OOP) in Python, including classes, inheritance, and encapsulation"),
    PY6("Learn Python", "Package Managers", "Learn how to use Python package managers such as Pip, PYPi and Conda"),

    JV1("Learn Java", "Learn the Fundamentals", "Familiarize yourself with Java's syntax, data types, variables, conditionals and loops, functions data structures, packages, and more."),
    JV2("Learn Java", "Getting Deeper", "Learn about memory management, collections, serialization, networking and sockets, streams, and more."),
    JV3("Learn Java", "Build Tools", "Learn how to use build tools such as Maven, Gradle and Ant to automate the build process."),
    JV4("Learn Java", "Web Frameworks", "Learn how to use Java web frameworks such as Spring, Spark, and Play."),
    JV5("Learn Java", "ORM", "Learn how to use Java Object-Relational Mapping (ORM) frameworks such as Hibernate and JPA."),
    JV6("Learn Java", "JDBC", "Learn how to use Java Database Connectivity (JDBC) to connect to databases."),
    JV7("Learn Java", "Logging Frameworks", "Learn how to use Java logging frameworks such as Log4j, SLF4J and Logback."),
    JV8("Learn Java", "Testing Frameworks", "Learn how to use Java testing frameworks such as JUnit, TestNG, and Mockito."),

    KT1("Learn Kotlin", "Install Kotlin", "Install Kotlin compiler and set up the development environment."),
    KT2("Learn Kotlin", "Learn Kotlin basics", "Understand Kotlin syntax, variables, functions, and control flow."),
    KT3("Learn Kotlin", "Develop a simple Android app", "Build a basic Android application using Kotlin, including UI elements and event handling."),

    HT1("Learn HTML", "Study HTML tags", "Learn the basic HTML tags and their purpose in creating the structure of a web page."),
    HT2("Learn HTML", "Build a static webpage", "Create a simple webpage using HTML, including headings, paragraphs, lists, and images."),
    HT3("Learn HTML", "Understand CSS integration", "Explore how CSS is linked to HTML and apply simple styles to enhance the webpage's appearance."),

    CS1("Learn CSS", "Learn CSS selectors", "Understand different CSS selectors and how they target specific HTML elements."),
    CS2("Learn CSS", "Style a webpage", "Apply CSS styles to control the layout, typography, colors, and spacing of a webpage."),
    CS3("Learn CSS", "Create responsive design", "Use CSS media queries and responsive techniques to adapt the webpage layout for different devices."),

    JS1("Learn JavaScript", "Grasp JavaScript fundamentals", "Learn variables, data types, functions, loops, and conditionals in JavaScript."),
    JS2("Learn JavaScript", "Manipulate webpage elements", "Use JavaScript to select, modify, and add interactivity to HTML elements dynamically."),
    JS3("Learn JavaScript", "Build a simple web application", "Develop a basic web app that responds to user input and performs relevant actions using JavaScript."),

    IN1("Learn Internet", "Understand networking basics", "Learn about IP addresses, DNS, HTTP, and TCP/IP protocols."),
    IN2("Learn Internet", "Explore client-server architecture", "Understand how web browsers communicate with web servers to retrieve and display web content."),
    IN3("Learn Internet", "Set up a local web server", "Install and configure a web server software (e.g., Apache) on your machine to serve web pages locally."),

    DG1("Learn Diagrams", "Learn UML basics", "Understand the different types of UML diagrams and their purpose in visualizing system design."),
    DG2("Learn Diagrams", "Create a class diagram", "Use UML notation to represent classes, relationships, and attributes in a class diagram."),
    DG3("Learn Diagrams", "Design a sequence diagram", "Illustrate the interaction between objects and the order of message exchanges in a system using UML sequence diagrams."),

    WS1("Learn WebServers", "Study web server concepts", "Learn about HTTP methods, request-response cycle, and server-side scripting."),
    WS2("Learn WebServers", "Set up a web server", "Install and configure a web server software (e.g., Apache, Nginx) to host and serve websites."),
    WS3("Learn WebServers", "Deploy a sample website", "Upload your web pages and assets to the web server and verify their accessibility through a browser."),

    CD1("Learn Cloud Systems", "Understand cloud computing", "Learn about cloud service models (IaaS, PaaS, SaaS) and deployment models (public, private, hybrid)."),
    CD2("Learn Cloud Systems", "Explore cloud providers", "Familiarize yourself with popular cloud platforms such as AWS, Azure, or Google Cloud."),
    CD3("Learn Cloud Systems", "Deploy an application on the cloud", "Utilize cloud services to host and run a web application, and configure necessary resources (e.g., virtual machines, databases)."),

    GT1("Learn Git", "Install Git", "Set up Git on your computer and configure your user settings."),
    GT2("Learn Git", "Learn Git basics", "Understand Git's fundamental concepts like repositories, commits, branches, and merges."),
    GT3("Learn Git", "Collaborate with Git", "Practice cloning repositories, making changes, creating branches, and pushing your work to remote repositories."),

    GH1("Learn GitHub", "Create a GitHub account", "Sign up for a GitHub account and explore the platform's features."),
    GH2("Learn GitHub", "Manage repositories", "Create a new repository, clone it to your local machine, make changes, and push them to GitHub."),
    GH3("Learn GitHub", "Collaborate on GitHub", "Fork a repository, make contributions through pull requests, and participate in open-source projects."),

    AP1("Learn API", "Understand API fundamentals", "Learn about REST, HTTP methods, status codes, and request/response structure."),
    AP2("Learn API", "Make API requests", "Use tools like Postman to send HTTP requests and explore different endpoints and their responses."),
    AP3("Learn API", "Build a simple API client", "Develop a program that consumes an API, retrieves data, and performs basic operations using the provided endpoints."),

    RS1("Learn REST", "Grasp RESTful API concepts", "Understand resource-oriented architecture, URI design, and RESTful principles."),
    RS2("Learn REST", "Design RESTful endpoints", "Create endpoints with appropriate HTTP methods and URL structures to expose resources."),
    RS3("Learn REST", "Implement CRUD operations", "Build a RESTful API that allows creating, reading, updating, and deleting resources using HTTP methods."),

    SP1("Learn SOAP", "Understand SOAP basics", "Learn about the Simple Object Access Protocol (SOAP) and its XML-based messaging structure."),
    SP2("Learn SOAP", "Create a SOAP client", "Use a programming language (e.g., Java, Python) to build a SOAP client that interacts with a SOAP web service."),
    SP3("Learn SOAP", "Implement SOAP server", "Develop a SOAP web service that exposes functionalities and responds to client requests."),

    AG1("Learn Agile Methods", "Explore Agile methodologies", "Learn about Scrum, Kanban, and Agile principles such as iterative development and continuous improvement."),
    AG2("Learn Agile Methods", "Participate in a Scrum team", "Act as a member of a Scrum team, attending daily stand-ups, sprint planning, and sprint reviews."),
    AG3("Learn Agile Methods", "Apply Agile practices", "Practice user story estimation, backlog grooming, and prioritization in an Agile project."),

    DV1("Learn DevOps", "Learn DevOps principles", "Understand the integration of development and operations, continuous integration/continuous delivery (CI/CD), and automation."),
    DV2("Learn DevOps", "Set up a CI/CD pipeline", "Configure a CI/CD pipeline using tools like Jenkins or GitLab CI to automate the build, test, and deployment processes."),
    DV3("Learn DevOps", "Deploy an application with infrastructure as code", "Use tools like Ansible or Terraform to provision and deploy an application infrastructure on a cloud platform."),

    OP1("Learn OOP", "Understand object-oriented programming (OOP) concepts", "Learn about classes, objects, inheritance, encapsulation, and polymorphism."),
    OP2("Learn OOP", "Model a real-world scenario", "Apply OOP principles to design and implement classes that represent entities and their relationships."),
    OP3("Learn OOP", "Build an OOP application", "Develop a program that demonstrates OOP concepts, such as inheritance, encapsulation, and polymorphism, to solve a specific problem."),

    SD1("Learn SOLID", "Study SOLID principles", "Understand the SOLID acronym and its principles (Single Responsibility, Open-Closed, Liskov Substitution, Interface Segregation, Dependency Inversion)."),
    SD2("Learn SOLID", "Apply SOLID to code", "Refactor existing code or write new code that adheres to SOLID principles, promoting maintainability and extensibility."),
    SD3("Learn SOLID", "Discuss SOLID examples", "Analyze code examples, identify violations or improvements, and discuss the benefits of following SOLID principles."),

    CC1("Learn Clean Code", "Learn clean code principles", "Understand the importance of readable, maintainable, and well-structured code."),
    CC2("Learn Clean Code", "Apply code formatting standards", "Follow established coding conventions (e.g., PEP 8 for Python) to improve code readability."),
    CC3("Learn Clean Code", "Refactor code for clarity", "Identify code smells and refactor them to improve the code's structure, readability, and maintainability."),

    TD1("Learn TDD", "Understand Test-Driven Development (TDD)", "Learn the TDD cycle (red-green-refactor) and its benefits in software development."),
    TD2("Learn TDD", "Write unit tests", "Practice writing unit tests before implementing new functionality and ensuring test coverage."),
    TD3("Learn TDD", "Apply TDD to a project", "Develop a small project using TDD principles, writing tests first and then implementing the required code."),

    CA1("Learn Clean Architecture", "Study clean architecture principles", "Learn about the separation of concerns, dependency inversion, and architectural boundaries."),
    CA2("Learn Clean Architecture", "Design clean architecture", "Apply clean architecture principles to design a system with clear boundaries between layers."),
    CA3("Learn Clean Architecture", "Implement clean architecture", "Develop a project following the clean architecture principles, separating business logic from infrastructure and ensuring testability."),

    VC1("Learn Visual Studio Code", "Install Visual Studio Code", "Download and set up Visual Studio Code, a popular code editor."),
    VC2("Learn Visual Studio Code", "Explore essential features", "Familiarize yourself with VS Code's debugging capabilities, extensions, and version control integration."),
    VC3("Learn Visual Studio Code", "Customize your workflow", "Configure settings, keybindings, and install extensions to optimize your development workflow in Visual Studio Code."),

    II1("Learn IntelliJ IDEA", "Install IntelliJ IDEA", "Download and set up IntelliJ IDEA, a powerful integrated development environment (IDE)."),
    II2("Learn IntelliJ IDEA", "Navigate the IDE", "Learn how to efficiently use features like code navigation, search, refactoring, and debugging in IntelliJ."),
    II3("Learn IntelliJ IDEA", "Optimize your workflow", "Customize settings, utilize shortcuts, and install plugins to streamline your development process in IntelliJ IDEA."),

    AD1("Learn Android Studio", "Set up Android development environment", "Install Android Studio and necessary SDKs for Android app development."),
    AD2("Learn Android Studio", "Learn Android app structure", "Understand the components (activities, services, layouts) and lifecycle of an Android app."),
    AD3("Learn Android Studio", "Develop a basic Android app", "Create a simple Android application with multiple activities, layouts, and basic functionality."),

    ND1("Learn Node.js", "Install Node.js", "Download and set up Node.js, a JavaScript runtime environment."),
    ND2("Learn Node.js", "Learn Node.js basics", "Understand Node.js modules, asynchronous programming, and the Node.js event loop."),
    ND3("Learn Node.js", "Build a Node.js application", "Develop a Node.js application that interacts with a database and exposes RESTful endpoints."),

    AN1("Learn Angular", "Install Angular CLI", "Set up Angular development environment using the Angular CLI."),
    AN2("Learn Angular", "Learn Angular basics", "Understand components, modules, data binding, and routing in the Angular framework."),
    AN3("Learn Angular", "Build a simple Angular app", "Develop an Angular application that fetches data from an API and displays it using components and routing."),

    RE1("Learn React", "Set up React development environment", "Install Node.js and create a new React project using create-react-app."),
    RE2("Learn React", "Learn React fundamentals", "Understand React components, JSX syntax, state management, and lifecycle methods."),
    RE3("Learn React", "Develop a React application", "Build a basic React application with multiple components, state management, and interactivity."),

    SPG1("Learn Spring", "Understand Spring framework", "Learn about Spring Boot, dependency injection, and inversion of control (IoC)."),
    SPG2("Learn Spring", "Develop a Spring application", "Build a simple Spring Boot application with RESTful endpoints and database integration."),
    SPG3("Learn Spring", "Explore Spring features", "Dive deeper into Spring's capabilities, such as security, caching, and integration with other frameworks."),

    DJ1("Learn Django", "Set up Django environment", "Install Django and set up a virtual environment for your Django project."),
    DJ2("Learn Django", "Create a basic Django app", "Build a simple Django application with a few models and views."),
    DJ3("Learn Django", "Implement user authentication", "Integrate Django's built-in authentication system to handle user registration and login."),

    BT1("Learn Bootstrap", "Install Bootstrap", "Download Bootstrap and set up a project with Bootstrap's CSS and JavaScript files."),
    BT2("Learn Bootstrap", "Learn Bootstrap basics", "Understand Bootstrap's grid system, components, and utilities."),
    BT3("Learn Bootstrap", "Build a simple website", "Develop a basic website using Bootstrap's grid system and components."),

    MY1("Learn MySQL", "Install MySQL", "Set up MySQL database server on your machine."),
    MY2("Learn MySQL", "Learn SQL basics", "Understand SQL syntax, data manipulation (SELECT, INSERT, UPDATE, DELETE), and table creation."),
    MY3("Learn MySQL", "Build a database-driven application", "Develop a simple application that interacts with a MySQL database, performing CRUD operations and querying data."),

    PS1("Learn PostgreSQL", "Install PostgreSQL", "Set up PostgreSQL database server on your machine."),
    PS2("Learn PostgreSQL", "Explore PostgreSQL features", "Learn about advanced data types, indexing, transactions, and querying capabilities."),
    PS3("Learn PostgreSQL", "Develop a database-driven application", "Build an application that utilizes a PostgreSQL database, focusing on efficient data manipulation and retrieval."),

    DC1("Learn Docker", "Install Docker", "Set up Docker on your machine and learn the basics of containerization."),
    DC2("Learn Docker", "Create Docker containers", "Build Docker images, define container configurations using Dockerfiles, and run containers locally."),
    DC3("Learn Docker", "Deploy containerized application", "Utilize Docker to package and deploy an application in a containerized environment."),

    FB1("Learn Firebase", "Set up Firebase project", "Create a Firebase project and configure Firebase SDK in your web or mobile application."),
    FB2("Learn Firebase", "Use Firebase Authentication", "Implement user authentication using Firebase Authentication services."),
    FB3("Learn Firebase", "Integrate Firebase Database", "Utilize Firebase Realtime Database or Firestore to store and retrieve data in your application.");
    private String topic;
    private String title;
    private String description;

    TaskBody(String topic, String title, String description) {
        this.topic = topic;
        this.title = title;
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String stage) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
