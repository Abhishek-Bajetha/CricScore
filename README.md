# CricScore – Live Cricket Scoring Platform 🏏

> **A real-time digital scoring platform for grassroots cricket, replacing paper scorebooks with professional-grade analytics.**

## 📌 Project Overview
**CricScore** is a full-stack web application designed to digitize local cricket matches. It enables scorers to record match data ball-by-ball, which is instantly processed to generate live scorecards and player statistics. 

The system features a unique **"Shadow Profiling"** architecture, allowing players to be added via mobile number during a match and "claim" their statistics later upon full registration.

## 🚀 Key Features

### ⚡ Phase 1: The Live Scorer (MVP)
* **Real-Time Scoring Engine:** comprehensive interface to record runs, wickets, extras (wides/no-balls), and wagon wheel data.
* **Dynamic Dashboard:** Uses **JavaScript & AJAX** to fetch and update live scores without page reloads, ensuring a seamless user experience.
* **Shadow Account System:**
    * **Instant Play:** Scorers can add players via mobile number.
    * **Lazy Registration:** If a player isn't registered, the system creates a "Shadow Profile" instantly so the game isn't delayed.
* **Ball-by-Ball Database:** Stores granular data for every delivery, enabling deep analytics (Strike Rates, Economy) rather than just match summaries.

### 🔮 Phase 2: Analytics & Networking (Roadmap)
* **Profile Claiming:** Users can register later and merge their "Shadow Profile" stats into their main account.
* **Career Analytics:** Automated calculation of Batting Averages, Bowling Figures, and Dismissal Analysis.
* **Tournament Management:** Automated points tables and schedule generation.

## 🛠️ Tech Stack

| Component | Technology |
| :--- | :--- |
| **Backend** | Java, Spring Boot (Spring Web, Spring Data JPA) |
| **Database** | MySQL (Relational Schema) |
| **Frontend** | HTML5, CSS3, Vanilla JavaScript (AJAX/Fetch API) |
| **Build Tool** | Maven |
| **Architecture** | RESTful API, MVC Pattern |

## 🏗️ System Architecture Highlights

### 1. The "Shadow Profile" Logic
To solve the problem of unregistered players in local matches, CricScore uses a unified `Players` table with a registration flag:
1.  **Scorer inputs Mobile No:** API checks DB.
2.  **If Found:** Returns Player Profile.
3.  **If Not Found:** Creates a `Shadow User` (Flag: `is_registered = false`) and links the match stats to this ID.
4.  **Future Claiming:** When the user eventually registers with that mobile number, the system updates the flag to `true`, instantly giving them access to their past match history.

### 2. Database Design (Future-Proofing)
Instead of storing simple summaries (e.g., "India: 200/5"), the database utilizes a **Granular Transactional Model**.
* **Table:** `Deliveries` (Logs every single ball).
* **Benefit:** Allows recalculation of any stat (e.g., "Runs scored against Spinners in Overs 16-20") at any time.

## ⚙️ Installation & Setup

1.  **Clone the Repository**
    ```bash
    git clone [https://github.com/Abhishek-Bajetha/CricScore.git](https://github.com/Abhishek-Bajetha/CricScore.git)
    ```

2.  **Configure Database**
    * Create a MySQL database named `cricscore_db`.
    * Update `src/main/resources/application.properties` with your credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/cricscore_db
    spring.datasource.username=your_root
    spring.datasource.password=your_password
    ```

3.  **Run the Application**
    ```bash
    mvn spring-boot:run
    ```

4.  **Access the App**
    * Open browser at `http://localhost:8080`

## 📸 Screenshots
![Loading...](screenshot01.png)

<!-- ## 🤝 Contributing
Contributions are welcome! Please fork the repository and create a pull request for any feature enhancements. -->

## 📄 License
This project is licensed under the MIT License.