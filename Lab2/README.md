CSE5306_DistributedSystems
Lab1:- Objectives:

An introduction to sockets.
Exposure to file exchange.
Exposure to thread management.
You will write a client/server system that will check for commonly misspelled words in a block of text. The system will be demonstrated with a server and three client processes. Each client process will connect to the server over a socket connection and register a username at the server. The server should be able to handle all three clients simultaneously and display the names of the connected clients in real time. Two or more clients may not use the same username simultaneously. Should the server detect a concurrent conflict in username, the client’s connection should be rejected, and the client’s user should be prompted to input a different name. A client will connect to the server over a socket and upload a user-supplied text file. The server will have a lexicon of commonly misspelled words that it will read from a file upon startup. The server will scan the user-supplied text file uploaded by the client and check each word against the lexicon. Any word in the user-supplied text file found in the lexicon will be surrounded by brackets. When the server is finished identifying words, the text file will be returned to the client and the connection will be closed. For example, assume a text file with this text was uploaded to the server: the quck brown fox jumpz over the lazy dg If the server had a lexicon with the following contents: quck jumpz dg The server should return a a text file with the following contents: the [quck] brown fox [jumpz] over the lazy [dg] Words in the lexicon will be delimited with white space and character case should be ignored. The files should be plain .txt files and the contents may be created and examined with any basic text editor (for instance, Notepad on Windows or TextEdit on macOS). The functionality of the client and server are summarized as follows.

Lab #2 Infrastructure
Each client will have the ability to make additions to the server’s lexicon. The client GUI will present the user with the
ability to enter words for inclusion into the lexicon. What GUI components are used to facilitate this are left to the
developer’s discretion.
Words entered by the user will be placed into a queue and presented on the client’s GUI. Every 60 seconds, the server
will poll each client’s queue. If the queue is not empty, the server should retrieve the contents of the queue and add
those to its lexicon.
Once the client has been polled, the contents of the queue should be purged. The server should remove any duplicate
entries in the lexicon. Subsequent comparison between user-supplied text files and the lexicon should reflect the
updated contents of the lexicon.

• All three clients and the server may run on the same machine. • The server must correctly handle an unexpected client disconnection without crashing. • When a client disconnects from the server, the server GUI must indicate this to the user in real time. • The program must operate independently of a browser.
