/* Initial beliefs and rules */

/* Initial goals */
!start.

/* Plans */
+abroad(no)[source(X)] <- 
	.print("Thanks ",X);
	.print("Computer, Lieutenant Commander Spock, First Officer").
	
+abroad(yes)[source(X)] <-
	.print("Waiting for transport...").

+!start <-
	.random(R); .wait(3000*R);
	.broadcast(askOne,abroad(Status)).
	
