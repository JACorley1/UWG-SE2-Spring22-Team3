import os
from credentials_manager.in_memory_impl import LocalCredentialsManager as CredentialsManager
from server import Server

''' Launches Server with appropriate CredentialsManager
 
 @author CS 3212
 @version Spring 2022
'''
def main():
    os.chdir(os.path.dirname(os.path.abspath(__file__)))
    Server.run(Server, CredentialsManager())
    


if (__name__ == "__main__"):
    main()
