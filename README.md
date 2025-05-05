Pharmacy Management System is a web-based dynamic application developed to automate and streamline the principal tasks involved in running a pharmacy. Implemented with Java technologies — namely JSP (JavaServer Pages), Servlets, and JDBC for database connections — the system offers administrative and customer-centric functionalities to facilitate efficient operations and an uninterrupted user experience.

Purpose and Scope
The main objective of this project is to minimize the use of manual record-keeping and enhance the precision and efficiency of pharmacy operations. It allows pharmacies to efficiently manage activities like medicine stock, user management, order processing, and tracking order history.
It accommodates two primary roles:
Admin: In charge of maintaining medicine information, managing orders, and maintaining system health.
Customer/User: Can register, login, view available drugs, order, and see previous transactions.

Core Functionalities
1. User Management
Registration: New users can register by giving basic information.
Login Authentication: Secure login through email and password.
Session Management: Users stay logged in while navigating; sessions are well-handled for logout and security.

2. Medicine Management (Admin)
Add New Medicine: Admin can give name, brand, quantity, price, and expiry date.
Update Medicine Info: Update the existing records with current stock and price.
Delete Medicine: Delete expired or obsolete medicines.
View All Medicines: Show all medicines in stock.
3. Medicine Browsing and Search (User)
View all medicines on hand.
Search medicines by brand or name.

4. Order Management
Cart and Checkout: Users can pick multiple medicines, enter quantities, and order.
Order Confirmation: Orders are stored with information such as user ID, medicine IDs, quantity, price, and delivery address.
Order History: Users are able to view their previous orders with all information (medicine names, prices, dates, etc.).
Admin Order View: Admin is able to view all user-placed orders along with user details and order details.

Technology Stack
Frontend: HTML, CSS, Bootstrap, JSP (JavaServer Pages)
Backend: Java Servlets (controller logic), Java Beans (data models)
Database: MySQL, leveraging JDBC for database interaction
Server: Apache Tomcat (Servlet container)
IDE: NetBeans

Database Design Overview
The application generally involves the following tables:
users – Stores registered users' information
medicines – Stores information about medicines
orders – Stores orders placed
order_items – Stores individual items of medicine tied to orders
(Relationships: Single order can involve multiple medicines.)

Benefits
Seamless operations of pharmacy
Less manual mistakes
Enhanced user experience
Order and stock management centralized
Easily extended to accommodate online payments, email/SMS notification, and mobile displays
