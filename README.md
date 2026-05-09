# Marble Order Manager

[![CI](https://github.com/mariozcn/marble-order-manager/actions/workflows/ci.yml/badge.svg)](https://github.com/mariozcn/marble-order-manager/actions/workflows/ci.yml)
[![Live Demo](https://img.shields.io/badge/demo-live-success)](https://oction.up.railway.app/dashboard)
[![Java 17](https://img.shields.io/badge/Java-17-orange)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-brightgreen)](https://spring.io/projects/spring-boot)

> Production web application replacing handwritten paper orders for a real marble & granite workshop. Built and deployed solo, used daily.

**Live demo:** [oction.up.railway.app/dashboard](https://oction.up.railway.app/dashboard)

---

## The Problem

The workshop tracked orders on paper: dimensions, materials, and cutting specifications written by hand. Slips were lost, illegible, or duplicated, and pricing was recalculated from memory each time. The business needed a single source of truth that the owner could update on a phone and that workers could read on a shop-floor tablet.

## The Solution

A Spring Boot web app that digitizes the entire order lifecycle — from creating an order with multiple cut lines, through automatic price calculation, to status tracking until pickup. Every screen was designed around a real workflow observed on site, not a generic CRUD form.

## Key Engineering Decisions

| Decision | Why |
|----------|-----|
| **Server-rendered Thymeleaf + Alpine.js** | The shop runs on a single tablet — no SPA build step, no auth-token shuffling, fast first paint over 4G. Alpine handles the small interactive bits (modals, dynamic order lines) without a framework tax. |
| **Tailwind via CDN** | Zero build pipeline. Edits land on Railway in seconds. Same utility classes shape every page, so the UI stays internally consistent. |
| **Layered architecture (Controller → Service → Repository)** | Pricing logic lives in `ComandaService` so it can be unit-tested in isolation without spinning up the web layer or the database. |
| **Split View / REST controllers** | `*ViewController` returns Thymeleaf templates; `*Controller` exposes JSON for browser fetch calls. Same services, two transports, no template hacks for AJAX. |
| **DTO boundary for order creation** | `ComandaDTO` / `LinieComandaDTO` decouple the JSON contract from JPA entities, so the schema can evolve without breaking clients. |
| **Spring Security + BCrypt** | Form login, hashed passwords, `/api/**` carve-out for stateless JSON endpoints called from the same origin. |
| **OpenAPI via springdoc** | `/swagger-ui` exposes every REST endpoint for inspection — useful for handing the project to a future maintainer. |

## Tech Stack

**Backend** — Java 17 · Spring Boot 4 · Spring MVC · Spring Data JPA · Spring Security · Hibernate · BCrypt
**Frontend** — Thymeleaf · Tailwind CSS · Alpine.js · Lucide icons
**Database** — MySQL
**Tooling** — Maven · JUnit 5 · Mockito · springdoc OpenAPI · GitHub Actions · Railway

## Architecture

```
┌──────────────────────────────────────────────────────────┐
│  Browser (tablet / phone)                                │
│  ─ Thymeleaf-rendered HTML + Alpine.js for interactivity │
└────────────────────┬─────────────────────────────────────┘
                     │ HTML pages          │ JSON (fetch)
                     ▼                     ▼
        ┌────────────────────┐   ┌────────────────────┐
        │  *ViewController   │   │  *Controller       │
        │  (Thymeleaf)       │   │  (REST)            │
        └────────┬───────────┘   └────────┬───────────┘
                 │                        │
                 ▼                        ▼
        ┌──────────────────────────────────────────┐
        │  Service layer  (business + pricing)     │
        │  ComandaService, ClientService, ...      │
        └────────────────────┬─────────────────────┘
                             ▼
        ┌──────────────────────────────────────────┐
        │  Spring Data JPA repositories            │
        └────────────────────┬─────────────────────┘
                             ▼
                       ┌──────────┐
                       │  MySQL   │
                       └──────────┘
```

## Domain Model

- **Client** — name, phone.
- **Material** — type (Marmură / Granit / Quartz), origin, thickness, price per m².
- **Comanda** (Order) — date, status, optional notes, owning Client, list of LinieComanda.
- **LinieComanda** (Order line) — material reference, dimensions (length × width × thickness), `TIP_CANT` (edge finish style), four boolean flags for which sides are finished, four flags for which corners are rounded, computed price.
- **Status** lifecycle — `Noua → InLucru → Gata → Ridicata`.

Pricing is computed server-side from material rate × surface area, plus a per-meter premium for any finished edges. The total propagates up to the order summary and the dashboard KPIs.

## Features

**Dashboard** — KPI cards (total revenue, active orders, customer count, catalog size), status distribution bars, top customers by spend, recent orders feed.
**Customers** — searchable list, create / edit / delete via modal, drill into per-customer order history.
**Materials** — searchable catalog with type-coded badges, full CRUD including origin and thickness.
**Orders** — list with live filter, color-coded status pills, inline status changes via modal, full detail view per order.
**Order creation** — multi-line form with dynamic add/remove of lines, per-line edge and corner specifications, automatic price calculation on save.
**Order detail** — client info, summary cards, line breakdown table with dimensions, edges, corners, and per-line price, plus a grand total.

## Testing

- Pricing logic in `ComandaService` is unit-tested with JUnit 5 + Mockito.
- Repositories are stubbed via Mockito so tests run without a database.
- `mvn test` runs locally; GitHub Actions runs the same suite on every push.

```bash
mvn test
```

## API Documentation

With the app running, browse to `/swagger-ui/index.html` for the interactive OpenAPI spec covering every REST endpoint exposed under `/api/**`.

## Running Locally

1. Clone the repository.
2. Create a MySQL database named `marble_orders`.
3. Set the following environment variables:
   ```
   DATABASE_URL=jdbc:mysql://localhost:3306/marble_orders
   DATABASE_USERNAME=your_username
   DATABASE_PASSWORD=your_password
   ```
4. Run with IntelliJ or `mvn spring-boot:run`.
5. Open `http://localhost:8081/dashboard` (you will be redirected to `/login` first).

## Deployment

- Hosted on **Railway** with a managed MySQL plugin.
- Spring Boot reads `DATABASE_URL` / `DATABASE_USERNAME` / `DATABASE_PASSWORD` from Railway-injected env vars.
- GitHub Actions runs the test suite on every push; merges to `main` trigger an automatic Railway deploy.

## Roadmap

- Receipt / invoice PDF export per order.
- Order timeline showing every status change with timestamp and actor.
- Multi-user roles (owner vs. shop worker) with finer-grained permissions.
- Material stock tracking with low-inventory alerts.

## License

Personal project. Source available for reference; not licensed for redistribution.
