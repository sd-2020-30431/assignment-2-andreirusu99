package com.example.wasteless.shared.utils

inline fun consume(toExecute: () -> Unit) = true.also { toExecute() }
