:- use_module(library(clpfd)).

money(S, E, N, D, M, O, R, Y) :-
	[S,E,N,D,M,O,R,Y] ins 0..9,
	all_different([S, E, N, D, M, O, R, Y]),
	S #>= 1, M #>= 1,
	1000*S + 100*E + 10*N + 1*D +
	1000*M + 100*O + 10*R + 1*E #=
	10000*M + 1000*O + 100*N + 10*E + 1*Y,
	label([S, E, N, D, M, O, R, Y]).
		
