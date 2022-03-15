import typing
from credentials_manager.base import CredentialsManager

''' Manages the set of system credentials for a single user.

 @author CS3212
 @version Spring 2022
'''
class DBCredentialsManager (CredentialsManager):
    
    ''' Create a new credential manager with no systems
    
     @precondition none
     @postcondition no systems exist
    '''
    def __init__(self):
        raise NotImplementedError()

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
        raise NotImplementedError()
    
    ''' Retrieves a list of the names for all systems with credentials in the password manager
     
     @precondition none
     @postcondition none
     
     @return list of the names for all systems with credentials in the password manager
    '''
    def getSystemNames(self: CredentialsManager) -> typing.List[str]:
        raise NotImplementedError()
    
    ''' Return the password for a specified system
     
     @precondition systemName != null &&
                     getSystemNames().contains(systemName)
     @postcondition none
     
     @param systemName name of the system
     
     @return password of the system if getSystemNames().contains(systemName)
               null                   if !getSystemNames().contains(systemName)
    '''
    def getSystemPassword(self, systemName: str) -> str:
        raise NotImplementedError()

    ''' Return the username for a specified system
     
     @precondition systemName != null &&
                     getSystemNames().contains(systemName)
     @postcondition none
     
     @param systemName name of the system
     
     @return username of the system if getSystemNames().contains(systemName)
               null                   if !getSystemNames().contains(systemName)
    '''
    def getSystemUsername(self, systemName: str) -> str:
        raise NotImplementedError()
    
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
        raise NotImplementedError()

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
        raise NotImplementedError()
    
    
