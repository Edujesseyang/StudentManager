# CS151 Term Project - Version 0.9
## **Students' Knowledgebase for Faculties**

---

# Project Description
This project is a desktop application designed for faculty members to record and manage students’ information such as programming skills, academic status, and evaluation notes.

## Version List:
### Version (**v0.8**):
It focuses on implementing the **"Report"** page with **"Black List"**/**"White List"** feature.

### Version (**v0.8**):  
It focuses on implementing the **"Edit student"** page.

### Version (**v0.7**):  
It focuses on implementing the **"Edit student"** page.

### Version (**v0.6**):
It focuses on implementing the **"Search for keywords"** page.

### Version (**v0.5**):
It focuses on implementing the **"Define Student "** page.

### Version (**v0.4**):
It focuses on implementing **"All Programming Languages"** page as well as permanently storing the programming languages info entered.
   
### Version (**v0.3**):
It focuses on implementing the **"Define Programming Languages"** page.

### Version (**v0.2**):
It focuses on implementing the **"Define Programming Languages"** page.
 
---
## Technical Spec: 

- **Language:** Java 21  
- **Framework:** JavaFX  
- **Build Tool:** Maven  
- **JDK:** ZuluFX 21 (verified working under Zulu 23)  
- **Database:** SQLite
- **Database library:** JDBC
- **Main Class:** `cs151.application.Main`
- **Style:** CSS

### UML
![UML](https://private-user-images.githubusercontent.com/142279444/517793709-97922a9b-fef7-4570-b5fc-c52165eb2b08.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NjM4NzM1MTIsIm5iZiI6MTc2Mzg3MzIxMiwicGF0aCI6Ii8xNDIyNzk0NDQvNTE3NzkzNzA5LTk3OTIyYTliLWZlZjctNDU3MC1iNWZjLWM1MjE2NWViMmIwOC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUxMTIzJTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MTEyM1QwNDQ2NTJaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xN2U5OTM2YzljNTY3NmVlZWFkYzIyMzA4YTQxNjBhMDM4ZDU0YzQzMWUxYThlOWU3YmVhYzFmYmQ4Yjc3M2Q5JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.25cgM5-u_52PhGb87Gz9uFG5nQw9l7z-7BrJmWdXksw)

### Package Structure
```text
src/
└── main/
    ├── java/
    │   ├── cs151/application/
    │   │   ├── controller/
    │   │   │   ├── EditPageController.java
    │   │   │   ├── DefineLanguagePageController.java
    │   │   │   ├── DefineStudentPageController.java
    │   │   │   ├── HomePageController.java
    │   │   │   ├── SearchPageController.java
    │   │   │   ├── StudentInfoPageController.java
    │   │   │   ├── ReportPageController.java
    │   │   │   └── StudentsListPageController.java
    │   │   ├── model/
    │   │   │   └── Student.java
    │   │   ├── services/
    │   │   │   ├── ControllerUtility.java
    │   │   │   ├── DataAccessor.java
    │   │   │   ├── DatabaseUtility.java
    │   │   │   ├── Logger.java
    │   │   │   └── ViewUtility.java
    │   │   ├── view/
    │   │   │   ├── EditStudentPage.java
    │   │   │   ├── DefineLanguagePage.java
    │   │   │   ├── DefineStudentPage.java
    │   │   │   ├── HomePage.java
    │   │   │   ├── ListDisplay.java
    │   │   │   ├── SearchStudentPage.java
    │   │   │   ├── StudentInfoPage.java
    │   │   │   ├── ReportPage.java
    │   │   │   └── StudentsListPage.java
    │   │   └── Main.java
    │   └── module-info.java
    └─── resources/
            ├── img/
            │   ├── bg.png
            │   ├── inputBg.png
            │   └── sectionBg.png
            └── style/
                └── homePage.css
```  
---
## Functional Spec :

### Required features:

#### 1. Home Page
- Displays a welcome message.
- Provides a button to navigate to the **Define Programming Languages** page.

#### 2. Define Programming Languages Page
- Allows the user to input a programming language name (required field).  
- Input validation prevents blank submissions.  
- Sstores entered languages in memory or text/JSON file (for testing only, will be replaced by database).  
- Includes a Back button to return to the Home page.

#### 3. Student List Page
- Allows the user to check the list of all students.  
- There is a Information button allows use to check a student's information.  
- Includes a Back button to return to the Home page.

#### 4. Define Student page.
- Allows the user to check the a button and fill a form to define a student.  
- It will auto sorting the list after store in the studentList data file.

#### 5. Local Data Storing Feature
- A json file on local for storing all students' data.
- When the program is launched, program will load data from the json file. When add a student and close the program, that json file will be updated.
- Make sure the data will not be lost when close the program.

#### 6. Search Keywords Page.
- A search page allow users to enter keywords that they want to search.
- After click the search button, application will pop a result page with table view style to show all students that has the keywords.

#### 7. Comment Editing.
- Allows the user to view, add, and delete comments on a student's profile
- After clicking on a student's account, the application will provide an option to edit a student's profile and modify their comments.
  
---
