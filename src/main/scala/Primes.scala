object Primes {
    def sieve(s: LazyList[Int]): LazyList[Int] = {
        s.head #:: sieve(s.tail.filter(_ % s.head != 0))
    }

    def nFirst(n: Int): List[Int] = {
        sieve(LazyList.from(2)).take(n).toList
    }
    def nth(n: Int): Int = {
        sieve(LazyList.from(2)).take(n).last
    }
}
