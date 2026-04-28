# Marble Order Manager

A web-based order management system built for a real marble & granite business.
Replaces handwritten paper orders with a digital solution.

## Problem Solved
The business previously managed orders on paper — handwritten dimensions, 
material types, and cutting specifications. Papers were often lost or illegible, 
causing errors. This application digitizes the entire process.

## Features
- Customer management with search by name or phone
- Material catalog with price per m²
- Order creation with multiple lines (slabs, tombstones, etc.)
- Automatic price calculation based on material + cutting (cant)
- Cutting specifications per side (left, right, top, bottom) and corners
- Order status tracking: New → In Progress → Ready → Picked Up
- Order detail view for workers

## Tech Stack
- **Backend:** Java 17, Spring Boot, Spring Data JPA, Hibernate
- **Database:** MySQL
- **Frontend:** Thymeleaf, Bootstrap 5
- **Build:** Maven
- **Deploy:** Railway

## Running Locally
1. Clone the repository
2. Create a MySQL database named `marble_orders`
3. Set environment variables:
DATABASE_URL=jdbc:mysql://localhost:3306/marble_orders
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
4. Run with IntelliJ or `mvn spring-boot:run`
5. Access at `http://localhost:8081/api/comenzi/pagina`

## Live Demo
[marmura-order-manager-production.up.railway.app](https://oction.up.railway.app/api/comenzi/pagina)
