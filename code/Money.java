package example;

import jsetl.*;
import jsetl.exception.Failure;

class Money {
	
	public static void main (String[] args) throws Failure {
		IntLVar s = new IntLVar("S", 0, 9);
		IntLVar e = new IntLVar("E", 0, 9);
		IntLVar n = new IntLVar("N", 0, 9);
		IntLVar d = new IntLVar("D", 0, 9);
		IntLVar m = new IntLVar("M", 0, 9);
		IntLVar o = new IntLVar("O", 0, 9);
		IntLVar r = new IntLVar("R", 0, 9);
		IntLVar y = new IntLVar("Y", 0, 9);
		
		IntLVar send = new IntLVar("SEND");
		IntLVar more = new IntLVar("MORE");
		IntLVar money = new IntLVar("MONEY");
		
		IntLVar[] letters = {s, e, n, d, m, o, r, y};
		
		Solver solver = new Solver();
		
		// Constraints
		
		// S >= 1 and M >= 1.
		solver.add(s.ge(1).and(m.ge(1)));

		// Each variable is different from each other
		solver.add(Constraint.allDifferent((Object[]) letters));
		
		// SEND = S*1000 + E*100 + N*10 + D
		solver.add(send.eq(s.mul(1000).sum(e.mul(100).sum(n.mul(10).sum(d)))));
		
		// MORE = M*1000 + O*100 + R*10 + E
		solver.add(more.eq(m.mul(1000).sum(o.mul(100).sum(r.mul(10).sum(e)))));
		
		// MONEY = M*10000 + O*1000 + N*100 + E*10 + Y
		solver.add(money.eq(m.mul(10000).sum(o.mul(1000).sum(n.mul(100).sum(e.mul(10).sum(y))))));
		
		// MONEY = SEND + MORE.
		solver.add(money.eq(send.sum(more)));
		
		// Labeling on all the variables
		solver.add(s.label());
		solver.add(e.label());
		solver.add(n.label());
		solver.add(d.label());
		solver.add(m.label());
		solver.add(o.label());
		solver.add(r.label());
		solver.add(y.label());
		
		// Try to find a solution
		solver.solve();
		
		System.out.println("SEND => " + s + e + n + d + " +");
		System.out.println("MORE => " + m + o + r + e + " =");
		System.out.println("MONEY => " + m + o + n + e + y);
		
      }
}
