from credentials_manager.in_memory_impl import LocalCredentialsManager as CredentialsManager
from server import Server

''' Launches Server with appropriate CredentialsManager
 
 @author CS 3212
 @version Spring 2022
'''
def main():
    Server.run(Server, CredentialsManager())


if (__name__ == "__main__"):
    main()
