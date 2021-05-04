import java.io.File

import Colors.{cprint, cprintln}
import Looker.{MAX_FILES, MAX_SIZE}

import scala.collection.mutable

class Looker() {
    val files: mutable.Queue[File] = new mutable.Queue()
    var hashes: Map[String, mutable.HashSet[String]] = Map()
    var tooLarge: Set[String] = Set()

    def +=(root: String): Unit = files += new File(root)
    def +=(file: File): Unit = files += file
    def +=(arrfil: Array[File]): Unit = arrfil.foreach(files += _)

    def look(): Unit = {
        while (files.nonEmpty && files.size <= MAX_FILES) {
            val file: File = files.dequeue()
            if (file.isDirectory) file.listFiles().foreach(files += _)
            else if (file.isFile) {
                if(FileMeta.getSize(file) < MAX_SIZE) {
                    val hash: Hash = new HashFile(file)
                    hash.disableEcho()
                    hash.makeHash()
                    if(hashes contains hash.getFullHash) hashes(hash.getFullHash) += file.getAbsolutePath
                    else hashes += (hash.getFullHash -> mutable.HashSet(file.getAbsolutePath))
                }
                else tooLarge += FileMeta.getSizeString(file) + "\t" + file.getAbsolutePath
            }
        }
        for(key <- hashes.keys) {
            val agesorted: List[String] = hashes(key).toList.sortWith(FileMeta.olderThan)
            if (agesorted.size > 1) {
                cprintln(Colors.BLACK, key)
                // TODO: Colliding hash color
                cprint(Colors.GREEN_BOLD, FileMeta.stringDate(agesorted.head))
                // TODO: Oldest file modification date color
                cprintln(Colors.GREEN_BOLD, "\t" + agesorted.head + "\t[OLDEST]")
                // TODO: Oldest file path color
                for (fname <- agesorted.tail) {
                    cprint(Colors.BLACK, FileMeta.stringDate(fname))
                    // TODO: File modification date color
                    cprintln(Colors.BLACK_BOLD, "\t" + fname)
                    // TODO: File path color
                }
            }
            else if (agesorted.size == 1) {
                cprintln(Colors.BLACK, key)
                // TODO: Colliding hash color
                cprint(Colors.GREEN_BOLD, FileMeta.stringDate(agesorted.head))
                // TODO: Only file modification date color (or remove date at all, since no collision occurred)
                cprintln(Colors.GREEN_BOLD, "\t" + agesorted.head + "\t[ONLY]")
                // TODO: Only file path color
            }
            print("\n")
        }

        if (tooLarge.nonEmpty) {
            cprintln(Colors.RED_BOLD,
                "Files too large to process (limit is set to " + FileMeta.sizeToString(MAX_SIZE) + "):")
            // TODO: "Files too large" title color
            tooLarge.foreach(path => cprintln(Colors.RED, "\t" + path))
            // TODO: Files too large color
        }
        else cprintln(Colors.YELLOW, "No files too large to process (limit was set to " + FileMeta.sizeToString(MAX_SIZE) + ")")
        // TODO: "No files too large" color
    }
}

object Looker {
    var MAX_SIZE: Long = 1L << 20  // in bytes
    var MAX_FILES: Long = 1000
}
