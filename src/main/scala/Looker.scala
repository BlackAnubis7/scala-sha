import java.io.File

import Looker.{MAX_FILES, MAX_SIZE}

import scala.collection.mutable

class Looker(val root: String) {
    val files: mutable.Queue[File] = new mutable.Queue()
    var hashes: Map[String, mutable.HashSet[String]] = Map()
    files += new File(root)

    def look(): Unit = {
        while (files.nonEmpty && files.size <= MAX_FILES) {
            val file: File = files.dequeue()
            if (file.isDirectory) file.listFiles().foreach(files += _)
            else if (file.isFile && FileMeta.getSize(file) < MAX_SIZE) {
                val hash: Hash = new HashFile(file)
                hash.makeHash()
                if(hashes contains hash.getFullHash) hashes(hash.getFullHash) += file.getAbsolutePath
                else hashes += (hash.getFullHash -> mutable.HashSet(file.getAbsolutePath))
            }
        }
        println(hashes)
    }
}

object Looker {
    var MAX_SIZE: Long = 1000000
    var MAX_FILES: Long = 1000
}
