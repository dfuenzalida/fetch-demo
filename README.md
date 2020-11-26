# ClojureScript Fetch API demo

An example of using the [Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) with React, ClojureScript, [Reagent](https://reagent-project.github.io/) and [core.async](https://github.com/clojure/core.async/) to ease the handling of [Promises](https://clojurescript.org/guides/promise-interop).

### Requirements

* [Java](https://adoptopenjdk.net/)
* [NodeJS](https://nodejs.org/)
* [Shadow-cljs](https://shadow-cljs.org/)
* [Yarn](https://yarnpkg.com/)

### Development mode

Navigate to the project folder and run the following commands in the terminal.

To download the NodeJS dependencies run:

```
yarn install
```

Copy the static HTML file to the target folder with:

```
yarn html
```

To start the compiler in watch mode:

```
yarn watch
```

Shadow-cljs will automatically push cljs changes to the browser.
Once the ClojureScript code is compiled, visit http://localhost:8080/

Type in the search box and get results from page names in the english Wikipedia.

### REPL

On watch mode a nREPL will be started on port 37117:

```
$ yarn watch
yarn run v1.22.10
$ shadow-cljs watch app
shadow-cljs - config: /home/denis/Projects/ClojureScript/fabric-todos/shadow-cljs.edn
shadow-cljs - HTTP server available at http://localhost:8080
shadow-cljs - server version: 2.11.7 running at http://localhost:9630
shadow-cljs - nREPL server started on port 37117
shadow-cljs - watching build :app
```
### License

MIT


