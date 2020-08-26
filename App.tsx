import React from 'react';
import {View} from 'react-native';
import 'react-native-gesture-handler';
import {NavigationContainer} from '@react-navigation/native';
import {createStackNavigator} from '@react-navigation/stack';
import {componentStyle} from './src/style/components';
import Home from "./src/components/Home";
import Materials from "./src/components/Materials";


const Stack = createStackNavigator();

function App(props: any) {


    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Home">
                <Stack.Screen name={'Home'} options={{headerShown: false}} component={Home}/>
                <Stack.Screen name={'Materials'} options={{headerShown: false}} component={Materials}/>
            </Stack.Navigator>
        </NavigationContainer>
    );
}

export default App;