package com.example.wasteless.shared.event

interface BaseEvent

fun <EVENT : BaseEvent> EVENT.wrap() = EventWrapper(this)
