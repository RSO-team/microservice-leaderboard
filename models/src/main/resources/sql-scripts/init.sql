insert into leaderboard (name,description) VALUES ('best of 3','This category is best of 3');
insert into leaderboard (name,description) VALUES ('best of 5','This category is best of 5');
insert into leaderboard (name,description) VALUES ('best of 7','This category is best of 7');
insert into leaderboard (name,description) VALUES ('best of 10','This category is best of 10');

insert into score (userid,score,leaderboard_id) VALUES (1,10,1);
insert into score  (userid,score,leaderboard_id)VALUES (2,50,1);
insert into score  (userid,score,leaderboard_id)VALUES (3,70,1);
insert into score  (userid,score,leaderboard_id)VALUES (4,80,1);

insert into score  (userid,score,leaderboard_id)VALUES (1,10,2);
insert into score  (userid,score,leaderboard_id)VALUES (2,50,2);
insert into score  (userid,score,leaderboard_id)VALUES (3,70,2);
insert into score  (userid,score,leaderboard_id)VALUES (4,80,2);

insert into score  (userid,score,leaderboard_id)VALUES (1,10,3);
insert into score  (userid,score,leaderboard_id)VALUES (2,50,3);
insert into score  (userid,score,leaderboard_id)VALUES (3,70,4);
insert into score  (userid,score,leaderboard_id)VALUES (4,80,4);
