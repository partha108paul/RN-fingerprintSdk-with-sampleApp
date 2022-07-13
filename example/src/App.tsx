import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
// import { StyleSheet, Button, View, Text, NativeModules } from 'react-native';
import { processBureauTest } from 'react-native-fingerprint';
// import { multiply, processBureauTest } from 'react-native-fingerprint';

export default function App() {
  // const [result, setResult] = React.useState<number | undefined>();
  const [result, setResult] = React.useState<string | undefined>();

  React.useEffect(() => {
    // multiply(3, 7).then(setResult);
    processBureauTest(
      'e87cdd41-fbf3-4041-bf77-d248972204b0',
      '34645c68-1da5-4e25-9915-75459-58927fc',
      'Partha',
      ''
    ).then(setResult);
  }, []);

  return (
    <View style={styles.container}>
      {/* <Text>Result: </Text> */}
      <Text>Result: {result}</Text>

      {/* <Button
        title="Submit Data"
        onPress={() => NativeModules.Fingerprint.submitData()}
      /> */}
    </View>
  );
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
