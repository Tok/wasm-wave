# WASM Wave

WebAssembly Wave Animation transpiled from Kotlin:
[https://tok.github.io/wasm-wave/web/index.html](https://tok.github.io/wasm-wave/web/index.html)

             |
           .-+-.             .---.             .-
          /  |  \           /     \           /
    -----/---+---\---------/-------\---------/---
       _/    |    \_     _/         \_     _/
    --'      |      '---'             '---'
             |

The code is mostly based on the [JetBrains html5Canvas Example](https://github.com/JetBrains/kotlin-native/tree/master/samples/html5Canvas) and integrates parts from [/Erwin](https://github.com/Tok/Erwin) and  [/Zir-Watchface](https://github.com/Tok/Zir-Watchface).

## Build
#### Gradle
See build.gradle 
* compiles the sources from *src/main/kotlin/*
* integrates the kotlin libraries from */lib*
* creates **wave.wasm** WebAssembly binary
* creates **wave.wasm.js** bindings
* copies the files from the buildpath to */web*

#### Klib Update
After updating Kotlin Native to a new version, the klibs for JS-interop should also be recompiled to */lib*. This can be done by executing the *rebuild-klibs.sh* script.

## Local Server
* cd *web/*
* python -m http.server 8000

http://localhost:8000/index.html
