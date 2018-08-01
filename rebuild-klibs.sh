#!/usr/bin/env bash

# Rebuilds the latest version of the specified klibs to /klib
# $KONAN_HOME should point to /bin in the kotlin-native directory

$KONAN_HOME/jsinterop -pkg kotlinx.interop.wasm.dom -o klib/dom -target wasm32 && rm -Rf klib/dom-build
$KONAN_HOME/jsinterop -pkg kotlinx.interop.wasm.math -o klib/math -target wasm32 && rm -Rf klib/math-build
