## Westwood FRC Wiring Guide (2017-2018)

<!-- Author: Alex Vuong -->
<!-- Date: 01/31/2018 -->

### Battery + Breaker

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/001/189/964/medium/cd04b5d8-d171-4259-87d6-d18a3bda2c8a.jpg)

- NEGATIVE LEAD goes to PDP
- POSITIVE LEAD goes to breaker
    + on side that reads "THERMAL CIRCUIT BREAKER"

### RoboRIO

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/000/289/903/original/b70ae894-e0c5-49d3-b8e2-024e76bd7c6c.png)

- 22gauge POWER CABLES to the left of USB port
    + POSITIVE goes in V
    + NEGATIVE goes in C
- terminate CAN CABLES to left of POWER CABLES
    + YELLOW goes in H
    + GREEN goes in L
- DIO ports (left side):
    + all boolean sensors go here (anything that has on & off state)
    + for example: infared sensors, limit switches
    + start plugging sensors in at 0, and count upwards
- PWM ports (right side):
    + all analog sensors go here (anything that has a range of outputs)
    + for example: ultrasonic rangefinders, potentiometers
    + start plugging sensors in at 0, and count upwards
- RSL port (left bottom):
    + \+ is red, other is black
- MXP, long port in center
    + NavX gyroscope goes here, centered in RoboRIO

### PDP

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/000/289/904/original/47968888-bef1-41ab-b81e-35e33bdd749c.png)

- 10-AMP FUSE top left
    + ROBORIO POWER goes here
- 20-AMP FUSE top right
    + VRM and PCM POWER goes here
- ports: 0 1 2 3 12 13 14 15
    + 40-AMP BREAKERS + Red&Black TALON POWER goes here
- ports: 4 5 6 7 8 9 10 11
    + 10-20AMP BREAKERS + CAMERA POWER goes here
- terminate CAN CABLES in bottom right
    + jumper switch must be in ON position

### VRM

![](https://s3.amazonaws.com/screensteps_live/image_assets/assets/000/289/906/original/b77b62ab-b9ca-4e03-9415-fdee3789af42.png?1483549210)

- POWER CABLES on top, connect to PDP
- 12V/2A
    + RADIO POWER (circular plug)

### PCM

![](http://slideplayer.com/slide/7346434/24/images/9/Pneumatic+Control+Module+(PCM).jpg)

- PCM POWER from PDP, left side
- include in CAN LOOP, top left
- PRESSURE METER POWER, bottom & first from left
- AIR COMPRESSOR POWER, bottom & second from left
- ports: 0 1 2 3 4 5 6 7
    + SOLENOID POWER goes here

### Radio (OM5P-AC)

![](https://www.broadbandbuyer.com/images/products/openmesh/om5p-ac-7.png?width=400)

<!-- - must be imaged before use, [download here](https://firstfrc.blob.core.windows.net/frc2018/Radio/FRC_Radio_Configuration_18_1_0.zip) -->
- RADIO POWER far left
- CAMERA ETHERNET middle (not required)
- ROBORIO ETHERNET far right

### Talon SRX

![](https://www.vexrobotics.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/2/1/217-4358-on-talon.jpg)

- TALON POWER is red and black, plug into 40-AMP PDP port
    + ***DO NOT PLUG INTO MOTORS***
- MOTOR POWER is green and white
- ENCODER DATA CABLE on top, covered by hex screws & plastic cover
- include in CAN LOOP

### Orange RSL Light

![](https://mililanirobotics.gitbooks.io/frc-electrical-bible/content/roboRIO/rsl.jpg)

- POWER CABLES from RoboRIO

---

### CAN LOOP instructions

- start at PDP CAN
- end at ROBORIO CAN
- all Talons and PCMs must be wired in between

### Wire Gauge guide

- 22 gauge: CAN LOOP, VRM POWER, PCM POWER, RADIO POWER, ROBORIO POWER, RSL POWER
- 16 gauge: TALON POWER, MOTOR POWER
- 6 gauge: BATTERY POWER, BREAKER POWER
