class Block512() {
    private val words: List[Word32] = List.fill(64)(new Word32())
    private var bytesWritten: Int = 0
    private val k: List[Word32] = Primes.nFirst(64).map(Word32.cbrt32)

    def getWord(index: Int): Word32 = words(index)
    def getBytes: Int = bytesWritten

    def resetAll(): Unit = {
        for (i <- 0 to 63) words(i) #= 0L
        bytesWritten = 0
    }
    def resetCount(): Unit = bytesWritten = 0
    def zeroFill(): Unit = for (i <- bytesWritten to 63) setByte(i, 0)

    override def toString: String = {
        var s: String = getWord(0).toString
        for (i <- 1 to 15) s += " | " + getWord(i).toString
        s
    }
    def toBinaryString: String = {
        var s: String = ""
        for (i <- 0 to 15) {
            var bin: String = "0" * 32
            bin += getWord(i).getWord.toBinaryString
            s += bin.takeRight(32) + "\n"
        }
        s
    }

    private def setByte(index: Int, byte: Byte): Unit = {  // only to be used by addByte
        require(0 <= index && index <= 63)  // only first 16 words are to be written byte by byte
        getWord(index / 4).setByte(index % 4, byte)
    }
    def addByte(byte: Byte): Int = {
        setByte(bytesWritten, byte)
        bytesWritten += 1
        bytesWritten
    }
    def appendBitSize(bytes: Long): Unit = {
        val bits: Long = bytes * 8
        getWord(15) #= bits & 0xffffffffL  // lower 32 bits
        getWord(14) #= (bits >> 32) & 0xffffffffL  // higher 32 bits
    }

    def calculateWords(): Unit = {  // calculates words 16 - 63
        val s0: Word32 = new Word32()
        val s1: Word32 = new Word32()
        for (i <- 16 to 63) {
            s0 #= (getWord(i-15) >@  7) xor (getWord(i-15) >@ 18) xor (getWord(i-15) >> 3)
            s1 #= (getWord(i-2) >@  17) xor (getWord(i-2) >@ 19) xor (getWord(i-2) >> 10)
            getWord(i) #= getWord(i-16) + s0 + getWord(i-7) +s1
        }
    }

    def makeHash(hash: List[Word32]): List[Word32] = {
        require(hash.length == 8)
        val a: Word32 = new Word32(hash.head)
        val b: Word32 = new Word32(hash(1))
        val c: Word32 = new Word32(hash(2))
        val d: Word32 = new Word32(hash(3))
        val e: Word32 = new Word32(hash(4))
        val f: Word32 = new Word32(hash(5))
        val g: Word32 = new Word32(hash(6))
        val h: Word32 = new Word32(hash.last)
        val S0: Word32 = new Word32()
        val S1: Word32 = new Word32()
        val ch: Word32 = new Word32()
        val maj: Word32 = new Word32()
        val temp1: Word32 = new Word32()
        val temp2: Word32 = new Word32()
        for (i <- 0 to 63) {
            S1 #= (e >@ 6) xor (e >@ 11) xor (e >@ 25)
            ch #= (e and f) xor ((~e) and g)
            temp1 #= h + S1 + ch + k(i) + getWord(i)
            S0 #= (a >@ 2) xor (a >@ 13) xor (a >@ 22)
            maj #= (a and b) xor (a and c) xor (b and c)
            temp2 #= S0 + maj

            h #= g
            g #= f
            f #= e
            e #= d + temp1
            d #= c
            c #= b
            b #= a
            a #= temp1 + temp2
        }
        List(hash.head + a, hash(1) + b, hash(2) + c, hash(3) + d, hash(4) + e, hash(5) + f, hash(6) + g, hash.last + h)
    }
}
