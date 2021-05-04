abstract class Hash() {
    private var byteCount: Long = 0
    private var hashes: List[Word32] = Primes.nFirst(8).map(Word32.sqrt32)
    private val block: Block512 = new Block512()
    
    def hasNext: Boolean
    def nextByte: Byte

    def displayIter(iter: Long): Boolean = {
        if (iter < 1e2) true
        else if (iter < 1e4) iter % 100 == 0
        else iter % 1000 == 0
    }

    def fillBlock(): Int = {  // fills block with bytes from the source
        // return values:
        // 0 - normal block written
        // 1 - ending byte written (0x80)
        // 2 - file length written (final block)

        //block.resetAll()
        block.resetCount()
        while (block.getBytes < 64 && hasNext) {
            block.addByte(nextByte)
        }
        byteCount += block.getBytes
        if (block.getBytes <= 55) {  // enough room for endbyte + size
            block.zeroFill()
            block.addByte(0x80.toByte)
            block.appendBitSize(byteCount)
            2
        }
        else if (block.getBytes < 64) {
            block.zeroFill()
            block.addByte(0x80.toByte)
            1
        }
        else 0
    }

    def getFullHash: String = {
        var hash256: String = ""
        for (i <- 0 to 7) hash256 += hashes(i).toRawString
        hash256
    }
    
    def makeHash(): String = {
        var fillState: Int = 0
        var iter: Long = 0L
        while (fillState == 0) {
            if (displayIter(iter)) println("\t- Calculating block " + iter)
            fillState = fillBlock()
            //println(block)
            block.calculateWords()
            hashes = block.makeHash(hashes)
            iter += 1
        }
        if (fillState == 1) {  // message size not yet written
            println("\t- Calculating block " + iter)
            block.resetAll()
            block.appendBitSize(byteCount)
            //println(block)
            block.calculateWords()
            hashes = block.makeHash(hashes)
        }
        getFullHash
    }
}
