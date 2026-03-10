<img width="304" height="284" alt="image" src="https://github.com/user-attachments/assets/b8c0c749-c952-470e-89e3-63348d8c52df" /># Evaluation Test Suite

An evaluation test suite is available at https://github.com/eclipse-4diac/4diac-examples/tree/master/compliance_tests
The project can be opened with a recent 4diac IDE (e.g., 4diac IDE 3.0.2).

The following examples are included. The label in column 1 refers to the sample in the 4diac project of the test suite. Screenshots show these examples within this file. 
Where applicable, the expected data values are added in brackets.

## Application A: **_01_EventConnections**
| Ref | Topic | Trigger Event | Expected Outputs |
|-----|-------|---------------|-----------------|
|  Ex1a   |  Simple event connection <img width="230" height="137" alt="image" src="https://github.com/user-attachments/assets/311f59e6-3c9d-46dd-ab61-0dc0a591f8e6" />   |      E_SPLIT.EI     |   E_REND.EO     |
|  Ex1b   |   Event connection <img width="320" height="131" alt="image" src="https://github.com/user-attachments/assets/ad3176f4-3c52-4815-b73a-40c49cbf4b4d" /> |     E_SPLIT.EI      |     E_SPLIT2.EO1, E_SPLIT2.EO2   |
|  Ex2    |   Fan-out <img width="227" height="116" alt="image" src="https://github.com/user-attachments/assets/b2dd6183-6842-4344-95a1-3800d659c6c0" /> |    E_SPLIT.EI    |   E_MERGE.EO, E_MERGE.EO     |
|  Ex3    |  Fan-in <img width="260" height="155" alt="image" src="https://github.com/user-attachments/assets/8fd8daa2-9ac1-483a-a2c5-c679b1d8acd4" />     |E_SPLIT.EI|         E_CTU.CUO (CV:=1, Q:=FALSE), E_CTU.CUO (CV:=2, Q:=TRUE)        |
|  Ex4    |   Self-loop  <img width="178" height="144" alt="image" src="https://github.com/user-attachments/assets/bc26cb11-5e36-4917-811b-59404dfb7dda" /> |   E_CTU.R      |   E_CTU.RO (CV:=0, Q:=FALSE), E_CTU.CUO (CV:=1, Q:=FALSE))    |
|  Ex5    |   Multiple kinds of FBs <img width="268" height="134" alt="image" src="https://github.com/user-attachments/assets/da9dda46-9152-42b9-a260-646ac2424208" />     |    E_PERMIT.EI           |        SimpleIO.CNF (DOI1:=TRUE)    |
|  Ex6a   |  For loop <img width="317" height="120" alt="image" src="https://github.com/user-attachments/assets/82987f78-084e-4ea9-b230-b9f8b211e137" />     |     E_PERMIT.EI          | SimpleNot.CNF (DOI1:=TRUE), SimpleNOT.CNF (DOI1:=FALSE)       |
|  Ex6b   |  For loop (control) <img width="565" height="247" alt="image" src="https://github.com/user-attachments/assets/88673ad2-1241-4a69-a5df-25eab455cca2" />     |       E_PERMIT.EI       |        none         |

## Application B: **_02_Parameters**
The value of a data pin depends on its initial value (if defined), the initial value of the type pin (if defined), or the initial value of the data type. Parameters override these initial values.
| Ref | Topic | Trigger Event | Expected Outputs |
|-----|-------|---------------|-----------------|
|   Ex1  |   Override initial value of data type  <img width="327" height="225" alt="image" src="https://github.com/user-attachments/assets/65e0fc36-f611-4124-88c5-d757a2092f3d" />   |     E_PERMIT.EI          |      E_PERMIT.EO           |
|   Ex2  |   Boolean parameter     <img width="329" height="207" alt="image" src="https://github.com/user-attachments/assets/4888261a-0ab8-465a-b19a-78fdd41e54eb" />|    E_PERMIT.EI           |       none          |
|   Ex3  |   Initial value (from type)                   <img width="188" height="106" alt="image" src="https://github.com/user-attachments/assets/67eb787e-9b4d-45d4-bab2-d99cc8e4136f" />|   E_PERMIT.EI            |     E_PERMIT.EO          |
|   Ex4  |   Override initial value (from type) <img width="433" height="239" alt="image" src="https://github.com/user-attachments/assets/6499ce18-78aa-4af3-88b1-7c58b8cabb44" />  |      E_PERMIT.EI         |     none            |
|   Ex5a  |  Integer Parameter   <img width="292" height="273" alt="image" src="https://github.com/user-attachments/assets/c4b379a4-ca6e-48d2-9d4d-36e5853697c3" />  |    INT2INT.REQ           |        INT2INT.CNF (OUT:=5)         |
|   Ex5b  |  Integer Parameter with Prefix    <img width="372" height="277" alt="image" src="https://github.com/user-attachments/assets/1099afda-0631-4de5-b0f4-4d67a57a7e54" />|     INT2INT.REQ          |         INT2INT.CNF (OUT:=5)        |
|   Ex5c  |  Integer Parameter with Implicit Cast  <img width="434" height="280" alt="image" src="https://github.com/user-attachments/assets/c27f10ac-1a0d-45db-9be3-2a784fcc6616" />| INT2INT.REQ           |     INT2INT.CNF (OUT:=5)            |
|   Ex6 |    Any-type Parameter with Implicit Casts <img width="304" height="284" alt="image" src="https://github.com/user-attachments/assets/155c6ad0-2a45-4777-9881-b5e144c2e929" />  |    F_ADD.REQ           |       F_ADD.CNF (OUT:=13)          |

