# Sibank Sinatech Library

A modular **Java library** designed for integrating with **Sinatech banking services** within the Sibank ecosystem.  
This library provides reusable components for secure communication, request/response modeling, and service orchestration — built with **Java 17** and ready for enterprise use.

---

## ⚙️ Purpose

The `sibank-sinatech-lib` module abstracts the complexity of interacting with Sinatech APIs, offering:

- Standardized request/response models  
- Secure message signing and verification  
- Configurable service endpoints  
- Exception handling and logging  
- Easy integration into Spring Boot or other Java applications  

---

## ✨ Key Features

- Written in **Java 17**  
- Designed for **Spring Boot** compatibility  
- Modular service classes for different banking operations  
- DTOs for request/response payloads  
- Utility classes for encryption, signing, and validation  
- Centralized error handling  
- Lightweight and dependency-free (except core Java + optional Spring)  

---

## 📁 Project Structure

sibank-sinatech-lib/ │── src/ │ ├── main/ │ │ ├── java/com/sibank/sinatech/ │ │ │ ├── service/ # Core service classes │ │ │ ├── model/ # DTOs and payloads │ │ │ ├── config/ # Endpoint and credential config │ │ │ ├── util/ # Signing, encryption, helpers │ │ │ └── exception/ # Custom exceptions │ └── resources/ │ └── application.yml # Sample config (if used) │── pom.xml # Maven dependencies

Code

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/mehdiarz/sibank-sinatech-lib.git
cd sibank-sinatech-lib
2. Build the library
bash
mvn clean install
3. Add to your project
In your consuming project’s pom.xml:

xml
<dependency>
  <groupId>com.sibank</groupId>
  <artifactId>sibank-sinatech-lib</artifactId>
  <version>1.0.0</version>
</dependency>
🔌 Usage Example
java
SinatechService service = new SinatechService(config);
TransactionRequest request = new TransactionRequest(...);
TransactionResponse response = service.sendTransaction(request);
🛡️ Security
Message signing with SHA256 or RSA

Configurable credentials and keys

Input validation and exception handling

Optional logging for audit trails

🧪 Roadmap
Add unit tests with JUnit + Mockito

Add support for async messaging (e.g. Kafka)

Add OpenAPI documentation

Publish to Maven Central or private registry

Add retry and circuit breaker support

👨‍💻 Author
Developed and maintained by Mehdi Arz

📄 License
This project is licensed under the MIT License.
