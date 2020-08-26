import React from "react";
import {Image, StyleSheet, TouchableHighlight, View} from "react-native";

const BottomNavigation = (props: any) => {
    return (
        <View style={selfStyle.component}>
            <TouchableHighlight onPress={() => props.navigation.navigate('Materials')}>
                <Image style={selfStyle.icon} source={require('../assets/mega-icon.svg')}/>
            </TouchableHighlight>
        </View>
    );
};

const selfStyle = StyleSheet.create({
    component: {
        flexDirection: 'row',
        justifyContent: 'space-around',
        alignSelf: "stretch",
        padding: 8,
    },
    icon: {
        width: 42,
        height: 42,
        backgroundColor: '#c8c8c8'
    },
});

export default BottomNavigation;