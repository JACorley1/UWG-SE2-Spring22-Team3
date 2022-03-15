import typing
from credentials_manager.base import CredentialsManager

''' Stores a single set of credentials.

 @author CS3212
 @version Spring 2022
'''
class _CredentialsSet:
    ''' Creates a new CredentialsSet with the specified username and password
     
     @precondition isinstance(username, str) &&
                   isinstance(password, str)
     @postcondition getUsername() == username &&
                    getPassword() == password
    '''
    def __init__(self, username: str, password: str):
        if (isinstance(username, str)) :
            raise Exception("username must be a str") 
        if (isinstance(password, str)) :
            raise Exception("password must be a str") 
        self._username: str = username
        self._password: str = password
    
    ''' Returns the username in the CredentialsSet
     
     @precondition none
     @postcondition none
     
     @return the username in the CredentialsSet
    '''
    def getUsername(self) -> str:
        return self._username
    
    ''' Returns the password in the CredentialsSet
     
     @precondition none
     @postcondition none
     
     @return the password in the CredentialsSet
    '''
    def getPassword(self) -> str:
        return self._password

''' Manages the set of system credentials for a single user.

 @author CS3212
 @version Spring 2022
'''
class InMemoryCredentialsManager (CredentialsManager):
    
    ''' Create a new credential manager with no systems
    
     @precondition none
     @postcondition no systems exist
    '''
    def __init__(self):
        self._systemCredentials: dict[str, _CredentialsSet] = {}

    ''' Add a new system with the specified credentials to the system
     
     @precondition systemName != null && !systemName.isEmpty() &&
                     username != null &&
                     password != null &&
                     !getSystemNames().contains(systemName)
     @postcondition getSystemNames().contains(systemName) &&
                      getUsername(systemName).equals(username) &&
                      getPassword(systemName).equals(password)
     
     @param systemName name of the system
     @param username username for the system
     @param password password for the system
     
     @return true  if system added successfully
               false if system not added successfully
    '''
    def addSystem(self, systemName: str, username: str, password: str) -> bool:
        if (systemName in self._systemCredentials) :
            return False
        credentialsSet = _CredentialsSet(username, password)
        self._systemCredentials[systemName] = credentialsSet
        return True
    
    ''' Retrieves a list of the names for all systems with credentials in the password manager
     
     @precondition none
     @postcondition none
     
     @return list of the names for all systems with credentials in the password manager
    '''
    def getSystemNames(self) -> typing.List[str]:
        systemNames = []
        
        for systemName in self._systemCredentials:
            systemNames.append(systemName)
        
        return systemNames
    
    ''' Return the password for a specified system
     
     @precondition systemName != null &&
                     getSystemNames().contains(systemName)
     @postcondition none
     
     @param systemName name of the system
     
     @return password of the system if getSystemNames().contains(systemName)
               null                   if !getSystemNames().contains(systemName)
    '''
    def getSystemPassword(self, systemName: str) -> str:
        if (systemName not in self._systemCredentials) :
            raise Exception("System with specified name not found")
        return self._systemCredentials[systemName].getPassword()

    ''' Return the username for a specified system
     
     @precondition systemName != null &&
                     getSystemNames().contains(systemName)
     @postcondition none
     
     @param systemName name of the system
     
     @return username of the system if getSystemNames().contains(systemName)
               null                   if !getSystemNames().contains(systemName)
    '''
    def getSystemUsername(self, systemName: str) -> str:
        if (systemName not in self._systemCredentials) :
            raise Exception("System with specified name not found")
        return self._systemCredentials[systemName].getUsername()
    
    ''' Remove a system with the specified name
     
     @precondition systemName != null &&
                     getSystemNames().contains(systemName)
     @postcondition !getSystemNames().contains(systemName) &&
                      getUsername(systemName) == null &&
                      getPassword(systemName) == null
     
     @param systemName name of the system
     
     @return true  if system removed successfully
               false if system not removed successfully
    '''
    def removeSystem(self, systemName: str) -> bool:
        if (systemName not in self._systemCredentials) :
            return False
        self._systemCredentials.pop(systemName)
        return True

    ''' Update an existing system with the specified credentials to the system
     
     @precondition systemName != null && !systemName.isEmpty() &&
                     username != null &&
                     password != null &&
                     getSystemNames().contains(systemName)
     @postcondition getSystemNames().contains(systemName) &&
                      getUsername(systemName).equals(username) &&
                      getPassword(systemName).equals(password)
     
     @param systemName name of the system
     @param username username for the system
     @param password password for the system
     
     @return true  if system updated successfully
               false if system not updated successfully
    '''
    def updateSystem(self, systemName: str, username: str, password: str) -> bool:
        if (systemName not in self._systemCredentials) :
            return False
        credentialsSet = _CredentialsSet(username, password)
        self._systemCredentials[systemName] = credentialsSet
        return True
    
    
