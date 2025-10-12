# CS151 Term Project - Version 0.2  
**Students' Knowledgebase for Faculties**

---

## Project Description
This project is a desktop application designed for faculty members to record and manage students’ information such as programming skills, academic status, and evaluation notes.

This version (**v0.2**) is the first working prototype.  
It focuses on implementing the **"Define Programming Languages"** page described in the project problem statement (Section 2.1.1, item 1).

This version (**v0.3**) is the second working prototype.  
It focuses on implementing the **"Show All Programming Languages"** page described in the project problem statement (Section 2.1.1, item 2).

---

## Implemented Features (v0.3) :

#### 1. Home Page
- Displays a welcome message.
- Provides a button to navigate to the **Define Programming Languages** page.

#### 2. Define Programming Languages Page
- Allows the user to input a programming language name (required field).  
- Input validation prevents blank submissions.  
- Sstores entered languages in memory or text/JSON file (for testing only, will be replaced by database).  
- Includes a Back button to return to the Home page.

#### 3. Show All Student Page
- Allows the user to check the list of all students.  
- There is a Information button allows use to check a student's information.  
- Includes a Back button to return to the Home page.

#### 4. Show Students' Programming Language Status.
- Allows the user to check the programming languages of all students.  
- Includes a Back button to return to the Home page.

#### 5. Local Data Storing Feature
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
- **Style:** CSS

### Package Structure
```text
src/main/
  └── java/
    ├─── cs151/application/
    | ├── Main.java
    | ├── stage/
    | | ├── HomePageStage.java
    | | ├── StudenstListPage.java
    | | ├── StudenstInfoPage.java
    | | ├── LanguagesShowStage.java
    | | ├── ListDisplay.java
    | | └── DefinePageStage.java
    | ├── model/
    | | └── Student.java
    | | └── Student.java
    | └── tool/
    |   └── JsonFileStoreTool.java
    └─ source/
        ├── img/
        |  ├── bg.png
        |  ├── inputBg.png
        |  └── sectionBg.png
        └── style/
           └── homePage.css
```
  

