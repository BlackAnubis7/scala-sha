import Word32._

import scala.math._

class Word32(inp: Long) {
    def this() = this(0L)
    def this(w: Word32) = this(w.getWord)

    private var word: Long = inp & mask32
    def getWord: Long = this.word

    def toRawString: String = {
        val hex: String = word.toHexString
        "" + "0" * (8 - hex.length) + hex  // adds trailing 0's up to 8 hex digits
    }
    override def toString: String = {
        "0x" + toRawString
    }

    override def equals(obj: Any): Boolean = {
        obj match {
            case obj: Word32 => obj.isInstanceOf[Word32] && obj.word == this.word
            case _ => false
        }
    }

    def +(w: Word32): Word32 = new Word32(this.word + w.getWord)
    def +=(w: Word32): Unit = this #= this.word + w.getWord

    def #=(a: Long): Unit = word = a & mask32  // assign with mod 2^32
    def #=(w: Word32): Unit = word = w.getWord & mask32  // assign with mod 2^32

    def and(w: Word32): Word32 = new Word32(this.word & w.getWord & mask32)  // bitwise and
    def or(w: Word32): Word32 = new Word32(this.word | w.getWord & mask32)  // bitwise or
    def xor(w: Word32): Word32 = new Word32(this.word ^ w.getWord & mask32)  // bitwise xor
    def unary_~(): Word32 = new Word32((~this.word) & mask32)  // bitwise unary not

    def >>(offset: Int): Word32 =  // binary right shift
        if (offset <= 32) new Word32(this.word >> offset)
        else new Word32(0)
    def >@(offset: Int): Word32 = {  // binary right rotate
        val off = offset % 32
        if (off == 0) new Word32(this.word)
        else {
            val rest: Long = this.word % (1 << off)
            new Word32((this.word >> off) + rest * (1 << (32 - off)))
        }
    }

    def setBit(index: Int, bit: Int): Unit = {  // set one bit (pos = 0 is the leftmost bit)
        require(-32 <= index && index <= 31)
        val rpos: Int = if (index < 0) -index - 1 else 31 - index  // pos = -1 can serve as the rightmost one
        if (bit == 0) this.word &= (1L << 32) - 1 - (1L << rpos)
        else if (bit == 1) this.word |= (1L << rpos)
    }
    def setBit(index: Int, bit: Boolean): Unit =  // version for Boolean
        if (bit) setBit(index, 1) else setBit(index, 0)

    def setByte(index: Int, byte: Byte): Unit = {  // set one byte (0 = leftmost, -1 = rightmost)
        require(-4 <= index && index <= 3)
        val longByte: Long = 0xFFL & byte
        val rpos: Int = if (index < 0) 8 * (-index - 1) else 8 * (3 - index)
        val mask: Long = (1L << 32) - 1 - (0xFFL << rpos)
        this.word = (this.word & mask) + (longByte << rpos)
    }
}

object Word32 {
    val two32: Long = 0x100000000L  // 2^32, to limit number of bits in the word
    val mask32: Long = 0xffffffffL  // 32-bit mask for bitwise operations

    def sqrt32(a: Int): Word32 = {  // take first 32 bits of fractional part of square root
        val rt = sqrt(a)
        new Word32(((rt - floor(rt)) * two32).toLong)
    }
    def cbrt32(a: Int): Word32 = {  // take first 32 bits of fractional part of cubic root
        val rt = cbrt(a)
        new Word32(((rt - floor(rt)) * two32).toLong)
    }
}
