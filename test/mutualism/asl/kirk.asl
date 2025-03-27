/* Initial beliefs and rules */
abroad(yes). 
skyNet("skynet.chon.group",5500).
scott("07ba9e4a-d539-4a0e-8c14-4ac336476858").
myUUID("41ff1712-b2f0-416d-8232-fef834651e77").

/* Initial goals */
!conf.

/* Plans */
+!conf : abroad(yes) & myUUID(ID) & (skyNet(Server,Port)) & scott(Scotty) <- 
	.concierge.policy(all,all,all,drop);
	.concierge.guidance(all,all,Scotty,all,accept);
	.concierge.connectCN(Server, Port, ID);
	!start.
		
+!conf : abroad(no) <- 
	.print("Computer, Commander James T. Kirk, Enterprise's Captain").
	
+!start : (abroad(yes) & energizing[source(X)]) <- 
	.print("Transporter ready!"); 
	-energizing[source(X)]; 
	+ready; 
	.random(R); .wait(2000*R);
	!start.
	
+!start : (abroad(yes) & (ready & scott(UUID))) <- 
	-ready; 
	-abroad(yes); 
	.concierge.moveOut(UUID,mutualism,spock).
	
+!start : (abroad(yes) & scott(UUID)) <- 
	.print("Kirk to Enterprise..... Beam us up, Scotty!"); 
	.random(R); .wait(3000*R); 
	.concierge.sendOut(UUID,tell,beam_us_up_scotty); 
	!start.
	
+!start: abroad(no) <- 
	.print("Computer, Commander James T. Kirk, Enterprise's Captain").
	
+abroad(no)[source(Agt)] <-
	.print("Thanks ",Agt);
	.send(scott,unachieve,greeting);
	!start.
