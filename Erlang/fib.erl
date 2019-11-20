-module(fib).
-export([fibonacci/1]).

fibonacci(N) when N<2 ->
 1;
fibonacci(N) when N>=2 ->
 fibonacci(N-1)+fibonacci(N-2).
