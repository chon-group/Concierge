/*beliefs*/
skyNet("10.0.3.9",5500). //More info: https://doi.org/10.5753/wei.2023.229753 
myUUID("07ba9e4a-d539-4a0e-8c14-4ac336476858").

/* initial plan */
!start.

/* plans */
+!start <-
	?myUUID(ID);
	?skyNet(Server,Port);
	.concierge.policy(all,all,all,accept);
	//.concierge.guidance(all,all,Scotty,all,accept);
	.hermes.configureContextNetConnection("skyNET", Server, Port, ID);
	.hermes.connect("skyNET");
	.print("Computer, Commander Montgomery Scott, Chief Engineering Office").

+beam_us_up_scotty[source(X)] <-
	.print("Transporter ready for ",X);
	.concierge.sendOut(X,tell,energizing);
	.random(R); .wait(3000*R);
	-beam_us_up_scotty[source(X)];
	!!greeting.

+!greeting <-
	.random(R); .wait(5000*R);
	.print("Welcome to Enterprise...");
	.broadcast(tell,abroad(no)).
