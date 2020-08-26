import React from "react";
import {View, Text, StyleSheet, ScrollView, SafeAreaView} from "react-native";
import {cardRadius, componentStyle, elementStyle, fontStyle} from "../style/components";
import {Button} from "react-native-elements";

const Materials = () => {
    return (
        <SafeAreaView style={componentStyle.container}>
            <ScrollView showsVerticalScrollIndicator={false}>

                <View style={[componentStyle.header, selfStyle.header]} />

                <View style={componentStyle.body}>
                    <Text style={[fontStyle.title, fontStyle.bold]}>Years</Text>

                    <View style={selfStyle.yearsContainer}>
                        <View style={selfStyle.yearCard}>
                            <View style={selfStyle.firstSemester}></View>
                            <View style={selfStyle.secondSemester}></View>
                        </View>

                        <View style={selfStyle.yearCard}>
                            <View style={selfStyle.firstSemester}></View>
                            <View style={selfStyle.secondSemester}></View>
                        </View>

                        <View style={selfStyle.yearCard}>
                            <View style={selfStyle.firstSemester}></View>
                            <View style={selfStyle.secondSemester}></View>
                        </View>

                        <View style={{flex: 1}} />

                        <Button  buttonStyle={selfStyle.moreButton} title="More..."/>

                    </View>

                    <Text style={[fontStyle.title, fontStyle.bold, {marginTop: 24}]}>Materials</Text>

                    <View >
                        <View style={[elementStyle.card, selfStyle.materialItemContainer]}/>
                        <View style={[elementStyle.card, selfStyle.materialItemContainer]}/>
                        <View style={[elementStyle.card, selfStyle.materialItemContainer]}/>
                    </View>

                </View>
            </ScrollView>
        </SafeAreaView>
    );
};

const selfStyle = StyleSheet.create({
    header: {
        backgroundColor: '#F0F2FE',
        borderBottomRightRadius: 64,
    },
    yearCard: {
        width: 75,
        height: 75,
        flexDirection: 'row',
        marginRight: 8,
    },
    firstSemester: {
        flex: 1,
        backgroundColor: '#444444',
        borderTopLeftRadius: cardRadius,
        borderBottomLeftRadius: cardRadius,

    },
    secondSemester: {
        flex: 1,
        backgroundColor: '#333333',
        borderTopRightRadius: cardRadius,
        borderBottomRightRadius: cardRadius,
    },
    yearsContainer: {
        flexDirection: 'row',
        alignSelf: "stretch",
        marginTop: 8,
    },
    moreButton: {
        width: 75,
        height: 75,
        borderRadius: cardRadius,
    },
    materialsContainer: {

    },
    materialItemContainer: {
        alignSelf: 'stretch',
        height: 64,
        backgroundColor: '#999999',
        marginTop: 12,
    },
});

export default Materials;