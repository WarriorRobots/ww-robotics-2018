# FRC 2018

Westwood Warriors #2478 robot code for the 2018 FRC game, Power Up.

This code uses [GradleRIO](https://github.com/Open-RIO/GradleRIO) as a build system. See below for instructions.

## How to Set Up a New Computer

After installing the necessary programs from the flash drive in the build room, open up **Eclipse** and use the default workspace that you are provided at `C:\\Users\<YOUR-USERNAME>\eclipse-workspace\`.

Open up **Git Bash** and run the command `cd ~/eclipse-workspace/`, then run

`git clone https://github.com/falsephoenix/ww-robotics-2018.git`.

---

Make sure you are connected to the internet before running these next steps.

Find **Windows Powershell** in the Start Menu and run the command `cd ~/eclipse-workspace/ww-robotics-2018`, then `./gradlew build`.

If you see the words "BUILD SUCCESSFUL" after the command finishes, run `./gradlew eclipse`.

If successful, you should be ready to open the project in Eclipse and begin work!

---

Need a place to upload a miscellaneous Eclipse project? Try [here](https://github.com/falsephoenix/ww-misc-2018).