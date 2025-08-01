# 💊 Pharmacy Management System  
**Digitizing and Streamlining Modern Pharmacy Operations**

A dynamic, Java-based web application designed to automate core pharmacy functions, improve operational efficiency, and offer a seamless experience for both customers and administrators.

---

## 📂 Table of Contents  
- [🚀 Project Overview](#-project-overview)  
- [🛠️ Key Features](#-key-features)  
- [📊 Data Model Highlights](#-data-model-highlights)  
- [⚙️ Technologies & Tools Used](#-technologies--tools-used)  
- [📚 What I Learned](#-what-i-learned) 
- [📄 License](#-license)  
- [💬 Feedback](#-feedback)

---

## 🚀 Project Overview  

The **Pharmacy Management System** is developed using **Java, JSP, Servlets**, and **MySQL** to automate and simplify the daily operations of a pharmacy.

### 🎯 Key Objectives:
- Eliminate manual record-keeping  
- Improve order and inventory accuracy  
- Provide a secure and user-friendly platform  
- Enable centralized data access for admins and users  

---

## 🛠️ Key Features

### ✅ User Registration & Login
- Secure signup and login using email and password  
- Session management ensures users remain logged in during navigation

### 💊 Medicine Management (Admin Panel)
- Add, update, delete, and view medicines  
- Fields include name, brand, quantity, price, expiry date

### 🔍 Medicine Browsing & Search (User)
- Users can view available medicines  
- Filter and search by medicine name or brand

### 🛒 Order Placement & History
- Add medicines to cart with specified quantity  
- Order confirmation includes full itemized summary  
- View complete order history with price and date

### 📦 Admin Order Monitoring
- View all user orders along with order and user details  
- Admin dashboard for tracking system operations

---

## 📊 Data Model Highlights

### 🔹 Core Tables
- `users` – Stores registered users' information  
- `medicines` – Stores information about medicines  
- `orders` – Tracks placed orders  
- `order_items` – Stores individual medicine entries per order  

### 🔗 Table Relationships
- **User → Orders** (One-to-Many)  
- **Order → Order_Items** (One-to-Many)  
- **Order_Item → Medicine** (Many-to-One)

---

## ⚙️ Technologies & Tools Used

| Technology / Tool         | Purpose                              |
|---------------------------|--------------------------------------|
| Java Servlets             | Backend controller logic             |
| JSP (JavaServer Pages)    | Dynamic UI rendering                 |
| HTML, CSS, Bootstrap      | Frontend design and layout           |
| JDBC                      | Java-MySQL database connectivity     |
| MySQL                     | Relational database                  |
| Apache Tomcat             | Web server / Servlet container       |
| NetBeans IDE              | Development environment              |

---

## 📚 What I Learned

- Designing and implementing **MVC architecture** in Java  
- Creating secure **authentication and session handling**  
- Performing **CRUD operations** with JDBC  
- Developing a **role-based system** (Admin/User)  
- Structuring scalable **relational databases**  
- Building responsive interfaces using **Bootstrap + JSP**

---

## 📄 License

This project is licensed under the **MIT License**.  
Free to use, modify, and distribute with attribution.

---

## 💬 Feedback

Have suggestions or want to contribute?  
Feel free to open an issue or connect with me via [LinkedIn](#).

---
