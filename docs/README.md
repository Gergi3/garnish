# Garnish
 
Real-time kitchen and staff management for restaurants — built with Spring Boot and Angular.
 
Garnish replaces paper tickets and shouting across the pass with a single app that keeps servers, cooks, bartenders and admins in sync. Servers take orders at the table, the kitchen and bar see live tickets on their own screens, admins manage the menu and see reports, and everyone can message each other without leaving the app.

---

## Expected Features
 
- **Role-based interfaces** for admins, managers, waiters, bartenders and cooks — each role sees only what they need.
- **Take orders at the table** with per-item notes, then send them to the right station automatically.
- **Live kitchen and bar displays** with per-item status, ticket timers, and push updates over WebSocket.
- **Menu and stock management** — categories, items, ingredients, recipes. Items disable automatically when ingredients run out.
- **Multiple menus** (lunch, dinner, seasonal) with a toggle to switch what servers see.
- **Full order lifecycle** — open → in progress → ready → served → paid. Each item tracked independently.
- **Receipts with tips and payment method**, one receipt per order.
- **In-app chat** — general, kitchen, bar, and direct messages.
- **Reviews and ratings** for menu items and staff.