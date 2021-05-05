import java.io.{File, PrintWriter}

import org.scalatest.FunSuite

class HashFileTest extends FunSuite {
    def randomString(n: Int): String = {
        n match {
            case 1 => util.Random.nextPrintableChar.toString
            case _ => util.Random.nextPrintableChar.toString ++ randomString(n-1)
        }
    }

    test("HashFile lookalike_files") {
        val path: String = System.getProperty("user.dir") + "\\src\\test\\files\\"
        val text1: String = randomString(2000)
        val text2: String = text1 + "\u0000"
        val file1: File = new File(path + "file1.txt")
        val file2: File = new File(path + "file2.txt")
        val pw1: PrintWriter = new PrintWriter(file1)
        val pw2: PrintWriter = new PrintWriter(file2)
        pw1.write(text1)
        pw2.write(text2)
        pw1.close()
        pw2.close()

        val h1 = new HashFile(path + "file1.txt")
        val h2 = new HashFile(path + "file2.txt")
        assert(h1.makeHash() != h2.makeHash())
    }

    test("HashFile same_content_files") {
        val path: String = System.getProperty("user.dir") + "\\src\\test\\files\\"
        val text: String = randomString(2000)
        val file1: File = new File(path + "thefile.csv")
        val file2: File = new File(path + "absolutelydifferentfile.sql")
        val pw1: PrintWriter = new PrintWriter(file1)
        val pw2: PrintWriter = new PrintWriter(file2)
        pw1.write(text)
        pw2.write(text)
        pw1.close()
        pw2.close()

        val h1 = new HashFile(path + "thefile.csv")
        val h2 = new HashFile(path + "absolutelydifferentfile.sql")
        assert(h1.makeHash() == h2.makeHash())
    }
}
