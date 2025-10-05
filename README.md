# CS151 Term Project - Version 0.2  
**Students' Knowledgebase for Faculties**

## Team Info
- **Team Number:** 21  
- **Team Members:**  
  - Jesse Yang (Main structure, data handling, file IO setup)  
  - Julian Christian Simmons (UI design, Git setup, )  
  - Ryan Monazzami (Navigation logic, Home page setup)  
  - Daniel Khant (Code documentation, testing, cleanup)

---

## Project Description
This project is a desktop application designed for faculty members to record and manage students’ information such as programming skills, academic status, and evaluation notes.

This version (**v0.2**) is the first working prototype.  
It focuses on implementing the **"Define Programming Languages"** page described in the project problem statement (Section 2.1.1, item 1).

---

## Implemented Features (v0.2) :

### Required features:

#### 1. Home Page
- Displays a welcome message.
- Provides a button to navigate to the **Define Programming Languages** page.

#### 2. Define Programming Languages Page
- Allows the user to input a programming language name (required field).  
- Input validation prevents blank submissions.  
- Sstores entered languages in memory or text/JSON file (for testing only, will be replaced by database).  
- Includes a Back button to return to the Home page.

### Extra features (Non required):

#### 3. Show All Exsist Students
- A button on the home page. 
- Allow user to check all students by clicking.
- A close butto on the showing page, allow user to close window by clicking.

#### 4. Local Data Storing feature
- A json file on local for storing all students' data.
- When the program is launched, program will load data from the json file. When add a student and close the program, that json file will be updated.
- Make sure the data will not be lost when close the program.
  
---

## Technical Details

- **Language:** Java 21  
- **Framework:** JavaFX  
- **Build Tool:** Maven  
- **JDK:** ZuluFX 21 (verified working under Zulu 23)  
- **Main Class:** `cs151.application.Main`

### Package Structure
```text
src/
└── main/java/
  └── cs151/application/
    ├── Main.java
    ├── stage/
    | ├── HomePageStage.java
    | ├── StudenstShowStage.java
    | └── DefinePageStage.java
    ├── model/
    | └── Student.java
    | └── StudentList.java
    └── tool/
      └── JsonFileStoreTool.java
```
  

