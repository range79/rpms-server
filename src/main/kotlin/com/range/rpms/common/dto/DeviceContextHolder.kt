package com.range.rpms.common.dto

object DeviceContextHolder {

    private val holder = ThreadLocal<DeviceContext>()

    fun set(ctx: DeviceContext) {
        holder.set(ctx)
    }

    fun get(): DeviceContext? = holder.get()

    fun clear() {
        holder.remove()
    }
}