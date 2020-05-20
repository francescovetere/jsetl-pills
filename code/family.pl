father(a, b).	/* Fact  1 */
father(b, c).	/* Fact  2 */

/* Rule  1 */
brother(X, Y) :- father(Z, X), father(Z, Y), X \= Y. 
/* Rule  2 */ 
grandfather(X, Y) :- father(X, Z), father(Z, Y).
