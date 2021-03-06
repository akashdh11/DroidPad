package com.akash.hiremath.droidpad.mouseUtils

import kotlin.experimental.and
import kotlin.experimental.or

inline class MouseReport (
        val bytes: ByteArray = ByteArray(7) {0}
) {


    var leftButton: Boolean
        get() = bytes[0] and 0b1 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b1
            else
                bytes[0] and 0b110
        }

    var rightButton: Boolean
        get() = bytes[0] and 0b10 != 0.toByte()
        set(value) {
            bytes[0] = if (value)
                bytes[0] or 0b10
            else
                bytes[0] and 0b101
        }

    var dxLsb: Byte
        get() = bytes[1]
        set(value) { bytes[1] = value }

    var dxMsb: Byte
        get() = bytes[2]
        set(value) { bytes[2] = value }


    var dyLsb: Byte
        get() = bytes[3]
        set(value) { bytes[3] = value }

    var dyMsb: Byte
        get() = bytes[4]
        set(value) { bytes[4] = value }

    var vScroll : Byte
        get() = bytes[5]
        set(value) {
            bytes[5]=value
        }

    var hScroll : Byte
        get() = bytes[6]
        set(value) {
            bytes[6]=value
        }


}