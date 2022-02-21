#ifndef DASHBOARDDATA_H
#define DASHBOARDDATA_H

#include <QObject>

class DashboardData : public QObject
{
    Q_OBJECT
    Q_PROPERTY(int speed READ getSpeed WRITE setSpeed)
    Q_PROPERTY(int rpm READ getRpm WRITE setRpm)
public:
    DashboardData();

    QString getMessage();
    void setMessage( const QString message);

    int getSpeed();
    void setSpeed(int speed);

    int getRpm();
    void setRpm(int rpm);

    void start();

/*
signals:
    void speedChanged();
*/

private:
    QString m_message;
    int m_speed = 0;
    int m_rpm = 0;
    bool m_started = false;
};

#endif // DASHBOARDDATA_H
