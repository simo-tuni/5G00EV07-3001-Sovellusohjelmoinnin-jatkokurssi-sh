import QtQuick 2.12
import QtQuick.Window 2.12

Window {
    width: 1024
    height: 512
    visible: true
    title: qsTr("Qt_Final_Exercise")
    Dashboard {
        id: dashboard
        //speed_number: 0
        //rpm_number: 0
        speed_number: dashboardData.speed
        rpm_number: dashboardData.rpm
        gasIconOpacity: 0
        checkEngineLightOpacity: 0
        anchors.fill: parent
    }

    EngineData {
        id: speedEngine
    }
    Connections {
        target: speedEngine
        onRpmReady: {
            console.log("rpm:" + String(rpm))
            dashboard.rpm_number = rpm
            speedEngine.generateSpeed()
        }
        onSpeedReady:{
            console.log("speed: " + String(speed))
            dashboard.speed_number = speed
        }
        onOpaciReady: {
            dashboard.gasIconOpacity = 100
        }
        onCheckEngineOpacityReady: {
            dashboard.checkEngineLightOpacity = 100
        }
    }

    MouseArea {
        anchors.fill: parent
        Timer {
              id: generateRpmTimer
              interval: 100
              repeat: false
              running: false
              onTriggered: {
                   speedEngine.generateRpm()
              }
          }
        Timer {
              id: reduceRpmTimer
              interval: 100
              repeat: false
              running: false
              onTriggered: {
                   speedEngine.reduceRpm()
              }
          }
        onPressedChanged: {
            if ( pressed ) {
                generateRpmTimer.running = true;
                generateRpmTimer.repeat = true
                reduceRpmTimer.running = false;
                reduceRpmTimer.repeat = false
            } else {
                generateRpmTimer.running = false;
                generateRpmTimer.repeat = false
                reduceRpmTimer.running = true;
                reduceRpmTimer.repeat = true
            }
        }
        /*
        onClicked: {
            speedEngine.generateRpm()
        }
        */
    }
}
