import {
  DeviceEventEmitter,
  NativeModules,
} from 'react-native';

const nativeModule = NativeModules.ProximityAndroid;

const start = (callback) => {
  nativeModule.startListener();
  DeviceEventEmitter.addListener('OnSensorChange', e => callback(e));
};

const stop = () => nativeModule.stopListener();

module.exports = {
  start,
  stop,
};
