import{_ as e,o as a,c as r,a as s}from"./app.72efe976.js";const h=JSON.parse('{"title":"Micrometer Operations Binder","description":"","frontmatter":{},"headers":[{"level":2,"title":"Metrics","slug":"metrics","link":"#metrics","children":[]},{"level":2,"title":"Usage","slug":"usage","link":"#usage","children":[]}],"relativePath":"documentation/binder/operation.md","lastUpdated":1743325747000}'),o={name:"documentation/binder/operation.md"};function n(d,t,i,c,l,p){return a(),r("div",null,t[0]||(t[0]=[s('<h1 id="micrometer-operations-binder" tabindex="-1">Micrometer Operations Binder <a class="header-anchor" href="#micrometer-operations-binder" aria-hidden="true">#</a></h1><p>Binder for <a href="https://micrometer.io" target="_blank" rel="noreferrer">Micrometer</a> some network metrics of your MongoDB.</p><h2 id="metrics" tabindex="-1">Metrics <a class="header-anchor" href="#metrics" aria-hidden="true">#</a></h2><table><thead><tr><th>Metric</th><th>Type</th><th>Description</th><th>Unit</th></tr></thead><tbody><tr><td>mongodb.server.status.operations.insert</td><td>Gauge</td><td>The total number of insert operations received since the mongod instance last started.</td><td>Operations</td></tr><tr><td>mongodb.server.status.operations.query</td><td>Gauge</td><td>The total number of queries received since the mongod instance last started.</td><td>Operations</td></tr><tr><td>mongodb.server.status.operations.update</td><td>Gauge</td><td>The total number of update operations received since the mongod instance last started.</td><td>Operations</td></tr><tr><td>mongodb.server.status.operations.delete</td><td>Gauge</td><td>The total number of delete operations since the mongod instance last started.</td><td>Operations</td></tr><tr><td>mongodb.server.status.operations.getmore</td><td>Gauge</td><td>The total number of getMore operations since the mongod instance last started.</td><td>Operations</td></tr><tr><td>mongodb.server.status.operations.command</td><td>Gauge</td><td>The total number of commands issued to the database since the mongod instance last started.</td><td>Operations</td></tr></tbody></table><h2 id="usage" tabindex="-1">Usage <a class="header-anchor" href="#usage" aria-hidden="true">#</a></h2><div class="language-scala"><button title="Copy Code" class="copy"></button><span class="lang">scala</span><pre class="shiki material-theme-palenight" tabindex="0"><code><span class="line"><span style="color:#FFCB6B;">OperationMetrics</span><span style="color:#BABED8;">(</span><span style="color:#FFCB6B;">MongoTestServer</span><span style="color:#BABED8;">.providerMetrics.database()).bindTo(registry)</span></span></code></pre></div>',6)]))}const u=e(o,[["render",n]]);export{h as __pageData,u as default};
