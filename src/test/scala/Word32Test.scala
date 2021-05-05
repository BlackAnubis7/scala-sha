import org.scalatest.FunSuite

class Word32Test extends FunSuite {
    test("Word32") {
        val w = new Word32(123456789)  // normal (= 0x75bcd15)
        assert(w.getWord == 0x075bcd15L)
    }
    test("Word32 all_bits") {
        val w = new Word32(0xffffffff)  // all bits on
        assert(w.getWord == 0xffffffffL)
    }
    test("Word32 overlimit") {
        val w = new Word32(12345678999L)  // over limit (= 2dfdc1c97)
        assert(w.getWord == 0xdfdc1c97L)
    }
    test("Word32 zero") {
        val w = new Word32(0)  // zero (= 0x0)
        assert(w.getWord == 0x00000000L)
    }
    test("Word32 negative") {
        val w = new Word32(-123456789L)  // negative (= 0xfffffffff8a432eb)
        assert(w.getWord == 0xf8a432ebL)
    }
    test("Word32 no_argument") {
        val w = new Word32()
        assert(w.getWord == 0x00000000L)
    }
    test("Word32 another_word32") {
        val a = new Word32(123456789)
        val b = new Word32(a)
        assert(b.getWord == 0x075bcd15L)
    }

    test("Word32.toRawString") {
        val w = new Word32(0xf8a432eb)
        assert(w.toRawString == "f8a432eb")
    }
    test("Word32.toRawString trailing_zeros") {
        val w = new Word32(1)
        assert(w.toRawString == "00000001")
    }
    test("Word32.toString") {
        val w = new Word32(0xf8a432eb)
        assert(w.toString == "0xf8a432eb")
    }
    test("Word32.toString trailing_zeros") {
        val w = new Word32(1)
        assert(w.toString == "0x00000001")
    }

    test("Word32.+") {
        val a = new Word32(0xcccccccc)
        val b = new Word32(0xe7e7e7e7)
        assert((a+b).getWord == 0xb4b4b4b3L)
    }
    test("Word32.+=") {
        val a = new Word32(0xcccccccc)
        val b = new Word32(0xe7e7e7e7)
        a += b
        assert(a.getWord == 0xb4b4b4b3L)
    }

    test("Word32.#=") {
        val w = new Word32(0)
        w #= 396734
        assert(w.getWord == 396734)
    }
    test("Word32.#= over_limit") {
        val w = new Word32(0)
        w #= -123456789L  // = 0xfffffffff8a432eb
        assert(w.getWord == 0xf8a432ebL)
    }
    test("Word32.#= word32") {
        val a = new Word32(0)
        val b = new Word32(-123456789L)
        a #= b  // = 0xfffffffff8a432eb
        assert(a.getWord == 0xf8a432ebL)
    }

    test("Word32.and") {
        val a = new Word32(324785)
        val b = new Word32(9537)
        assert((a and b).getWord == (324785 & 9537))
    }
    test("Word32.or") {
        val a = new Word32(324785)
        val b = new Word32(9537)
        assert((a or b).getWord == (324785 | 9537))
    }
    test("Word32.xor") {
        val a = new Word32(324785)
        val b = new Word32(9537)
        assert((a xor b).getWord == (324785 ^ 9537))
    }
    test("Word32.unary_~") {
        val w = new Word32(0x0f0c4ff2)
        assert((~w).getWord == 0xf0f3b00dL)
    }

    test("Word32.>>") {
        val w = new Word32(0x0f0c4ff2)
        assert((w >> 4).getWord == 0x00f0c4ffL)
    }
    test("Word32.>> no_shift") {
        val w = new Word32(0x0f0c4ff2)
        assert((w >> 0).getWord == 0x0f0c4ff2L)
    }
    test("Word32.>> over32") {
        val w = new Word32(0x0f0c4ff2)
        assert((w >> 76).getWord == 0L)
    }

    test("Word32.>@") {
        val w = new Word32(0x0f0c4ff2)
        assert((w >@ 4).getWord == 0x20f0c4ffL)
    }
    test("Word32.>@ no_shift") {
        val w = new Word32(0x0f0c4ff2)
        assert((w >@ 0).getWord == 0x0f0c4ff2L)
    }
    test("Word32.>@ over32") {
        val w = new Word32(0x0f0c4ff2)
        assert((w >@ 76).getWord == 0xff20f0c4L)
    }
    test("Word32.>@ cycle") {
        val w = new Word32(0x0f0c4ff2)
        assert((w >@ 1024).getWord == 0x0f0c4ff2L)
    }

    test("Word32.sqrt32") {
        assert(Word32.sqrt32(19).getWord == 0x5be0cd19L)
    }
    test("Word32.sqrt32 high_fractional") {
        // sqrt(960) has the highest fractional part of first 1000 natural numbers
        assert(Word32.sqrt32(960).getWord == 0xfbdeb14fL)
    }
    test("Word32.cbrt32") {
        assert(Word32.cbrt32(19).getWord == 0xab1c5ed5L)
    }

    test("Word32.setBit") {
        val w = new Word32(0x0f0c4ff2)
        w.setBit(1, 1)  // 0x4f0c4ff2
        w.setBit(0, 0)  // 0x4f0c4ff2
        w.setBit(-2, bit = false)  // 0x4f0c4ff0
        w.setBit(-2, 9)  // 0x4f0c4ff0
        w.setBit(-32, bit = true)  // 0xcf0c4ff0
        assert(w.getWord == 0xcf0c4ff0L)
    }
    test("Word32.setBit low_index") {
        val w = new Word32(0x0f0c4ff2)
        assertThrows[IllegalArgumentException] {w.setBit(-33, bit = true)}
    }
    test("Word32.setBit high_index") {
        val w = new Word32(0x0f0c4ff2)
        assertThrows[IllegalArgumentException] {w.setBit(32, bit = true)}
    }
}
