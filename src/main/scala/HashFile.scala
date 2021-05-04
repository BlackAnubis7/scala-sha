import java.io.{BufferedInputStream, File, FileInputStream}

class HashFile(val filename: String) extends Hash {
    def this(file: File) = this(file.getAbsolutePath)
    val bis = new FileInputStream(filename)
    var nextRead: Int = bis.read()

    def hasNext: Boolean = nextRead != -1
    def nextByte: Byte = {
        val temp: Byte = nextRead.toByte
        nextRead = bis.read()
        temp
    }

    def close(): Unit = bis.close()
}
