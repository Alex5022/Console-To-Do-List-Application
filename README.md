# Personal To-Do List Application

A simple **Java console-based To-Do List manager** with PostgreSQL database integration.  
This project allows users to create, update, delete, view, and manage tasks with priorities and due dates.

---

## Features
- **Create Task** – Add new tasks with title, description, due date, and priority.
- **Update Task** – Modify existing tasks.
- **Delete Task** – Remove tasks.
- **View All Tasks** – Display all tasks currently stored.
- **Mark Task as Completed** – Check off tasks.
- **Filter Tasks by Status** – View only completed or pending tasks.
- **Sort Tasks** – Sort by priority, due date, or title.
- **Persistent Storage** – Tasks are saved in a PostgreSQL database.

---

## Technologies Used
- **Java** (Core, JDBC, Collections, Scanner)
- **PostgreSQL** (Database storage)
- **JDBC Driver** (Database connection)
- **Object-Oriented Design** (Classes: `App`, `Task`, `ToDoList`, `ConnectionToDatabase`, `Priority`)
---
## How to run

### Prerequisites
- Docker & Docker Compose installed

### Steps
```bash
# Clone the repository 
git clone https://github.com/Alex5022/Console-To-Do-List-Application.git
cd Console-To-Do-List-Application

# Build the Docker image and run the database
docker compose up
```

Alternatively, download the ZIP file, extract it, open the folder in your terminal or code editor, and run:
```bash
docker compose up
```
Open that project in a code editor and run it



