import org.scalatest.FunSuite

class HashBytesTest extends FunSuite {
    test("HashBytesTest 1") {
        val input: Array[Byte] = Array(116, 101, 115, 116, 116, 101, 120, 116)
        val h = new HashBytes(input)
        assert(h.makeHash() == "172dc98f926c2d66413486c4ff128b263184ed38b53ef19b7884a391fba39983")
    }
}
