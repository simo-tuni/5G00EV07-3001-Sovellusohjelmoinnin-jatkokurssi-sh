import * as React from 'react';
import {Button, View, Text} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import DeviceInfo, {getBatteryLevel} from 'react-native-device-info';

export default function HomeScreen({navigation}) {
  let [battery, setBattery] = React.useState(50);

  DeviceInfo.getBatteryLevel().then(batteryLevel => {
    setBattery(batteryLevel);
  });

  return (
    <View
      style={{
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
      }}>
      <Text
        style={{
          padding: 10,
        }}>
        Welcome, begin search by pressing the button below.
      </Text>
      <Button
        title="Show Standard League currencies"
        onPress={() => navigation.navigate('SecondScreen')}
      />

      <Text
        style={{
          padding: 100,
        }}>
        Battery: {(Math.round(battery * 100) / 100) * 100}%
      </Text>
    </View>
  );
}
