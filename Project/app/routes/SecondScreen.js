import * as React from 'react';
import {
  Alert,
  Image,
  FlatList,
  StyleSheet,
  Button,
  View,
  Text,
} from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import NetInfo from '@react-native-community/netinfo';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 22,
  },
  tinyLogo: {
    width: 50,
    height: 50,
  },
  item: {
    padding: 10,
    fontSize: 18,
    height: 44,
  },
});

export default function SecondScreen({navigation}) {
  const netInfo = NetInfo.useNetInfo();
  let [curr, setCurr] = React.useState([]);

  React.useEffect(() => {
    const getCurrencyFromApi = async () => {
      try {
        const response = await fetch(
          'https://poe.ninja/api/data/CurrencyOverview?league=Standard&type=Currency',
        );
        const json = await response.json();
        let tmp = [];
        for (let item of json.lines) {
          let tmp = {
            name: item.currencyTypeName,
            price: item.chaosEquivalent,
          };
          setCurr(curr => [...curr, tmp]);
        }
      } catch (error) {
        console.error(error);
      }
    };

    return NetInfo.addEventListener(state => {
      if (!state.isInternetReachable) {
        Alert.alert('You are offline!');
      } else {
        getCurrencyFromApi();
      }
    });
  }, []);

  return (
    <View style={styles.container}>
      <Button
        title="Back to Home Screen"
        onPress={() => navigation.navigate('HomeScreen')}
      />
      <FlatList
        data={curr}
        renderItem={({item}) => (
          <Text style={styles.item}>
            Name: {item.name}, Price: {item.price} c
          </Text>
        )}
      />
    </View>
  );
}
