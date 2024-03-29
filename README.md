<h1> Student Management </h1> 
<h2>Etapa 1</h2>
</br>
<b>Tema proiectului:</b> gestiunea unor studenti dintr-o facultate.
<li><b>clasa Address: </b>clasa pentru reprezentarea unei adrese</li>
<li><b>clasa Group: </b>clasa ce ofera informatii legate de grupa din care face parte un student </li>
<li><b>clasa Person: </b>clasa care este mostenita de clasa Student</li>
<li><b>clasa Series: </b>clasa ce ofera informatii legate de seria din care face parte un student</li>
<li><b>clasa Student: </b>clasa ce retine datele despre studenti</li>
<li><b>clasa StudyProgram: </b>clasa ce ofera informatii legate de programul de studiu</li>
<li><b>clasa Subject: </b>clasa pentru reprezentarea materiilor studiate de un student</li>
<li><b>clasa University: </b>clasa ce cuprinde informatii despre universitatea la care studiaza un student</li>
<br/>
Pe langa clasele de baza proramul mai cuprinde si clasele:
<li><b>clasa Main: </b>clasa ce instantiaza un meniu si ruleaza programul principal al aplicatiei</li>
<li><b>clasa Menu: </b>clasa ce implementeaza un meniu interactiv cu design pattern de tip singleton</li>
<li><b>clasa Service: </b>clasa ce cuprinde toate functiile necesare pentru realizarea comenzilor din meniu</li>
</br>

![diagrama](diagrama.jpg)

### *Meniul interactiv al aplicatiei:*
```[python]
-----------------------------------
1: Add new subject
2: Print all subjects
3: Add new study program
4: Print all study programs
5: Add new group
6: Print all groups
7: Add new series
8: Print all series
9: Add new university
10: Print all universities
11: Add new student
12: Print all students
13: Sort students by average grade
14: Average grade of a student
15: Delete a student
Exit
-----------------------------------
```
<h2>Etapa 2</h2>
Configurarea unei baze de date cu JDBC si MySQL cu tabele corespunzatoare claselor StudyProgram, Group, University, Subject si Student. Pentru introducerea si modificarea datelor din tabele au fost create 5 clase de tip repository si singleton ce se conecteaza la baza de date.

Tabela Student poate fi modificata folosind toate cele 4 operatii CRUD: create, read, update si delete. Toate operatiile din meniu au fost modificate pentru a persista datele atat in fisierele CSV, cat si in tabelele bazei de date. In meniu au fost adaugate urmatoarele functionalitati:

```[python]
-----------------------------------
12: Print student by a given id
13: Update student name by a given id
14: Delete student by a given id
-----------------------------------
```

<h2>Serviciu de audit</h2>
Pentru inregistrarea tuturor actiunilor facute intr-o rulare a programului am implementant o clasa de tip singleton ce scrie toate operatiunile desfasurate intr-un fisier de tip CSV cu formatul nume_actiune, timestamp. Un exemplu de astfel de fisier:

```[python]
-----------------------------------
configure tables,2023-05-26 16:36:08
load data,2023-05-26 16:36:08
add subject,2023-05-26 16:36:17
print subjects,2023-05-26 16:36:25
print domains,2023-05-26 16:36:28
close connection with database,2023-05-26 16:36:37
-----------------------------------
```
