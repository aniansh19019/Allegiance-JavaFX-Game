# Allegiance - A JavaFX Color Switch Clone!

## Overview

The game is a clone of the famous Color Switch game available on iOS and Android. Instead of the original game graphics though, we decided to theme the game based on
classic space shooter games. We have a ship in place of the ball and several other objects as obstacles in the game. 

## Gameplay

The player moves the ship upwards by pressing <kbd>space</kbd> and the player can shoot bullets(collected in the game through power ups) using the <kbd>X</kbd> key.

The ship can change color by picking up the **color switcher** power ups as we progress through the game and a ship can only pass through an obstacle if both the obstacle and the ship are of the same color, otherwise the ship crashes into the obstacle.
<br>
<br>
The player also collects **stars** in the game which count towards the player's score and also help revive the ship in case the ship is destroyed.
<br>
We also have a number of power ups to be collected by the player during gameplay:
<br>
- **Bullet** - Adds one normal bullet to the ammo clip. A normal bullet can be fired at an obstacle to destroy the obstacle on impact.
- **Time Bullet** - Adds one time bullet to the ammo clip. A time bullet can be fired at an obstacle to slow the obstacle down on impact.
- **Shield Power Up** - Spawns a protective shield around the player's ship for 10 seconds. When an obstacle comes into contact with the ship and is not of the same color as the ship, instead of destroying the ship, the obstacle is instead destroyed.
- **Time Power Up** - Slows down the time by half for everything in the game except the player's ship for 10 seconds.
<br>
The player can also pause the game and we also have the option of saving the game to load later from that very moment in the game.
<br>
Here's a demo video showing the gameplay:
https://youtu.be/xKSWd4SBy-U

## Other Functionality

Some more features apart from the gameplay are:

- **Save/Load Game** - The player can save the game at any point by pausing and using the save option. The player can then use the Load Game option in the main menu to load from the 10 most recent saved games. The game starts at exactly the moment where the game was saved by the player.
- **Leaderboard** - All scores are recored in the game against the names entered by different players while starting a new game.
- **Sound Setting** - The user can also toggle gamewide sounds as per their preference.
- **Ship Choice** - The player can choose from 4 different ships to play the game.


## Design Patterns Identified

The following design patterns have been employed in the Game:

- Strategy: In adding extra features to obstacles by making subclasses of abstract class Obstacle and in setting different animations for different power up collections.
- Facade: Used to simplify Menu classes by making classes of menu items separately.
- Factory: Used to generate random obstacles and power ups as the game progresses.
- Decorator: Used to wrap the main SinglePlayerGame class to implement the restart functionality.
- Composite: Used to make new obstacles having two separate obstacles working in coordination.

## Screenshots
<p float = "left" align = "center">
	<img src = "/screenshots/1.png" width = "35%" height="35%">
	<img src = "/screenshots/2.png" width = "35%" height="35%">
</p>
<br>
<p float = "left" align = "center">
	<img src = "/screenshots/3.png" width = "35%" height="35%">
	<img src = "/screenshots/4.png" width = "35%" height="35%">
</p>
<p float = "left" align = "center">
	<img src = "/screenshots/5.png" width = "35%" height="35%">
	<img src = "/screenshots/6.png" width = "35%" height="35%">
</p>
<p float = "left" align = "center">
	<img src = "/screenshots/7.png" width = "35%" height="35%">
	<img src = "/screenshots/8.png" width = "35%" height="35%">
</p>
<p float = "left" align = "center">
	<img src = "/screenshots/9.png" width = "35%" height="35%">
	<img src = "/screenshots/10.png" width = "35%" height="35%">
</p>
<p float = "left" align = "center">
	<img src = "/screenshots/11.png" width = "35%" height="35%">
	<img src = "/screenshots/12.png" width = "35%" height="35%">
</p>
