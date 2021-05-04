import org.scalatest.FunSuite

class HashTextTest extends FunSuite {
    test("HashTextTest 1") {
        val h = new HashText("testtext")
        assert(h.makeHash() == "172dc98f926c2d66413486c4ff128b263184ed38b53ef19b7884a391fba39983")
    }
    test("HashTextTest 2") {
        val h = new HashText("never gonna give you up never gonna let you down never gonna")
        // this text is 60 bytes long - size bytes will have to be moved to next block
        assert(h.makeHash() == "a0b03b1ea7be1e06dce7dcb90ed751fd76c39b1cbeaebdbf17e0be8c6a1ff187")
    }
    test("HashTextTest 3") {
        val h = new HashText("never gonna give you up never gonna let you down never gonna tea")
        // this text is 64 bytes long - endbyte 0x80 will have to be moved to the next block
        assert(h.makeHash() == "0edba56d4734d111e3161329ed959e77d25c0547245910f198d8aceb55dad373")
    }
    test("HashTextTest 4") {
        val h = new HashText("never gonna give you up never gonna let you down never gonna tell")
        // this text is 65 bytes long - size bytes will be in the block where the message ends
        assert(h.makeHash() == "0040bd0a1f221969874c51a0d6b4e7fc16bc11b3195169aa5bd231833907314e")
    }
}