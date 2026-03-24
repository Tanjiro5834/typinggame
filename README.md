# 🚀 Typing Game (Full-Stack)

A full-stack typing speed game inspired by Monkeytype, built with a custom frontend engine and a Spring Boot backend API.

---

## 🧠 Overview

This project is a real-time typing game where users can:

* ⌨️ Test typing speed (WPM & accuracy)
* 🏆 View leaderboard rankings
* 📜 Track personal typing history
* 📊 Analyze performance statistics

---

## 🏗️ Architecture

```
Frontend (Vanilla JS)
        ↓ REST API
Spring Boot Backend
        ↓
MySQL Database (JPA / Hibernate)
```

---

## ⚙️ Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL (XAMPP)

### Frontend

* Vanilla JavaScript
* HTML/CSS
* Custom typing engine

### Tools

* Postman
* VS Code
* Git & GitHub

---

## 📦 Features

### 🎮 Typing Engine

* Real-time WPM calculation
* Accuracy tracking
* Multiple modes (timed, words, quote)

### 🏆 Leaderboard

* Top scores by WPM
* Filter by mode and duration
* Sorted results (WPM + accuracy)

### 📜 Personal History

* Track previous runs
* Sorted by latest activity

### 📊 User Stats

* Average WPM
* Best WPM
* Accuracy average
* Total games played
* Rank system (Novice → Legendary)

### 🧠 Anti-Cheat (Basic)

* Rejects physically impossible scores

---

## 🌐 API Endpoints

### Submit Score

```
POST /api/scores
```

**Body:**

```json
{
  "username": "Nathaniel",
  "wpm": 95,
  "accuracy": 98.5,
  "mode": "TIMED",
  "duration": 60,
  "language": "en"
}
```

---

### Get Leaderboard

```
GET /api/scores/top?mode=TIMED&duration=60&limit=10
```

---

### Get Personal History

```
GET /api/scores/history?username=Nathaniel
```

---

### Get User Stats

```
GET /api/scores/stats?username=Nathaniel
```

---

### Get Random Words

```
GET /api/words/random?difficulty=medium
```

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/typing-game.git
cd typing-game
```

---

### 2. Run the backend

```bash
mvn spring-boot:run
```

Backend runs at:

```
http://localhost:8080
```

---

### 3. Open the frontend

Use VS Code Live Server:

```
http://127.0.0.1:5500
```

---

## ⚠️ Important Config

### CORS (Required for frontend)

```java
.allowedOrigins("http://127.0.0.1:5500")
```

---

### Disable Spring Security (for development)

```java
.anyRequest().permitAll()
```

---

### Database config

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

## 🧪 Testing

Use:

* Browser (GET endpoints)
* Postman (POST /api/scores)

---

## 🧠 Future Improvements

* 🔐 JWT Authentication (login/register)
* ⚡ Leaderboard caching (Redis)
* 🧠 Advanced anti-cheat system
* 🌐 Deployment (Docker + VPS)
* 🎮 Real-time leaderboard (WebSocket)
* 📈 Analytics dashboard

---

## 📌 Notes

* Frontend and backend are decoupled
* API-first design
* Built with scalability in mind

---

## 👨‍💻 Author

Nathaniel
Backend-focused developer passionate about building scalable systems.

---

## ⭐ Acknowledgements

Inspired by:

* Monkeytype
* Real-time typing engines
* Competitive programming tools
