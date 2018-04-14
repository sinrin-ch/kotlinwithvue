/**
 * Created by sinrin on 2018/4/14.
 */
const path = require('path');
const glob = require('glob');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CommonsChunkPlugin = require("webpack/lib/optimize/CommonsChunkPlugin");
module.exports = {
    // Project deployment base
    // By default we assume your app will be deployed at the root of a domain,
    // e.g. https://www.my-app.com/
    // If your app is deployed at a sub-path, you will need to specify that
    // sub-path here. For example, if your app is deployed at
    // https://www.foobar.com/my-app/
    // then change this to '/my-app/'
    baseUrl: '/',
    // where to output built files
    // 打包后的输出目录
    outputDir: '../webapp/js',
    // whether to use eslint-loader for lint on save.
    // 保存时是不是用eslint-loader 来lint 代码
    lintOnSave: false,
    // use the full build with in-browser compiler?
    // https://vuejs.org/v2/guide/installation.html#Runtime-Compiler-vs-Runtime-only
    // 使用runtime-only 还是 in-browser compiller
    compiler: false,
    // tweak internal webpack configuration.
    // see https://github.com/vuejs/vue-cli/blob/dev/docs/webpack.md
    // webpack 配置~
    chainWebpack: (config) => {
        // 删除原来的 app入口 src/main.js
        config.entryPoints.delete('app')
        // 删除原来的html插件
        config.plugins.delete('html')
        config.plugins.delete('CommonsChunkPlugin')
    },
    configureWebpack: (config) => {
        const entries = getEntry('src/**/main.js', 'src');  // 入口是src目录下的所有main.js文件.
        // console.log(config.plugins)
        // console.log(entries)
        return {
            entry: entries,
            output: {
                path: path.resolve(__dirname, '../webapp/js'),
                publicPath: '/js/',
                filename: '[name]/build.js',
                // chunkFilename: '[id].chunk.js'   //chunk生成的配置
            },

            plugins: Object.keys(entries).map(function (name) {
                return new HtmlWebpackPlugin({
                    template: 'public/index.html',
                    filename: path.resolve(__dirname, `../webapp/WEB-INF/jsp/${name}/index.html`),
                    hash: true,
                    chunks: [name, 'manifest', 'vendor'],
                    // favicon: false,
                    minify: {
                        removeComments: false,
                        collapseWhitespace: false,
                        removeAttributeQuotes: false
                        // more options:
                        // https://github.com/kangax/html-minifier#options-quick-reference
                    },
                })
            })
        }
    },
    // vue-loader options
    // https://vue-loader.vuejs.org/en/options.html
    // vue-loader 配置
    vueLoader: {},
    // generate sourceMap for production build?
    // 生产环境的sourceMap 要不要？
    productionSourceMap: true,
    // CSS related options
    css: {
        // extract CSS in components into a single CSS file (only in production)
        extract: false,
        // enable CSS source maps?
        sourceMap: false,
        // pass custom options to pre-processor loaders. e.g. to pass options to
        // sass-loader, use { sass: { ... } }
        loaderOptions: {},
        // Enable CSS modules for all css / pre-processor files.
        // This option does not affect *.vue files.
        // 用不用 css Modules 啊？
        modules: false
    },
    // use thread-loader for babel & TS in production build
    // enabled by default if the machine has more than 1 cores
    // 使用多线程否？
    parallel: require('os').cpus().length > 1,
    // split vendors using autoDLLPlugin?
    // can also be an explicit Array of dependencies to include in the DLL chunk.
    // See https://github.com/vuejs/vue-cli/blob/dev/docs/cli-service.md#dll-mode
    // 用不用 autoDLLPlugin，厉害了
    dll: false,
    // options for the PWA plugin.
    // see https://github.com/vuejs/vue-cli/tree/dev/packages/%40vue/cli-plugin-pwa
    // pwa 相关
    pwa: {},
    // configure webpack-dev-server behavior
    // Webpack dev server
    devServer: {
        open: process.platform === 'darwin',
        host: '0.0.0.0',
        port: 8088,
        https: false,
        hotOnly: false,
        // See https://github.com/vuejs/vue-cli/blob/dev/docs/cli-service.md#configuring-proxy
        // proxy: null, // string | Object
        proxy: 'http://localhost:8080',
        before: app => {
        }
    },
    // options for 3rd party plugins
    pluginOptions: {
        // ...
    }
};

function getEntry(globPath, pathDir) {
    var files = glob.sync(globPath);
    var entries = {},
        entry, dirname, basename, pathname, extname;

    for (var i = 0; i < files.length; i++) {
        entry = files[i];
        dirname = path.dirname(entry);
        // extname = path.extname(entry);
        // basename = path.basename(entry, extname);
        pathname = path.join(dirname, basename || '');
        pathname = pathDir ? pathname.replace(new RegExp('^' + pathDir), '') : pathname;
        pathname = pathname || '/main';  // 根目录就用main代替
        pathname = pathname.replace(/\\/g, '/');  // 所有反斜杠src\page1\main.js变成斜杠src/page1/main.js
        pathname = pathname.substring(1);   // 去掉第一个斜杠 /
        entries[pathname] = './' + entry;
    }
    return entries;
}