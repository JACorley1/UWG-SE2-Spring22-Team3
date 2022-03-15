import typing

''' Manages the set of credentials for all system users.

 @author Team Three
 @version Spring 2022
'''
class CredentialsManager:
    
    ''' Create a new credential manager with no credentials
    
     @precondition none
     @postcondition no credentials exist
    '''
    def __init__(self):
        raise NotImplementedError()

    ''' Add a new user with the specified credentials to the system
     
     @precondition username != null && password != null &&
     @postcondition getUsername(systemName).equals(username) 
 && getPassword(systemName).equals(password

     @param username username for the system
     @param password password for the system
     
     @return true  if user was added successfully
               false if user was not added successfully
    '''
    def addUser(self, username: str, password: str) -> bool:
        raise NotImplementedError()
   
    ''' Update an existing user with the specified credentials to the system
     
     @precondition username != null && password != null 
     @postcondition 
     
     @param username username for the specified user
     @param password password for the specified user
     
     @return true  if user credentials updated successfully
                   false otherwise
    '''
    def updateUserPassword(self, username: str, password: str) -> bool:
        raise NotImplementedError()

    
    
