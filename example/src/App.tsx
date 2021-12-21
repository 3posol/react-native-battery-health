import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { health } from 'react-native-battery-health';

export default function App() {
  const [result, setResult] = React.useState();

  React.useEffect(() => {
    health().then((res: any) => {
      console.log('res', JSON.stringify(res, null, 4));
      setResult(res);
    });
  }, []); 
  if (result) {
    return (
      <View style={styles.container}>
        <Text>Battery health -  { result.health }</Text>
        <Text>Battery temperature - { result.temperature }</Text>
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
