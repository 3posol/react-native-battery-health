import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { health } from 'react-native-battery-health';

export default function App() {
  const [result, setResult] = React.useState();

  React.useEffect(() => {
    health().then(setResult);
  }, []);

  console.log(result);
  if (result) {
    return (
      <View style={styles.container}>
        <Text>Battery condition: {result?.health}</Text>
        <Text>Temprature: {result?.temperature}</Text>
      </View>
    );
  } else {
    return (
      <View style={styles.container}>
        <Text>Getting info...</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
