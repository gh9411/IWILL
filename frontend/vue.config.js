module.exports = {
  devServer: {
    overlay: false,
  },
  css: {
    loaderOptions: {
      css: {
        sourceMap: true,
      },
    },
  },
  transpileDependencies: ["vuetify"],
};
