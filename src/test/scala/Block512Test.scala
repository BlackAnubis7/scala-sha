import org.scalatest.FunSuite

class Block512Test extends FunSuite {
    test("Block512 begin") {
        val b: Block512 = new Block512()
        assert(b.getWord(0).getWord == 0L)
    }
    test("Block512 end") {
        val b: Block512 = new Block512()
        assert(b.getWord(63).getWord == 0L)
    }
    test("Block512 behind_end") {
        val b: Block512 = new Block512()
        assertThrows[IndexOutOfBoundsException](b.getWord(64))
    }
    test("Block512 different_instances") {
        val b: Block512 = new Block512()
        b.getWord(6) #= 5378
        assert(b.getWord(39).getWord == 0L)
    }

    test("Block512.resetAll") {
        val b: Block512 = new Block512()
        b.getWord(0) #= 5225511
        b.getWord(63) #= 38355
        b.resetAll()
        assert(b.getWord(0).getWord == 0L && b.getWord(63).getWord == 0L)
    }
}
