const MB = 1024 * 1024;

export default {
    // server: process.env.NODE_ENV === 'development' ? '//192.168.1.101:9200' : '',
    server: process.env.NODE_ENV === 'development' ? '//182.139.58.165:9200' : '',

    maxImageSize: MB * 3,
    maxBackgroundImageSize: MB * 5,
    maxAvatarSize: MB * 1.5,

    // client default system setting
    primaryColor: '74, 144, 226',
    primaryTextColor: '247, 247, 247',
    // backgroundImage: require('@/assets/images/background.jpg'),
    sound: 'default',
};
