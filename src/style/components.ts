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
        alignItems: 'center',
        justifyContent: 'center',
    },
    button: {
        borderRadius: 4,
        backgroundColor: '#006064',
    },
    header: {
        alignSelf: "stretch",
        height: 400,
        backgroundColor: '#F0F2FE',
        borderBottomRightRadius: 64,
    },
    body: {
        padding: 24,
        paddingBottom: 0,
    },
});

export const fontStyle = {
    title: {
        fontSize: 24,
        color: '#1D285D',
    },
    bold: {
        fontWeight: 'bold',
    }
}
