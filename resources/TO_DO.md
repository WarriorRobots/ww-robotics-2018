### To-Do (sorted by priority)

- DEBUG AutonomoDriveStraight() to figure out what it does when encoders fail
- find an alternative to Autonomous SendableChooser because we need to use dashboard variables
- set a mechanical deadband to disable P, and enable I (alternatively set a minimum P value)
- map out electrical connections on cardboard sheets (remember to create cable relief loops)
- use encoder.getRate() to check for disconnected encoders, and throw error in driver station
- make an electrical build guide for the team
- write code to integrate pneumatic pressure sensors
- design a prototype to knock cubes onto their side (pneumatic piston hits)
- can't use chooser because we need to change values on the fly
- Control Mode switcher: controls all run from XboxController at press of a button

---

- PID 3 modes for shooter
	+ driver override in case of jam
- reverse talon ids
- merge drivetrain subsystem changes in
- change autonomodrivestraight int to double
- make the if statement better with var assignments
- debug statement for gyro (add a space)
- auto encoder measurement conversion
- exponential turning in tank drive

---

### ERRORS

- Motor Safety Handler
- gradlew socket is not established
- battery voltage enters brownout with new batteries
- inconsistent radio connections
	+ solution tested 2/10 12:31pm: change roborio to static ((failure))
	+ needs practice static ip
- robot lags behind during low voltage turn
- robot using average of two encoders will drive at double distance
- navx failure on practice bots 2/12 5pm

### HOW TO DEBUG THE ROBOT

- DO THIS BEFORE COMMITTING PULL REQUESTS
- open console, connect to robot, check that there are no errors or java stacktraces
- enable teleop, drive with all possible teleop commands
- enable autonomous, check that autonomous selector works and that commands run