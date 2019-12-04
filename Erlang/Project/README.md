## Chat server project for Programming Paradigms class

Instructions:


### -Server

open console and type 'make chat_server' that's it, up and running


### -Client(s)

open a different console and type erl -sname client1 -setcookie pass 

*********note that the 1 in client1 can be changed to n depending on how many users there are*************

### -Then for each client

type on each client console:

chat_client:start("Name", {chat_server, serv@ComputerName}).


## Chat away!
