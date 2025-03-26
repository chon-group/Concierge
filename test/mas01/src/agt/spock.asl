/* Initial beliefs and rules */
kqml::bel_no_source_self(NS,Content,Ans)[hide_in_mind_inspector,source(self)] :- (NS::Content[|LA] & (kqml::clear_source_self(LA,NLA) & ((Content =.. [F,T,_29]) & (NS::Ans =.. [NS,F,T,NLA])))).
kqml::clear_source_self([],[])[hide_in_mind_inspector,source(self)].
kqml::clear_source_self([source(self)|T],NT)[hide_in_mind_inspector,source(self)] :- kqml::clear_source_self(T,NT).
kqml::clear_source_self([A|T],[A|NT])[hide_in_mind_inspector,source(self)] :- ((A \== source(self)) & kqml::clear_source_self(T,NT)).
abroad(yes)[source(kirk)].

/* Initial goals */

/* Plans */
@p__8[source(self),url("file:asl/spock.asl")] +abroad(no)[source(X)] <- .print("Thanks ",X); .print("Computer, Lieutenant Commander Spock, First Officer").
@p__9[source(self),url("file:asl/spock.asl")] +abroad(yes)[source(X)] <- .print("Waiting for transport...").
@p__10[source(self),url("file:asl/spock.asl")] +!start <- .random(R); .wait((3000*R)); .broadcast(askOne,abroad(Status)).

