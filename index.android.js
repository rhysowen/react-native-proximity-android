import {
  DeviceEventEmitter,
  NativeModules,
} from 'react-native';

const EVENT = 'OnSensorChange';

const nativeModule = NativeModules.ProximityAndroid;

const startListener = (callback) => {
  nativeModule.startListener();
  DeviceEventEmitter.addListener(EVENT, e => callback(e));
};

const stopListener = (listener) => {
  nativeModule.stopListener();
  DeviceEventEmitter.removeAllListeners(EVENT, listener);
}

module.exports = {
  startListener,
  stopListener,
};
