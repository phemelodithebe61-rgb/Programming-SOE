# Mattress Sales Java Assignment — Questions 1 and 2

Two complete, separate Java 11+ Maven modules, combined in one parent project. Open the **root folder** in Apache NetBeans (or IntelliJ/Eclipse with Maven support).

## Requirements
Java JDK 11+; Maven 3.6+ for automated JUnit tests (downloads dependencies on first use); a desktop display for Question 2.

## Run
From this root folder:

- Test both questions: `mvn test`
- Question 1 console: `mvn -pl Question1_Console_Report exec:java -Dexec.mainClass=com.mattress.SalesReportApp`
- Question 2 GUI: `mvn -pl Question2_GUI_Application exec:java -Dexec.mainClass=za.ac.mattress.ProductApp`

Run without Maven:

- Question 1: `javac -d Question1_Console_Report/out Question1_Console_Report/src/main/java/com/mattress/*.java` then `java -cp Question1_Console_Report/out com.mattress.SalesReportApp`
- Question 2: `javac -d Question2_GUI_Application/out Question2_GUI_Application/src/main/java/za/ac/mattress/*.java` then `cd Question2_GUI_Application` and `java -cp out za.ac.mattress.ProductApp` (compile from the Question 2 directory if following this last step).

Question 1 sample input: `Question1_Console_Report/sample/question1-input.txt`. Expected totals: 153, average 17, most popular Cozy Nights Monarch(25).

Question 2: select a mattress, enter a customer and price, press PROCESS. For R3500, VAT R525, commission R175, total R4200. Each transaction is appended to `data.txt` in the application's **current working directory**. File > Exit; Tools > Process/Clear.

## Submission evidence
Capture screenshots on your computer: Question 1 report; Question 2 form, File and Tools menus, Process result dialog, saved `data.txt`, and passing unit tests. These are not pre-captured because GUI/JUnit execution is not available in this environment.

Read `RUBRIC_CHECKLIST.md` for a criterion-by-criterion check and remaining verification limitations.
