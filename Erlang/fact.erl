-module(fact).
-export([factorial/1]).

factorial(N) when N<2 ->
 1;
factorial(N) when N>=2 ->
 factorial(N-1)* N.
 
