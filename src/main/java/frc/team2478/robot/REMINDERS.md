### Encoder Wiring

- DIO ports, 2 per encoder
- first port: blue wire goes to outside, white wire to inside of RIO
- second port: black wire goes to inside of RIO, blank PWM goes to middle
- When holding Encoder so that pins are on bottom half:
	+ BLACK RED WHITE NONE BLUE wires
	
### Programming Advice

- if you are copying code, make a class or method for it!
- static fields are shared across all instantiations.
- final classes *cannot* be subclassed
- favor arcadeDriveAutonomous() instead of Tank Drive