
# 🏨 Book My Stay – Hotel Booking Management System

## 📌 Overview
**Book My Stay** is a Java-based Hotel Booking Management System created to demonstrate the practical use of **Core Java concepts and fundamental data structures** in real-world scenarios.

The project is designed using an **incremental use case approach**, where each use case introduces new programming concepts and system behaviors. The focus is on understanding **core application logic, system flow, and software design principles**, rather than graphical interfaces.

---

# 🚀 Use Case 1 – Application Entry & Welcome Message

## 🎯 Goal
The goal of this use case is to establish a **clear and predictable starting point** for the Hotel Booking application by demonstrating how a Java program begins execution and produces console output.

---

## 👤 Actor
**User**

The user launches the application from the **command line or an IDE**, initiating program execution.

---

## 🔄 Execution Flow
1. The user runs the application.
2. The **Java Virtual Machine (JVM)** loads the program.
3. The JVM invokes the **main() method**, which acts as the entry point.
4. The application displays a **welcome message** with the application name and version.
5. The program terminates successfully.

---

## 💡 Key Concepts Demonstrated

### 1️⃣ Java Class
Every Java application must be defined within a **class**.  
The class acts as a container for program behavior and defines the logical boundary of the program.

---

### 2️⃣ `main()` Method
The `main()` method is the **entry point** of every standalone Java application.

The JVM specifically looks for the following method signature:

```java
public static void main(String[] args)
