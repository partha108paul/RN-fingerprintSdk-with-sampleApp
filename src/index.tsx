import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-fingerprint' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const Fingerprint = NativeModules.Fingerprint
  ? NativeModules.Fingerprint
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function multiply(a: number, b: number): Promise<number> {
  return Fingerprint.multiply(a, b);
  // return Fingerprint.submit
}

export function processBureauTest(
  clientId: String,
  sessionId: String,
  userId: String = '',
  flow: String = ''
  // ): Promise<string> {
) {
  return Fingerprint.processBureauTest(clientId, sessionId, userId, flow);
}
