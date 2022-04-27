import zmq # type: ignore
import time, json
from credentials_manager.base import CredentialsManager
from typing import MutableMapping, Any

''' Handles server requests and returns appropriately formatted responses

 @author Team Three
 @version Spring 2022
'''
class _RequestHandler:
    _credentialsManager: CredentialsManager
    ''' Creates a new RequestHandler with the provided CredentialsManager
     
     @precondition credentialsManager != None && isinstance(credentialsManager, CredentialsManager)
     @postcondition RequestHandler has appropriate CredentialsManager to server requests
    '''
    def __init__(self, credentialsManager: CredentialsManager):
        if (credentialsManager == None):
            raise Exception("Must be provided a CredentialsManager")
        if (not isinstance(credentialsManager, CredentialsManager)):
            raise Exception("Must provide a subtype of CredentialsManager")
        self._credentialsManager = credentialsManager
    
    ''' Returns a response for the getPassword request
     Format: a string of the password
     
     @precondition none
     @postcondition none
     
     @return response string using appropriate format (see description for details)
    '''
    def _verifyPassword(self, userName, password) -> MutableMapping[str, Any]:
        if(password == password) :
            user = self._credentialsManager.getUser(userName)
            response = {"successCode": 1, "isValid": "1", "user": user}
        else :
            response = {"successCode": 1, "isValid": "0" }
        return response

    def _addUser(self, userName, password, profile) -> MutableMapping[str, Any]:
        userAdded = self._credentialsManager.addUser(userName, password, profile)
        if(userAdded):
            response = {"successCode": 1, "response": "1" }
        else:
            response = {"successCode": 1, "response": "0" }
        return response
        
    def _updateUser(self, userName, profile) -> MutableMapping[str, Any]:
        userUpdated = self._credentialsManager.updateUserProfile(userName, profile)
        if(userUpdated):
            response = {"successCode": 1, "response": "1" }
        else:
            response = {"successCode": 1, "response": "0" }
        return response

    def _getProfiles(self) -> MutableMapping[str, Any]:
        users = self._credentialsManager.getProfiles()
        if(users):
            response = {"successCode": 1, "response": "1", "users": users }
        else:
            response = {"successCode": 1, "response": "0" }
        return response

    def _removeUser(self, userName) -> MutableMapping[str, Any]:
        userRemove = self._credentialsManager.removeUser(userName)
        if(userRemove):
            response = {"successCode": 1, "response": "1" }
        else:
            response = {"successCode": 1, "response": "0" }
        return response

    def handleRequest(self, request: MutableMapping[str, Any]) -> MutableMapping[str, Any]:
        response: MutableMapping[str, Any]
        if ("requestType" not in request) :
            response = {"successCode": -1, "errorMessage": "Malformed Request, missing Request Type"}
        if (request["requestType"] == "ping") :
            response = {"successCode": 1}
        elif (request["requestType"] == "verifyPassword") :
            data = json.loads(request["request"])
            userName = data["username"]
            password = data["password"]
            response = self._verifyPassword(userName, password)
        elif (request["requestType"] == "addUser") :
            data = json.loads(request["request"])
            userName = data["username"]
            password = data["password"]
            profile = data["profile"]
            response = self._addUser(userName, password, profile)
        elif (request["requestType"] == "updateUser") :
            data = json.loads(request["request"])
            userName = data["username"]
            profile = data["profile"]
            response = self._updateUser(userName, profile)
        elif (request["requestType"] == "getProfiles") :
            response = self._getProfiles()
        elif (request["requestType"] == "removeUser") :
            data = json.loads(request["request"])
            userName = data["username"]
            response = self._removeUser(userName)
        else :
            errorMessage = "Unsupported Request Type ({requestType})".format(requestType = request['requestType'])
            response = {"successCode": -1, "errorMessage": errorMessage}
        return response

''' Handles server communication
 
 @author Team Three
 @version Spring 2022
'''
class Server:
    ''' Launches server main loop using the provided CredentialManager
     
     @precondition credentialsManager != None && isinstance(credentialsManager, CredentialsManager)
     @postcondition none
    '''
    def run(self, credentialsManager: CredentialsManager):
        if (credentialsManager == None):
            raise Exception("Must be provided a CredentialsManager")
        if (not isinstance(credentialsManager, CredentialsManager)):
            raise Exception("Must provide a subtype of CredentialsManager")
        
        requestHandler = _RequestHandler(credentialsManager)
        context = zmq.Context()
        socket = context.socket(zmq.REP)
        socket.bind("tcp://127.0.0.1:5555")
        
        while True:
            #  Wait for next request from client
            print("waiting for message... \n")
            jsonRequest = socket.recv_string()
            request = json.loads(jsonRequest)
            jsonResponse: str
            print("Received request: %s" % request)
            if (request["request"] == "exit"):
                return
            else :
                response = requestHandler.handleRequest(request)
                jsonResponse = json.dumps(response)
            
            #  Do some 'work'
            time.sleep(1)
        
            #  Send reply back to client
            socket.send_string(jsonResponse)

########################################################################

from threading import Thread
import time
import unittest

class _DummyCredentialsManager (CredentialsManager):
    def __init__(self):
        pass

'''
Test Class for _RequestManager

@author Team Three
@version Spring 2022
'''
class Test_RequestManager (unittest.TestCase):
    
    def test_unsupportedRequestType(self):
        request = {"requestType" : "not supported"}
        manager = _DummyCredentialsManager()
        handler = _RequestHandler(manager)
        
        response = handler.handleRequest(request)
        
        self.assertEquals(response["successCode"], -1, "checking success code")
        self.assertEquals(response["errorMessage"], "Unsupported Request Type (not supported)", "checking error message")

