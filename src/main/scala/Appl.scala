import java.io.File
import java.nio.file.{Files, Path}
import java.io.IOException
import java.nio.file.attribute.FileTime
import java.util.concurrent.TimeUnit

import javax.swing.JFileChooser

// ask:
// - outscoping variables
// - when use types

object Appl {
    def main(args: Array[String]): Unit = {
        //val fil: File = new File("D:\\Marcin\\Buff\\")
        //val path: Path = fil.toPath
        //println(Files.isDirectory(path))

        //val ttt = new Looker("D:\\Marcin\\Buff\\scalatest")
        //ttt.look()

        print(Colors.RED_BACKGROUND)
        println(FileMeta.stringDate("D:\\Marcin\\Buff\\radio_alt.mp4"))

        var f: File = null
        val file = new JFileChooser
        file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES)
        if (file.showOpenDialog(null) eq Predef.int2Integer(JFileChooser.APPROVE_OPTION)) {
            f = file.getSelectedFile
            println(f.getPath)
        }

        try {
            if (f.isFile) {
                val h = new HashFile(f)
                println(h.makeHash())
            }
            else if (f.isDirectory) {
                val ttt = new Looker("D:\\Marcin\\Buff\\scalatest")
                ttt.look()
            }
        }

        /*val path: String = "D:\\Marcin\\Buff\\abc.wav"
        val h = new HashFile(path)
        println(h.makeHash())
        h.close()*/
    }
}
