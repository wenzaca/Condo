const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const HtmlWebpackPluginInstance = new HtmlWebpackPlugin({
    template: './index.html',
    filename: 'index.html',
    inject: 'body'
})

const webpackConfigurations = {
    context: __dirname,
    entry: `${__dirname}/src/bootstrap.js`,
    devtool: 'source-map',
    module: {
        loaders: [{
            test:/\.js$/,
            loader: 'babel-loader',
            exclude: /node_modules/,
            options: {
                presets: ['react', 'es2015','stage-1']
            }
        }]
    },
    output: {
        path: `${__dirname}/dist/`,
        filename: 'bundle.js'
    },
    plugins: [HtmlWebpackPluginInstance]
}


module.exports = webpackConfigurations;