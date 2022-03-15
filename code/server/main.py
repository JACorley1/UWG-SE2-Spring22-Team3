from credentials_manager.in_memory_impl import InMemoryCredentialsManager as CredentialsManager
from server import Server

''' Launches Server with appropriate CredentialsManager
 
 @author CS 3212
 @version Spring 2022
'''
def main():
    server.run(CredentialsManager())


if (__name__ == "__main__"):
    main()
