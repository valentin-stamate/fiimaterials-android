import {StyleSheet} from 'react-native';

export const componentStyle = StyleSheet.create({
    view: {
        flex: 1,
        alignItems: 'flex-start',
        justifyContent: 'flex-start',
        backgroundColor: "#fff"
    },
    container: {
        flex: 1,
        backgroundColor: '#fff',
    },
    header: {
        alignSelf: "stretch",
        height: 400,
    },
    body: {
        alignSelf: 'stretch',
        padding: 24,
    },
});

export const fontStyle = StyleSheet.create({
    title: {
        fontSize: 24,
        color: '#1D285D',
    },
    bold: {
        fontWeight: 'bold',
    }
});

export const cardRadius = 16;
export const elementStyle = StyleSheet.create({
    card: {
        borderRadius: cardRadius
    },
    button: {
        borderRadius: 4,
        backgroundColor: '#006064',
    },
});
