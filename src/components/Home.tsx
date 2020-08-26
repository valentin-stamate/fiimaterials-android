import React from "react";
import {ScrollView, StyleSheet, View} from "react-native";
import {Button, Text} from "react-native-elements"
import {componentStyle, elementStyle, fontStyle} from "../style/components";
import BottomNavigation from "./BottomNavigation";
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import {NavigationContainer} from "@react-navigation/native";

const Tab = createBottomTabNavigator();

const Home = (props: any) => {
    return (
        <View style={componentStyle.view}>

            <View style={[componentStyle.header, selfStyle.header]}>

            </View>

            <View style={componentStyle.body}>
                <Text style={[fontStyle.title, fontStyle.bold]}>Introduction</Text>
            </View>

            <ScrollView horizontal accessibilityElementsHidden showsHorizontalScrollIndicator={false}>
                <View style={selfStyle.introComponent}>
                    <View style={[selfStyle.introCard, elementStyle.card]}></View>
                    <View style={[selfStyle.introCard, elementStyle.card]}></View>
                    <View style={[selfStyle.introCard, elementStyle.card]}></View>
                </View>
            </ScrollView>

            <BottomNavigation navigation={props.navigation} />

        </View>
    );
}

const selfStyle = StyleSheet.create({
    introComponent: {
        flexDirection: 'row',
        marginTop: 12,
        paddingLeft: 12,
        paddingRight: 12,
    },
    introCard: {
        height: 180,
        width: 160,
        marginLeft: 8,
        marginRight: 8,
        backgroundColor: '#F0F2FE',
    },
    header: {
        backgroundColor: '#F0F2FE',
    },

});

export default Home;