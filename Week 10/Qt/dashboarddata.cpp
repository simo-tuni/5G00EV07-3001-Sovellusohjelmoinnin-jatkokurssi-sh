#include "dashboarddata.h"

DashboardData::DashboardData()
{

}
QString DashboardData::getMessage(){
    return m_message;
}
void DashboardData::setMessage( const QString message){
    m_message = message;
}

int DashboardData::getSpeed(){
    return m_speed;
}
void DashboardData::setSpeed(int speed){
    m_speed = speed;
}

int DashboardData::getRpm(){
    return m_rpm;
}
void DashboardData::setRpm(int rpm){
    m_rpm = rpm;
}

void DashboardData::start(){
    m_started = true;
    m_rpm = 1000;
}
