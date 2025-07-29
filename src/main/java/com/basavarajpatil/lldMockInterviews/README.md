# 🧪 Low-Level Design Mock Interviews

This section contains real-world Low-Level Design problems commonly asked in technical interviews. Each problem is implemented with clean, object-oriented code following SOLID principles.

## 📊 Difficulty Levels

### 🟢 Easy Level
Problems suitable for entry-level to mid-level positions. Focus on basic OOP concepts and simple design patterns.

- **[Vending Machine](easy/vendingMachine/)** - Complete implementation with inventory, payment, and dispensing logic
- **[Document Reader System](easy/documentReaderSystem/)** - Multi-format document processing with Factory and Singleton patterns

### 🟡 Medium Level (Coming Soon)
Problems for senior positions requiring deeper design thinking and pattern knowledge.

### 🔴 Hard Level (Coming Soon)
Complex system design problems for senior/lead positions requiring advanced architectural decisions.

## 🎯 Learning Objectives

### Technical Skills
- **Object-Oriented Design**: Classes, interfaces, inheritance, polymorphism
- **Design Patterns**: Factory, Singleton, Observer, Strategy, etc.
- **SOLID Principles**: Single responsibility, Open/closed, Liskov substitution, etc.
- **Error Handling**: Exception management and edge cases
- **Code Organization**: Package structure and modularity

### Interview Skills
- **Requirements Gathering**: Ask clarifying questions
- **System Thinking**: Break down complex problems
- **Iterative Development**: Start simple, add complexity
- **Communication**: Explain design decisions clearly
- **Trade-offs**: Discuss pros and cons of different approaches

## 🚀 How to Approach LLD Problems

### 1. 📋 Understand Requirements
- Ask clarifying questions
- Identify core functionalities
- Understand constraints and assumptions
- Define scope clearly

### 2. 🎨 High-Level Design
- Identify main entities/classes
- Define relationships between entities
- Choose appropriate design patterns
- Plan the overall architecture

### 3. 🏗️ Detailed Design
- Define interfaces and abstract classes
- Implement concrete classes
- Handle edge cases and error scenarios
- Apply SOLID principles

### 4. 🧪 Implementation
- Write clean, readable code
- Add proper error handling
- Include comments for complex logic
- Create a working demo/client

### 5. 🔄 Review and Refactor
- Review for design patterns
- Check for SOLID principle violations
- Optimize for readability and maintainability
- Consider scalability and extensibility

## 📚 Common Interview Patterns

### System Components
- **Entities**: Core business objects (User, Product, Order)
- **Services**: Business logic layer (PaymentService, NotificationService)
- **Repositories**: Data access layer (UserRepository, ProductRepository)
- **Controllers**: API/interaction layer (OrderController, UserController)
- **Utils**: Helper classes and utilities

### Design Patterns Frequently Used
- **Factory**: Creating objects based on type
- **Singleton**: Single instance classes (DB connection, Logger)
- **Strategy**: Different algorithms for same problem
- **Observer**: Event-driven systems
- **Builder**: Complex object construction
- **State**: State-dependent behavior

## 🎓 Interview Tips

### ✅ Do's
- Start with basic functionality
- Ask questions throughout the process
- Explain your thought process
- Consider future extensibility
- Write clean, readable code
- Handle edge cases
- Test your solution mentally

### ❌ Don'ts
- Don't jump into coding immediately
- Don't ignore requirements
- Don't write monolithic classes
- Don't forget error handling
- Don't ignore SOLID principles
- Don't overcomplicate initially

## 🔧 Problem Selection Guide

### For Beginners
Start with **Easy** level problems that focus on:
- Basic class design
- Simple inheritance/composition
- Error handling
- Basic design patterns

### For Intermediate
Move to **Medium** level problems involving:
- Multiple design patterns
- Complex relationships
- Concurrency considerations
- Performance optimization

### For Advanced
Tackle **Hard** level problems requiring:
- Distributed system concepts
- Advanced architectural patterns
- Scalability considerations
- Complex state management

## 📖 Study Resources

### Books
- "Clean Code" by Robert C. Martin
- "Design Patterns" by Gang of Four
- "Effective Java" by Joshua Bloch
- "Head First Design Patterns" by Freeman & Sierra

### Online Resources
- [Grokking the Object Oriented Design Interview](https://www.educative.io/courses/grokking-the-object-oriented-design-interview)
- [System Design Interview](https://github.com/donnemartin/system-design-primer)
- [LeetCode Design Problems](https://leetcode.com/problemset/all/?topicSlugs=design)

## 🏃‍♂️ Quick Start

1. Pick a problem based on your level
2. Read the requirements carefully
3. Try implementing it yourself first
4. Compare with the provided solution
5. Run the code and test edge cases
6. Review and understand the design decisions

---

💡 **Remember**: The journey of mastering LLD is iterative. Start simple, practice regularly, and gradually tackle more complex problems!
