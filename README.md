# react-native-proximity-android
An Android RN Bridge for Proximity Sensor

## Getting started

### Install library

    npm i react-native-proximity-android --save

### Link library

    rnpm link react-native-proximity-android

## Usage

### Import

```js
    import Proximity from 'react-native-proximity-android';
```

## Listeners & callback

```js
    const proximityCallback = (event) => {
      const { data } = event;
    }

    componentDidMount() {
      Proximity.startListener(proximityCallback);
    }

    componentWillUnmount() {
      Proximity.stopListener(proximityCallback);
    }
```