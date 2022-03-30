
# Volleyball Game App

## Introduction

I am a huge fan of volleyball, even if I don't play on any serious level myself. 
I have always considered volleyball to be simple but after doing research, I found 
the complex strategies to be fascinating. 
Therefore, this game requires some level of volleyball strategy and intuition, as well as 
a bit of luck. </br>
In this game, players will be able to simulate a simplified version of volleyball. 
Users can choose their own teams with their own teams with players of varying traits as well as
how the team will attack or defend their court. 
They will play against a few already made bots which have their own level of difficulty 
and playing tendencies. 
Users who want a game that is tedious, challenging but rewarding game will enjoy this game. 

## How the game should work
(note: any changes to the game's rules have been crossed out. This is mostly due to time constraints)
### Rules: 
Because this is a 2D game, some aspects of the original game's rules will have to be modified. 
The following rules will be the ones to apply: </br> Note: This is a console game for now.
- Once the ball touches on one side's court, the rally ends with the opposite court earning a point
- The game ends once one team reaches 15 points and the difference between the two scores are ≥ 2. 
  - If the second condition is not met, the rally continues until there it is met. 
- ~~If a ball is blocked, it is instantly the blocker's point~~ Note: Will implement later on if possible
- ~~If a ball is hit out, the point goes to the opposite team.~~ 
- Once a player properly receives a ball after an opponent's attack, the ball goes to the designated setter
- The winner of the previous point will serve
- Players can only attack if they are in the front row
- Back row players may not block or attack. 
- Players will rotate one player clockwise ~~after scoring the first point in a row.~~ after every point scored by that team.
- Each starting lineup of players must have the following
  - 1 Setter
    - Setter may not ever be on the defence in the back row. However, they may block. 
  - 1 Opposite (right side) hitter
  - 2 Outside (left side) hitter
  - 2 Middle blockers


### Instructions
- Before each attack, users are given ~~10 seconds~~ to position each player to defend
  - The screen will show which opponent player(s) might be attacking the ball, shown with an arrow so the user can take a guess
  - Users will move their front players into a position to block the oncoming attack
  - Users will move their back players into a position to receive the ball if the block fails
  - The user will press defend or the time runs out and the attack starts
    - Note players won't move after their positions are confirmed. Must make sure that they are in the correct position
- After a successful receive, users are ~~given 10 seconds~~ to choose who to attack
  - Users will have the choice to attack with the outside hitter, middle blocker who are in front. The opposite hitter can also attack if they are in the front row.
  - After choosing a player, the user also chooses a direction to hit and power to hit. 
    - ~~more power means it will go farther but user must be careful not to hit it out~~



## User Stories

### Phase 1 user stories
- As a user, I should be able to create and add an arbitrary number of players to my teams
- As a user, I should be able to choose which opposing team I want to play
- As a user, I should be able to choose my starting 6 players
- As a user, I should be able to systematically arrange my player's positions before an enemy attack
- As a user, I should be able to decide which player I want to spike (attack) after a receive
- As a user, I want to see the scoreboard
- As a user, I want to see which enemies are potential attackers

### Phase 2 user stories
 
- As a user, I should be able to save my current match. 
  - This includes the score, rotations of the teams, players etc.
- As a user, I should be able to retrieve and continue playing saved matches.
  - This includes the score, rotations of the teams, players etc.


![](../../../Desktop/Screen Shot 2022-03-30 at 9.41.45 AM.png)
![](../../../Desktop/Screen Shot 2022-03-30 at 9.46.56 AM.png)