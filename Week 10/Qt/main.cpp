#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>
#include "dashboarddata.h"

int main(int argc, char *argv[])
{
    QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);

    QGuiApplication app(argc, argv);


    QQmlApplicationEngine engine;
    const QUrl url(QStringLiteral("qrc:/main.qml"));
    QObject::connect(&engine, &QQmlApplicationEngine::objectCreated,
                     &app, [url](QObject *obj, const QUrl &objUrl) {
        if (!obj && url == objUrl)
            QCoreApplication::exit(-1);
    }, Qt::QueuedConnection);
    engine.load(url);



    QQmlContext* qmlContext = engine.rootContext();
    DashboardData* dashboardData = new DashboardData{};
    qmlContext->setContextProperty("dashboardData", dashboardData);

    // Tuodaan DashboardData QML -maailman käyttöön elementiksi
    qmlRegisterType<DashboardData>("CustomComponents", 1, 0, "DashboardData");

    return app.exec();
}
