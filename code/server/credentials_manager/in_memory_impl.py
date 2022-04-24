import os
import typing
import json
import jsonschema
from jsonschema import validate
from credentials_manager.base import CredentialsManager


''' Stores a single set of credentials.

 @author Team Three
 @version Spring 2022
'''


class _EmployeeProfile:
    PROFILE_PROPERTY = "__profile__"
    FIRSTNAME = "firstName"
    MIDDLENAME = "middleName"
    LASTNAME = "lastName"
    EMAIL = "email"
    PHONE = "phone"
    HR = "hr"
    TIMESHEET = "timesheets"

    profileSchema = {
        "type": "object",
        "properties": {
            "__profile__": {"type": "boolean"},
            "id": {"type": "number"},
            "firstname": {"type": "string"},
            "middlename": {"type": "string"},
            "lastname": {"type": "string"},
            "email": {"type": "string"},
            "phone": {"type": "string"},
            "hr": {"type": "boolean"},
            "timesheets": {"type": "array",
                "items": {"type": "object",
                    "properties": {
                        "startDate": {"type": "string"},
                        "daysheets": {"type": "array",
                            "items": {"type": "object",
                                "properties":{
                                    "dayIndex": {"type": "number"},
                                    "times": {"type": "array",
                                        "items": {"type": "object",
                                            "properties":{
                                                "dayIndex": {"type": "number"},
                                                "clockin": {"type": "string"},
                                                "clockout": {"type": "string"}
                                    }}}
                        }}}
            }}}
        },
    }

    def validateJson(jsonData):
        try:
            validate(instance=jsonData, schema=_EmployeeProfile.profileSchema)
        except jsonschema.exceptions.ValidationError as err:
            return False
        return True
    
class _User:
    USER_PROPERTY = "__user__"
    USERNAME = "username"
    PASSWORD = "password"
    PROFILE = "profile"

    ''' Creates a new User with the specified username and password

     @precondition isinstance(username, str) &&
                   isinstance(password, str)
     @postcondition getUsername() == username &&
                    getPassword() == password
    '''

    def __init__(self, username: str, password: str, profile: str):

        self._username: str = username
        self._password: str = password
        self._profile: str = profile

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

    ''' Returns the profile of the User
     
     @precondition none
     @postcondition none
     
     @return the profile of the User
    '''
    def getProfile(self) -> str:
        return self._profile
    
    ''' Sets the profile of the User
     @precondition none
     @postcondition none
    '''
    def setProfile(self, profile: str):
        timesheets = _EmployeeProfile.TIMESHEET
        currentHasTimesheet =  timesheets in self._profile
        noNewTimeSheet = timesheets not in profile or len(profile[timesheets]) < 1
        if currentHasTimesheet and noNewTimeSheet:
            profile[timesheets] = self._profile[timesheets]
        self._profile = profile

    ''' Encodes user to serializable object 
     @precondition 
     @postcondition
    '''
    def encode_user(obj: object) -> object:
        if isinstance(obj, _User):
            return {
                _User.USER_PROPERTY: True,
                _User.USERNAME: obj._username,
                _User.PASSWORD: obj._password,
                _User.PROFILE: obj._profile
            }
        else:
            type_name = obj.__class__.__name__
            raise TypeError(
                f"Object of type '{type_name}' is not JSON serializable")

    ''' Decodes dict into user 
     @precondition 
     @postcondition
    '''
    def decode_user(dct):
        if _User.USER_PROPERTY in dct:
            isValid = _EmployeeProfile.validateJson(dct[_User.PROFILE])
            if(isValid):
                return _User(dct[_User.USERNAME], dct[_User.PASSWORD], dct[_User.PROFILE])
        return dct


''' Manages the set of user credentials for all users in the system.

 @author Team Three
 @version Spring 2022
'''


class LocalCredentialsManager (CredentialsManager):
    DATA_FILE = 'data.json'

    ''' Create a new credential manager with default user credentials

     @precondition none
     @postcondition default credentials exist
    '''

    def __init__(self):
        self._systemCredentials: dict[str, _User] = {}
        self.loadData()

    ''' Saves data to data file from system credentials 

     @precondition 
     @postcondition
    '''
    def saveData(self):
        file = open(LocalCredentialsManager.DATA_FILE, 'w')
        json.dump(self._systemCredentials, file, default=_User.encode_user, indent=4, sort_keys=True)
        file.close

    ''' Loads data from data file to system credentials 

     @precondition 
     @postcondition
    '''
    def loadData(self):
        with open(LocalCredentialsManager.DATA_FILE) as user_data:
            data = user_data.read()
            self._systemCredentials = json.loads(data, object_hook=_User.decode_user) 

    ''' Add a new user with the specified credentials to the system
     
     @precondition username != null && password != null
     @postcondition
     
     @param username username of the user
     @param password password of the user
     
     @return true  if user added successfully
               false otherwise
    '''

    def addUser(self, username: str, password: str, profile: dict) -> bool:
        if(username in self._systemCredentials):
            return False
        isValid = _EmployeeProfile.validateJson(profile)
        if(isValid) :
            credentialsSet = _User(username, password, profile)
            self._systemCredentials[username] = credentialsSet
            self.saveData()
            return True
        return False

    ''' Return the password for a specified user
     
     @precondition userName != null &&
                     getUserNames().contains(userName)
     @postcondition none
     
     @param userName username of the user
     
     @return password of the user if getUserNames().contains(userName)
               null                   if !getUserNames().contains(userName)
    '''

    def getUserPassword(self, userName: str) -> str:
        if (userName not in self._systemCredentials):
            raise Exception("System with specified name not found")
        return self._systemCredentials[userName].getPassword()

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
        if (userName not in self._systemCredentials):
            return False
        self._systemCredentials.pop(userName)
        self.saveData()
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
        if (username not in self._systemCredentials):
            return False
        credentialsSet = _User(username, password)
        self._systemCredentials[username] = credentialsSet
        return True

    ''' Update an existing user with the specified profile
     
     @precondition username != null && profile != null &&
                     getUserNames().contains(username)
     @postcondition getUserNames().contains(username) &&
                      getUser(username).getProfile.equals(username) &&
     
     @param username username for the user
     @param profile  profile for the user
     
     @return true  if user’s profile updated successfully
               false otherwise
    '''
    def updateUserProfile(self, username: str, profile: str) -> bool:
        if(username not in self._systemCredentials):
            return False
        self._systemCredentials[username].setProfile(profile)
        self.saveData()
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

    def getUser(self, username: str) -> _User:
        return json.dumps(self._systemCredentials[username], default=_User.encode_user)
    
    def getProfiles(self) -> str:
        profiles = []

        for profile in self._systemCredentials.values():
            profiles.append(profile)

        return json.dumps(profiles, default=_User.encode_user)