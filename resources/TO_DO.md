### To-Do (sorted by priority)

- pid on shooter
- find an alternative to Autonomous SendableChooser because we need to use dashboard variables
- auto encoder measurement conversion
- change autoDrives to compensate for encoder failure
- make all util classes getselected
- add a reset button that does Scheduler.getInstance().removeAll() and sets all motors to 0
- make driving easier (lower gains, cheesy algos?)
- remove all @debug variables right before comp
- exponential turning in tank drive
- Control Mode switcher: controls all run from XboxController at press of a button

---

### ERRORS

- robot using average of two encoders will drive at double distance
- navx failure on practice bots 2/12 5pm
- talon ids are changing (2/27)

### HOW TO DEBUG THE ROBOT

- DO THIS BEFORE COMMITTING PULL REQUESTS
- open console, connect to robot, check that there are no errors or java stacktraces
- enable teleop, drive with all possible teleop commands
- enable autonomous, check that autonomous selector works and that commands run