## Application C: **_03_DataConnections**
| Ref | Topic | Trigger Event | Expected Outputs |
|-----|-------|---------------|-----------------|
| Ex1a    |  Boolean connection   <img width="486" height="241" alt="image" src="https://github.com/user-attachments/assets/5e18f627-90fa-4621-9f2f-fb242db21c77" />  |    Fb1.REQ           |        Fb2.CNF (OUT:=TRUE)         |
| Ex1b    |  Integer connection   <img width="457" height="239" alt="image" src="https://github.com/user-attachments/assets/cb3d9627-c2bc-4016-9353-e8eb9e56ac91" /> |     Fb1.REQ          |       Fb2.CNF (OUT:=5)          |
| Ex1c    |  WORD connection   <img width="575" height="256" alt="image" src="https://github.com/user-attachments/assets/969c2405-9467-4e1d-b21c-9c94dd58595e" />  |       Fb1.REQ        |          Fb2.CNF (OUT:=AFFE)       |
| Ex2a    |  Fan-out (two connected FBs)   <img width="555" height="371" alt="image" src="https://github.com/user-attachments/assets/88ae2f3f-853c-4563-8d61-d8c49ecf61ff" />  |      Fb1.REQ         |       (Fb2a.OUT := TRUE), Fb2b.CNF (OUT:=TRUE)          |
| Ex2b    |  Fan-out (three connected FBs)   <img width="580" height="433" alt="image" src="https://github.com/user-attachments/assets/8322e7dd-c179-4abe-ae67-b95f462d7727" /> |     Fb1.REQ          |     (Fb2a.OUT := TRUE), (Fb2b.OUT := TRUE), Fb2c.CNF (OUT:=TRUE)            |
| Ex3     |  Multiple kinds of FBs  <img width="553" height="300" alt="image" src="https://github.com/user-attachments/assets/c046a09a-faf7-4b77-a9ae-4d2608aadb0c" />  |FB1.CU               |        Fb2.CNF(OUT:=TRUE)         |
| Ex4a    |  Explicit type upcast across connections  <img width="646" height="278" alt="image" src="https://github.com/user-attachments/assets/cff71a87-024d-4104-932f-fab3e1ef16eb" /> |  Fb1.CU             |   Fb3.CNF(OUT:=1)      |
| Ex4b    |  Explicit type downcast across connections   <img width="622" height="287" alt="image" src="https://github.com/user-attachments/assets/3f6845fc-ea31-45fb-97bf-6ba5b89b5cb3" />  |      Fb1.REQ         |   Fb3.CUO(CV:=1,Q:=TRUE)    |
| Ex5a    |  Implicit type upcast to ANY   <img width="567" height="299" alt="image" src="https://github.com/user-attachments/assets/db831699-98c6-4ba4-afc5-53f625de871e" />   |      Fb1.CU         |   Fb2.CNF (OUT:=6)   |
| Ex5b    |  Implicit type upcast to REAL  <img width="561" height="303" alt="image" src="https://github.com/user-attachments/assets/6cef4031-5fae-4e76-85c5-8ca8c008f617" />  |      Fb1.CU         |    Fb2.CNF (OUT:=1.0) |
