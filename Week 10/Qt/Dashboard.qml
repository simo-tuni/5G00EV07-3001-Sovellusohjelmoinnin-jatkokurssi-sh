import QtQuick 2.0

Rectangle {
    property int speed_number: 0
    property int rpm_number: 0
    property int gasIconOpacity: 0
    property int checkEngineLightOpacity: 0

    color: "black"
    Image{
        id: kmh
        source: "kmh.png"
        anchors.left: parent.left
        width: 512
        height: 512
        Image {
           id: kmhneedle
           parent:kmh
           source: "needle2.png"
           width: 20
           height: 180
           transformOrigin: Item.Bottom
           rotation: -135 + speed_number
           anchors.horizontalCenter: parent.horizontalCenter
           anchors.verticalCenter: parent.verticalCenter
           anchors.verticalCenterOffset: -0.5 * height
           Behavior on rotation {
               PropertyAnimation {
                   duration: 3000
               }
           }
        }
    }
    Image {
        id: rpm
        source: "rpm.png"
        anchors.right: parent.right
        width: 512
        height: 512
        Image{
           id: rpmneedle
           parent:rpm
           source: "needle3.png"
           width: 20
           height: 180
           transformOrigin: Item.Bottom
           rotation: -122 + (rpm_number/32)
           anchors.horizontalCenter: parent.horizontalCenter
           anchors.verticalCenter: parent.verticalCenter
           anchors.verticalCenterOffset: -0.5 * height
           Behavior on rotation {
               PropertyAnimation {
                   duration: 3000
               }
           }
        }
    }
    Rectangle {
        id: gasIcon
        opacity: gasIconOpacity
        color: "red"
        width: 50
        height: 50
        z: 1
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter
        Text {
            text: "Low\nGas!"
            font.family: "Helvetica"
            font.pointSize: 14
            color: "Blue"
        }

    }
    Rectangle {
        id: checkEngineLight
        opacity: checkEngineLightOpacity
        color: "red"
        width: 75
        height: 50
        z: 1
        anchors.horizontalCenter: parent.horizontalCenter
        anchors.verticalCenter: parent.verticalCenter+100
        Text {
            text: "Check\nEngine!"
            font.family: "Helvetica"
            font.pointSize: 14
            color: "Blue"
        }

    }

    /*
    MouseArea {
        anchors.fill: rpm
        onPressed: {
            console.log("pressed")
            rpmneedle.rotation = 80
        }
        onReleased: {
            console.log("released")
            rpmneedle.rotation = -122
        }
    }
    */
}
