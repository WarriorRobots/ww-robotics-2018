// Define UI elements


let ui = {
    timer: document.getElementById('timer'),
    robotState: document.getElementById('robot-state').firstChild,
    gyro: {
        container: document.getElementById('gyro'),
        val: 0,
        offset: 0,
        visualVal: 0,
        arm: document.getElementById('gyro-arm'),
        number: document.getElementById('gyro-number')
    },
    auto:{
        auto_display: document.getElementById('auto_display'),
        button: document.getElementById('auto_button')
    },
    fms:{
        display: document.getElementById('fms_display');
    },
    shooter:{
        speedtxt: document.getElementById('sht_spd_txt')
    }
    pid:{
        p_display: document.getElementById('Ptxt'),
        i_display: document.getElementById('Itxt'),
        d_display: document.getElementById('Dtxt')
    }
};

// Key Listeners

// Gyro rotation
let updateGyro = (key, value) => {
    ui.gyro.val = value;
    ui.gyro.visualVal = Math.floor(ui.gyro.val - ui.gyro.offset);
    ui.gyro.visualVal %= 360;
    if (ui.gyro.visualVal < 0) {
        ui.gyro.visualVal += 360;
    }
    ui.gyro.arm.style.transform = `rotate(${ui.gyro.visualVal}deg)`;
    ui.gyro.number.innerHTML = ui.gyro.visualVal + 'º';
};
NetworkTables.addKeyListener('/SmartDashboard/angle', updateGyro);

let getInfared = (key, value) =>}{
    theState = value;
    console.log(value);
}
NetworkTables.addKeyListner('/SmartDashboard/fmsresult', (key, value) =>{
    ui.fms.fms_display.innerHTML = value;
});

NetworkTables.addKeyListener('/robot/time', (key, value) => {
    // This is an example of how a dashboard could display the remaining time in a match.
    // We assume here that value is an integer representing the number of seconds left.
    ui.timer.innerHTML = value < 0 ? '0:00' : Math.floor(value / 60) + ':' + (value % 60 < 10 ? '0' : '') + value % 60;
});

NetworkTables.addKeyListener('/SmartDashboard/speed', (key, value)=>{
        ui.shooter.speedtxt.innherHTML = value + "ft/s";
});


ui.auto.button.onclick = function() {
    // Set NetworkTables values to the opposite of whether button has active class.
    var chosenAuto;
var radios = document.getElementsByName('auto-type');

for (var i = 0, length = radios.length; i < length; i++)
{
 if (radios[i].checked)
 {
  // do whatever you want with the checked radio
  chosenAuto = radios[i].value;

  // only one radio can be logically checked, don't check the rest
  break;
 }
}
    ui.auto.auto_display.innherhtml = chosenAuto;
    NetworkTables.putValue('/SmartDashboard/auto_selected', chosenAuto);
};

// Reset gyro value to 0 on click
ui.gyro.container.onclick = function() {
    // Store previous gyro val, will now be subtracted from val for callibration
    ui.gyro.offset = ui.gyro.val;
    // Trigger the gyro to recalculate value.
    updateGyro('/SmartDashboard/angle', ui.gyro.val);
};

addEventListener('error',(ev)=>{
    ipc.send('windowError',{mesg:ev.message,file:ev.filename,lineNumber:ev.lineno})
})
