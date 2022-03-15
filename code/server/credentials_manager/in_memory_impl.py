import typing
from credentials_manager.base import CredentialsManager

''' Stores a single set of credentials.

 @author Team Three
 @version Spring 2022
'''
class _User:
    ''' Creates a new User with the specified username and password
     
     @precondition isinstance(username, str) &&
                   isinstance(password, str)
     @postcondition getUsername() == username &&
                    getPassword() == password
    '''
    def __init__(self, username: str, password: str):

        self._username: str = username
        self._password: str = password
    
    ''' Returns the username of the User
     
     @precondition none
     @postcondition none
     
     @return the username of the User
    '''
    def getUsername(self) -> str:
        return self._username
    
    ''' Returns the password of the User
     
     @precondition none
     @postcondition none
     
     @return the password of the User
    '''
    def getPassword(self) -> str:
        return self._password

''' Manages the set of user credentials for all users in the system.

 @author Team Three
 @version Spring 2022
'''
class LocalCredentialsManager (CredentialsManager):
    
    ''' Create a new credential manager with default user credentials
    
     @precondition none
     @postcondition default credentials exist
    '''
    def __init__(self):
       self._systemCredentials: dict[str, _User] = {}
       self.addUser("destiny", "harper")
       self.addUser("brianna", "irie")
       self.addUser("miguel", "campos")
       self.addUser("fernando", "dominguez")

    ''' Add a new user with the specified credentials to the system
     
     @precondition username != null && password != null
     @postcondition
     
     @param username username of the user
     @param password password of the user
     
     @return true  if user added successfully
               false otherwise
    '''
    def addUser(self, username: str, password: str) -> bool:
        if (username in self._systemCredentials) :
            return False
        credentialsSet = _User(username, password)
        self._systemCredentials[username] = credentialsSet
        return True
    
        
    ''' Return the password for a specified user
     
     @precondition userName != null &&
                     getUserNames().contains(userName)
     @postcondition none
     
     @param userName username of the user
     
     @return password of the user if getUserNames().contains(userName)
               null                   if !getUserNames().contains(userName)
    '''
    def getUserPassword(self, userName: str) -> str:
        if (userName not in self._systemCredentials) :
            raise Exception("System with specified name not found")
        return self._systemCredentials[systemName].getPassword()

       
    ''' Remove a user with the specified username
     
     @precondition userName != null &&
                     getUserNames().contains(userName)
     @postcondition !getUserNames().contains(userName) &&
                      getUsername(userName) == null &&
                      getPassword(userName) == null
     
     @param userName Username associated with User
     
     @return true  if user is removed successfully
               false if user is not removed successfully
    '''
    def removeUser(self, userName: str) -> bool:
        if (userName not in self._systemCredentials) :
            return False
        self._systemCredentials.pop(userName)
        return True

    ''' Update an existing user with the specified credentials in the system
     
     @precondition username != null && password != null &&
                     getUserNames().contains(username)
     @postcondition getUserNames().contains(systemName) &&
                      getUsername(username).equals(username) &&
                      getPassword(username).equals(password)
     
     @param username username for the user
     @param password password for the user
     
     @return true  if user’s password updated successfully
               false otherwise
    '''
    def updateUserPassword(self, username: str, password: str) -> bool:
        if (username not in self._systemCredentials) :
            return False
        credentialsSet = _User(username, password)
        self._systemCredentials[username] = credentialsSet
        return True

    
    ''' Retrieves a list of the names for all users with credentials in the password manager
     
     @precondition none
     @postcondition none
     
     @return list of the names for all users with credentials in the password manager
    '''
    def getUserNames(self) -> typing.List[str]:
        userNames = []
        
        for userName in self._systemCredentials:
            userNames.append(userName)
        
        return userNames

    
    
