module.exports = {
    "transpileDependencies": [
        "vuetify"
    ],
    outputDir: "target/dist",
    assetsDir: "static",
    devServer: {
        proxy: {
            '/': {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true
            }
        }
    },
}
