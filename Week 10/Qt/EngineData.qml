import QtQuick 2.0

Item {
    signal rpmReady(int rpm);
    property int speed: 0;
    signal speedReady(int speed);
    property int opaci: 0
    signal opaciReady(int opaci)
    property int checkEngineOpacity: 0
    signal checkEngineOpacityReady(int checkEngineOpacity)

    function generateRpm(){
        //rpm = (Math.random() * 8000);
        //dashboarddata.setRpm(dashboarddata.rpm + 100)
        //dashboardData.setRpm(dashboardData.rpm + 100)
        dashboardData.rpm = dashboardData.rpm + 100;
        //rpm = rpm + 100
        if(dashboardData.rpm > 5000){
            opaciReady(100)
            if(dashboardData.rpm > 8500){
                checkEngineOpacityReady(checkEngineOpacity)
            }
        }
        rpmReady(dashboardData.rpm);
        generateSpeed()
    }
    function reduceRpm(){
        if((dashboardData.rpm - 100) <= 0){
            console.log("rpm 0")
            dashboardData.rpm = 0
            reduceRpmTimer.running = false;
            reduceRpmTimer.repeat = false
        }
        else {
            console.log("reducing rpm")
            dashboardData.rpm = dashboardData.rpm - 100
        }
        rpmReady(dashboardData.rpm);
        generateSpeed()
    }
    function generateSpeed(){
        speed = (dashboardData.rpm / 36);
        speedReady(speed);
    }
}
