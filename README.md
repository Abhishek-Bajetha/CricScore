# CricScore – Cricket Scoring & Match Management Backend 🏏

> **A Spring Boot backend for managing cricket matches, players, teams, and ball-by-ball scoring data.**

## 📌 Project Overview

**CricScore** is a backend-focused cricket scoring and match management application designed to digitize local cricket matches.

The application provides RESTful APIs for managing cricket matches, teams, players, and delivery-level scoring data. Instead of storing only final match summaries, CricScore follows a **granular ball-by-ball data model**, making it possible to generate scorecards and player statistics from individual deliveries.

The project is currently focused on completing the **backend architecture and core scoring logic**. Frontend and live-scoring features are planned for later development.

---

## 🚀 Current Features

### ⚙️ Match & Player Management

* REST APIs for managing cricket matches, teams, and players.
* Match creation with validation of participating teams and players.
* Persistent storage using **Spring Data JPA and MySQL**.

### 🏏 Ball-by-Ball Scoring

* Designed to store individual delivery-level match data.
* Supports recording runs, wickets, and extras such as wides and no-balls.
* Delivery data can be used to derive innings and player statistics.

### 👤 Shadow Profile System

CricScore includes a **Shadow Profile** concept to handle players who participate in a match before creating a full account.

The basic workflow is:

1. Scorer provides the player's mobile number.
2. The backend checks whether the player already exists.
3. If the player does not exist, a temporary **Shadow Profile** can be created.
4. Match statistics can be associated with that player.
5. **Profile claiming and account merging are planned for a future phase.**

---

## 🔮 Roadmap

The following features are planned but are **not considered completed in the current version**:

* [ ] Complete ball-by-ball scoring APIs
* [ ] Automated scorecard generation
* [ ] Career and player statistics
* [ ] Shadow Profile claiming and account merging
* [ ] Tournament management and points tables
* [ ] Frontend scoring dashboard
* [ ] AJAX/Fetch-based live score updates
* [ ] Authentication and authorization

---

## 🛠️ Tech Stack

| Component            | Technology                 |
| :------------------- | :------------------------- |
| **Language**         | Java                       |
| **Backend**          | Spring Boot, Spring Web    |
| **Data Access**      | Spring Data JPA, Hibernate |
| **Database**         | MySQL                      |
| **API Architecture** | RESTful APIs               |
| **Build Tool**       | Maven                      |
| **Frontend**         | Planned                    |
| **Version Control**  | Git & GitHub               |

---

## 🏗️ Architecture Highlights

### 1. Granular Ball-by-Ball Data Model

Instead of storing only a final score such as:

```text
Team A: 185/6
```

CricScore is designed around **delivery-level data**.

Each delivery can contain information such as:

* Over and ball number
* Striker
* Non-striker
* Bowler
* Runs scored
* Extras
* Wicket information

This approach allows match statistics and scorecards to be calculated from the underlying delivery data rather than relying only on manually maintained summaries.

### 2. Shadow Profile Architecture

The Shadow Profile system is designed to solve a common problem in local cricket: players may participate in matches without having an account.

```text
Mobile Number
      ↓
Check Player
      ↓
 ┌───────────────┐
 │ Player Exists │
 └───────┬───────┘
         │
     Yes │ No
         │
         ↓
  Existing Player
         │
         └───────────────┐
                         ↓
                  Create Shadow
                     Profile
```

A shadow profile allows match participation and statistics to be associated with a player before the player completes registration.

---

## 📂 Project Structure

```text
CricScore/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## ⚙️ Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Abhishek-Bajetha/CricScore.git
cd CricScore
```

### 2. Configure MySQL

Create a MySQL database:

```sql
CREATE DATABASE cricscore_db;
```

Update your database credentials in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cricscore_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application from your IDE.

The backend will start at:

```text
http://localhost:8080
```

---

## 🧪 API Testing

The REST APIs can be tested using tools such as **Postman**.

Typical backend operations include:

```text
POST   /api/...
GET    /api/...
PUT    /api/...
DELETE /api/...
```

Refer to the controller classes in the source code for the currently implemented endpoints.

---

## 📈 Future Vision

The long-term goal of CricScore is to evolve from a basic digital scorekeeping backend into a complete cricket statistics platform.

The planned architecture will allow:

**Ball-by-ball data → Match Scorecard → Player Statistics → Career Analytics → Tournament Analytics**

---

## 📄 License

This project is licensed under the **MIT License**.
