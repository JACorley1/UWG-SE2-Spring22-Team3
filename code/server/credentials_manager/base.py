 import typing

''' Manages the set of credentials for all system users.

 @author Team Three
 @version Spring 2022
'''
class CredentialsManager:
    
    ''' Create a new credential manager with default credentials
    
     @precondition none
     @postcondition default credentials exist
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

    ''' Remove a user with the specified name
     
     @precondition userName != null &&
                     getUserNames().contains(userName)
     @postcondition !getUserNames().contains(userName) &&
                      getUsername(userName) == null &&
                      getPassword(userName) == null
     
     @param userName name of the system
     
     @return true  if user is removed successfully
               false if user is not removed successfully
    '''
    def removeUser(self, userName: str) -> bool:
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
    
''' Retrieves a list of the names for all users with credentials in the password manager
     
     @precondition none
     @postcondition none
     
     @return list of the names for all users with credentials in the password manager
    '''
    def getUserNames(self) -> typing.List[str]:
        raise NotImplementedError()


    
    
