import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-battery-health' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const BatteryHealth = NativeModules.BatteryHealth
  ? NativeModules.BatteryHealth
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function health(): IBHealth {
  return BatteryHealth.getHealth();
}

export interface IBHealth {
  health: number | string;
  temperature: number;
  voltage: number;
  capacity: number;
}
