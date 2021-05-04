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
        FileChooser.choose()
    }
}
