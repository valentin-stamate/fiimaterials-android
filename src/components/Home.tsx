import React from "react";
import {View, StyleSheet, ScrollView} from "react-native";
import {Text} from "react-native-elements"
import {componentStyle, fontStyle} from "../style/components";

const Home = () => {
    return (
        <View style={componentStyle.view}>

            <View style={componentStyle.header}>

            </View>

            <View style={componentStyle.body}>
                <Text style={[fontStyle.title, fontStyle.bold]}>Introduction</Text>
            </View>

            <ScrollView horizontal accessibilityElementsHidden showsHorizontalScrollIndicator={false}>
                <View style={selfStyle.introComponent}>
                    <View style={selfStyle.introCard}></View>
                    <View style={selfStyle.introCard}></View>
                    <View style={selfStyle.introCard}></View>
                </View>
            </ScrollView>

            <View style={bottomNavigation.component}>

            </View>

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
        backgroundColor: '#F0F2FE',
        borderRadius: 16,
        marginLeft: 8,
        marginRight: 8,
    }

});

const bottomNavigation = StyleSheet.create({
    component: {
        height: 64,
        alignSelf: "stretch",
        backgroundColor: '#f8f8f8',
    }
});

export default Home;