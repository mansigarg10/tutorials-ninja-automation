# 🚀 Tutorials-Ninja-Automation
![Java](https://img.shields.io/badge/Java-17-blue)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=flat-square&logo=selenium&logoColor=white)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)

---

## 📝 Overview

This project automates the core e-commerce flows on the [TutorialsNinja](https://tutorialsninja.com/demo/) platform using **Selenium WebDriver**, **TestNG**, and **Java**. It includes comprehensive test cases covering:

- Login
- Registration
- Product Search
- Add to Cart
- Checkout

---

## ✨ Features

- **Login Automation**: Validates user login functionality.
- **Registration Automation**: Automates the registration process and email verification.
- **Add to Cart Automation**: Validates the add to cart process.
- **Product Search Automation**: Validates the product search functionality.
- **Checkout Automation**: Validates the checkout process after registration.
- **Extent Reports Integration**: Generates detailed test execution reports.
- **Data-Driven Testing**: Uses JSON files for managing test data.
- **Html Formatting**: Provides a well-structured HTML report for test results.

---

## 🛠 Technologies Used

| Component            | Technology         |
|----------------------|--------------------|
| Programming Language | Java               |
| Build Tool           | Maven              |
| Testing Framework    | TestNG             |
| Web Automation       | Selenium WebDriver |
| Reporting Tool       | Extent Reports     |
| JSON Parsing         | Jackson Library    |

---

## 📂 Project Structure

```
tutorials-ninja-automation/
├── src/
│   ├── main/
│   │   ├── java/                # Page objects, utilities, constants
│   │   └── resources/           # Test data in JSON format
│   └── test/
│       ├── java/                # Test classes, TestNG listeners
│       └── resources/           # TestNG XML configuration
```
## ✅ Prerequisites

- **Java**: JDK 17 or higher
- **Maven**: Installed and configured
- **Browser**: Microsoft Edge (used by default)
- **IDE**: IntelliJ IDEA (recommended)

---

## ⚙️ Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/mansigarg10/tutorials-ninja-automation.git
   ```

2. Open the project in IntelliJ IDEA.

3. Use Maven to install dependencies:
   ```bash
   mvn clean install
   ```

4. Update JSON files in `src/main/resources/` with valid test data.

---

## 🚀 Running Tests

- To run all tests via Maven:
  ```bash
  mvn test
  ```

- To run specific tests:
  Use corresponding **TestNG XML** files located in root directory.

---

## 🧪 Test Data

Test data is maintained as JSON files:

| File Name              | Purpose                |
|------------------------|------------------------|
| `Login.json`   		 | Login credentials      |
| `Register.json`		 | User registration info |
| `Search.json` 		 | Product search info 	  |

---

## 📊 Reporting

Test execution reports are generated using **Extent Reports** and saved at:

```
/docs/index.html
```

Make sure the screenshot paths in the reports are valid and accessible.

---

## 🔗 View Hosted Report

You can view the latest test execution report hosted via GitHub Pages:

[👉 View Report on GitHub Pages](https://mansigarg10.github.io/tutorials-ninja-automation/)

> The report includes structured HTML output and embedded screenshots. Ensure paths are properly updated post-generation for accurate rendering.

---

## 📌 Key Classes

### 🖥 Pages

- `RegisterPage` – Manages user registration
- `AccountSuccessPage` – Displays account creation success message
- `LoginPage` – Handles login functionality
- `HomePage` – Manages navigation and common operations
- `SearchPage` – Manages product search
- `ProductDisplayPage` – Displays product details
- `ShoppingCartPage` – Manages cart operations
- `CheckoutPage` – Handles checkout process
- `OrderSuccessPage` – Displays order confirmation details

### 🧪 Tests

- `RegisterTest` – Validates user registration functionality
- `LoginTest` – Validates user login functionality
- `SearchTest` – Validates product search functionality
- `AddToCartTest` – Validates add to cart functionality
- `CheckoutTest` – Validates checkout process

### 🛠 Utilities

- `TutorialsNinjaConstants` – Contains constant values used across the framework
- `BaseTest` – Handles common setup/tear-down methods
- `HtmlFormatter` – Formats HTML files by removing leading empty lines

---

## ⚠️ Known Issues

- Ensure that the JSON test data files are properly formatted to avoid deserialization errors.
- Screenshot paths used in **Extent Reports** must be accessible to render properly.

---

## 🤝 Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.  
For major changes, please open an issue first to discuss what you'd like to change.

> You can also suggest enhancements for tests, reporting, or refactoring strategies.

---

## 📄 License

This project is licensed under the **MIT License**.

---

## 👩‍💻 Author

**Mansi Garg**

---
