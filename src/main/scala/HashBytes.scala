class HashBytes(val bytes: Iterable[Byte]) extends Hash {
    private val byteIter: Iterator[Byte] = this.bytes.iterator

    def hasNext: Boolean = byteIter.hasNext
    def nextByte: Byte = byteIter.next()
}
