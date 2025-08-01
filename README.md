💊 Pharmacy Management System
A dynamic web application that automates and streamlines the core operations of a pharmacy. Built with Java technologies like JSP, Servlets, and JDBC, the system supports both admin and customer roles for efficient management of medicines and orders.

📌 1. Purpose & Scope
The system is designed to:

Eliminate manual record-keeping

Enhance accuracy in stock and order handling

Provide a seamless experience for both admins and customers

👥 User Roles
Admin

Manage medicines

Monitor and process orders

Customer/User

Register, login, browse medicines

Place orders and view order history

⚙️ 2. Core Functionalities
🔐 2.1 User Management
Register: Sign up with personal information

Login: Secure authentication via email/password

Session Handling: Persistent login and logout functionality

💊 2.2 Medicine Management (Admin)
Add: Insert new medicine details

Update: Modify stock quantity, price, expiry

Delete: Remove outdated or unavailable medicines

View All: See full medicine inventory

🔍 2.3 Medicine Browsing & Search (User)
View available medicines

Search by name or brand

🛒 2.4 Order Management
Cart & Checkout: Select medicines and place orders

Order Confirmation: Store order details (user ID, items, quantity, price, address)

Order History (User): View all past transactions

Order Tracking (Admin): View and manage all user orders

🧰 3. Technology Stack
Layer	Technologies
Frontend	HTML, CSS, Bootstrap, JSP
Backend	Java Servlets, JavaBeans
Database	MySQL with JDBC
Server	Apache Tomcat
IDE	NetBeans

🗃️ 4. Database Design Overview
Main Tables:

users – Stores user account details

medicines – Holds medicine inventory data

orders – Captures order metadata

order_items – Links multiple medicines to a single order

🔁 One order → Multiple medicines (One-to-Many)

✅ 5. Key Benefits
Automates entire pharmacy workflow

Reduces human error in stock/order handling

Secure user authentication

Real-time inventory and order tracking

Scalable for:

💳 Online payments

📩 Email/SMS notifications

📱 Mobile responsiveness

Let me know if you'd like the following additions:

✅ Project setup steps

📸 UI screenshots

📦 Deployment instructions

📝 Future enhancement ideas

