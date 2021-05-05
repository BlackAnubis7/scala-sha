import java.io.File

import javax.swing.JFileChooser

object FileChooser {
    def choose(): Unit = {
        val jfc = new JFileChooser
        // TODO: jfc.setApproveButtonText("<BUTTON TEXT>")
        // TODO: jfc.setDialogTitle("<WINDOW TITLE>")

        jfc.setMultiSelectionEnabled(true)
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES)
        val jfc_return: Int = jfc.showOpenDialog(null)
        if (jfc_return eq Predef.int2Integer(JFileChooser.APPROVE_OPTION)) {
            val sel: Array[File] = jfc.getSelectedFiles
            val looker = new Looker()
            looker += sel
            looker.look()
        }
        else if (jfc_return eq Predef.int2Integer(JFileChooser.CANCEL_OPTION)) {
            Colors.cprintln(Colors.RED_BACKGROUND_BRIGHT + Colors.BLACK_BOLD, " Choice cancelled ")
            // TODO: "Choice cancelled" color
        }
    }
}
