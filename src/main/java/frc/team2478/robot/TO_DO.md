- PID controller up and running
- drivetrain subsystem
- motion sensors subsystem
- autonomous subsystem
- timer object integrated into PID (pid.calculate()?)
- alignment mode
- controller lockmode
- xboxcontroller mode

---

- periodic timer
- testmode wait()
- use encoder.getRate() to check for missing (throw error in driver station)

---

- update RIO with lifeboat
- autonomoBase

---

electrical guide ppt
pressure sensors
cable relief stickybacks / loops
try/catch joysticks
modulus PID
DO NOT PUSH THE ROBOT
joystick / xbox control scheme switching 
cubekicker
set a mechanical deadband to disable P, and enable I
(justify this) name buttons according to function, not appearance (robotmap uses appearance)
https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html (robotmap subclasses)

can't use chooser because we need to change values on the fly
DEBUG autonomodrivestraight: failure on 0 (use scheduler.getinstance to dashboard)

method name shorteners