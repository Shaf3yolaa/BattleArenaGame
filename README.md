# 🎮 Battle Arena Game (JavaFX)

A real-time 2D multiplayer battle arena game built using **JavaFX**, featuring dynamic combat, multiple character classes, and a custom physics engine.

---

## 🚀 Overview

Battle Arena is a local multiplayer fighting game where two players select characters and battle in a 2D arena. The project demonstrates real-time game development using Java, JavaFX, and object-oriented design principles.

---

## 🎯 Features

- 🎮 Two-player local multiplayer combat
- 🧠 Multiple character classes:
  - Warrior
  - Mage
  - Archer
- 🔫 Weapon system with different stats:
  - Pistol
  - Bow
  - Cannon
  - Magic Wand
- 💥 Projectile-based shooting system
- ❤️ Health system with visual health bars
- ⚙️ Custom physics engine (movement + collision detection)
- 🖥️ JavaFX UI (menus, instructions, HUD)
- ⏱️ Real-time game loop using AnimationTimer

---

## 🕹️ Controls

### Player 1
- Move: W A S D
- Shoot: F
- Switch Weapons: 1 - 4

### Player 2
- Move: Arrow Keys
- Shoot: L
- Switch Weapons: 7 - 0

---

## 🧩 Project Structure

com.battlearena.battlearenagame

├── engine
│   └── BattleArenaApp.java     → Main game engine & UI

├── models
│   ├── Fighter (abstract class)
│   ├── Warrior / Mage / Archer
│   ├── Weapon system
│   └── Projectile logic

├── physics
│   ├── PhysicsEngine.java      → Movement & collision
│   └── Projectile.java         → Bullet behavior

---

## 🧠 Core Concepts Used

- Object-Oriented Programming (OOP)
  - Inheritance (Fighter → characters)
  - Polymorphism (weapons & behavior)
- Encapsulation & modular design
- Real-time game loop (AnimationTimer)
- Collision detection system
- Event-driven input handling

---

## ⚙️ Tech Stack

- Java
- JavaFX
- OOP Design Principles

---

## 🏁 How to Run

1. Clone the repository:
git clone https://github.com/Shaf3yolaa/BattleArenaGame.git

2. Open the project in IntelliJ IDEA / Eclipse

3. Ensure JavaFX is configured properly

4. Run:
BattleArenaApp.java

---

## 👨‍💻 Team

- Engine & Core Logic: Ahmed Elshafeey & Mazen Abdelmoneim
- Models & Gameplay Design: Seif Eldin Tarek & Mohamed Elsoul
- Physics System: Ezz Eldin Gamal & Eyad Eshba 

---

## ⭐ Highlights

This project demonstrates:
- Strong OOP design and architecture
- Real-time system development
- Game loop implementation
- Physics simulation (movement + collision)
- JavaFX UI development
