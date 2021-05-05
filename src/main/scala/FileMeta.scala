import java.io.File
import java.nio.file.{Files, Paths}
import java.nio.file.attribute.FileTime

object FileMeta {
    def getSize(file: File): Long = Files.size(file.toPath)
    def getSize(path: String): Long = Files.size(Paths.get(path))

    def sizeToString(size: Long): String = {
        if (size < (1L<<10)) "" + size + "B"
        else if (size < (1L<<20)) "" + size / (1L<<10) + "kB"
        else if (size < (1L<<30)) "" + size / (1L<<20) + "MB"
        else if (size < (1L<<40)) "" + size / (1L<<30) + "GB"
        else "" + size / (1L<<40) + "TB"
    }
    def getSizeString(file: File): String = sizeToString(getSize(file))
    def getSizeString(path: String): String = sizeToString(getSize(path))

    def getDate(file: File): FileTime = Files.getLastModifiedTime(file.toPath)
    def getDate(path: String): FileTime = Files.getLastModifiedTime(Paths.get(path))

    def stringDate(path: String): String = {
        val dat: String = getDate(path).toString
        dat.substring(0, 10) + " " + dat.substring(11, 16)
    }

    def olderThan(pathA: String, pathB: String): Boolean = getDate(pathA).toMillis < getDate(pathB).toMillis
}
