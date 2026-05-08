# 2D physics Engine (Java)

A custom 2D physics engine written from scratch in Java.  
This project focuses on understanding how game engines work internally by implementing rendering, motion, and collision systems without external libraries.

---

## Overview

This engine simulates simple 2D rigid bodies with basic collision detection and rendering.  
Everything is built manually, including the game loop and pixel-level drawing.

The goal is not to be production-ready, but to learn how physics engines and rendering pipelines actually work.

---

## Features

### Rendering
- Custom software renderer using `BufferedImage`
- Pixel-by-pixel drawing
- Primitive shapes:
  - Circles
  - Rectangles
- Clear separation between rendering and logic

---

### Game Loop
- Fixed update loop (~60 FPS)
- Manual control over:
  - Update phase
  - Render phase

---

### Physics System
- Basic rigid body implementation:
  - Position (`Vector2`)
  - Velocity (`Vector2`)
- Scene-based object management
- Separation between:
  - Physics
  - Rendering
  - Object data

---

### Collision System
- Pairwise collision detection (`O(n²)`)
- Collision matrix using `BiConsumer`:
  - Maps collider types → collision functions
- Currently implemented:
  - Circle vs Box (basic)

---

### Architecture
- Moving toward a **component-based design**
- Separation of concerns:
  - `RigidBody2D` → physics data
  - `Renderer` → drawing
  - `Physics` → interactions
  - `Collider` → shapes

---

## Project Structure

```
src/
├── core/
│   ├── GamePanel.java          # Contains the main method, initializes the window and starts the engine
│   ├── Game.java               # Main loop and engine lifecycle
│   └── Scene.java              # Stores and updates all entities / rigid bodies
│
├── physics/
│   ├── Physics.java            # Physics update pipeline and collision solver
│   ├── RigidBody2D.java        # Physical object data (position, velocity, collider, mesh)
│   │
│   ├── collider/
│   │   ├── CollisionShape.java     # Base collider abstraction
│   │   ├── ColliderType.java       # Enum used for collision matrix indexing
│   │   ├── CircleCollider.java
│   │   └── BoxCollider.java
│   │
│   └── collision/
│       ├── CollisionMatrix.java    # Stores collision handlers
│       ├── CollisionSolver.java    # Collision response methods
│       └── CollisionDetector.java  # Collision detection methods
│
├── render/
│   ├── Renderer.java           # Software renderer and drawing utilities
│   ├── GraphicElements2D.java  # Fill/border rendering data
│   ├── ColorRGB.java           # Color utilities and RGB handling
│   │
│   └── mesh/
│       ├── Mesh.java           # Base render mesh abstraction
│       ├── CircleMesh.java
│       └── BoxMesh.java
│
├── math/
│   └── Vector2.java            # 2D vector math utilities
│
└── util/
    └── Time.java               # Delta time / timing utilities (future)
```

---

## Concepts Explored

- Game loop design
- Real-time simulation
- Collision detection
- Collision response (basic)
- Software rendering
- Object-Oriented vs Component-Based design
- Function-based dispatch (`BiConsumer`)

---

## Current Limitations

- Collision detection is approximate (not fully accurate)
- No proper collision resolution (impulses not implemented)
- No gravity or forces yet
- No optimization (broad-phase missing)
- Order-dependent behavior may still occur in some cases

---

## Future Improvements

- Accurate collision detection (e.g. circle-box using closest point)
- Circle vs Circle and Box vs Box collisions
- Proper physics response (impulse-based)
- Gravity and forces
- Static Bodies
- Spatial partitioning (QuadTree / Grid)
- Better architecture (full composition / ECS approach)
- Rendering improvements (double buffering, UI layer)

---

## Purpose

This project is built for learning and experimentation.

Instead of using existing engines, the goal is to:
- Understand how things work under the hood
- Build systems step by step
- Explore engine architecture decisions

---

## How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA (or any Java IDE)
3. Run `core.GamePanel` (or your main entry class)

---

## Inspiration

Inspired by:
- Game engine architecture principles
- Low-level rendering techniques
- This Youtube video by ZanzLanz: https://www.youtube.com/watch?v=nXrEX6j-Mws&t=287s

---

## License

This project is for educational purposes.  
Feel free to use or modify it.

---
