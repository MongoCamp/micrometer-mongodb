import Unocss from 'unocss/vite'
import {defineConfig} from 'vitepress'
import {version} from '../../package.json'
import {SearchPlugin} from 'vitepress-plugin-search'

export default defineConfig({
    lang: 'en-US',
    title: 'MongoCamp Micrometer MongoDB',
    description: 'Write and monitor data with Miccrometer and MongoDb.',

    lastUpdated: true,

    themeConfig: {
        logo: '/logo_without_text.png',

        nav: nav(),

        sidebar: {
            '/documentation/': sidebarDocumentation()
            // '/config/': sidebarConfig(),
            // '/plugins/': sidebarPlugins()
        },

        editLink: {
            pattern: 'https://github.com/MongoCamp/micrometer-mongodb/edit/master/docs/:path',
            text: 'Edit this page on GitHub'
        },

        socialLinks: [
            {icon: 'github', link: 'https://github.com/MongoCamp/micrometer-mongodb'}
        ],

        footer: {
            message: 'Released under the Apache License 2.0.',
            copyright: 'Copyright © 2023 - MongoCamp Team'
        },

    },
    vite: {
        plugins: [
            Unocss({
                configFile: '../../unocss.config.ts',
            }),
            SearchPlugin(),
        ],
    },

})

function nav() {
    return [
        {
            text: 'Documentation',
            items: [
                {text: 'Getting Started', link: '/documentation/getting-started'},
                {text: 'Registry', link: '/documentation/registry'},
                {text: 'Binder', link: '/documentation/binder/'}
            ]
        },
        {
            text: version,
            items: [
                {
                    text: 'Changelog',
                    link: '/changelog.html'
                },
            ],
        },

    ]
}

function sidebarDocumentation() {
    return [
        {
            text: 'Getting Started',
            link: '/documentation/getting-started'
        },
        {
            text: 'Registry',
            link: '/documentation/registry'
        },
        {
            text: 'Binder',
            link: '/documentation/binder/',
            collapsible: true,
            collapsed: true,
            items: [
                {text: 'Collection Metrics', link: 'documentation/binder/collection'},
                {text: 'Connections Metrics', link: 'documentation/binder/connections'},
                {text: 'Database Metrics', link: 'documentation/binder/database'},
                {text: 'Network Metrics', link: 'documentation/binder/network'},
                {text: 'Operation Metrics', link: 'documentation/binder/operation'},
                {text: 'Server Metrics', link: 'documentation/binder/server'}
            ]
        }
    ]
}
