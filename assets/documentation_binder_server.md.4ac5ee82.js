import{_ as r,o as t,c as a,a as s}from"./app.a2d39b52.js";const m=JSON.parse('{"title":"Micrometer Server Binder","description":"","frontmatter":{},"headers":[{"level":2,"title":"Metrics","slug":"metrics","link":"#metrics","children":[]},{"level":2,"title":"Usage","slug":"usage","link":"#usage","children":[]}],"relativePath":"documentation/binder/server.md","lastUpdated":1730047821000}'),i={name:"documentation/binder/server.md"};function n(o,e,d,c,l,h){return t(),a("div",null,e[0]||(e[0]=[s('<h1 id="micrometer-server-binder" tabindex="-1">Micrometer Server Binder <a class="header-anchor" href="#micrometer-server-binder" aria-hidden="true">#</a></h1><p>Binder for <a href="https://micrometer.io" target="_blank" rel="noreferrer">Micrometer</a> some server status metrics of your MongoDB.</p><h2 id="metrics" tabindex="-1">Metrics <a class="header-anchor" href="#metrics" aria-hidden="true">#</a></h2><table><thead><tr><th>Metric</th><th>Type</th><th>Description</th><th>Unit</th></tr></thead><tbody><tr><td>mongodb.server.status.uptime</td><td>Gauge</td><td>The uptime of your Server in Seconds</td><td>Milliseconds</td></tr></tbody></table><h2 id="usage" tabindex="-1">Usage <a class="header-anchor" href="#usage" aria-hidden="true">#</a></h2><div class="language-scala"><button title="Copy Code" class="copy"></button><span class="lang">scala</span><pre class="shiki material-theme-palenight" tabindex="0"><code><span class="line"><span style="color:#FFCB6B;">ServerMetrics</span><span style="color:#BABED8;">(</span><span style="color:#FFCB6B;">MongoTestServer</span><span style="color:#BABED8;">.providerMetrics.database()).bindTo(registry)</span></span></code></pre></div>',6)]))}const u=r(i,[["render",n]]);export{m as __pageData,u as default};