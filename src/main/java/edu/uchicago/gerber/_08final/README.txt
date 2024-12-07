Here are some instructionsn of my game and how it works:

Check turbo.mvc files and within in the controller, model and view folders

So my game is modeled after the 1981 game Turbo where there is a target amount of cars and you have to pass the cars in
order to accumulate points which determine whether you pass the level or not. You use the arrow keys on the keyboard to
accelerate your car forward and steer them past the other cars, trying to achieve the highest score before the timer
runs out. The level system works by incrementing how many more cars you need to pass by 5 everytime you clear a level
and if you fail you got back to the default amount at 25 cars

Key components coded:

So the biggest challenger in this project was transforming the free flying space in which asteroids was set in into a
continous racing game where cars would randomly pop up and act as obstacles.

The first major components were changing  user car and the enemy car (asteroids) to simulate this driving feeling.
When playing through asteroids I really honed  in on the "center" game modes where the user is in fixed position
while everything moves relative to your movement and the level-ending aspect where there are no asteroids on the screen
to spawn new ones in to simulate a level change. Using these aspects I was able to design methods which can control
where the asteroids spawn once the level increases and change the movement in the asteroid class making their movement
the opposite of the users simulating the user having to dodge them, disregarding the leveling up process.

Next was creating the raceway for the racers to be on. The raceway was made using continousing vertical segments that
cover the entire screen and is first initialized to cover the whole screen. Then we adjust how they move in the raceway
class and similiar to the enemycars, spawns another segment once another one goes off the screen.

Behind that I created a Background class to handle collisions and to make sure the enemycars and usercar stay inside the
raceway. Creating the class Raceway and Background and using the collision handling (rectangle and contains) to have
appropriate actions if a part of the user/enemy cars go off track.

For game logic, I implemented a Gametimer where the game will only end once the timer goes down. And to determine if the
user passes, the number of cars passed is then counted and then a new game is initilzed withe a new target to achieve to
simulate what the 1981 turbo game does. There are also some added on floaters such as the Shikanoko (japanese for deer)
and timer where if you hit them they either spawn a huge deer obstructing your view, or increase your time to get a
better score.


