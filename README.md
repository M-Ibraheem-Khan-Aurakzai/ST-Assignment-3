# ST-Assignment-3
Software Testing Assignment 3 
20L-2176

The task is related to CI/CD (continues integration and continues deployment). You have to create
CI/CD pipeline using GitHub workflow

(It took me alot of time & tries to understand and get all of this working, which is why there are alot of commits on this branch.)


The first photo shows the succesfull tests of the original function "authenticateUser" which only takes the Email and returns the name.
Since the function doesn't take a password it is not possible to test proper authentication.
![Test of original code](https://github.com/user-attachments/assets/663b8abb-e929-4f1e-a0d1-73fba126a1d4)

After correcting the code by adding a function "authenticateUserPassword" which takes the email and the password we can test proper authentication.
![Test of Corrected code](https://github.com/user-attachments/assets/09898618-2dbc-457b-bab6-de4c14c43470)

Here we can see after succesfull checks in the pull request, we can now merge the test branch with the main branch.
![image](https://github.com/user-attachments/assets/d39eb52f-f5fc-43d5-8794-df4d8f1d2cdc)
