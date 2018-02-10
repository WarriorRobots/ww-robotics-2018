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
- driver override in case of jam
- requires((Subsystem) this.drivetrain)
- reverse talon ids
- merge drivetrain subsystem changes in
- change autonomodrivestraight int to double